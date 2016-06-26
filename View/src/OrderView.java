import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by threaz on 04/06/16.
 */

public class OrderView extends JTextPane implements ActionListener {

    private List<Integer> orders = new ArrayList<>();
    private Map<Integer, Integer> pricesAndTheirAmounts = new HashMap<>();

    public void actionPerformed(ActionEvent e)
    {
        String msg = e.getActionCommand();
        String[] info = msg.split("\\s+", 4);

        int trID      = Integer.parseInt(info[1]);
        int placeID   = Integer.parseInt(info[2]);
        int placeCost = Integer.parseInt(info[3]);

        if(msg.startsWith("D")) // delete order
            removeOrder(placeID, placeCost);
        else
            addOrder(placeID, placeCost);

        // write orders details
        String result    = "";

        int resultAmount = 0;
        for(Map.Entry<Integer, Integer> entry : pricesAndTheirAmounts.entrySet()) {
            result += entry.getKey() + " x " + entry.getValue() + "\n";
            resultAmount += entry.getKey() * entry.getValue();
        }
        result += "--------------------------\n";
        result += "Total: " + Integer.toString(resultAmount);

        if(orders.isEmpty())
            setText("Zamówienie jest puste");
        else
            setText(result);

        setVisible(true);
    }

    private void addOrder(int o, int cost)
    {
        orders.add(o);

        if(! pricesAndTheirAmounts.containsKey(cost))
                pricesAndTheirAmounts.put(cost, 1);
        else
            {
                int x = pricesAndTheirAmounts.get(cost);
                pricesAndTheirAmounts.put(cost, x + 1);
            }
    }

    private void removeOrder(int o, int cost)
    {
        orders.remove((Integer) o);
        int x = pricesAndTheirAmounts.get(cost);
        pricesAndTheirAmounts.put(cost, x - 1);

        if(pricesAndTheirAmounts.get(cost) == 0)
            pricesAndTheirAmounts.remove(cost);
    }

    public List<Integer> getOrders() { return orders; }

    public OrderView()
    {
        setText("Zamówienie jest puste");
        setPreferredSize(new Dimension(100, 100));
    }
}