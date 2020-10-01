function* autoGen(){
    var counter = 1;
    while(true){
    yield counter;
    counter++;
    }
}
    