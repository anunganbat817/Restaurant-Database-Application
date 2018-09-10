package Database;

import Customer.CustomerController;
import Customer.CustomerModel;
import Customer.CustomerView;
import Member.MemberController;
import Member.MemberModel;
import Member.MemberView;
import Owner.OwnerController;
import Owner.OwnerModel;
import Owner.OwnerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class MainController {
    public static DatabaseConnection db;
    public static Login frame;
    public static void main(String[] args){
        db = new DatabaseConnection();
        System.out.println("aaa");
        if(db.connect()){
            frame = new Login();
            frame.setVisible(true);
            loadFrame();
        }
    }

    private static void loadFrame() {
        frame.addActionToOwnerLoginButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadOwnerView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame.addActionToCustomerButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadCustomerView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame.addActionToMemberLoginRegister(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadMembrView();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    db.getCon().close();
                    System.exit(0);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private static void loadMembrView() throws SQLException{
        MemberModel model = new MemberModel(db.getCon());
        MemberView view = new MemberView();
        String email = frame.getMemberemail();
        String mphone = frame.getMemberPhone();
        String mname = frame.getMemberName();
        if(!model.checkIsMember(mphone)) {
            System.out.println("not a member");
            model.insertMember(mphone,email,mname);
        }
        String p = model.getPoint(mphone);
        view.getAllRestaurants().setPoints(p);
        Vector<Object> objs = model.allRestaurant();
        view.getAllRestaurants().setTableView(objs);
        frame.loadView(view);
        MemberController memberController = new MemberController(view,model,mname,mphone);
    }

    private static void loadCustomerView() throws SQLException{
        CustomerView customerView = new CustomerView();
        CustomerModel customerModel = new CustomerModel(db.getCon());
        Vector<Object> objs = customerModel.allRestaurant();
        customerView.getAllRestaurants().setTableView(objs);
        frame.loadView(customerView);
        CustomerController customerController = new CustomerController(customerView, customerModel);
    }

    private static void loadOwnerView() throws SQLException{
        OwnerModel ownerModel = new OwnerModel(db.getCon());
        OwnerView ownerView = new OwnerView();
        String ownerid = frame.getOwnerID();
        Vector<Object> objs = ownerModel.searchResByOwnerid(Integer.parseInt(ownerid));
        ownerView.getLor().setTableView(objs);
        String s = ownerModel.findCustomerByReview(Integer.parseInt(ownerid));
        ownerView.getLor().setAllcustomers(s);
        frame.loadView(ownerView);
        OwnerController ownerController = new OwnerController(ownerModel,ownerView);
    }
}
