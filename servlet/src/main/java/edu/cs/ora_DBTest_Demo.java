package edu.cs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ora_DBTest_Demo {

	public String message = "";
	
	public ora_DBTest_Demo (){
		
	}

	// public int testconnection_mysql (int hr_offset) { 
	public int testconnection_mysql (String filename, String filecontent) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        int flag = 0;
        String content= filecontent;
    	
		try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager
	    		          .getConnection("jdbc:mysql://<IPofEC2instnce>/<Databasename>?"+ "user=<username>&password=<Password>");
	    	  // String qry1a = "SELECT CURDATE() + " + hr_offset;
		      String qry1a= "insert into mytable values ('"+filename+"', '"+filecontent+"');";

	    	  System.out.println(qry1a);
	    	  preparedStatement = connect.prepareStatement(qry1a);
	    	  // "id, uid, create_time, token for id_management.id_logtime";
	    	  // Parameters start with 1
	    	  
	    	  preparedStatement.execute();

	            /* if (r1.next())
	            {
	              String nt = r1.getString(1); 
	              System.out.println(hr_offset + " hour(s) ahead of the system clock of mysql at 149.4.223.xxx is:" + nt);
	            }
	            r1.close(); */
	            preparedStatement.close();
	    	  
	    	} catch (Exception e) {
	    		try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  	} finally {
			      if (preparedStatement != null) {
				        try {
							preparedStatement.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  }	      

			      if (connect != null) {
			        try {
						connect.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			      }
		    }
		return flag;
	}

	
	public static void main(String[] args)
	{
		try
		{
			if (args.length != 1) {
				System.out.println("Usage: java -jar Ora_DBTest.jar <number_of_hr_offset>");
				System.out.println("Success returns errorlevel 0. Error return greater than zero.");
				System.exit(1);
			}

	        /* Print a copyright. */
	        System.out.println("Example for Oracle DB connection via Java");
	        System.out.println("Copyright: Bon Sy");
	        System.out.println("Free to use this at your own risk!");
	        
	    	ora_DBTest_Demo DBConnect_instance = new ora_DBTest_Demo();

//	    	if (DBConnect_instance.testconnection_mysql(Integer.parseInt(args[0])) == 0) {
//	            System.out.println("Successful Completion");
//	        } else {
//	            System.out.println("mysql DB connection fail");
//	        }
	    	
	       // System.out.println(DBConnect_instance.testconnection_mysql(Integer.parseInt(args[0])));
	       DBConnect_instance.testconnection_mysql("myfilename","test");
	       
		} 
		catch (Exception e){
			// probably error in input
			System.out.println("Hmmm... Looks like input error ....");
		}		
  }
		
}



