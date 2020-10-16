let library = [];
for(let i=0;i<10;i++){
    library.push(new Book(`Book ${i+1}`, `Author ${i+1}`, (i+1)*100, i%2 === 0));
}

const COLORS = ['#ef3023', '#8bc34a']

const libraryList = document.querySelector('.library');

function Book(title, author, pages, read=false) {
	this.title = title;
	this.author = author;
	this.pages = pages;
	this.read = read;
}
document.addEventListener('DOMContentLoaded', runApp);


function runApp() {
    
    displayBooks();
}
function displayBooks() {
    libraryList.innerHTML = '';
    
    for(let i=0; i<library.length; i++) {
        libraryList.append(createBookCard(i));
        changeColor(i);
    }
}

function createBookCard(index) {
    const libraryItem = document.createElement('div');
    libraryItem.classList.add('book-card');
    libraryItem.innerHTML = 
        `<h3>${library[index].title}</h3>
        <h3>${library[index].author}</h3>
        <h3>${library[index].pages}</h3>
        <small class="status" id="status-${index}" onclick="changeStatus(${index})">${library[index].read ? 'already read' : 'not yet read'}</small>
        <span class="remove-btn" onclick="removeBook(${index})">&#10005;</span>`;
    return libraryItem;
}


function removeBook(index) {
    if(confirm(`Are you sur you want to delete ${library[index].title} book`)){
        library.splice(index, 1);
        displayBooks();
    }
    return;
}

function changeStatus(index) {
    library[index].read = !library[index].read;
    changeColor(index);
}

function changeColor(index) {
    let bookStatus = document.querySelector(`#status-${index}`);
    bookStatus.textContent = library[index].read ? 'already read' : 'not yet read';
    bookStatus.style.backgroundColor = COLORS[library[index].read ? 1 : 0];
    bookStatus.style.borderColor = COLORS[library[index].read ? 1 : 0];
}

function addBookToLibrary() {
	let title = document.getElementById('form-title').value;
	let author = document.getElementById('form-author').value;
	let pages = document.getElementById('form-pages').value;
	let read = document.getElementById('form-read').checked;

	if (title !== '' && author !== '' && pages !== '' ) {
		library.push(new Book(title, author, pages, read));
		displayBooks();
		clearForm();
	} else {
		alert('Form not filled out');
	}
}

function clearForm() {
    document.getElementById('form-title').value = '';
	document.getElementById('form-author').value = '';
	document.getElementById('form-pages').value = '';
	document.getElementById('form-read').checked = false;
}