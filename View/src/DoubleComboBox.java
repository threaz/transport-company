import javax.swing.*;

/**
 * Created by threaz on 13/06/16.
 */
public class DoubleComboBox {
    private JPanel combo;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public void addToFirst(String msg)
    {
        comboBox1.addItem(msg);
    }

    public void addToSecond(String msg)
    {
        comboBox2.addItem(msg);
    }

    public String getInfo()
    {
        return (String)comboBox1.getSelectedItem() + " " +
                (String)comboBox2.getSelectedItem();
    }
}
