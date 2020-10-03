const container = document.getElementById('container');
let colorMode = "custom";

let gridMaker = document.getElementById("gridMaker");
gridMaker.addEventListener('click', makeGrid);

function getRows() {   //asks for number of rows in the grid and takes only numbers
	while(true){
		let rows = prompt("Enter number of rows in the grid");
		if (rows == null) {
        	alert("1X1 Grid.");
        	return true;
        }
        else{
        	if (rows.length<=0 || isNaN(rows)) { 
            	alert("Examples of numbers are 1, 2, 5, 16, 33, 49, 57.....");
            }
            else {
                return rows;
            }
        }
	}
	return rows;
}

function makeGrid(e) {
	while(container.hasChildNodes()){   //clears the previous grid before making a new one.
		container.removeChild(container.firstChild);
	}
		let rows = getRows();

	for(let i=0; i<rows**2; i++){   //runs to make rows*rows number of grid boxes
		let gridBox = document.createElement('div');   //creates 1 grid box
		gridBox.style.width = 500/rows + 'px';   //sets width and height to fit inside the container div
		gridBox.style.height = 500/rows + 'px';
		gridBox.className = "grid-box";
		container.appendChild(gridBox);   //makes the 1 grid box created a child of container
	}
	startDraw();   //calls function which then gives the mouseover event, AFTER THE GRID HAS BEEN CREATED AND APPENDED INTO THE CONTAINER
}

let colorInput = document.querySelector("input");   //sets colorMode to custom when the color input box is clicked
colorInput.addEventListener('click', function(e) {
	colorMode = "custom";
});

function startDraw(){
	let boxes = container.childNodes;   //node-list of all the grid boxes
	boxes.forEach(box => box.addEventListener('mouseover', changeColor));   //forEach gives all the elements in the node-list a mouseover event
}

function changeColor(e) {   //function when mouse hovers to change color according to colorMode
	if(colorMode === "rainbow"){
		let color = getRainbow();
		this.style.backgroundColor = color;
	}
	else if(colorMode === "custom"){
		let color = document.getElementById("favColor");
		this.style.backgroundColor = color.value;
	}
	else {
		this.style.backgroundColor = "white";
	}
}

let clearBtn = document.getElementById("clear");
clearBtn.addEventListener('click', clearAll);

function clearAll(e) {   //function to store all the grid boxes of container and then make their background white.
	let boxes = container.childNodes;
	for(let i=0; i<boxes.length; i++){
		boxes[i].style.backgroundColor = "white";
	}
}

let rainbowBtn = document.getElementById("rainbow");   //sets colorMode to rainbow when the rainbow button is clicked
rainbowBtn.addEventListener('click', function(e) {
	colorMode = "rainbow";
});

function getRainbow() {   //makes the random rainbow color
    let letters = '0123456789ABCDEF';
    let color = '#';

    for (i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

let eraser = document.getElementById("eraser");
eraser.addEventListener('click', function(e) {
	colorMode = "erase";
});