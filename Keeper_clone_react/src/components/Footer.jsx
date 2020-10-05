import React from "react";

function Footer() {
  var year = new Date().getFullYear();
  return (
    <>
      <footer>
        <p>copyright &copy; {year} </p><br />
        <p>Created With ❤️ By Shubham Dutta</p>
      </footer>
    </>
  );
}

export default Footer;
