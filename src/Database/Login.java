package Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panel,ownerLoginPanel, memberPanel;
    private JButton ownerLogin;
    private JButton customer = new JButton("Continue as Customer");;
    private JButton memberLoginRegister = new JButton("Login/Register as Member");
    private JTextField ownerID = new JTextField(10);
    private JTextField memberName = new JTextField(10);
    private JTextField memberPhone = new JTextField(12);
    private JTextField memberEmail = new JTextField(20);

    public Login(){
        panel = new JPanel();
        ownerLoginPanel = new JPanel();
        memberPanel = new JPanel();
        ownerLogin = new JButton("Log in as Owner");
        ownerLoginPanel.add(new JLabel("Owner ID"));
        ownerLoginPanel.add(ownerID);
        ownerLoginPanel.add(ownerLogin);
        panel.add(ownerLoginPanel);
        panel.add(customer);
        memberPanel.add(new JLabel("Name: "));
        memberPanel.add(memberName);
        memberPanel.add(new JLabel("Phone: "));
        memberPanel.add(memberPhone);
        memberPanel.add(new JLabel("email: "));
        memberPanel.add(memberEmail);
        memberPanel.add(memberLoginRegister);
        panel.add(memberPanel);
        this.setTitle("Online Food Ordering and Reservation Service");
        this.setMinimumSize(new Dimension(800,500));
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public String getOwnerID(){return ownerID.getText();}

    public String getMemberName(){return memberName.getText();}
    public String getMemberPhone(){return memberPhone.getText();}
    public String getMemberemail(){return memberEmail.getText();}

    public void addActionToOwnerLoginButton(ActionListener listener){
        ownerLogin.addActionListener(listener);
    }

    public void addActionToCustomerButton(ActionListener listener){
        customer.addActionListener(listener);
    }

    public void addActionToMemberLoginRegister(ActionListener listener){
        memberLoginRegister.addActionListener(listener);
    }

    public void loadView(JPanel p) {
        if (panel != null)
            this.remove(panel);
        panel = p;
        this.getContentPane().add(panel);
        this.setVisible(true);
    }
}
