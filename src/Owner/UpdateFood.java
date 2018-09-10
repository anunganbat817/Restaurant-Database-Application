package Owner;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UpdateFood extends JPanel {
    private JButton update = new JButton("Update");
    private JTextField foodID = new JTextField(5);
    private JTextField foodName = new JTextField(10);
    private JTextField foodPrice = new JTextField(5);
    private JButton back = new JButton("Back");

    public UpdateFood(){
        this.add(new JLabel("Food ID: "));
        this.add(foodID);
        this.add(new JLabel("New Name: "));
        this.add(foodName);
        this.add(new JLabel("New Price: "));
        this.add(foodPrice);
        this.add(update);
        this.add(back);
    }

    public String getfoodID(){return foodID.getText();}

    public String  getFoodName() {
        return foodName.getText();
    }

    public String getfoodPrice(){return foodPrice.getText();}
    public void addListenertoBack (ActionListener actionListener){back.addActionListener(actionListener);}
    public void addActionListnertoUpdateButton (ActionListener actionListener){update.addActionListener(actionListener);}
}
