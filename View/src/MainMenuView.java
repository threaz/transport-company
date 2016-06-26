import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by threaz on 13/06/16.
 */
public class MainMenuView extends StateHolder {

    private JPanel MenuView;
    private JButton buy_button;
    private JButton basket_button;
    private JButton editProfile_button;
    private JButton exit_button;
    private JFrame frame;

    private String customerEmail = null;

    public MainMenuView(String customerEmail, List<ActionListener> al) {

        actionListeners = al;
        this.customerEmail = customerEmail;

        frame = new JFrame("Main menu");
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

                TravelSearchingView travelSearchingView = new TravelSearchingView(MainMenuView.this.customerEmail,
                        actionListeners);

                travelSearchingView.showWindow();
            }
        });

        editProfile_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ProfileEditView pew = new ProfileEditView(MainMenuView.this.customerEmail, actionListeners);
            }
        });
        basket_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                BasketView basketView = new BasketView(MainMenuView.this.customerEmail, actionListeners);

                basketView.showWindow();
            }
        });
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }


}
