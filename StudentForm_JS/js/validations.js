function checkDate(){
    var GivenDate = document.getElementById("dob").value;
    var CurrentDate = new Date();
    GivenDate = new Date(GivenDate);

    if(GivenDate > CurrentDate){
        alert('Given date is greater than the current date.');
        return false;
    }
}

function ValidateEmail() 
{
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.getElementById("mail").value))
    {
        return (true)
    }
    alert("You have entered an invalid email address!")
        return (false)
}

function validatephone()
{
    if (/^\d{10}$/.test(document.getElementById("phone").value))
    {
        return (true)
    }
    alert("You have entered an invalid phone number!")
        return (false)
}

function checkFields(){
    if (checkDate()==false){
        return false;
    }

    if (mail.value.length != 0)
    { 
        if(ValidateEmail()==false){
            return false;
        }
    }

    if (phone.value.length != 0)
    { 
        if(validatephone()==false){
            return false;
        }
    }

    if (student_name.value.length == 0)
    { 
        alert("Name missing");  	
        return false; 
    }

    if (gender.value.length == 0)
    { 
        alert("mark gender");  	
        return false; 
    }

    if (dob.value.length == 0)
    { 
        alert("dob missing");  	
        return false; 
    }
    if (branch.value.length == 0)
    { 
        alert("branch missing");  
        return false;	
    }
}

function reset_radio(){
    document.getElementById("M").checked = false;
    document.getElementById("F").checked = false;
}