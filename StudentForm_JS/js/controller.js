window.addEventListener("load",init);

var auto = autoGen();
function loadId(){
document.querySelector('#id').innerText = auto.next().value;
document.querySelector('#id').focus();
}
function clearAll(){
    itemObject = new Item();
    for(let key in itemObject){
        if(key=='isMarked' || key=='id'){
            continue;
        }
        document.querySelector('#'+key).value = '';
    }
}

function init(){
    clearAll();
    loadId();
    showTotal();
    bindEvents();
   
}
function updateRecord(){
    if (checkFields() == false){
        return;
    }
    reset_radio()
    for(let key in itemObject){
        if(key=='isMarked'){
            continue;
        }
        itemObject[key] = document.querySelector('#'+key).value;
    }
    printTable(itemOperations.items);
}

function bindEvents(){
    document.querySelector('#update').addEventListener('click',updateRecord);
    document.querySelector('#remove').addEventListener('click',deleteRecords);
    document.querySelector('#add').addEventListener('click',addRecord);
}

function deleteRecords(){
    var items = itemOperations.remove();
    printTable(items);
}

function showTotal(){
    document.querySelector('#total').innerText = itemOperations.items.length;
    document.querySelector('#mark').innerText = itemOperations.countTotalMark();
    document.querySelector('#unmark').innerText = itemOperations.items.length - itemOperations.countTotalMark();
}

function createIcon(className,fn, id){
    var iTag = document.createElement("i");
    iTag.className = className;
    iTag.addEventListener('click',fn);
    iTag.setAttribute("data-itemid", id) ;

    return iTag;
}

var itemObject;
function edit(){
    var id = this.getAttribute('data-itemid');
    itemObject = itemOperations.search(id);
    fillFields();
    console.log("i am Edit ",this.getAttribute('data-itemid'));
}

function fillFields(){
    for(let key in itemObject){
        if(key=='isMarked'){
            continue;
        }
        document.querySelector('#'+key).value = itemObject[key];
    }
}

function trash(){
    let id = this.getAttribute('data-itemid');
    itemOperations.markUnMark(id);
    showTotal();
    let tr = this.parentNode.parentNode;

    tr.classList.toggle('alert-danger');
    console.log("I am Trash ",this.getAttribute('data-itemid'))
}

function addRecord(){

    if (checkFields() == false){
        return;
    }
    reset_radio();
    var item = new Item();
    for(let key in item){
        if(key=='isMarked'){
            continue;
        }
        if(key=='id'){
            item[key] = document.querySelector('#'+key).innerText;
            continue;
        }
        item[key] = document.querySelector('#'+key).value;
    }
    itemOperations.add(item);
    printRecord(item);
    console.log('Item Object is ',item);
    showTotal();
    loadId();
    clearAll();
}

function printTable(items){
   var tbody =  document.querySelector('#items');
   tbody.innerHTML ='';
    items.forEach(item=>printRecord(item));
    showTotal();
}

function printRecord(item){
    var tbody = document.querySelector('#items');
    var tr = tbody.insertRow();
    var index = 0;
    for(let key in item){
        // console.log(key);
        // console.log(item[key]);
        if(key=='isMarked'){
            continue;
        }
        if(key=="gender"){
            if(item[key]=="M"){
                tr.setAttribute("bgcolor","green");
            }
            else if(item[key]=="F"){
                tr.setAttribute("bgcolor","yellow");
            }
        }
        let cell = tr.insertCell(index);
        cell.innerText = item[key] ;
        index++;
    }
    var lastTD = tr.insertCell(index);
    lastTD.appendChild(createIcon('fas fa-trash mr-2',trash,item.id));
    lastTD.appendChild(createIcon('fas fa-edit',edit,item.id));
}