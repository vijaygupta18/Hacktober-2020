const itemOperations = {
    items:[],
    add(itemObject){
        this.items.push(itemObject);
    },
    remove(){
         this.items = this.items.filter(itemObject=>!itemObject.isMarked);   
        return this.items;
        },
    search(id){
            return this.items.find(itemObject=>itemObject.id ==id);
    },
    searchAll(key, val){
        if(val){
          return  this.items.filter(itemObject=>itemObject[key]==val);  
        }
        else{
            return this.items;
        }
        },
    markUnMark(id){
            this.search(id).toggle();
    },
    countTotalMark(){
           return this.items.filter(itemObject=>itemObject.isMarked).length; 
    },
    sortByPrice(){
        return this.items.sort((a,b)=>a.price-b.price);
    },
    update(){

    }
}