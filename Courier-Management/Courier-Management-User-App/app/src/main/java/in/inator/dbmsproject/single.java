package in.inator.dbmsproject;

public class single {
    String title;
    String weight;
    String description;
    String address;
    String city;
    String state;
    String pincode;

    public single (){

    }

    public single (String title,String weight, String description,String address, String city, String state, String pincode){
        this.title = title;
        this.weight = weight;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getWeight() {
        return weight;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
