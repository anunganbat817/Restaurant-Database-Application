package Database;

/**
 * Created by Serenanana on 17-11-14.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cs304";
    private Connection con;
    private final String USERNAME = "Desta";
    private final String PASSWARD = "11111111";

    public DatabaseConnection(){}

    public Connection getCon(){
        return this.con;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connect(USERNAME,PASSWARD)){
                con.setAutoCommit(false);
                System.out.println("successfully connected to the database");
                return true;
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean connect(String username, String passward) {
        try {
            con = DriverManager.getConnection(DATABASE_URL,username,passward);
            System.out.println("Logged in with username " + username + ". SUCCESS~~");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Attempt to login with username '" + username + "' failed.");
        }
        return true;
    }
}