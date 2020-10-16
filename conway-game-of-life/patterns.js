const PATTERNS = {
    'blinker': {
        'name': 'Blinker',
        'cells': [
            [0, 1],
            [1, 1],
            [2, 1]
        ]
    },
    'boat': {
        'name': 'Boat',
        'cells': [
            [4, 4],
            [4, 5],
            [5, 4],
            [6, 5],
            [5, 6]
        ]
    },
    'pulsar': {
        'name': 'Pulsar',
        'cells': [
            [10, 5],
            [11, 6],
            [11, 7],
            [10, 7],
            [9, 7]
        ]
    },
    'pentadecathlon': {
        'name': 'Pentadecathlon',
        'cells': function getPentadecathlon(length) {
            let arr = [];
            for (let j = 0; j < length; j++) arr.push([0, j]);
            return arr;
        }
    },
    'gosperGlidingGun': {
        'name': 'Gosper Gliding Gun',
        'cells': [
            [6, 1],
            [6, 2],
            [7, 1],
            [7, 2],

            [5, 11],
            [6, 11],
            [7, 11],
            [4, 12],
            [8, 12],
            [3, 13],
            [3, 14],
            [9, 13],
            [9, 14],

            [6, 15],
            [4, 16],
            [8, 16],
            [5, 17],
            [6, 17],
            [7, 17],
            [6, 18],

            [3, 21],
            [4, 21],
            [5, 21],
            [3, 22],
            [4, 22],
            [5, 22],

            [2, 23],
            [6, 23],

            [1, 25],
            [2, 25],
            [6, 25],
            [7, 25],

            [4, 35],
            [4, 36],
            [5, 35],
            [5, 36],
        ]
    },
    'lightWeithSpaceShip': {
        'name': 'Light Weith Space Ship',
        'cells': [
            [1, 2],
            [2, 1],
            [3, 1],
            [4, 1],
            [4, 2],
            [4, 3],
            [4, 4],
            [3, 5],
            [1, 5],
        ]
    },
    'smallExploder': {
        'name': 'Small Exploder',
        'cells': [
            [0, 1],
            [1, 0],
            [1, 1],
            [1, 2],
            [2, 0],
            [2, 2],
            [3, 1]
        ]
    }
}


Array.prototype.transform = function (si, sj) {
    return this.map(pair => [pair[0] + si, pair[1] + sj]);
}
