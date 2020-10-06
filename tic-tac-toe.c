#include <stdlib.h>
#include <stdbool.h>

//Red and blue are two colors used to represent two different players

typedef enum {
  BLUE = -1,
  NONE,
  RED
} Player;

typedef struct {
  int size;
  int **board;
  int *rowScores;
  int *colScores;
  int topLeftBottomRightDiagonalScores;
  int topRightBottomLeftDiagonalScores;
} Game;

typedef struct {
  int x;
  int y;
} Position;

Game *CreateGame(int size) {
  Game *g = malloc(sizeof(Game));
  g->size = size;
  g->board = malloc(size * sizeof(int *));

  for (int i = 0; i < size; i++) {
    g->board[i] = malloc(size * sizeof(int));
  }

  g->rowScores = calloc(size, sizeof(int));
  g->colScores = calloc(size, sizeof(int));
  g->topRightBottomLeftDiagonalScores = 0;
  g->topLeftBottomRightDiagonalScores = 0;

  return g;
}

bool isPlayerWinner(Game *game, Position *lastMove, Player player) {
  if (game->rowScores[lastMove->y] == game->size * player ||
      game->colScores[lastMove->x] == game->size * player ||
      game->topRightBottomLeftDiagonalScores == game->size * player ||
      game->topLeftBottomRightDiagonalScores == game->size * player) {
    return true;
  } else {
    return false;
  }
}

// Checking winner to display results

Player checkForWinner(Game *game, Position *lastMove) {
  if (isPlayerWinner(game, lastMove, BLUE))
    return BLUE;

  if (isPlayerWinner(game, lastMove, RED))
    return RED;

  return NONE;
}

Player move(Game *game, Position *lastMove, Player player) {
  game->board[lastMove->y][lastMove->x] = player;
  game->rowScores[lastMove->y] += player;
  game->colScores[lastMove->x] += player;

  if (lastMove->y == lastMove->x) {
    game->topLeftBottomRightDiagonalScores += player;
  }

  if (lastMove->x == game->size - lastMove->x) {
    game->topRightBottomLeftDiagonalScores += player;
  }

  return checkForWinner(game, lastMove);
}

Position *CreatePosition(int x, int y) {
  Position *position = malloc(sizeof(position));
  position->x = x;
  position->y = y;

  return position;
}

int main (int argc, char const *argv[]) {
  Game *game = CreateGame(2);
  printf("%d\n", move(game, CreatePosition(0, 0), BLUE));
  printf("%d\n", move(game, CreatePosition(0, 1), RED));
  printf("%d\n", move(game, CreatePosition(1, 1), BLUE));

  return 0;
}
