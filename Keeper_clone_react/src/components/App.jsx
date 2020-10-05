import React, { useState } from "react";
import Header from "./Header";
import Note from "./Note";
import Footer from "./Footer";
import InputArea from "./Input";

function App() {
  const [notes, setNotes] = useState([]);

  function handleSubmit(newNote) {
    // console.log(newNote);
    setNotes((prevValue) => {
      console.log(prevValue);
      return [...prevValue, newNote];
    });
  }

  function deleteNote(id) {
    console.log(id);
    setNotes((prevValue) => {
      return prevValue.filter((element, index) => {
        return index !== id;
      });
    });
  }
  return (
    <>
      <Header />
      <InputArea onAdd={handleSubmit} />
      {notes.map((noteItem, index) => (
        <Note
          key={index}
          id={index}
          title={noteItem.title}
          content={noteItem.content}
          delete={deleteNote}
        />
      ))}
      <Footer />
    </>
  );
}

export default App;

//CHALLENGE:
//1. Implement the add note functionality.
//- Create a constant that keeps track of the title and content.
//- Pass the new note back to the App.
//- Add new note to an array.
//- Take array and render seperate Note components for each item.

//2. Implement the delete note functionality.
//- Callback from the Note component to trigger a delete function.
//- Use the filter function to filter out the item that needs deletion.
//- Pass a id over to the Note component, pass it back to the App when deleting.

//This is the end result you're aiming for:
//https://pogqj.csb.app/
