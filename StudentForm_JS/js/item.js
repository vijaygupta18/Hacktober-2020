class Item{
    constructor(id , student_name, gender, dob, mail, phone, branch){
         this.id = id;
         this.student_name = student_name;
         this.gender = gender;
         this.dob = dob;
         this.mail = mail;
         this.phone = phone;
         this.branch = branch;
         this.isMarked = false;
    }
    toggle(){
        this.isMarked = !this.isMarked;
    }
}