/**
 * Interface of the database which holds the past orders of all users registered
 * to the system.
 */
public interface OrderDatabase {

    /**
     * Add order to database
     * @param order order
     */
    void addOrder(Order order);
}
