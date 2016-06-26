import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by threaz on 13/06/16.
 */
public class TravelSearchingView {

    private JPanel travelSearchingView;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel title_label;
    private JButton closeButton;
    private JButton searchButton;
    private DoubleComboBox doubleCombo;
    private TripleComboBox tripleCombo;
    private JRadioButton coachRadioButton;
    private JRadioButton airplaneRadioButton;
    private JFrame frame;

    private String customerEmail = null;

    private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

    public void subscribe(ActionListener actionListener)
    {
        actionListeners.add(actionListener);
    }

    public void unsubscribe(ActionListener actionListener)
    {
        if(actionListeners.contains(actionListener))
            actionListeners.remove(actionListener);
    }

    public TravelSearchingView(String customerEmail, List<ActionListener> al) {

        actionListeners = al;
        this.customerEmail = customerEmail;

        frame = new JFrame("Find yourself a travel");
        frame.setContentPane(travelSearchingView);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.pack();
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                frame.setVisible(false);
            }
        });

        for(int i = 1; i < 32; ++i)
            tripleCombo.addToFirst(((i < 10) ? "0" : "") + Integer.toString(i));
        for(int i = 1; i < 13; ++i)
            tripleCombo.addToSecond(((i < 10) ? "0" : "") + Integer.toString(i));
        for(int i = 2016; i < 2020; ++i)
            tripleCombo.addToThird(Integer.toString(i));

        for(int i = 0; i <= 23; ++i)
            doubleCombo.addToFirst(((i < 10) ? "0" : "") + Integer.toString(i));
        for(int i = 0; i <= 60; ++i)
            doubleCombo.addToSecond(((i < 10) ? "0" : "") + Integer.toString(i));

        // send message to database in order to
        // fetch data from database and fill components with it

        for(ActionListener a : actionListeners)
            a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "[TravelSearch]"));


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(! coachRadioButton.isSelected() && ! airplaneRadioButton.isSelected()) {
                    showMessage("You must select mean of transport!");
                    return;
                }

                String query = "[GetListOfTravels] ";

                // from
                query += comboBox2.getSelectedItem() + " ";
                // to
                query += comboBox1.getSelectedItem() + " ";
                // date
                query += tripleCombo.getInfo() + " ";
                // time
                query += doubleCombo.getInfo() + " ";
                // mean of transport
                if(coachRadioButton.isSelected() && airplaneRadioButton.isSelected())
                    query += "both";
                else if(coachRadioButton.isSelected())
                    query += "coach";
                else if(airplaneRadioButton.isSelected())
                    query += "airplane";

                for(ActionListener a : actionListeners)
                    a.actionPerformed(new ActionEvent(TravelSearchingView.this,
                            ActionEvent.ACTION_PERFORMED, query));
            }
        });
    }

    private void fillboxFrom(List<String> items)
    {
        Collections.sort(items);

        for(String e : items)
            comboBox2.addItem(e);
    }

    private void fillboxTo(List<String> items)
    {
        Collections.sort(items);

        for(String e : items)
            comboBox1.addItem(e);
    }

    public void receivedData(String msg)
    {
        String[] msgs = msg.split("\\s+");
        List<String> comboboxItems = new ArrayList<>();


        switch(msgs[0])
        {
            case "[TravelSearch::From]":
                for(int i = 1; i < msgs.length; ++i)
                    comboboxItems.add(msgs[i]);

                 // remove duplicates
                List<String> comboboxItemsNew = new ArrayList<>(new LinkedHashSet<>(comboboxItems));

                fillboxFrom(comboboxItemsNew);
                break;

            case "[TravelSearch::To]":
                for(int i = 1; i < msgs.length; ++i)
                    comboboxItems.add(msgs[i]);

                 // remove duplicates
                comboboxItemsNew = new ArrayList<>(new LinkedHashSet<>(comboboxItems));
                fillboxTo(comboboxItemsNew);
                break;

            default:
                System.err.println("Not recognized option :: receivingDataHappened");
                break;
        }

        showWindow();
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }

    public void receivedTravels(List<Travel> trls) {

        if(trls.isEmpty())
        {
            showMessage("No such travels!");
            return;
        }

        TravelResultView trw = new TravelResultView(customerEmail, trls, actionListeners);

        trw.showWindow();
    }

}
