import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by threaz on 12/06/16.
 */

public class LoginView extends StateHolder {

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel email_field;
    private JLabel passwd_field;
    private JButton register_button;
    private JButton ok_button;
    private JButton closeButton;
    private JPanel LoginWindow;

    private JFrame frame;

    private String customerEmail = null;

    public LoginView() {

        frame = new JFrame("Log in to your account");
        frame.setContentPane(LoginWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setMinimumSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ok_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(textField1.getText().isEmpty() || passwordField1.getText().isEmpty())
                    {
                        showMessage("There are fields which are still empty.");
                        return;
                    }

                String msg = "[Login] ";

                // TODO: make this more secure
                // TODO: set each byte of password to zero
                char[] passwd = passwordField1.getPassword();

                customerEmail = textField1.getText();

                msg += "\"" + customerEmail + "\" " + "\"" + String.valueOf(passwd) + "\"";

                for(ActionListener a : actionListeners)
                    a.actionPerformed(new ActionEvent(LoginView.this, ActionEvent.ACTION_PERFORMED, msg));
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                frame.setVisible(false);
                System.exit(0);
            }
        });
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegisterCustomerView  registerView   = new RegisterCustomerView();
                registerView.showWindow();

                for(ActionListener a : actionListeners)
                    registerView.subscribe(a);
            }
        });
    }

    public void showMainMenuWindow()
    {
        MainMenuView mainMenuWindow = new MainMenuView(customerEmail, actionListeners);

        frame.dispose();
        frame.setVisible(false);

        mainMenuWindow.showWindow();
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public void userLoggedIn(ViewController.LoginState st) {

        switch(st)
        {
            case WRONG_PASSWD:
                showMessage("Wrong password!");
                break;

            case NOT_IN_DATABASE:
                showMessage("User not in database!");
                break;

            case OK_LOGIN:
                showMainMenuWindow();
                break;
        }
    }
}
