import java.util.ArrayList;
import java.util.List;

/**
 * Created by threaz on 03/06/16.
 */
public class Customer {

    private String m_name;
    private String m_address;
    private String m_email;

    private List<Order>   m_currentOrders = new ArrayList<>();
    private List<Integer> m_pastOrders    = new ArrayList<>();

    public Customer(String name, String address, String email)
    {
        m_name = name;
        m_address = address; m_email = email;
    }

    public String getEmail()
    {
        return m_email;
    }

    public void addTemporarilyOrder(Order o) {

        m_currentOrders.add(o);
    }

    public void clearTemporarilyOrders() {

        m_currentOrders = new ArrayList<>();
    }

    public void addPastOrderID(int orderID)
    {
        m_pastOrders.add(orderID);
    }

    public List<Order> getTemporarilyOrders()
    {
        return m_currentOrders;
    }

    public String getName() {
        return m_name;
    }

    public String getAddress() {
        return m_address;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setAddress(String address) {
        this.m_address = address;
    }

    public void removeTemporarilyOrder(int travelID, int placeID) throws RuntimeException {

        Order o = findOrder(travelID, placeID);
        if(o == null)
            throw new RuntimeException("Order not found");

        m_currentOrders.remove(o);
    }

    private Order findOrder(int travelID, int placeID)
    {
        for(Order o : m_currentOrders)
            if(o.getTravelID() == travelID && o.getPlaceID() == placeID)
                return o;

        return null;
    }
}
