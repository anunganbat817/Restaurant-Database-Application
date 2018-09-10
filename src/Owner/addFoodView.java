package Owner;

import javax.swing.*;
import java.awt.event.ActionListener;

public class addFoodView extends JPanel {
    private JTextField foodName;
    private JTextField foodPrice;
    private JButton addfood= new JButton("add Food");
    private JButton back = new JButton("Back");

    public addFoodView(){
        this.add(new JLabel("Food Name"));
        foodName = new JTextField(20);
        this.add(foodName);
        foodPrice = new JTextField(5);
        this.add(foodPrice);
        this.add(addfood);
        this.add(back);
    }

    public String getFoodName(){return foodName.getText();}

    public Integer getFoodPrice(){return Integer.parseInt(foodPrice.getText()); }

    public void addActionToaddButton(ActionListener listener){addfood.addActionListener(listener);}

    public void addActionTobackButton(ActionListener listener) {back.addActionListener(listener);}
}
