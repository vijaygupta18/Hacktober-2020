import React,{useState} from "react";
import AddIcon from '@material-ui/icons/Add';
import Fab from '@material-ui/core/Fab';
import Zoom from '@material-ui/core/Zoom';
function CreateArea(props) {
  
  const [note,setNote] = useState({
      title : "",
      content : "" 
  })
  
  function handleChange(event){
    const {name,value} = event.target;

    setNote(prevNote=>{

        return {
            ...prevNote,
            [name]:value
        };
    })

    
  }
  function submitNote(event){
     props.onAdd(note);
      setNote({
          title: "",
          content: ""
      })
      setisClicked(false);
    event.preventDefault();
  }

  const[isClicked,setisClicked] = useState(false);
  function handleClick(){
      setisClicked(true);
  }
  return (
    <div>
      <form className="create-note">
        {isClicked && <input onChange = {handleChange} name="title" placeholder="Title" autoComplete = "off" value = {note.title}/>}
        <textarea onClick = {handleClick} onChange = {handleChange} name="content" placeholder="Take a note..." rows={isClicked?3:1}  autoComplete = "off" value = {note.content} />
        <Zoom in ={isClicked?true:false}><Fab onClick = {submitNote}><AddIcon /></Fab></Zoom>
        
      </form>
    </div>
  );
}

export default CreateArea;
