package com.example.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.sqlite.SQLiteConfig;

public class DataManager {
	private Connection connection;
	private String dbFileName;
	private boolean isOpened = false;
	//private final static String QUERY_SELECT_BY_NAME = "SELECT * FROM media WHERE FilePath=?;";
	//private final static String QUERY_SELECT_BY_NAME_HASHCODE = "SELECT * FROM media WHERE FilePath=? AND CheckSum=?;";
	//private final static String QUERY_SELECT_THUMBNAIL = "SELECT Thumbnail FROM media WHERE FilePath=?;";
	//public final static String DATABASE = "crystalcube.db";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataManager(String databaseFileName) {
		this.dbFileName = databaseFileName;
	}

	public boolean open() {
		try {
			SQLiteConfig config = new SQLiteConfig();
			//config.setReadOnly(true);
			this.connection = DriverManager.getConnection("jdbc:sqlite:/" + this.dbFileName, config.toProperties());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		isOpened = true;
		return true;
	}

	public boolean close() {
		if (this.isOpened == false) {
			return true;
		}

		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public HashMap<String, String> readMeta(String userId) throws SQLException {
		
		if(this.isOpened == false) { return null; }
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		String query = "SELECT MSG_SEQ, USER_ID, TITLE, DATA, REG_DT, UDT_DT FROM B_MESSAGE WHERE USER_ID=?;";
		PreparedStatement prep = this.connection.prepareStatement(query);
		prep.setString(1, userId);
		
		ResultSet row = prep.executeQuery();
		
		while(row.next()) { 
			String msgSeq = row.getString("MSG_SEQ");
			String userId2 = row.getString("USER_ID");
			String title = row.getString("TITLE");
			String data = row.getString("DATA");
			
			System.out.println( "msgSeq : " + msgSeq );
			System.out.println( "userId : " + userId2 );
			System.out.println( "title : " + title );
			System.out.println( "data : " + data );
			
			resultMap.put("msgSeq", msgSeq);
			resultMap.put("userId", userId);
			resultMap.put("title", title);
			resultMap.put("data", data);
			
		} 
		
		row.close(); 
		prep.close();
		
		return resultMap; 
	}
	
	public boolean writeMeta(String msgSeq, String userId, String title, String data) throws SQLException {
		if(this.isOpened == false) { return false; }
		boolean result = false;
		
		
		String query = "insert into B_MESSAGE( MSG_SEQ,USER_ID,TITLE,DATA,REG_DT,UDT_DT)values( ?,?,?,?,datetime('now'),datetime('now')); ";
		PreparedStatement prep = this.connection.prepareStatement(query);
		prep.setString(1, msgSeq);
		prep.setString(2, userId);
		prep.setString(3, title);
		prep.setString(4, data);
		
		prep.executeUpdate();
		
		
		prep.close();
		return result; 
	}
	


}
