import React, { useState } from "react";
import AddIcon from "@material-ui/icons/Add";
import Fab from "@material-ui/core/Fab";
import Zoom from "@material-ui/core/Zoom";

function InputArea(props) {
  const [note, setNote] = useState({
    title: "",
    content: "",
  });

  const [isExpanded, setisExpanded] = useState(false);

  function handleChange(event) {
    const { name, value } = event.target;
    setNote((prevValue) => {
      return {
        ...prevValue,
        [name]: value,
      };
    });
  }
  return (
    <form className="create-note">
      {isExpanded && (
        <input
          onChange={handleChange}
          name="title"
          placeholder="Title..."
          value={note.title}
        />
      )}
      <textarea
        onChange={handleChange}
        name="content"
        placeholder="Take a Note..."
        value={note.content}
        onClick={() => {
          setisExpanded(true);
        }}
        rows={isExpanded ? "3" : "1"}
      />
      <Zoom in={isExpanded}>
        <Fab
          color="secondary"
          onClick={(event) => {
            props.onAdd(note);
            setNote({ title: "", content: "" });
            event.preventDefault();
          }}
        >
          <AddIcon fontSize="large" />
        </Fab>
      </Zoom>
    </form>
  );
}

export default InputArea;
