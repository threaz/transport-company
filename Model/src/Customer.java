import java.util.ArrayList;
import java.util.List;

/**
 * Customer with basic properties: name, address and email address.
 * Each customer has their current orders which have not yet been placed
 * and orders' ID from the past which they have made so far.
 */
public class Customer {

    private String m_name;
    private String m_address;
    private String m_email;

    private List<Order>   m_currentOrders = new ArrayList<>();
    private List<Integer> m_pastOrders    = new ArrayList<>();

    /**
     * Default constructor.
     * @param name customer's name
     * @param address customer's address
     * @param email customer's email address
     */
    public Customer(String name, String address, String email)
    {
        m_name = name;
        m_address = address; m_email = email;
    }

    /**
     * Return customer's email address.
     * @return email address
     */
    public String getEmail()
    {
        return m_email;
    }

    /**
     * Add order to the list of ones that haven't been placed yet.
     * @param o order to be added
     */
    public void addTemporarilyOrder(Order o) {

        m_currentOrders.add(o);
    }

    /**
     * Wipe out customer's orders.
     */
    public void clearTemporarilyOrders() {

        m_currentOrders = new ArrayList<>();
    }

    /**
     * Add ID of the order customer made in the past to the list of such orders.
     * @param orderID order ID to be added
     */
    public void addPastOrderID(int orderID)
    {
        m_pastOrders.add(orderID);
    }

    /**
     * Return list of orders that haven't been placed yet.
     * @return list of orders that haven't been placed yet
     */
    public List<Order> getTemporarilyOrders()
    {
        return m_currentOrders;
    }

    /**
     * Return customer's name.
     * @return customer's name
     */
    public String getName() {
        return m_name;
    }

    /**
     * Return customer's address
     * @return customer's address
     */
    public String getAddress() {
        return m_address;
    }

    /**
     * Set customer's name
     * @param name name of the customer to be set
     */
    public void setName(String name) {
        m_name = name;
    }

    /**
     * Set customer's address.
     * @param address customer's address to be set
     */
    public void setAddress(String address) {
        this.m_address = address;
    }

    /**
     * Remove selected order from the list of temporarily orders.
     * @param travelID travel ID
     * @param placeID place ID
     * @throws RuntimeException
     */
    public void removeTemporarilyOrder(int travelID, int placeID) throws RuntimeException {

        Order o = findOrder(travelID, placeID);
        if(o == null)
            throw new RuntimeException("Order not found");

        m_currentOrders.remove(o);
    }

    // PRIVATE

    private Order findOrder(int travelID, int placeID)
    {
        for(Order o : m_currentOrders)
            if(o.getTravelID() == travelID && o.getPlaceID() == placeID)
                return o;

        return null;
    }
}
