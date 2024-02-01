
package model;

public class ITStudent extends Student{
    
    protected double javaScore;
    protected double cssScore;
    protected Address address = new Address();

    public ITStudent( String ID, String fullName, Address address, double javaScore, double cssScore, double averageScore) {
        super(ID, fullName, address, averageScore);
        this.javaScore = javaScore;
        this.cssScore = cssScore;
    }

    public double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    public double getCssScore() {
        return cssScore;
    }

    public void setCssScore(double cssScore) {
        this.cssScore = cssScore;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public double calculateITAverageScore(){
       return (double) (3* javaScore + cssScore)/4;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Java Score = " + String.format("%.2f", javaScore) + ", CSS Score = " + String.format("%.2f", cssScore) + ",Average Score = "+ String.format("%.2f", calculateITAverageScore())+ '}' + "\n";
    }
}
