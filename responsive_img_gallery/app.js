const numberOfImg = 50;
const totalImg = 1600;
const imgWidth = 480;
const imgHeight = 360;
const collectionID = 3694365;

function randomImg(randomNumber){
    let url = `https://source.unsplash.com/collection/${collectionID}/${imgWidth}x${imgHeight}/?sig=${randomNumber}`;
    fetch(url).then((response) => {
        let img_gallery = document.querySelector(".img-gallery");
        let img = document.createElement("IMG");
        img.src = response.url;
        img_gallery.appendChild(img);
    }).catch((error)=>{
        console.log(error);
    });
}

for(let i =0;i<numberOfImg;i++){
    let randomNo = Math.floor(Math.random()*totalImg);
    randomImg(randomNo);
}