let dim = 100;
const COLOR = 'black';
const di = [0, 1, 1, 1, 0, -1, -1, -1];
const dj = [1, 1, 0, -1, -1, -1, 0, 1];
let speed = 0.2; // 1 second
let started = false;
let interval = undefined;
let cells = [];
let aliveCells = [];


document.addEventListener('DOMContentLoaded', runApp);


function runApp() {

    let grid = document.querySelector('.grid');
    const startBtn = document.querySelector('.start-btn');
    startBtn.addEventListener('click', function() {
        if (started){
            started = false;
            this.textContent = 'Start';
            clearInterval(interval);
        }
        else{
            started = true;
            this.textContent = 'Stop';
            playGame(dim, cells);
        }
    });

    const dimensionBtn = document.querySelector('.grid-dim-btn');
    dimensionBtn.addEventListener('click', function() {
        const inputDim = document.querySelector('#dimension');
        dim = +inputDim.value;
        buildGrid(dim);
        fillGrid();
    });

    const select = document.querySelector('#patterns');
    for(let key in PATTERNS){
        let option = document.createElement('option');
        option.textContent = PATTERNS[key].name;
        option.classList.add(key);
        select.appendChild(option);
    }
    select.addEventListener('change', function() {
        selectedOption = this.options[this.selectedIndex].classList[0];
        if(selectedOption === 'pentadecathlon') {
            length = parseInt(window.prompt('Length of Pentadecathlon ?', '10')) || 10;
            aliveCells = PATTERNS[selectedOption].cells(length).transform(25, 25);
        }
        else{
            aliveCells = PATTERNS[selectedOption].cells.transform(25, 25);
        }
        clearGrid();
        fillGrid();
    });
    

    buildGrid(dim);

    aliveCells = aliveCells.concat(PATTERNS['blinker'].cells);
    aliveCells = aliveCells.concat(PATTERNS['boat'].cells.transform(5, 0));
    aliveCells = aliveCells.concat(PATTERNS['pulsar'].cells.transform(9, 0));
    
    aliveCells = aliveCells.concat(PATTERNS['gosperGlidingGun'].cells.transform(12, 20));
    aliveCells = aliveCells.concat(PATTERNS['lightWeithSpaceShip'].cells.transform(40, 25));

    aliveCells = aliveCells.concat(PATTERNS['pentadecathlon'].cells(5).transform(45, 50));
    aliveCells = aliveCells.concat(PATTERNS['pentadecathlon'].cells(10).transform(60, 40));

    fillGrid();


    function buildGrid(dimension) {
        grid.innerHTML = '';

        grid.style.gridTemplateColumns = "repeat(" + dimension + ", 1fr)";
        grid.style.gridTemplateRows = "repeat(" + dimension + ", 1fr)";
        const totalCells = dimension * dimension;

        for (let i = 0; i < totalCells; i++) {
            const cell = document.createElement("div");
            cell.classList.add('cell');

            grid.appendChild(cell);
        }
    }

    function fillGrid() {
        cells = Array.from(document.getElementsByClassName('cell'));
        getStartingState(aliveCells, cells);
    }

    function randomColumn(dimension) {
        return Math.floor(Math.random() * dimension);
    }

    function colorRandomCells(dimension, cells) {
        clearGrid(cells);
        for (let row = 0; row < dimension; row++) {
            const index = randomColumn(dimension);
            cells[index + row * dimension].style.backgroundColor = COLOR;
        }
    }

    function clearGrid() {
        cells.forEach(cell => cell.style.backgroundColor = '');
    }

    function isAlive(cell) {
        return cell.style.backgroundColor === COLOR;
    }

    function killCell(cell) {
        cell.style.backgroundColor = '';
    }

    function makeCellAlive(cell) {
        cell.style.backgroundColor = COLOR;
    }

    function isValid(row, column) {
        return row >= 0 && row < dim && column >= 0 && column < dim;
    }

    function getCell(row, column) {
        return row * dim + column;
    }

    function checkStatus(row, column, cells) {
        let cell = cells[getCell(row, column)];
        let liveNeighbours = 0;
        let isAliveCell = isAlive(cell);
        for (let k = 0; k < 8; k++) {
            if (isValid(row + di[k], column + dj[k])) {
                liveNeighbours += isAlive(cells[getCell(row + di[k], column + dj[k])]);
            }
        }
        if (isAliveCell) {
            if (liveNeighbours !== 2 && liveNeighbours !== 3) {
                return 0;
            }
            else return 1;
        }
        else {
            if (liveNeighbours === 3) {
                return 1;
            }
            else return 0;
        }
    }

    function updateGridState(dimension, cells) {
        const totalCells = dimension * dimension;
        let gridState = new Array(totalCells).fill(0);
        for (let row = 0; row < dimension; row++) {
            for (let column = 0; column < dimension; column++) {
                let status = checkStatus(row, column, cells);
                gridState[getCell(row, column)] = status;
            }
        }
        
        for(let i = 0; i < totalCells; i++) {
            if(gridState[i]){
                makeCellAlive(cells[i]);
            }
            else{
                killCell(cells[i]);
            }
        }
    }

    function playGame(dimension, cells) {
        interval = setInterval(() => {
            updateGridState(dimension, cells);
        }, speed * 1000);
    }

    function getStartingState(aliveCells, cells) {
        aliveCells.forEach(aliveCell => {
            cell = cells[getCell(aliveCell[0], aliveCell[1])];
            cell.style.backgroundColor = COLOR;
        });
    }

}


