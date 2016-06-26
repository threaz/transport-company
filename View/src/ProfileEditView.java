import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by threaz on 18/06/16.
 */
public class ProfileEditView {

    private JPanel profileEditView;
    private JTextField textField1;
    private JTextField textField2;
    private JButton cancelButton;
    private JButton saveChangesButton;
    private JPasswordField passwordField1;
    private JFrame frame;

    private List<ActionListener> actionListeners;

    private String customerEmail = null;


    public ProfileEditView(final String customerEmail, final List<ActionListener> actionListeners)
    {

        this.customerEmail   = customerEmail;
        this.actionListeners = actionListeners;

        frame = new JFrame("ProfileEditView");
        frame.setContentPane(profileEditView);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.pack();

        String msg = "[ProfileInfo] " + customerEmail + " ";

        // fill textBoxes with information
        for(ActionListener a : actionListeners)
            a.actionPerformed(new ActionEvent(ProfileEditView.this, ActionEvent.ACTION_PERFORMED, msg));

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(textField1.getText().isEmpty() || textField2.getText().isEmpty() ||
                        passwordField1.getText().isEmpty()) {
                    showMessage("There are fields which are still empty.");
                    return;
                }

                char[] passwd = passwordField1.getPassword();


                String msg = "[EditProfileInfo] " + customerEmail + " ";
                msg += "\"" + textField1.getText() + "\" ";
                msg += "\"" + textField2.getText() + "\" ";
                msg += "\"" + String.valueOf(passwd) + "\"";

                for(ActionListener a : actionListeners)
                    a.actionPerformed(new ActionEvent(ProfileEditView.this, ActionEvent.ACTION_PERFORMED, msg));
            }

        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                closeWindow();
            }
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void showWindow()
    {
        frame.setVisible(true);
    }
    public void closeWindow() { frame.dispose(); frame.setVisible(false); }

    public void profieInfoDelivered(Customer customer, String passwd) {

        textField1.setText(customer.getName());
        textField2.setText(customer.getAddress());
        passwordField1.setText(passwd);

        showWindow();
    }

    public void profieInfoEdited() {

        closeWindow();
    }
}
