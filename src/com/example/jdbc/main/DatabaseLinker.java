package com.example.jdbc.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.example.util.DatabaseUtil;

public class DatabaseLinker {
	
	public static void main(String[] args) {
		DatabaseLinker main = new DatabaseLinker();
		DatabaseUtil util = new DatabaseUtil("oracle","192.168.25.22","1521","orcl","scott","tiger");
		
		
		
		//=======================================================
		//select test
		//=======================================================
		main.selectTestData(util);
		
		//=======================================================
		//insert test
		//=======================================================
		main.insertTestData(util);
		System.out.println("-----");
		main.selectTestData(util);
		
		//=======================================================
		//update test
		//=======================================================
		main.updateTestData(util);
		System.out.println("-----");
		main.selectTestData(util);

		//=======================================================
		//delete test
		//=======================================================
		main.deleteTestData(util);
		System.out.println("-----");
		main.selectTestData(util);
		
				
		
	}
	
	private void selectTestData(DatabaseUtil util){
		String query = "SELECT * FROM EMP WHERE JOB=? AND DEPTNO=?";
		
		List<Object> arguments = new ArrayList<Object>(); 
		arguments.add("SALESMAN");
		arguments.add(30);
		
		ArrayList<HashMap<String, String>> resultArray 
			= (ArrayList<HashMap<String, String>>)util.executeQuery(DatabaseUtil.SELECT_LIST, query, arguments);
		
		for (int i = 0, end = resultArray.size(); i < end; i++) {
			HashMap<String, String> map = resultArray.get(i);
			Iterator<String> itr = map.keySet().iterator();
			while(itr.hasNext()){
				String key = itr.next();
				System.out.print( "[key:" + key + ", value:" + map.get(key) + "]");
			}
			System.out.print("\n");
					
			//System.out.println( map.toString() );
		}
	}
	
	private void insertTestData(DatabaseUtil util){
		String query = "INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)"
						+ "VALUES( ?, ?, ?, ?, sysdate, ?, ?, ?)";
		
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(7935);
		arguments.add("TESTER");
		arguments.add("SALESMAN");
		arguments.add(7698);
		arguments.add(1000);
		arguments.add(0);
		arguments.add(30);
		
		int result = (Integer)util.executeQuery(DatabaseUtil.UPDATE, query, arguments);
		
		System.out.println( result );
	}
	
	private void updateTestData(DatabaseUtil util){
		String query = "UPDATE EMP SET SAL=? WHERE ENAME=? AND EMPNO=?";
		
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(1700);
		arguments.add("TESTER");
		arguments.add(7935);
		
		int result = (Integer)util.executeQuery(DatabaseUtil.UPDATE, query, arguments);
		
		System.out.println( result );
	}
	
	private void deleteTestData(DatabaseUtil util){
		String query = "DELETE FROM EMP WHERE ENAME=? AND EMPNO=?";
		
		List<Object> arguments = new ArrayList<Object>();
		arguments.add("TESTER");
		arguments.add(7935);
		
		int result = (Integer)util.executeQuery(DatabaseUtil.UPDATE, query, arguments);
		
		System.out.println( result );
	}
}
