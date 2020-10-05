import React from "react";
import DeleteIcon from "@material-ui/icons/Delete";
import IconButton from "@material-ui/core/IconButton";

function Note(props) {
  return (
    <div className="note">
      <h1>{props.title}</h1>
      <p>{props.content}</p>
      <IconButton
        className="delete"
        onClick={() => {
          props.delete(props.id);
        }}
      >
        <DeleteIcon fontSize="large" />
      </IconButton>
    </div>
  );
}

export default Note;
