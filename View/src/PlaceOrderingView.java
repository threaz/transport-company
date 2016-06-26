import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by threaz on 18/06/16.
 */
public class PlaceOrderingView {

    private JFrame  frame;
    private JButton closeButton;
    private JButton reserveButton;
    private List<ActionListener> actionListeners;

    private MeanOfTransportView meanOfTransportView1;
    private OrderView orderView;

    private JPanel placeOrderingView;
    private Travel currentTravel;

    private String customerEmail = null;

    public PlaceOrderingView(final String customerEmail, Travel tr, List<ActionListener> al) {

        this.customerEmail = customerEmail;
        actionListeners = al;
        currentTravel   = tr;

        frame = new JFrame("Get yourself a place");
        frame.setContentPane(placeOrderingView);
        frame.setLocationRelativeTo(null);

        frame.pack();
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                closeWindow();
            }
        });
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                closeWindow();

                List<Integer> idOfPlacesOrdered = orderView.getOrders();

                // there wasn't any order
                if(idOfPlacesOrdered.size() == 0)
                    return;

                String msg = "[TempReservePlace] " + customerEmail + " " + currentTravel.getID() + " ";
                for(int i : idOfPlacesOrdered)
                    msg += i + " ";

                for(ActionListener a : actionListeners)
                    a.actionPerformed(new ActionEvent(PlaceOrderingView.this,
                            ActionEvent.ACTION_PERFORMED, msg));
            }
        });
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }
    public void closeWindow()
    {
        frame.dispose();
        frame.setVisible(false);
    }

    private void createUIComponents() {

        orderView = new OrderView();

        meanOfTransportView1 = new MeanOfTransportView(currentTravel);
        meanOfTransportView1.subscribe(orderView);
    }
}
