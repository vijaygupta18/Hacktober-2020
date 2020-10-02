# A tic-tac-toe AI that i made in hyperskill

# 2020: wow this code is horrible


import random
import copy

grid = [['_', '_', '_'],
        ['_', '_', '_'],
        ['_', '_', '_']]

user1 = "easy"
user2 = "easy"


def check_x():
    tbl = [0, 0, 0, 0]
    for x in range(len(grid)):
        if grid[x][0] == "O" and grid[x][1] == "O" and grid[x][2] == "O":
            tbl[0] = tbl[0] + 1
        elif grid[x][0] == "X" and grid[x][1] == "X" and grid[x][2] == "X":
            tbl[1] = tbl[1] + 1

    return tbl


def check_y():
    tbl = [0, 0, 0, 0]
    for x in range(len(grid)):
        if grid[0][x] == "O" and grid[1][x] == "O" and grid[2][x] == "O":
            tbl[0] = tbl[0] + 1
        elif grid[0][x] == "X" and grid[1][x] == "X" and grid[2][x] == "X":
            tbl[1] = tbl[1] + 1

    return tbl


def check_dig():
    tbl = [0, 0, 0, 0]
    if grid[0][2] == "O" and grid[1][1] == "O" and grid[2][0] == "O":
        tbl[0] = tbl[0] + 1
    elif grid[0][2] == "X" and grid[1][1] == "X" and grid[2][0] == "X":
        tbl[1] = tbl[1] + 1

    return tbl


def check_anti_dig():
    tbl = [0, 0, 0, 0]
    if grid[0][0] == "O" and grid[1][1] == "O" and grid[2][2] == "O":
        tbl[0] = tbl[0] + 1
    elif grid[0][0] == "X" and grid[1][1] == "X" and grid[2][2] == "X":
        tbl[1] = tbl[1] + 1

    return tbl


def has_positions():
    for x in grid:
        for y in x:
            if y == "_":
                return True
    return False


def check_winner():
    oWinner = check_x()[0] + check_y()[0] + check_dig()[0] + check_anti_dig()[0]
    xWinner = check_x()[1] + check_y()[1] + check_dig()[1] + check_anti_dig()[1]

    if (oWinner > 0 and xWinner > 0) or abs(grid.count('X') - grid.count('O')) > 1:
        return "Impossible"

    if has_positions() and oWinner == 0 and xWinner == 0:
        return None

    if not has_positions() and (oWinner == 0 and xWinner == 0):
        return "Draw"

    if oWinner > 0:
        return "O wins"
    else:
        return "X wins"

    return None


def grid_to_array():
    new_array = []

    for y in range(len(grid)):
        for x in range(len(grid)):
            new_array.append(grid[y][x])

    return new_array


def reverse_type(type):
    if type == "O":
        return "X"
    if type == "X":
        return "O"


def get_all_empty_grid():
    new_array = []

    for y in range(len(grid)):
        for x in range(len(grid)):
            if grid[y][x] == "_":
                new_array.append([x, y])

    return new_array


def print_grid():
    print('---------')
    print('|', grid[0][0], grid[0][1], grid[0][2], '|')
    print('|', grid[1][0], grid[1][1], grid[1][2], '|')
    print('|', grid[2][0], grid[2][1], grid[2][2], '|')
    print('---------')


def make_move(type, x, y):
    global grid

    if x > 2 or x < 0 or y > 2 or y < 0:
        return "Coordinates should be from 1 to 3!"

    if grid[2 - y][x] != "_":
        return "This cell is occupied! Choose another one!"

    grid[2 - y][x] = type


def ai_easy_move(type):
    print('Making move level "easy"')

    result = "This cell is occupied! Choose another one!"

    while result == "This cell is occupied! Choose another one!":
        result = make_move(type, random.randrange(0, 3), random.randrange(0, 3))


def ai_medium_move(type):
    print('Making move level "medium"')
    global grid

    moves = get_all_empty_grid()

    jitjack = copy.deepcopy(grid)

    final_move = []

    for co in moves:
        grid = copy.deepcopy(jitjack)
        make_move(type, co[0], co[1])

        winner = check_winner()

        if winner == type + " wins":
            final_move = co
            break

        if winner == reverse_type(type) + " wins":
            continue

        if winner is None:
            final_move = "random"

        final_move = "random"

    grid = copy.deepcopy(jitjack)

    if final_move == "random":
        result = "This cell is occupied! Choose another one!"
        while result == "This cell is occupied! Choose another one!":
            result = make_move(type, random.randrange(0, 3), random.randrange(0, 3))
    else:
        make_move(type, final_move[0], final_move[1])

maioneze = {}

def minimax(type,depth, isMax):
    winner = check_winner()

    if winner is not None:
        score = 0
        if winner == type + " wins":
            score = score + 1
        if winner == reverse_type(type) + " wins":
            score = score - 1

        return score

    possible = get_all_empty_grid()

    if isMax:
        bestScore = -9999
        for x in possible:
            grid[x[1]][x[0]] = type

            score = 0

            if maioneze.get(tuple(grid_to_array())) is not None:
                score = maioneze[tuple(grid_to_array())]
            else:
                score = minimax(type, depth + 1, False)
                maioneze[tuple(grid_to_array())] = score

            grid[x[1]][x[0]] = '_'
            bestScore = max(score, bestScore)

        return bestScore
    else:
        bestScore = 9999
        for x in possible:
            grid[x[1]][x[0]] = reverse_type(type)
            score = 0

            if maioneze.get(tuple(grid_to_array())) is not None:
                score = maioneze[tuple(grid_to_array())]
            else:
                score = minimax(type, depth + 1, True)
                maioneze[tuple(grid_to_array())] = score


            grid[x[1]][x[0]] = '_'
            bestScore = min(score, bestScore)

        return bestScore

def ai_hard_move(type):
    print('Making move level "hard"')
    global grid

    possible = get_all_empty_grid()

    bestScore = -9999
    bestMove = []

    for x in possible:

        grid[x[1]][x[0]] = type
        if maioneze.get(tuple(grid_to_array())) is not None:
            score = maioneze[tuple(grid_to_array())]
        else:
            score = minimax(type, 0, False)
            maioneze[tuple(grid_to_array())] = score

        if score > bestScore:
            bestScore = score
            bestMove = x

        grid[x[1]][x[0]] = '_'

    grid[bestMove[1]][bestMove[0]] = type


def user_move(type):
    while True:

        coords = input("Enter the coordinates: ").split(" ")

        if len(coords) < 2 or (not coords[0].isnumeric() and not coords[1].isnumeric()):
            print("You should enter numbers!")
            continue

        mov = make_move(type, int(coords[0]) - 1, int(coords[1]) - 1)

        if mov is not None:
            print(mov)
            continue

        break


valid_modes = ['easy', 'user', 'medium', 'hard']

print('Type "start mode mode"')
print("Valid modes: user, easy, medium, hard")
print("example: start user hard")

while True:
    command = input("Input command: ").split()

    if command[0] == "start":

        if len(command) < 2:
            print("Bad parameters!")
            continue

        if command[1] not in valid_modes or command[2] not in valid_modes:
            print("Bad parameters!")
            continue

        user1 = command[1]
        user2 = command[2]

        break

    if command[0] == "exit":
        exit()

print_grid()

while True:

    if user1 == "user":
        user_move('X')

    if user1 == "easy":
        ai_easy_move('X')

    if user1 == "medium":
        ai_medium_move('X')

    if user1 == "hard":
        ai_hard_move('X')

    print_grid()

    winner = check_winner()

    if winner is not None:
        print(winner)
        break

    if user2 == "user":
        user_move('O')

    if user2 == "easy":
        ai_easy_move('O')

    if user2 == "medium":
        ai_medium_move('O')

    if user2 == "hard":
        ai_hard_move('O')

    print_grid()

    winner = check_winner()

    if winner is not None:
        print(winner)
        break
