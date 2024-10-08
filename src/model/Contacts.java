package model;

public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

        /**
         * This is the constructor for the Contacts class
         * @param contactID
         * @param contactName
         * @param contactEmail
         */
    public Contacts(int contactID, String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * This is the getter and setters for Contacts
     * @return
     */

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return contactName;
    }
}
