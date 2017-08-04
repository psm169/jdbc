package com.seokmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcMain {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {


		    Connection conn = connectToDatabaseOrDie();
		    
		    List<Member2> members = new ArrayList<>();
		    try 
		    {
		      Statement st = conn.createStatement();
		      ResultSet rs = st.executeQuery("SELECT id, name, details FROM member2");
		      while ( rs.next() )
		      {
		    	Member2 member = new Member2();
		        member.setId(rs.getLong("id"));
		        member.setName(rs.getString ("name"));
		        member.setDetails(rs.getString ("details"));
		        members.add(member);
		      }
		      rs.close();
		      st.close();
		      
		      
		      for(Member2 member: members){
		    	  System.out.println(member.getId() + " " + member.getName());
		      }
		    }
		    catch (SQLException se) {
		      System.err.println("Threw a SQLException creating the list of blogs.");
		      System.err.println(se.getMessage());
		    }

	}
	
	
  private static Connection connectToDatabaseOrDie()
  {
    Connection conn = null;
    try
    {
      Class.forName("org.postgresql.Driver");
      String  url = "jdbc:postgresql://localhost:5432/postgres";
      conn = DriverManager.getConnection(url,"postgres", "1q2w3e");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      System.exit(2);
    }
    return conn;
  }

}
