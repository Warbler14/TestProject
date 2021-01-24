package com.example.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseUtil {
	
	static ConnectonArgument argument;
    public static final String SELECT_ONE = "selectOne";
    public static final String SELECT_LIST = "selectList";
    public static final String INSERT = "update";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    
	public DatabaseUtil(final String type, final String host, final String port, final String SID
						, final String user, final String pwd){
		argument = new ConnectonArgument(type, host, port, SID, user, pwd);
	}
	
	public Object executeQuery(final String type, String query, List<Object> argList){
		
		Object result = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {
			System.out.println("!sql : " + query);
			con = DriverManager.getConnection(argument.getUrl(), argument.getUser(), argument.getPwd());
			//===================================================
			pstmt = con.prepareStatement(query);
			
			for(int idx0 = 0, end0 = argList.size() ; idx0<end0 ; idx0++ ){
				Object param = argList.get(idx0);
				
				if( param instanceof String ){
					pstmt.setString(idx0+1, String.valueOf(param));
				}else if( param instanceof Integer){
					pstmt.setInt(idx0+1, (int)(param));
				}else if( param instanceof Long){
					pstmt.setLong(idx0+1, (long)(param));
				}
				
			}//end for
			
			if(SELECT_LIST.equals(type)){
				rs = pstmt.executeQuery();
				result = getResultSetToArrayMap(rs);				
			}else{
				result = pstmt.executeUpdate();
			}
			//===================================================

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(query);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(query);
		}
			finally {
		
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	public class ConnectonArgument{
		private String url = "jdbc:oracle:thin:@192.168.25.22:1521:orcl";
		private String host = null;
		private String port = null;
		private String user = null;
		private String pwd = null;
	    
		public ConnectonArgument(final String type, final String host, final String port, final String SID
								, final String user, final String pwd){
			this.host = host;
			this.port = port;
			this.user = user;
			this.pwd = pwd;
			
			if(type != null && !type.isEmpty() ){
				switch(type){
				case "oracle" : 
					this.url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + SID;
					break;
				case "mysql" :
					this.url = "jdbc:mysql://" + host + ":" + port + "/" + SID;
					break;
				default : break;
				}
			}
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		
	}
	
	private ArrayList<HashMap<String, String>> getResultSetToArrayMap(ResultSet rs) throws Exception{
		ResultSetMetaData metaData = rs.getMetaData();
		int sizeOfColumn = metaData.getColumnCount();
		
		ArrayList<HashMap<String, String>> resultArray 
    		= new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> dataMap = null;
		String column = null;
		
		while(rs.next()){
			dataMap = new HashMap<String, String>();
			
			for (int idxColumn = 0; idxColumn < sizeOfColumn; idxColumn++) {
				column = metaData.getColumnName(idxColumn + 1);
				dataMap.put(column, rs.getString(column));
				
			}//end for
			
			resultArray.add(dataMap);
		}//end while
		
		return resultArray;
	}
}
