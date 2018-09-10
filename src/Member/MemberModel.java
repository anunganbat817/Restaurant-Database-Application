package Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MemberModel {

    Connection con;
    Integer reservid = 0;
    public MemberModel(Connection con){
        this.con = con;
    }

    public Object[] searchByResPhone(String resphone) throws SQLException {
        Object[] result = new Object[]{};
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Restaurant where Restaurant.resphone='"+resphone+"'");
        while (all.next()) {
            String address = all.getString("address");
            String name = all.getString("rname");
            Integer score = all.getInt("resscore");
            String phone = all.getString("resPhone");
            String ownerid = all.getString("ownerid");
            result = new Object[]{address,name,score,phone,ownerid};
        }
        sql.close();
        return result;
    }

    public void insertMember(String cphone, String email, String cname) {
        insertAsCustomerFirst(cphone,cname);
        System.out.println(" start insertMember ");
        PreparedStatement ps1;
        try {
            ps1 = con.prepareStatement("INSERT INTO Member (customerphone,points,email,cname) VALUES (?, ?, ?, ?)");
            ps1.setString(1, cphone);
            ps1.setInt(2,0);
            ps1.setString(3, email);
            ps1.setString(4, cname);

            ps1.executeUpdate();
            con.commit();
            System.out.println("insertMember successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("insertMemmer Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void insertAsCustomerFirst(String cphone, String cname){
        try{
            PreparedStatement p = con.prepareStatement("insert into customer(customerphone, cname) values (?,?)");
            p.setString(1,cphone);
            p.setString(2,cname);
            p.executeUpdate();
            con.commit();
            p.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteRevied(int reviewid) {
        System.out.println(" start delete review");
        PreparedStatement ps1;

        try {
            ps1 = con.prepareStatement("DELETE FROM Review WHERE reviewid = ?");
            ps1.setInt(1, reviewid);
            ps1.executeUpdate();
            con.commit();
            System.out.println("Review deleted successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("deletereview Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean checkIsMember(String cphone){
        try {
            Statement sql= con.createStatement();
            ResultSet all = sql.executeQuery("SELECT * FROM Member where customerphone='"+cphone+"'");
            return all.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPoint(String cphone){
        Statement sql = null;
        String result="0";
        try {
            sql = con.createStatement();
            ResultSet all = sql.executeQuery("SELECT * FROM Member WHERE customerphone='"+cphone+"'");
            all.next();
            result = all.getString("points");
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public Vector<Object> allRestaurant() throws SQLException {
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Restaurant");
        Vector<Object> data = getData(all);
        sql.close();
        return data;
    }
    public Vector<Object> getData(ResultSet all)throws SQLException{
        Vector<Object> data = new Vector<Object>();
        ResultSetMetaData md = all.getMetaData();
        int columns = md.getColumnCount();
        while (all.next()) {
            Vector<Object> row = new Vector<Object>(columns);
            for (int i = 1; i <= columns; i++)
            {
                row.addElement(all.getObject(i));
            }
            data.addElement(row);
        }
        return data;
    }
    public void updateMemberBasicInfo(String cphone, String email) {
        System.out.println(" start update member basic info");
        PreparedStatement ps1;

        try {
            ps1 = con.prepareStatement("UPDATE Member SET email = ? WHERE customerphone = ?");
            ps1.setString(1, email);
            ps1.setString(2, cphone);
            ps1.executeUpdate();
            con.commit();
            System.out.println("member info updated successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("updateMember Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public ArrayList<Object[]> searchReview(String resphone) throws SQLException{
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Review where Review.resphone='"+resphone+"'");
        while (all.next()) {
            String reviewid = all.getString("reviewid");
            String content = all.getString("content");
            String reviewscore = all.getString("reviewscore");
            Object[] temp = new Object[]{reviewid, content, reviewscore};
            result.add(temp);
        }
        sql.close();
        return result;
    }

    public int getPrice(){
        Integer result = 0;
        Statement sql = null;
        try {
            sql = con.createStatement();
            ResultSet all = sql.executeQuery("SELECT * FROM Food,Contain where Food.foodid=Contain.foodid AND Contain.reservationid="+reservid);
            while (all.next()) {
                int price =  all.getInt("price");
                result += price;
            }
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateMemberPoint(String cphone, int pts) {
        System.out.println(" start update member ");
        PreparedStatement ps1;

        try {
            Statement sql = con.createStatement();
            ResultSet all = sql.executeQuery("SELECT * FROM Member WHERE customerphone='"+cphone+"'");
            all.next();
            System.out.println(all.getInt("points"));
            int currentPts = all.getInt("points");
            sql.close();
            ps1 = con.prepareStatement("UPDATE Member SET points = ? WHERE customerphone = ?");
            int totalPts = currentPts + pts;
            ps1.setInt(1, totalPts);
            ps1.setString(2, cphone);
            ps1.executeUpdate();
            con.commit();
            System.out.println("member points updated successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("updateMember Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void insertReservation(ArrayList<Integer> foodid, String date, int numcustomer, String cphone, String rphone, int ownerid, String cname) {
        System.out.println(" start insertReservation for customer ");
        try {
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO Reservation (reservationdate,numberofcustomer,customerphone,resphone,cusname,ownerid) VALUES (?,?,?,?,?,?)");
            ps1.setString(1, date);
            ps1.setInt(2,numcustomer);
            ps1.setString(3, cphone);
            ps1.setString(4, rphone);
            ps1.setString(5,cname);
            ps1.setInt(6, ownerid);
            System.out.println("inerted");

            ps1.executeUpdate();
            con.commit();
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("insertReservation Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
        try {
            Statement sql = con.createStatement();
            ResultSet s = sql.executeQuery("SELECT LAST_INSERT_ID() AS lastid");
            if(s.next())
                reservid = Integer.parseInt(s.getObject(1).toString());
            System.out.println("insertReservation successfully");
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertReservationInContain(foodid,reservid);
        //update Contain table to insert its foodid and reservationid
    }

    public void insertReservationInContain(ArrayList<Integer> foodids,int reservationid){
        for(int i=0;i<foodids.size();i++){
            System.out.println(" start insert a food in a contain ");
            PreparedStatement ps1;
            try {
                ps1 = con.prepareStatement("INSERT INTO Contain(foodid,reservationid) VALUES (?, ?)");
                ps1.setInt(1, foodids.get(i));
                ps1.setInt(2,reservationid);
                ps1.executeUpdate();
                con.commit();
                System.out.println("insertContainInstance successfully");
                ps1.close();
            }
            catch (SQLException ex) {
                System.out.println("insertContainInstance Could not get result: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Object[]> searchFood(String resphone) throws SQLException{
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Food where Food.resphone='"+resphone+"'");
        while (all.next()) {
            String price = all.getString("price");
            String foodid = all.getString("foodid");
            String fname = all.getString("fname");
            Object[] temp = new Object[]{ foodid, fname,price};
            result.add(temp);
        }
        sql.close();
        return result;
    }

    public void insertReview(String resphone, String cphone, String content,float reviewscore) {
        System.out.println(" start insertReview ");
        PreparedStatement ps1;

        try {
            ps1 = con.prepareStatement("INSERT INTO Review (resphone,customerphone,content,reviewscore) VALUES (?, ?, ?, ?)");
            ps1.setString(1, resphone);
            ps1.setString(2, cphone);
            ps1.setString(3,content);
            ps1.setFloat(4, reviewscore);
            ps1.executeUpdate();
            con.commit();
            System.out.println("insertReview successfully");
            ps1.close();
        }

        catch (SQLException ex) {
            System.out.println("insertReview Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
