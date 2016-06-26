import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by threaz on 13/06/16.
 */
public class RegisterCustomerView extends StateHolder {

    private JPanel RegisterCustomerView;
    private JTextField name_field;
    private JTextField address_field;
    private JTextField email_field;
    private JButton register_button;
    private JButton cancel_button;
    private JLabel name_label;
    private JLabel address_label;
    private JLabel email_label;
    private JLabel passwd_label;
    private JPasswordField passwd_field;
    private JFrame frame;

    public RegisterCustomerView() {
        frame = new JFrame("Register to our service");
        frame.setContentPane(RegisterCustomerView);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.pack();
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(name_field.getText().isEmpty() || address_field.getText().isEmpty() ||
                        email_field.getText().isEmpty() || passwd_field.getText().isEmpty())
                    {
                        showMessage("There are fields which are still empty.");
                        return;
                    }

                String msg = "[Register] ";

                char[] passwd = passwd_field.getPassword();

                // TODO: make this more secure
                // TODO: set each byte of password to zero
                msg +=  "\"" + name_field.getText() + "\" "
                        + "\"" + address_field.getText() + "\" "
                        + "\"" + email_field.getText() + "\" "
                        + "\"" + String.valueOf(passwd) + "\" ";

                for(ActionListener a : actionListeners)
                    a.actionPerformed(new ActionEvent(RegisterCustomerView.this, ActionEvent.ACTION_PERFORMED, msg));
            }
        });
        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                frame.setVisible(false);
            }
        });
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }

    public void hideWindow() {
        frame.dispose();
        frame.setVisible(false);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public void userRegisteredIn(ViewController.RegisterState rs) {

        switch(rs)
        {
            case CUSTOMER_EXISTS:
                showMessage("User already exists!");
                break;
            case OK_REGISTER:
                showMessage("Registration succesfull!");
                hideWindow();
                break;
        }
    }

}
