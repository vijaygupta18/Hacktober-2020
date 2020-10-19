axios.get("https://api.coronadaily.org/covid/location/INDIA").then(handleResponse);

function handleResponse(data){
  populateTable(data.data);
}

function populateTable(data){
  var table= document.querySelector(".main-table")
  var locationResponse= data.locationResponse;

 for(var i=0; i<locationResponse.length;i++){
   var row= document.createElement("tr");

   var cell1= document.createElement("td");
   var cell2= document.createElement("td");
   var cell3= document.createElement("td");
   var cell4= document.createElement("td");
   var cell5= document.createElement("td");


   cell1.innerHTML = locationResponse[i].locationName;

   cell2.innerHTML = locationResponse[i].dataTypeCountMap.TOTAL;

   cell3.innerHTML = locationResponse[i].dataTypeCountMap.ACTIVE;

   cell4.innerHTML = locationResponse[i].dataTypeCountMap.RECOVERED;

   cell5.innerHTML = locationResponse[i].dataTypeCountMap.DEATH;

   row.appendChild(cell1);
   row.appendChild(cell2);
   row.appendChild(cell3);
   row.appendChild(cell4);
   row.appendChild(cell5);

   table.appendChild(row);
 }
 var rowtotal= document.createElement("tr");

 var cell1total= document.createElement("td");
 var cell2total= document.createElement("td");
 var cell3total= document.createElement("td");
 var cell4total= document.createElement("td");
 var cell5total= document.createElement("td");

var total = data.dataTypeCountMap;
 cell1total.innerHTML = "Country Total";
 cell2total.innerHTML = total.TOTAL;
 cell3total.innerHTML = total.ACTIVE;
 cell4total.innerHTML = total.RECOVERED;
 cell5total.innerHTML = total.DEATH;

 rowtotal.appendChild(cell1total);
 rowtotal.appendChild(cell2total);
 rowtotal.appendChild(cell3total);
 rowtotal.appendChild(cell4total);
 rowtotal.appendChild(cell5total);

 table.appendChild(rowtotal);
}
