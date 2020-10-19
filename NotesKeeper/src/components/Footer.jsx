import React from "react";

function Footer(){
 let d = new Date();
 let year = d.getFullYear();
    return <footer><p>Copyrightⓒ{year}</p></footer>;

}

export default Footer;