package database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseManager;
import chatapp.User;


public class UserDAO {
	
	private String stringToMd5(String input) {
		try { 
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        }
	}
	
	public User login(String user, String pass) {
		String sql = "select * from Account where UserName = ? and Pass = ?";
		try {
			Connection con = DatabaseManager.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, stringToMd5(pass));
            ResultSet rs = pstmt.executeQuery();
            User emp = new User();
            while(rs.next()) {
                emp.setUserName(rs.getString("UserName"));
                emp.setPassword(rs.getString("Pass"));
                emp.setEmail(rs.getString("Email"));
                emp.setSdt(rs.getString("Phone"));
                System.out.println();
                return emp;
            }
            con.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}	
		return null;
	}

	public User getUser(String user) {
		String sql = "  select * from TaiKhoan where UserName = ? ";
		try {
			Connection con = DatabaseManager.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();
            User emp = new User();
            while(rs.next()) {
                emp.setUserName(rs.getString("UserName"));
                emp.setPassword(rs.getString("Password"));                
                emp.setEmail(rs.getString("Email"));
                emp.setSdt(rs.getString("SDT"));
            	return emp;
            }
            con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return null;
	}
	
	public List<User> searchByName(String search) {
		String sql = "SELECT * FROM User where UserName like '%"+search+"%'";

        List<User> list = new ArrayList<>();
        try {
            Connection con = DatabaseManager.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	User emp = new User();
                emp.setUserName(rs.getString("UserName"));
                emp.setPassword(rs.getString("Password"));
                emp.setEmail(rs.getString("Email"));
                emp.setSdt(rs.getString("SDT"));
                
                
                list.add(emp);
            }
            con.close();
            return list;
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }
	
	
	public boolean addUser(User e) throws Exception {
        String sql = "Insert into Account values(?,?,?,?)";
        try {
            Connection con = DatabaseManager.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, e.getUserName());
            pstmt.setString(2, stringToMd5(e.getPassword()));
            pstmt.setString(3, e.getSdt());
            pstmt.setString(4, e.getEmail());
            
            pstmt.executeUpdate();

            return true;
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return false;
    }
	
//	public boolean delHLV(String id) throws Exception{
//        String sql = "DELETE FROM HLV WHERE Id='"+id+"';";
//        try {
//            Connection con = DataBase.openConnection();
//            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.executeUpdate();
//            return pstmt.executeUpdate() > 0;
//        } catch (Exception ed) {
//            System.err.println(ed);
//        }
//        return false;
//    }
	public boolean updateUser(String userName, User e) throws Exception{
        String sql="Update Account set UserName=?,Password=?"
        		+ "SDT=?,Email=? where UserName='"+userName+"';";
        try {
            Connection con = DatabaseManager.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, e.getUserName());
            pstmt.setString(2, stringToMd5(e.getPassword()));
            pstmt.setString(3, e.getSdt());
            pstmt.setString(4, e.getEmail());
            
            System.out.println("Oke");
            return pstmt.executeUpdate() > 0;
        } catch (Exception eu) {
            System.err.println("Update error");
        }return false;
    }
	
	
}