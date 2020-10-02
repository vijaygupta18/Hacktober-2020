// JShind es6 version

import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import './Join.css';



const Join = () => {

    const [name,setName] = useState("");
    const [room,setRoom] = useState("");
    const year = new Date().getFullYear();
    
    return (
        <div>
          <header>
                <h1>
                    My-Chat-Application
                </h1>
            </header>
            <div className = 'joinOuterContainer'>
                <div className = "joinInnerContainer">
                    <h1 className = "heading">Join</h1>
                    <div><input placeholder="Name" className="joinInput" type= "text" onChange={(event)=> setName(event.target.value)}/></div>
                    <div><input placeholder="Room" className="joinInput mt-20" type= "text" onChange={(event)=> setRoom(event.target.value)}/></div>  
                    <Link onClick={e => (!name || !room) ? e.preventDefault() : null} to={`/chat?name=${name}&room=${room}`}>
                        <button className = "button mt-20" type= "submit">Sign In</button>
                    </Link>
                </div>
            </div>
            <footer>
                <p>Copyright ⓒ {year}</p>
            </footer>
        </div>
    );
};

export default Join;