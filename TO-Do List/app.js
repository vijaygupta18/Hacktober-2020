console.log("hello");

//define ui vars

const form = document.querySelector("#task-form");
const addBtn = document.querySelector("#addBtn");
const taskList = document.querySelector(".collection");
const clearBtn = document.querySelector(".clear-tasks");
const filter = document.querySelector("#filter");
const taskInput = document.querySelector("#task");

// load all event listeners
loadEventListeners();
function loadEventListeners(){
    //DOM load event
    document.addEventListener('DOMContentLoaded',getTasks)
    //add task event
    form.addEventListener('submit',addTask);
    //delete task event
    taskList.addEventListener('click',removeTask);
    filter.addEventListener('keyup',filterList);
    clearBtn.addEventListener('click',clearTasks);
}
// function trans(){
//     const main=document.querySelector('.main');
//     main.
// }
function getTasks(){
    let tasks;

    if(localStorage.getItem('tasks')=== null){
        tasks = [];

    }
    else{
        tasks = JSON.parse(localStorage.getItem('tasks'));
    }
     //create li element
    tasks.forEach(function(task){

    
     const li = document.createElement('li');
     // add  class
     
     li.className = 'collection-item';
     // create text node and append to the li
     
     li.appendChild(document.createTextNode(task));
     
     // create new link element
     const link = document.createElement('a');
     // add class 
     link.className = 'deleteItem secondary-content';
     //add icon html
     link.innerHTML = ` 
     <i class="fa fa-remove cross"></i>`;
     // appemd the link to the li
     
     li.appendChild(link);
     
     // append li to ul
     taskList.appendChild(li);
    });
}

function addTask(e){
if(taskInput.value === ""){
    alert("Add a Task!");
}
else{

    //create li element
    
    const li = document.createElement('li');
    // add  class
    
    li.className = 'collection-item';
    // create text node and append to the li
    
    li.appendChild(document.createTextNode(taskInput.value));
    
    // create new link element
    const link = document.createElement('a');
    // add class 
    link.className = 'deleteItem secondary-content';
    //add icon html
    link.innerHTML = '<i class="fa fa-remove"></i>';
    // appemd the link to the li
    
    li.appendChild(link);
    
    // append li to ul
    taskList.appendChild(li);
    
    // clear input 
    addToLocalStorage(taskInput.value);
    taskInput.value = '';
    console.log(li);
    
}
e.preventDefault();
}

function addToLocalStorage(task){
    let tasks;

    if(localStorage.getItem('tasks')=== null){
        tasks = [];

    }
    else{
        tasks = JSON.parse(localStorage.getItem('tasks'));
    }

    tasks.push(task);
    localStorage.setItem('tasks',JSON.stringify(tasks));

}


function removeTask(e){
    console.log(e.target);
    if(e.target.parentElement.classList.contains('deleteItem')){
        if(confirm('Are you sure?')){
            e.target.parentElement.parentElement.classList.add('fall');
            e.target.parentElement.parentElement.addEventListener('transitionend',function(){
                
                e.target.parentElement.parentElement.remove();
            })
        }
    }

    removeTasksFromLoacalStorage(e.target.parentElement.parentElement);

}

function removeTasksFromLoacalStorage(taskItem){
let tasks;
if(localStorage.getItem('tasks')=== null){
    tasks=[];
}
else{
    tasks = JSON.parse(localStorage.getItem('tasks'));

}
tasks.forEach(function(task,index){
    if(taskItem.textContent===task){

        tasks.splice(index,1);
    }
    localStorage.setItem('tasks',JSON.stringify(tasks));
});
}

function filterList(e){
    const text = e.target.value.toLowerCase();

    document.querySelectorAll('.collection-item').forEach(function(task){

        const item = task.firstChild.textContent;
        // console.log(item); 
        if(item.toLowerCase().indexOf(text) != -1){
            task.style.display = 'block';

        }
        else
        task.style.display = 'none';
    });
}


function clearTasks(){
    if(confirm('Are you sure?')){
        document.querySelectorAll('.collection-item').forEach(function(task){
            task.remove();
     });
     localStorage.clear();
    }
    
      
    
}















