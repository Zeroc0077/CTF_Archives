#include <stdio.h>
#include <string.h>
#include "mouse.h"

void initMouse(mouse_t *_this, uint32_t ty) {
	if(ty != HERO_MOUSE) {
		strncpy(_this->name, attr_list[ty].mayUse, strlen(attr_list[ty].mayUse));
	}
	else {
		_this->initBag = &initBagImpl;
		_this->useItem = &useItemImpl;
		_this->queryStat = &queryStatImpl;
		_this->buyGoods = &buyGoodsImpl;
		_this->initBag(_this);
	}
	_this->id = ++cid;
	_this->type = ty;
	_this->hp = attr_list[ty].hp;
	_this->atk = attr_list[ty].atk;
	_this->coin = attr_list[ty].coin;
	_this->attr = attr_list[ty].attr;
}

void initMouseDummy(mouse_t *_this, mouse_t *father, uint32_t id) {
	char buf[0x10];
	sprintf(buf, attr_list[DUMMY].mayUse, id);
	
	initMouse(_this, DUMMY);
	
	strncpy(_this->name, buf, strlen(buf));
	_this->atk = father->atk;
	_this->attr = father->attr;
}

void initBagImpl(mouse_t *_this) {
	int i = 0;
	for( ; i < BAG_CAP; ++i) {
		_this->bag[i] = item_list[i];
	}
}

void useItemImpl(mouse_t *_this, char *obj) {
	int i = 0;
	for( ; i <= BAG_CAP; ++i) {
		if(!strncmp(obj, _this->bag[i].name, 8)) {
			if(_this->bag[i].attr & I_USEABLE) {
				if(_this->bag[i].count == 0) {
					puts("[God] You don't have that.");
					return;
				}

				if(!strcmp(obj, "nameTag")) {
					char buf[0x10];
					printf("[God] tell me your name: ");
					
					uint32_t len = read(0, buf, 0x10);
					_RM_LINEFEED(buf, len);
					strncpy(_this->name, buf, strlen(buf));
					
					printf("[God] enjoy yourself: %s.\n", \
						_this->name);
				}
				else if(!strcmp(obj, "book1")) {
					_this->attr |= S_HEAL;
					
					printf("[God] %s learned new skill: "
					"\033[32mheal magic\033[m.\n", _this->name);
				}
				else if(!strcmp(obj, "book2")) {
					_this->attr |= S_FIRE;
					
					printf("[God] %s learned new skill: "
					"\033[31mfire ball\033[m.\n", _this->name);
				}
				else if(!strcmp(obj, "book3")) {
					_this->attr |= S_CLONE;
					
					printf("[God] %s learned new skill: "
					"\033[33mclone\033[m.\n", _this->name);
				}
				else if(!strcmp(obj, "potion1")) {
					++_this->hp;
					
					printf("[God] %s's hp is now: %u.\n", _this->name, _this->hp);
				}
				else if(!strcmp(obj, "potion2")) {
					++_this->atk;
					
					printf("[God] %s's atk is now: %u.\n", _this->name, _this->atk);
				}
			}
			--_this->bag[i].count;
			printf("[God] you used %s, leave %u.\n", \
				_this->bag[i].name, _this->bag[i].count);
		}
	}
}

void queryStatImpl(mouse_t *_this) {
	int i;
	printf("\n----------------status------------------\n");
	printf("name: \033[34m%s\033[m, "
		"coin: \033[33m%u\033[m, "
		"HP: \033[32m%u\033[m, "
		"ATK: \033[31m%u\033[m\n",	\
		_this->name, _this->coin, _this->hp, _this->atk);
	
	puts("skill: ");
	for(i = 1; i < 8; i <<= 1) {
		if(_this->attr & i){
			if(i == S_HEAL) {
				printf("\033[32mheal magic\033[m ");
			}
			else if(i == S_FIRE) {
				printf("\033[31mfire ball\033[m ");
			}
			else if(i == S_CLONE) {
				printf("\033[33mclone\033[m ");
			}
		}
	}
	putchar(0x0A);
	
	puts("bag: ");
	for(i = 0; i < BAG_CAP; ++i) {
		if(_this->bag[i].count>0) {
			printf(" -%s ", _this->bag[i].name);
			if(_this->bag[i].attr & I_USEABLE) {
				printf("num: %u", _this->bag[i].count);
			}
			putchar(0x0A);
		}
	}
	
	printf("----------------------------------------\n\n");
}

void buyGoodsImpl(mouse_t *_this, char *obj) {
	int i = 0;
	for( ; i < BAG_CAP; ++i) {
		if(!strncmp(obj, shop_list[i].name, strlen(shop_list[i].name))) {
			if((int)_this->coin >= (int)shop_list[i].price) {
				_this->coin -= shop_list[i].price;
				++_this->bag[i].count;
				
				printf("[God] you bought a %s, left %u coin(s).\n", \
					shop_list[i].name, _this->coin);
				break;
			}
			else {
				puts("[God] your coin not enough.");
				break;
			}
		}
	}
	if(i == BAG_CAP) {
		printf("[God] %s? we don't have that.\n", obj);
	}
}
