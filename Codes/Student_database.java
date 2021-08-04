/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student_database;
import java.sql.*;
import java.util.*;
/**
 *
 * @author karthik
 */
public class Student_database {
    private static final Connection con = ConnectionManager.getConnection();

    /**
     * @param args the command line arguments
     */
    public ArrayList<Object> getDatabase() {
        try {
            String query = "Select * from student_database_new";
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData Rss = rs.getMetaData();
            ArrayList<Object> List = new ArrayList();
            List.add(rs);
            List.add(Rss);
            return List;
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }
    public void add(long prn_data, String fullName, long contactNumber, String branch,String email) {
        try {
            String query = "insert into student_database_new values(?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, getRows() + 1);
            st.setString(2, fullName);
            st.setLong(3, prn_data);
            st.setLong(4, contactNumber);
            st.setString(5, branch);
            st.setString(6, email);
            int count = st.executeUpdate();
            System.out.println(count + "row(s) affected");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void remove(int id) throws SQLException
    {
        String query = "delete from student_database_new where SR = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);
        st.executeUpdate();
        query = "select * from student_database_new";
        Statement st_1 = con.createStatement();
        ResultSet rs = st_1.executeQuery(query);
        int serial;
        PreparedStatement st_2;
        while (rs.next()) {
            serial = rs.getInt(1);
            if (serial != 1) {
                query = "update student_database_new set SR = ? where SR = " + (rs.getInt(1)-1);
                st_2 = con.prepareStatement(query);
                st_2.setInt(1, serial - 1);
                st_2.executeUpdate();
            }
        }    
    }
    public void update(int id,String name,Long prn,Long contact,String branch,String email) throws SQLException{
        String query = "update student_database_new set StudentName = ?,PRN = ?, Contact = ?,Branch = ?,Email = ?" + "where SR = " + id;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,name);
        st.setLong(2,prn);
        st.setLong(3,contact);
        st.setString(4,branch);
        st.setString(5,email);
        st.executeUpdate();
        
    }
    
    public int getRows() {
        try {
            int count = 0;
            String query = "Select * from student_database_new";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count += 1;
            }
            return count;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public boolean alreadyExistsPrn(long prn_data) {
        try {
            ArrayList<Long> prnData = PrnDatabase();
            if (prnData.contains(prn_data)) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean alreadyExistsEmail(String email) {
        try {
            ArrayList<String> emailData = EmailDatabase();
            if (emailData.contains(email)) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean alreadyExistsContact(long contact_data) {
        try {
            ArrayList<Long> contactData = ContactDatabase();
            if (contactData.contains(contact_data)) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean alreadyExists(long prn, long contact,String email) {
        if (alreadyExistsPrn(prn) || alreadyExistsContact(contact)|| alreadyExistsEmail(email))
            return true;

        else return alreadyExistsPrn(prn) && alreadyExistsContact(contact) && alreadyExistsEmail(email);

    }
    public ArrayList<Long> PrnDatabase() throws SQLException {
        String query = "Select * from student_database_new";
        ArrayList<Long> prnData = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        String name;
        long prn;
        long contact;
        String branch;
        String email;
        while (rs.next()) {
            name = rs.getString(2);
            prn = rs.getLong(3);
            contact = rs.getLong(4);
            branch = rs.getString(5);
            email = rs.getString(6);
            prnData.add(new Student(name, prn, contact, branch,email).getPRN());
        }
        return prnData;
    }
    public ArrayList<Long> ContactDatabase() throws SQLException {
        String query = "Select * from student_database_new";
        ArrayList<Long> contactData = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        String name;
        long prn;
        long contact;
        String branch;
        String email;
        while (rs.next()) {
            name = rs.getString(2);
            prn = rs.getLong(3);
            contact = rs.getLong(4);
            branch = rs.getString(5);
            email = rs.getString(6);
            contactData.add(new Student(name, prn, contact, branch,email).getContact());
        }
        return contactData;
    }
    public ArrayList<String> EmailDatabase() throws SQLException {
        String query = "Select * from student_database_new";
        ArrayList<String> emailData = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        String name;
        long prn;
        long contact;
        String branch;
        String email;
        while (rs.next()) {
            name = rs.getString(2);
            prn = rs.getLong(3);
            contact = rs.getLong(4);
            branch = rs.getString(5);
            email = rs.getString(6);
            emailData.add(new Student(name, prn, contact, branch,email).getEmail());
        }
        return emailData;
    }
}
