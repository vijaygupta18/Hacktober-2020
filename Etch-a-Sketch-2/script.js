
document.addEventListener('DOMContentLoaded', runApp);

function runApp() {
    let N = 16;
    let container = document.querySelector('.grid');
    const clearBtn = document.querySelector('.clear-btn');
    const randomColorBtn = document.querySelector('.random-btn');
    const defaultColorBtn = document.querySelector('.default-btn');
    let COLOR_ON_HOVER = 'black';
    let isRandomColorEnabled = false;

    defaultColorBtn.addEventListener('click', function () {
        COLOR_ON_HOVER = 'black';
        isRandomColorEnabled = false;
        randomColorBtn.style.backgroundColor = 'white';
    });

    randomColorBtn.addEventListener('click', function () {
        if (isRandomColorEnabled) {
            isRandomColorEnabled = false;
            this.style.backgroundColor = 'white';
        }
        else {
            isRandomColorEnabled = true;
            this.style.backgroundColor = '#ccc';
        }
    });

    buildGrid(N);

    clearBtn.addEventListener('click', function () {
        container.innerHTML = "";
        N = parseInt(prompt("Enter the size of the grid", '16'));
        buildGrid(N);
    });

    function buildGrid(n) {
        container.style.gridTemplateColumns = "repeat(" + n + ", 1fr)";
        container.style.gridTemplateRows = "repeat(" + n + ", 1fr)";

        for (let i = 0; i < n * n; i++) {
            const cell = document.createElement("div");
            cell.classList.add('cell');
            cell.addEventListener('mouseover', changeBackgroundColor);

            container.appendChild(cell);
        }
    }

    function changeBackgroundColor() {
        let color = COLOR_ON_HOVER;
        if (isRandomColorEnabled) {
            color = pickRandomColor();
            // console.log(color);
        }
        this.style.backgroundColor = color;
    }

    function getRandomHexDigit() {
        let d = Math.floor(Math.random() * 15);
        if (d > 9) {
            d = String.fromCharCode(97 + d - 10);
        }
        return d;
    }

    function pickRandomColor() {
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += getRandomHexDigit();
        }
        return color;
    }

}