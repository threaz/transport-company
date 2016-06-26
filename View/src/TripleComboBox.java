import javax.swing.*;

/**
 * Created by threaz on 13/06/16.
 */
public class TripleComboBox {

    private JComboBox comboBox1;
    private JPanel    panel1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    public void addToFirst(String msg)
    {
        comboBox1.addItem(msg);
    }

    public void addToSecond(String msg)
    {
        comboBox2.addItem(msg);
    }

    public void addToThird(String msg)
    {
        comboBox3.addItem(msg);
    }

    public String getInfo()
    {
        return (String)comboBox1.getSelectedItem() + " " +
                (String)comboBox2.getSelectedItem() + " " +
                (String)comboBox3.getSelectedItem();
    }
}
