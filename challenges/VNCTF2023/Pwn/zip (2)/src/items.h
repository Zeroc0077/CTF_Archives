#ifndef _ITEMS_H_
#define _ITEMS_H_

#include <stdlib.h>
#include <stdint.h>
#include <unistd.h>

#define I_USEABLE 1
#define I_ATK     2
#define I_DFN     4

typedef struct Item {
	char name[0x8];
	uint32_t attr;
	uint32_t count;
} item_t;

typedef struct ShopItem {
	char name[0x8];
	uint32_t price;
} shopItem_t;

static item_t item_list[0x8] = {
	{"sword",   I_ATK, 0},
	{"shield",  I_DFN, 0},
	{"nameTag", I_USEABLE, 0},
	{"book1",   I_USEABLE, 0},
	{"book2",   I_USEABLE, 0},
	{"book3",   I_USEABLE, 0},
	{"potion1", I_USEABLE, 0},
	{"potion2", I_USEABLE, 0},
};

static shopItem_t shop_list[0x8] = {
	{"sword", 5},
	{"shield", 5},
	{"nameTag", 68},
	{"book1", 98},
	{"book2", 198},
	{"book3", 0x114514},
	{"potion1", 5},
	{"potion2", 30},
};

#endif
