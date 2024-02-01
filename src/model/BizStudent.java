
package model;

public class BizStudent extends Student{
    
    protected double accountingScore;
    protected double marketingScore;

    public BizStudent( String ID, String fullName, Address address, double accountingScore, double marketingScore, double averageScore) {
        super(ID, fullName, address, averageScore);
        this.accountingScore = accountingScore;
        this.marketingScore = marketingScore;
    }

    public double getAccountingScore() {
        return accountingScore;
    }

    public void setAccountingScore(double accountingScore) {
        this.accountingScore = accountingScore;
    }

    public double getMarketingScore() {
        return marketingScore;
    }

    public void setMarketingScore(double marketingScore) {
        this.marketingScore = marketingScore;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public double calculateBizAverageScore(){
       return (double) (accountingScore*2 + marketingScore)/3;
    }
   
    @Override
    public String toString() {
        return super.toString() + "Accounting Score = " + String.format("%.2f", accountingScore) + ", Marketing Score = " + String.format("%.2f", marketingScore) + ",Average Score = "+ String.format("%.2f", calculateBizAverageScore())+ '}' + "\n";
    }
}
