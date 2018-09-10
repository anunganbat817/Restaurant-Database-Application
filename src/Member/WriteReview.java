package Member;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionListener;

public class WriteReview extends JPanel {
    private JTextArea content = new JTextArea(5,50);
    private JButton submit = new JButton("Submmit Review");
    private JTextField score = new JTextField(3);
    private JButton back = new JButton("Go Back");

    public WriteReview(){
        this.add(new JLabel("Leave comment here: "));
        this.add(content);
        this.add(new JLabel("Leave a score, max 5: "));
        this.add(score);
        this.add(submit);
        this.add(back);
    }

    public String getContent(){return content.getText();}
    public String getscore(){return score.getText();}
    public void addListneronBackButton(ActionListener actionListener){back.addActionListener(actionListener);}

    public void addsubmitListener(ActionListener listener) {
        submit.addActionListener(listener);
    }
}
