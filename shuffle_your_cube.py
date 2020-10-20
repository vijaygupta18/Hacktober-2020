from random import randint

move_types =   ['F', 'Fp', 'B', 'Bp', 'L', 'Lp',
                'R', 'Rp', 'U', 'Up', 'D', 'Dp']

successors = {
      'F':  ['L', 'Lp', 'R', 'Rp', 'U', 'Up', 'D', 'Dp'],
      'Fp': ['L', 'Lp', 'R', 'Rp', 'U', 'Up', 'D', 'Dp'],
      'B':  ['L', 'Lp', 'R', 'Rp', 'U', 'Up', 'D', 'Dp'],
      'Bp': ['L', 'Lp', 'R', 'Rp', 'U', 'Up', 'D', 'Dp'],
      'L':  ['F', 'Fp', 'B', 'Bp', 'U', 'Up', 'D', 'Dp'],
      'Lp': ['F', 'Fp', 'B', 'Bp', 'U', 'Up', 'D', 'Dp'],
      'R':  ['F', 'Fp', 'B', 'Bp', 'U', 'Up', 'D', 'Dp'],
      'Rp': ['F', 'Fp', 'B', 'Bp', 'U', 'Up', 'D', 'Dp'],
      'U':  ['F', 'Fp', 'B', 'Bp', 'L', 'Lp', 'R', 'Rp'],
      'Up': ['F', 'Fp', 'B', 'Bp', 'L', 'Lp', 'R', 'Rp'],
      'D':  ['F', 'Fp', 'B', 'Bp', 'L', 'Lp', 'R', 'Rp'],
      'Dp': ['F', 'Fp', 'B', 'Bp', 'L', 'Lp', 'R', 'Rp']
}

def random_move():
    index = randint(0,11)
    return move_types[index]

def random_successor(move):
    index = randint(0,7)
    return successors[move][index]

def main():
    
    n_moves = int(input('Number of moves: '))

    current_move = None
    moves = []

    for i in range(n_moves):
        if current_move == None:
            current_move = random_move()
        moves.append(current_move)
        current_move = random_successor(current_move)

    return moves


if __name__ == '__main__':
    print(*main())
    
