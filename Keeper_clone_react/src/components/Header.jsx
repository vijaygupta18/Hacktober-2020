import React from "react";
// import NoteIcon from "@material-ui/icons/Note";
// import SubjectIcon from '@material-ui/icons/Subject';
import NoteAddIcon from "@material-ui/icons/NoteAdd";

function Header() {
  return (
    <header>
      <h1>
        <NoteAddIcon fontSize="large" />
        Keeper App
      </h1>
    </header>
  );
}

export default Header;
