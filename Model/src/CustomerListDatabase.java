import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by threaz on 12/06/16.
 */
public class CustomerListDatabase implements CustomerDatabase {

    private List<Customer>      m_customers = new ArrayList<>();
    private Map<String, String> m_passwds   = new HashMap<>();

    @Override
    public void addCustomer(String name, String address, String email, String passwd)
            throws RuntimeException
    {
        if(customerExists(email))
            throw new RuntimeException("User exists.");

        Customer customer = new Customer(name, address, email);

        m_customers.add(customer);
        m_passwds.put(email, passwd);
    }


    @Override
    public void removeCustomer(String email) throws RuntimeException {

        if(! customerExists(email))
            throw new RuntimeException("Custommer not in database.");

        Customer c = findCustomer(email);
        m_customers.remove(c);
    }


    @Override
    public boolean customerExists(String email) {

        if(findCustomer(email) == null)
            return false;

        return true;
    }

    @Override
    public boolean customerPasswdCorrect(String email, String passwd) {
        return m_passwds.get(email).equals(passwd);
    }


    public Customer findCustomer(String email)
    {
        for(Customer cst : m_customers)
            if(cst.getEmail().equals(email))
                return cst;

        return null;
    }

    private void changePassword(String email, String newPasswd)
    {
        Customer customer = findCustomer(email);

        if(customer == null)
            throw new RuntimeException("User not found.");

        m_passwds.put(email, newPasswd);
    }

    @Override
    public void editCustomer(String email, String newName, String newAddress, String newPasswd) {

        Customer customer = findCustomer(email);

        customer.setName(newName);
        customer.setAddress(newAddress);

        changePassword(email, newPasswd);
    }

    @Override
    public String getCustomerPassword(String email) {

        return m_passwds.get(email);
    }

}
