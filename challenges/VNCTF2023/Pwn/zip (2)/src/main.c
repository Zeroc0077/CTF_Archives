#include <stdlib.h>
#include <string.h>
#include <ncurses.h>
#include <unistd.h>
#include "game.h"

void init() {
	int i, stRow, stCol;
	
	alarm(600);
	setbuf(stdin, 0);
	setbuf(stdout, 0);
	setbuf(stderr, 0);	
	
	initscr();
	clear();
	getmaxyx(stdscr, winRow, winCol);
	noecho();
	curs_set(0);
	
	attron(A_BOLD);
	stRow = (winRow-BANNER_ROW)/2-6>0?(winRow-BANNER_ROW)/2-6:0;
	stCol = (winCol-BANNER_COL)/2>0?(winCol-BANNER_COL)/2:0;
	for(i = 0; i < BANNER_ROW; ++i) {
		mvprintw(stRow+i, stCol, banner[i]);
	}
	refresh();
	
	attron(A_BLINK);
	mvprintw(stRow+i+3, (winCol-26)/2, "> Press any key to start <");
	refresh();
	attroff(A_BLINK | A_BOLD);
	
	getch();
	
	endwin();
}

int main() {
	init();
	
	prologue();
	start();
	
	return 0;
}
