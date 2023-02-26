cc = gcc

SRC = main.c mouse.c game.c

LDFLAGS = -lncurses -lpthread -g

all:
	$(cc) $(SRC) $(LDFLAGS)  -o rats

x:
	chmod 777 ./rats

mov:
	mv ./rats ../rats
