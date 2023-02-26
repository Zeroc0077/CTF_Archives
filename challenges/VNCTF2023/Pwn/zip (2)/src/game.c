#include <ncurses.h>
#include <pthread.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "game.h"

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t m_mut = PTHREAD_MUTEX_INITIALIZER;

pthread_t dummyThread[LEVEL_MAX_NUM_DUMMY];

uint32_t position(mouse_t *m, int pos) {
	return (((uint64_t)m >> (pos*8)) & 0xFFFF) ^ 0xBAAD;
}

void prologue() {
	char buf[0x10];
	uint32_t len;
	
	eeee = malloc(sizeof(mouse_t));

	for( ; curChapt < CHAPTER_1; ++curChapt) {
		printf(dialogue[curChapt]);
		usleep(200000);
	}
	printf("[small mouse] my name is: ");
	
	
	len = read(0, buf, 0x10);
	_RM_LINEFEED(buf, len);
	
	strncpy(eeee->name, buf, strlen(buf));
	initMouse(eeee, HERO_MOUSE);
	
	// gift
	//eeee->attr |= S_CLONE;
	
	printf("\n[God] %s is spanned at the position: (%u, %u, %u).\n\n", \
		eeee->name, position(eeee, 0), position(eeee, 2), position(eeee, 4));
}

void shop() {
	char buf[0x10];
	uint32_t len;
	
	printf(shopMenu);
	printf("[%s] I want a ", eeee->name);

	len = read(0, buf, 0x10);
	_RM_LINEFEED(buf, len);
	
	eeee->buyGoods(eeee, buf);
}

void status() {
	char buf[0x10];
	uint32_t op, len;
	
	eeee->queryStat(eeee);
	printf(statusMenu);
	
	read(0, buf, 0x8);
	op = atoi(buf);
	
	if(op == 1) {
		printf("[God] which one do you want to use?\n");
		printf("[%s] ", eeee->name);
		
		len = read(0, buf, 0x10);
		_RM_LINEFEED(buf, len);
		
		eeee->useItem(eeee, buf);
	}
}

uint64_t calcDmg(mouse_t *m, mouse_t *d) {
	uint64_t dmg = m->atk;
	if(m->attr & S_FIRE) {
		dmg *= 2;
	}
	
	if(m->bag[0].count) {
		dmg += 10;
	}
	
	if(d->bag[1].count) {
		dmg /= 2;
	}
	
	if(m->hp > d->hp) {
		dmg += (m->hp - d->hp)/10;
	}
	
	//printf("[%s] caused %d damage. \n", m->name, dmg);
	return dmg;
}

uint32_t doFight(mouse_t *a, mouse_t *b) {
	uint32_t ret = F_WIN;
	uint64_t dmg1 = calcDmg(a, b);
	uint64_t dmg2 = calcDmg(b, a);
	
	if(dmg1 >= dmg2) {
		
		// sideeffect
		if(!(a->attr & S_HEAL) && a->type == HERO_MOUSE) {
			--a->hp;
		}
		
		if(a->type == HERO_MOUSE && a->hp == 0) {
		
			ret = F_NO_HP;
		}
	}
	else {
		if(a->type == HERO_MOUSE) {
			
			ret = F_LOSE;
		}
		else if(a->type == DUMMY){
		
			ret = F_LOSE_DUMMY;
		}
	}
	
	return ret;
}

uint32_t fight(mouse_t *a, mouse_t *b) {
	pthread_mutex_lock(&mutex);
	
	char buf[0x10];
	uint32_t sig = doFight(a, b);
	
	switch(sig) {
		case F_WIN:
			printf("\033[34m[God] %s beat %s.\033[m \n", a->name, b->name);
			
			if(b->coin) {
				a->coin += b->coin;
				printf("\033[33m[God] %s won %u coin(s).\033[m \n", a->name, b->coin);
			}
			
			free(b);
			
			// especial mouse will do sth here...
			// ...
			if(b->type == LOST_MOUSE) {
				printf("[lost mouse] I lost my name in a duel in the past.\n");
				printf("[lost mouse] Can you help me find my original name? (y/N)\n");
				printf("[%s]", a->name);
				read(0, buf, 2);
				
				if(buf[0] == 'y' || buf[0] == 'Y') {
					printf("[lost mouse] I only remember that"
						" my name has 2 characters.\n");
					printf("[%s] ", a->name);
					read(0, b->name, 2);
					
					if(*((uint64_t**) (b->name))[0] == O_NAME) {
						printf("[%2s] that's my name!\n", b->name);
						printf("[%2s] thank you very much!\n", b->name);
						
					}
					else {
						*((uint64_t *) b->name) = O_NAME;
						printf("[  ] That's not my name. "
							"Maybe I will remember it at the end of my life.\n");
					}
				}
			}
			
			break;
		case F_LOSE:
			printf("[God] Oops, it looks like you lost the fight with %s. \n", \
				b->name);
			printf("[God] Unfortunately, this adventure will be over ahead of schedule. \n");
			
			free(a);
			
			pthread_mutex_unlock(&mutex);
			doExit();
			
		case F_LOSE_DUMMY:
			printf("\033[31m[God] %s died.\033[m \n", a->name);
			free(a);
			
			pthread_mutex_unlock(&mutex);
			return 1;
			
		case F_NO_HP:
			printf("[God] %s is exhausted. (hp == 0)\n", a->name);
			printf("[God] pay all your money and reborn? (y/N)\n");
			printf("[%s] ", a->name);
			
			read(0, buf, 0x8);
		
			if(buf[0] == 'N' || buf[0] == 'n' || a->coin == 0) {
				if(a->coin == 0) {
					printf("[God] I'm sorry, your money not enough. \n");
				}
				
				free(a);
				
				pthread_mutex_unlock(&mutex);
				doExit();
			}
			
			free(b);
			
			a->coin = 0;
			a->hp = 1;
			
			break;
		default:
			break;
	}
	pthread_mutex_unlock(&mutex);
	
	return 0;
}

void loadLevel(uint32_t lev) {
	int i;
	--lev;
	for(i = 0; i < LEVEL_MAX_NUM; ++i) {
		levelEnemies[i] = enemies[lev][i];
	}
	
	// generate enemies
	for(i = 1; i <= levelEnemies[0]; ++i) {
		ene[i] = malloc(sizeof(mouse_t));
		initMouse(ene[i], levelEnemies[i]);
	}
	
	for(i = 1; i <= levelEnemies[0]; ++i) {
		fight(eeee, ene[i]);
		
		sleep(1);
	}
}

void dummyFight(uint32_t *id) {
	mouse_t *dummy = malloc(sizeof(mouse_t));
	mouse_t *ene = malloc(sizeof(mouse_t));
	mouse_t *hiden_ene;
	initMouseDummy(dummy, eeee, *id);
	initMouse(ene, dummyLevelEnemies[*id]);
	
	if(ene->type == NORMAL_MOUSE) {
		hiden_ene = malloc(sizeof(mouse_t));
		initMouse(hiden_ene, LOST_MOUSE);
	}
	
	printf("[God] %s is spanned at the position: (%u, %u, %u).\n", \
		dummy->name, position(dummy, 0), position(dummy, 2), position(dummy, 4));
	
	if(fight(dummy, ene)) {
		return ;
	}
	
	if(ene->type == NORMAL_MOUSE \
		&& fight(dummy, hiden_ene)) {
		return ;
	}
	
	if(dummy->coin) {
		eeee->coin += dummy->coin;
		printf("\033[33m[God] %s give %s %u coin(s).\033[m\n", \
			dummy->name, eeee->name, dummy->coin);
	}
}

void dummyArena() {
	char buf[0x10];
	uint32_t dummyNum, i;

	printf("[God] welcome to CLone Arena.\n");
	printf("[God] how many dummies do you want to summon?\n");
	printf("[%s] ", eeee->name);
	
	read(0, buf, 0x8);
	dummyNum = atoi(buf);
	
	if(dummyNum >= LEVEL_MAX_NUM_DUMMY) {
		printf("[God] you cannot summon so many dummies!\n");
		return ;
	}
	
	srand((unsigned)time(NULL));
	dummyLevelEnemies[0] = dummyNum;
	
	do {
		
		for(i = 1; i < dummyLevelEnemies[0]; ++i) {
			dummyLevelEnemies[i] = rand()%3+3;
		}
		
		printf("[God] your dummies will fight with:\n");
		for(i = 1; i < dummyLevelEnemies[0]; ++i) {
			printf("\033[34m(%u) %s\033[m, ", \
				i, attr_list[dummyLevelEnemies[i]].mayUse);
			if(i % (winCol/22) == 0)
				putchar(0x0A);
		}
		
		printf("\n[God] sure?(y/N)\n");
		printf("[%s] ", eeee->name);
		
		read(0, buf, 2);
		
	} while(buf[0]!='y' && buf[0]!='Y');
	
	pthread_mutex_lock(&mutex);
	
	for(i = 1; i < dummyNum; ++i) {
		pthread_create(&dummyThread[i], NULL, dummyFight, (uint32_t *) &i);
		usleep(10000);
	}
	
	pthread_mutex_unlock(&mutex);
	
	for(i = 1 ; i < dummyNum; ++i) {
		pthread_join(dummyThread[i], NULL);
	}

}

void arena() {
	char buf[0x10];
	uint32_t op;
	
	if(curChapt == CHAPTER_1) {
		for( ; curChapt < CHAPTER_2; ++curChapt) {
			printf(dialogue[curChapt], eeee->name);
			usleep(200000);
		}
	}

	printf(arenaMenu);
	
	read(0, buf, 0x8);
	op = atoi(buf);
	
	switch(op) {
		case 1:
		
		case 2:
		
		case 3:
			if(op == 3 && curChapt == CHAPTER_2) {
				for( ; curChapt < CHAPTER_3; ++curChapt) {
					printf(dialogue[curChapt], eeee->name);
					usleep(200000);
				}
			}
			
			loadLevel(op);
			
			if(op == 3 && curChapt == CHAPTER_3) {
				for( ; curChapt < CHAPTER_4; ++curChapt) {
					printf(dialogue[curChapt], eeee->name);
					usleep(200000);
				}
			}
			
			break;
		case 0:
			if(eeee->attr & S_CLONE) {
				dummyArena();
				break;
			}
			else {
				puts("[God] please learn skill clone first!");
				break;
			}
		default:
			puts("[God] no such level!");
	}
}

int cnt;
void doExit() {
	printf("[God] see you next time~\n");
	free(eeee);
	_exit(0);
}

void remake() {
	printf("[God] you wanna remake?\n");
	printf("[God] okay, I'll satisfy you.\n");
	
	char *tmp = eeee->name;
	
	eeee = malloc(sizeof(mouse_t));
	
	initMouse(eeee, HERO_MOUSE);
	strncpy(eeee->name, tmp, strlen(tmp));
	
	printf("[God] ...and I'll give you a nameTag.\n");
	++eeee->bag[2].count;
}

void start() {
	uint32_t op;
	char buf[0x10];
	
	while(1) {
		printf(mainMenu);
		
		read(0, buf, 0x8);
		op = atoi(buf);
		
		switch(op) {
			case 1:
				arena();
				break;
			case 2:
				shop();
				break;
			case 3:
				status();
				break;
			case 4:
				remake();
				break;
			case 5:
				doExit();
			default:
				puts("[God] try some useful number?");	
		}
	}
}
