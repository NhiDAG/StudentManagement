
package model;

public class Student {
   
   protected String ID;
   protected String fullName;
   protected double averageScore;
   protected Address address = new Address();

    public Student(String ID, String fullName, Address address, double averageScore) {
        this.ID = ID;
        this.fullName = fullName;
        this.address = address;
        this.averageScore = averageScore;
    }
    
    public Student(){
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "ID = " + ID + ", Full Name = " + fullName 
                + "Country = " + address.getCountry() + ", City = " + address.getCity() 
                + ", District = " + address.getDistrict() + ", Street = " + address.getStreet();
    }
}
