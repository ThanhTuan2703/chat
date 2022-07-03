package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
//	private static final String Q_UPDATE_CLIENT = "UPDATE Person SET Name = ? WHERE Id = 2 ";
//	private static final String Q_SAVE_MESSAGE = "INSERT INTO Message(DirectId,ReceiverId,MessageText) VALUES (?, ?, ?)";

	public static Connection openConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://DESKTOP-QHMC6QJ:1433;" + "databaseName=ChatApp;User=sa;Password=sa";
		Connection con = DriverManager.getConnection(connectionUrl);
		//System.out.println("Connect successfully");
		return con;
	}

//	public static void updateClientName(String name) {
//		Connection connection;
//		try {
//			connection = openConnection();
//			PreparedStatement pst = connection.prepareStatement(Q_UPDATE_CLIENT,
//					PreparedStatement.RETURN_GENERATED_KEYS);
//			pst.setString(1, name);
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void saveMessage(String sender, String message) {
//		Connection connection;
//		try {
//			connection = openConnection();
//			PreparedStatement pst = connection.prepareStatement(Q_SAVE_MESSAGE,
//					PreparedStatement.RETURN_GENERATED_KEYS);
//			if (sender.equals("Manager")) {
//				pst.setInt(1, 1);
//				pst.setInt(2, 2);
//			} else {
//				pst.setInt(1, 2);
//				pst.setInt(2, 1);
//			}
//			pst.setString(3, message);
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}