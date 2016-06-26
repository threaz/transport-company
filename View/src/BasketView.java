import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

/**
 * Created by threaz on 19/06/16.
 */
public class BasketView {

    private JPanel basketView;
    private JTable table1;
    private JButton payForYourOrdersButton;
    private JButton deleteOrderButton;
    private JButton closeButton;
    private JFrame frame;
    private DefaultTableModel dtm;

    private String customerEmail;
    private List<ActionListener> actionListeners;

    public BasketView(final String customerEmail, final List<ActionListener> actionListeners)
    {
        this.customerEmail   = customerEmail;
        this.actionListeners = actionListeners;

        frame = new JFrame("Your basket");
        frame.setContentPane(basketView);
        frame.setMinimumSize(new Dimension(1130, 300));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        basketView.setLayout(new BorderLayout());
        basketView.add(table1.getTableHeader(), BorderLayout.PAGE_START);
        basketView.add(table1, BorderLayout.CENTER);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                closeWindow();
            }
        });


        dtm = new DefaultTableModel();
        dtm.addColumn("Travel ID");
        dtm.addColumn("From");
        dtm.addColumn("To");
        dtm.addColumn("Departure");
        dtm.addColumn("Arrival");
        dtm.addColumn("Mean of tr.");
        dtm.addColumn("Place number");
        dtm.addColumn("Price");

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table1.setDefaultEditor(Object.class, null);

        // fetch orders
        String msg = "[RefreshBasket] " + customerEmail;
        for(ActionListener a: actionListeners)
            a.actionPerformed(new ActionEvent(BasketView.this, ActionEvent.ACTION_PERFORMED, msg));

        payForYourOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(table1.getRowCount() == 0)
                    return;

                int res    = showConfirmWindow("Are you sure?");
                String msg = "[ProceedWithUserOrder] " + customerEmail;

                if(res == JOptionPane.YES_OPTION)
                {
                    for (ActionListener a : actionListeners)
                        a.actionPerformed(new ActionEvent(BasketView.this, ActionEvent.ACTION_PERFORMED, msg));

                    // clear table
                    table1.setModel(new DefaultTableModel());
                    showWindow();

                }

            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // no row selected
                if(table1.getSelectedRow() == -1)
                    return;

                int res    = showConfirmWindow("Are you sure?");

                String travelID = (String)table1.getValueAt(table1.getSelectedRow(), 0);
                String placeID  = (String)table1.getValueAt(table1.getSelectedRow(), 6);

                String msg = "[DeleteUserOrder] " + customerEmail + " " + travelID + " " + placeID;

                if(res == JOptionPane.YES_OPTION)
                    for(ActionListener a : actionListeners)
                        a.actionPerformed(new ActionEvent(BasketView.this, ActionEvent.ACTION_PERFORMED, msg));
            }
        });

        setColumnWidths(40, 100, 100, 250, 250, 150, 150, 90);

        table1.setModel(dtm);
    }

    private int showConfirmWindow(String message)
    {
        return JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
    }

    private void setColumnWidths(int... widths)
    {
        TableColumnModel columnModel = table1.getColumnModel();

        for (int i = 0; i < widths.length; i++)
        {
            if (i < columnModel.getColumnCount())
            {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else
                break;
        }
    }

    public void closeWindow()
    {
        frame.dispose();
        frame.setVisible(false);
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }

    public void ordersDelivered(List<Order> temporarilyOrders, List<Travel> travels) {

        // fill the table in
        for(Order o : temporarilyOrders)
            dtm.addRow((Object[])createTableRow(findTravel(travels, o.getTravelID()), o));
    }

    private Travel findTravel(List<Travel> lot, int travelID)
    {
        for(Travel t : lot)
            if(t.getID() == travelID)
                return t;

        return null;
    }

    private String[] createTableRow(Travel tr, Order or)
    {
        String[] arr = new String[8];

        arr[0] = Integer.toString(tr.getID());
        arr[1] = tr.getFrom();
        arr[2] = tr.getTo();

        Date departure = tr.getDeparture();
        Date arrival   = tr.getArrival();

        arr[3] = departure.toString();
        arr[4] = arrival.toString();

        arr[5] = tr.getMeanOfTransport() instanceof Airplane ? "airplane" : "coach";
        arr[6] = Integer.toString(or.getPlaceID());
        arr[7] = Integer.toString(or.getPlaceCost());

        return arr;
    }

    public void orderDeleted(int travelID, int placeID) {

        int rowNumber = findRowToBeDeleted(travelID, placeID);

        if(rowNumber == -1)
            return;

        dtm.removeRow(rowNumber);
        showWindow();
    }

    private int findRowToBeDeleted(int travelID, int placeID)
    {
        for(int i = 0; i < dtm.getRowCount(); ++i)
        {
            if(Integer.parseInt((String)table1.getValueAt(i, 0)) == travelID &&
                    Integer.parseInt((String)table1.getValueAt(i, 6)) == placeID)
                return i;
        }

        return -1;
    }
}
