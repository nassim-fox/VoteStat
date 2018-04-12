

    package sql_connection ; 



		import java.sql.Connection ; 
		import java.sql.DriverManager ; 
		import java.sql.Statement ; 
		import java.sql.ResultSet ; 
		import java.sql.SQLException  ; 
		import java.util.* ;
		import java.text.DateFormat ; 
		import java.text.SimpleDateFormat ; 


		public class VoteDeleteEditSql extends VoteLocalhostUrl 
		{
      
		  private int id ; 
		  private String nom , prenom , date ; 
		  private List<Object[]> ls ; 
		  private Object[][] inf ;
		  private int i = 0 ; 
		  
			private final static String JDBC_DRIVER =	"com.mysql.jdbc.Driver" ; 
		 // private static String DB_URL ; 
		
			//private final static String USER ; 
			//private final static String PASS ; 

			public VoteDeleteEditSql(String db,int id)
			{
			  //DB_URL = VoteLocalhostUrl.toString() ; 
			  //USER =  VoteLocalhostUrl.getUser() ; 
			  //PASS =  VoteLocalhostUrl.getPass() ; 
			  
				Connection c = null ; 
				Statement s = null ; 

				try
				{


					Class.forName("com.mysql.jdbc.Driver") ; 

					c = DriverManager.getConnection(DB_URL+db,USER,PASS) ; 

					System.out.println("connection") ; 

					s = c.createStatement() ; 

					
					String sql = "DELETE FROM `vote`.`etud` WHERE `etud`.`ID` = "+id+";" ; 
					
					System.out.println(sql) ; 

					s.executeUpdate(sql) ;

					
					
					
				}
				catch(SQLException e ) 
				{
					e.printStackTrace() ;
				}
				catch(Exception e)
				{
					e.printStackTrace() ; 
				}
				finally
				{
					try
					{
						if(c!=null)
							c.close() ; 
					}
					catch(SQLException e)
					{
						e.printStackTrace() ; 
					}
				}

			}
			
		}