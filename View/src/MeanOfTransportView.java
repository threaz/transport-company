import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.logging.ConsoleHandler;

public class MeanOfTransportView extends JPanel {

   	public MeanOfTransportView(Travel tr) {

        setBounds(100, 100, 576, 553);

        GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths  = new int[]{0};
		gbl_contentPane.rowHeights    = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights    = new double[]{Double.MIN_VALUE};

		setLayout(gbl_contentPane);

        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.anchor             = GridBagConstraints.FIRST_LINE_START;

        MeanOfTransport mot = tr.getMeanOfTransport();

        int cols = 0, rows = 0, floor = 0;
        int[][] placesLayoutInfo = mot.getPlaceLayoutInfo();

        // TODO: add handling multiple-floor vehicles
        int totalSeatsOnCurrentLevel = placesLayoutInfo[0][0] * placesLayoutInfo[0][1];

        for(int i = 0; i < totalSeatsOnCurrentLevel; ++i)
        {
            Place p = mot.getPlace(i);

            final int nr = p.getID();
            final int placeCost = tr.getPlaceCost(nr);
            final int travelID  = tr.getID();
            constraints1.gridx = cols;
            constraints1.gridy = rows;
            constraints1.fill  = GridBagConstraints.BOTH;

            JToggleButton b = new JToggleButton(Integer.toString(nr));

            if(! tr.isPlaceAvailable(nr))
                b.setEnabled(false);

            b.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    AbstractButton ab = (AbstractButton)actionEvent.getSource();

                    // create a snapshot of all listeners
                    List<ActionListener> snapshot = actionListeners;

                    for(ActionListener a : snapshot)
                    {
                        String msg = "";
                        if(! ab.getModel().isSelected())
                            msg = "D ";
                        else
                            msg = "A ";

                        msg += Integer.toString(travelID) + " " + Integer.toString(nr) +
                                " " + Integer.toString(placeCost);
                        a.actionPerformed(new ActionEvent(MeanOfTransportView.this,
                                ActionEvent.ACTION_PERFORMED, msg));
                        System.out.print(msg);
                    }

            }});

            add(b, constraints1);

            if(cols == placesLayoutInfo[floor][1] - 1)
            {
                cols = 0;
                ++rows;
            }
            else
                ++cols;

        }

	}

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

    public void showWindow()
    {
        setVisible(true);
    }

}
