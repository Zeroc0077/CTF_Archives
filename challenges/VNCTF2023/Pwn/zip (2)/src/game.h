#ifndef _GAME_H_
#define _GAME_H_

#include "mouse.h"

#define F_WIN 0
#define F_LOSE 1
#define F_LOSE_DUMMY 2
#define F_NO_HP 3

#define LEVELS 3
#define LEVEL_MAX_NUM 6
#define LEVEL_MAX_NUM_DUMMY 0xFF
#define USELESS 0xFFFFFFFF

#define CHAPTER_1 4
#define CHAPTER_2 10
#define CHAPTER_3 14
#define CHAPTER_4 20

#define O_NAME 0

static uint32_t enemies[LEVELS][LEVEL_MAX_NUM] = {
	{1, NORMAL_MOUSE, USELESS, USELESS, USELESS, USELESS},
	{2, ARMED_MOUSE, ARMED_MOUSE, USELESS, USELESS, USELESS},
	{1, BOSS_MOUSE, USELESS, USELESS, USELESS, USELESS}
};

uint32_t levelEnemies[LEVEL_MAX_NUM];
uint32_t dummyLevelEnemies[LEVEL_MAX_NUM_DUMMY];

mouse_t *ene[LEVEL_MAX_NUM];

uint32_t curChapt;
static char *dialogue[0x20] = {
	"\033[35m[God] welcom to VNCTF2023.\033[m\n",
	"\033[35m[God] now, you are teleported to the kingdom of mouse.\033[m\n",
	"\033[35m[God] survive and find your flag.\033[m\n",
	"\033[35m[God] tell me your name before everything starts.\033[m\n",
	"\033[35m[God] Anyway, what is your dream?\033[m\n",
	"\033[35m[%s] I want to becom a Gangstar!\033[m\n",
	"\033[35m[God] Gangstar? what's that?\033[m\n",
	"\033[35m[%s] Star in gangsters.\033[m\n",
	"\033[35m[God] sounds good, just prove yourself in these arenas.\033[m\n",
	"\033[35m[God] you will be a ganstar.\033[m\n",
	"\033[35m[%s] I feel a dangerous smell.\033[m\n",
	"\033[35m[God] goahead?\033[m\n",
	"\033[35m[%s] sure. this guy must be the one I'm looking for.\033[m\n",
	"\033[35m[%s] gangstar!\033[m\n",
	"\033[35m[God] brilliant!\033[m\n",
	"\033[35m[God] you successfully defeated gangstar, and now you have your wish.\033[m\n",
	"\033[35m[God] but is it really over?\033[m\n"
};

static char mainMenu[] = {
	"\n------------------MENU------------------\n"
	"1. Mouse Arena\n"
	"2. Shop\n"
	"3. Status\n"
	"4. Remake\n"
	"5. Exit\n"
	"Your choice: "
};

static char shopMenu[] = {
	"\n------------------SHOP------------------\n"
	"Name: \033[32msword\033[m (need 5 coins)\n"
	"	\033[31mWith a sword, you can cause more damage.\033[m\n\n"
	"Name: \033[32mshield\033[m (need 5 coins)\n"
	"	\033[31mShield can protect you.\033[m\n\n"
	"Name: \033[32mnameTag\033[m (need 68 coins)\n"
	"	\033[31mChange your name.\033[m\n\n"
	"Name: \033[34mbook1\033[m (need 98 coins)\n"
	"	\033[31myou can learn heal magic.\033[m\n\n"
	"Name: \033[34mbook2\033[m (need 198 coins)\n"
	"	\033[31myou can learn fire magic.\033[m\n\n"
	"Name: \033[33mbook3\033[m (need 1131796 coins)\n"
	"	\033[31myou can learn clone.\033[m\n\n"
	"Name: \033[34mpotion1\033[m (need 5 coins)\n"
	"	\033[31madd 1 hp for you\033[m\n\n"
	"Name: \033[35mpotion2\033[m (need 30 coins)\n"
	"	\033[31madd 1 atk for you\033[m\n\n"
	"[God] which one do you want to buy?\n"
};

static char statusMenu[] = {
	"1. use item\n"
	"2. continue\n"
	"Your choice: "
};

static char arenaMenu[] = {
	"\n-----------------ARENAS-----------------\n"
	"\033[32mlevel 1: Wet and dark sewer (easy)\033[m\n"
	"\033[34mlevel 2: Armed forces (normal)\033[m\n"
	"\033[31mlevel 3: Mouse GANGSTAR (hard)\033[m\n"
	"level 0: Clone arena (especial)\033[m\n\n"
	"Your choice: "
};

void prologue(void);
void start(void);
void shop(void);
void status(void);
void arena(void);
void remake(void);
void doExit(void);
void dummyArena(void);
void dummyFight(uint32_t *id);
void loadLevel(uint32_t lev);

uint32_t fight(mouse_t *a, mouse_t *b);
uint32_t doFight(mouse_t *a, mouse_t *b);
uint32_t position(mouse_t *m, int pos);
uint64_t calcDmg(mouse_t *m, mouse_t *d);

#endif
