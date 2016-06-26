import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

/**
 * Created by threaz on 14/06/16.
 */

public class TravelResultView {

    private JTable table1;
    private JPanel panel1;
    private JButton forwardButton;
    private JFrame frame;

    private String customerEmail = null;

    private List<Travel> listOfTravels           = null;
    private List<ActionListener> actionListeners = null;

    public TravelResultView(final String customerEmail, List<Travel> lst, List<ActionListener> al) {

        listOfTravels   = lst;
        actionListeners = al;
        this.customerEmail = customerEmail;

        frame = new JFrame("Your results");
        frame.setContentPane(panel1);
        frame.setMinimumSize(new Dimension(890, 300));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        panel1.setLayout(new BorderLayout());
        panel1.add(table1.getTableHeader(), BorderLayout.PAGE_START);
        panel1.add(table1, BorderLayout.CENTER);

        table1.setDefaultEditor(Object.class, null);

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("ID");
        dtm.addColumn("From");
        dtm.addColumn("To");
        dtm.addColumn("Departure");
        dtm.addColumn("Arrival");
        dtm.addColumn("Places left");
        dtm.addColumn("Mean of tr.");

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for(Travel tr : listOfTravels)
            dtm.addRow((Object[])createTableRow(tr));


        table1.setModel(dtm);

        setColumnWidths(40, 100, 100, 250, 250, 90, 100);

        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int selectedRow = table1.getSelectedRow();

                // there was no row chosen
                if(selectedRow == -1)
                    return;

                int travelID = Integer.parseInt((String)table1.getValueAt(selectedRow, 0));

                Travel selectedTravel = findSelectedTravel(travelID);

                if(selectedTravel == null)
                    throw new RuntimeException("Travel not found.");

                new PlaceOrderingView(customerEmail, selectedTravel, actionListeners).showWindow();
            }
        });
    }

    private Travel findSelectedTravel(int ID)
    {
        for(Travel tr : listOfTravels)
            if(tr.getID() == ID)
                return tr;

        return null;
    }

    private String[] createTableRow(Travel tr)
    {
        String[] arr = new String[7];

        arr[0] = Integer.toString(tr.getID());
        arr[1] = tr.getFrom();
        arr[2] = tr.getTo();

        Date departure = tr.getDeparture();
        Date arrival   = tr.getArrival();

        arr[3] = departure.toString();
        arr[4] = arrival.toString();

        arr[5] = Integer.toString(tr.getNumberOfTemporarilyAvailablePlaces());

        arr[6] = tr.getMeanOfTransport() instanceof Airplane ? "airplane" : "coach";

        return arr;
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

    public void showWindow()
    {
        frame.setVisible(true);
    }
}
