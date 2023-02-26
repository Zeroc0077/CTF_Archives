#ifndef _MOUSE_H_
#define _MOUSE_H_

#include <stdlib.h>
#include <unistd.h>
#include <stdint.h>
#include "items.h"
#include "arg.h"

#define BOSS_MOUSE   0
#define HERO_MOUSE   1
#define DUMMY        2
#define NORMAL_MOUSE 3
#define ARMED_MOUSE  4
#define ELITE_MOUSE  5
#define LOST_MOUSE   6

#define S_HEAL 1
#define S_FIRE 2
#define S_CLONE 4

#define _RM_LINEFEED(buf, len)			\
({						\
	if(buf[(len-1>0?len-1:0)] != '\x00')		\
		buf[(len-1>0?len-1:0)] = '\x00';	\
})						\

uint32_t cid;

typedef struct Mouse {
	char name[0x10];
	item_t  bag[BAG_CAP];
	
	uint32_t id;
	uint32_t type;
	uint32_t hp;
	uint32_t atk;
	uint32_t coin;
	uint32_t attr;
	
	void*  (*initBag)(struct Mouse *_this);
	void*  (*useItem)(struct Mouse *_this, char *obj);
	void*  (*queryStat)(struct Mouse *_this);
	void*  (*buyGoods)(struct Mouse *_this, char *obj);
} mouse_t;
mouse_t *eeee;

typedef struct MouseAttr {
	uint32_t hp;
	uint32_t atk;
	uint32_t coin;
	uint32_t attr;
	
	char     mayUse[0x10];
} mouseAttr_t;

static mouseAttr_t attr_list[0x8] = {
	{0xeeeea, 0xeeeea, 0xeeeea, S_FIRE|S_HEAL, "eeee"},
	{13, 10, 0, 0, ""},
	{1, 0, 0, 0, "dummy%04u"},
	{10, 5, 1, 0, "normal mouse"},
	{11, 6, 2, 0, "armed mouse"},
	{50, 20, 4, 0, "elite mouse"},
	{80, 80, 0, 0, "lost mouse"},
};

void initMouse(mouse_t *_this, uint32_t ty);
void initMouseDummy(mouse_t *_this, mouse_t *father, uint32_t id);
void initBagImpl(mouse_t *_this);
void useItemImpl(mouse_t *_this, char *obj);
void queryStatImpl(mouse_t *_this);
void buyGoodsImpl(mouse_t *_this, char *obj);

#endif
