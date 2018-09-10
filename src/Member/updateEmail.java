package Member;

import javax.swing.*;
import java.awt.event.ActionListener;

public class updateEmail extends JPanel {
    private JTextField newEmail = new JTextField(20);
    private JButton update = new JButton("update");
    private JButton back =new JButton("Go back");
    public updateEmail(){
        this.add(new JLabel("New Email: "));
        this.add(newEmail);
        this.add(update);
        this.add(back);
    }

    public String getNewEmail(){
        return newEmail.getText();
    }

    public void addListinerOnBackButton (ActionListener l){back.addActionListener(l);}

    public void addListineronUpdateButton (ActionListener listener){update.addActionListener(listener);}
}
