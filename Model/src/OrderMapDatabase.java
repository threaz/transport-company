import java.util.ArrayList;
import java.util.List;

/**
 * Created by threaz on 19/06/16.
 */
public class OrderMapDatabase implements OrderDatabase {

    List<Order> m_orders      = new ArrayList<>();
    static int  m_nextOrderID = 0;

    private int getNextOrder()
    {
        return m_nextOrderID++;
    }

    @Override
    public void addOrder(Order order) {

        order.setID(getNextOrder());
        m_orders.add(order);
    }
}
