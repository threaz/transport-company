import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by threaz on 13/06/16.
 */
public class MyMenuView extends StateHolder {

    private JPanel MenuView;
    private JButton buy_button;
    private JButton basket_button;
    private JButton editProfile_button;
    private JButton exit_button;
    private JFrame frame;

    private String customerEmail = null;

    public MyMenuView(String customerEmail, List<ActionListener> al) {

        actionListeners = al;
        this.customerEmail = customerEmail;

        frame = new JFrame("MyMenuView");
        frame.setContentPane(MenuView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.pack();
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                frame.dispose();
                frame.setVisible(false);
                System.exit(0);
            }
        });

        buy_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                TravelSearchingView travelSearchingView = new TravelSearchingView(MyMenuView.this.customerEmail,
                        actionListeners);

                travelSearchingView.showWindow();
            }
        });

        editProfile_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ProfileEditView pew = new ProfileEditView(MyMenuView.this.customerEmail, actionListeners);
            }
        });
        basket_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                BasketView basketView = new BasketView(MyMenuView.this.customerEmail, actionListeners);

                basketView.showWindow();
            }
        });
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }


}
