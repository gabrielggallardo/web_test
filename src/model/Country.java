package model;

public class Country {
    private int countryID;
    private String countryName;
    /**
     * This is the constructor for the Country class
     * @param countryID
     * @param countryName
     */

    public Country(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * This is the getter and setters for Country
     * @return
     */
    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString(){
        return countryName;
    }
}
