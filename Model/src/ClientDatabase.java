/**
 * Interface to the database which is able to hold information about clients
 * registered to the system. The information include customer's: name, address, email and password.
 */

// bridge pattern

public interface ClientDatabase {

    /**
     * Add customer to the database.
     * @param name customer's name
     * @param address customer's address
     * @param email customer's email address
     * @param passwd customer's password
     */
    public void addCustomer(String name, String address, String email, String passwd);

    /**
     * Remove customer based on their unique email address.
     * @param email customer's email address
     */
    public void removeCustomer(String email);

    /**
     * Check whether the customer exists in the database.
     * @param email customer's emails address
     * @return
     */
    public boolean customerExists(String email);

    /**
     * Check whether the customer's password match to their email address.
     * @param email customer's email address
     * @param passwd customer's password
     * @return true if matches, false otherwise
     */
    public boolean customerPasswdCorrect(String email, String passwd);

    /**
     * Find customer in the database. Match is performed based on customer's email.
     * @param email customer's email address
     * @return Customer object if found, null otherwise
     */
    public Customer findCustomer(String email);

    /**
     * Modify customer's properties.
     * @param email customer's email address
     * @param newName customer's new name
     * @param newAddress customer's new address
     * @param newPassword customer's new password
     */
    public void editCustomer(String email, String newName, String newAddress, String newPassword);

    /**
     * Retrieve the customer's password.
     * @param email customer's email address
     * @return customer's password as string if success, null otherwise
     */
    String getCustomerPassword(String email);
}
