package model;

public class Division {
    private int id;
    private String name;
    private Country country;

    /**
     * This is the constructor for the Division class
     * @param id
     * @param name
     * @param country
     */
    public Division(int id, String name, Country country){
        this.id = id;
        this.name = name; //this is the name of the division
        this.country = country;
    }

    /**
     * This is the getter and setters for Division
     * @return
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
