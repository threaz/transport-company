/**
 * Order which can be placed by customer. Each order is assigned to exactly one place booked.
 * Its properties include: order ID, travel ID, cost of the place and unique customer email.
 */
public class Order {

    private int                       m_ID;
    private int                       m_travelID;
    private int                       m_placeID;
    private int                       m_placeCost;
    private String                    m_customerEmail;

    /**
     * Default constructor.
     * @param ID order ID(should be unique)
     * @param travelID travel ID
     * @param placeID place ID
     * @param placeCost place cost
     * @param customerEmail email of the customer placing the order
     */
    public Order(int ID, int travelID, int placeID, int placeCost, String customerEmail)
    {
        m_ID = ID;
        m_travelID = travelID;   m_placeID = placeID;
        m_placeCost = placeCost; m_customerEmail = customerEmail;
    }

    /**
     * Overloaded constructor with default value for order ID, which is in this case 0.
     * @param travelID travel ID
     * @param placeID place ID
     * @param placeCost place cost
     * @param customerEmail email of the customer placing the order
     */
    public Order(int travelID, int placeID, int placeCost, String customerEmail)
    {
        this(0, travelID, placeID, placeCost, customerEmail);
    }

    /**
     * Comparing two orders.
     * @param obj order to compare with
     * @return true if orders are the same, false otherwise
     */
    public boolean equals(Object obj)
    {
        Order o = (Order) obj;
        return (o.m_placeID == m_placeID &&
        o.m_travelID == m_travelID);
    }

    /**
     * Return travel ID.
     * @return travel ID
     */
    public int getTravelID()
    {
        return m_travelID;
    }

    /**
     * Return place ID.
     * @return place ID
     */
    public int getPlaceID() {
        return m_placeID;
    }

    /**
     * Return place cost.
     * @return place cost
     */
    public int getPlaceCost() {
        return m_placeCost;
    }

    /**
     * Set order ID.
     * @param ID order ID to be set
     */
    public void setID(int ID) { m_ID = ID; }

    /**
     * Get order ID.
     * @return order ID
     */
    public int  getID() { return  m_ID; }
}
