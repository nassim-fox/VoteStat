

    package sql_connection ; 



		import java.sql.Connection ; 
		import java.sql.DriverManager ; 
		import java.sql.Statement ; 
		import java.sql.ResultSet ; 
		import java.sql.SQLException  ; 
		import java.util.* ;
		import java.text.DateFormat ; 
		import java.text.SimpleDateFormat ; 


		public class VoteEditSql extends VoteLocalhostUrl
		{
      
		  private int id ; 
		  private String nom , prenom , date ; 
		  private List<Object[]> ls ; 
		  private Object[][] inf ;
		  private int i = 0 ; 
		  
			private final static String JDBC_DRIVER =	"com.mysql.jdbc.Driver" ; 
		
			public VoteEditSql(String db,String nom , String prenom ,String email,int id)
			{
					 
				Connection c = null ; 
				Statement s = null ; 

				try
				{


					Class.forName("com.mysql.jdbc.Driver") ; 

					c = DriverManager.getConnection(DB_URL+db,USER,PASS) ; 

					System.out.println("connection") ; 

					s = c.createStatement() ; 

					
					String sql = "UPDATE `vote`.`etud` SET `name` = '"+nom+"' , `prenom` ='"+prenom+"' , `email` ='"+email+"' WHERE `etud`.`ID` = '"+id+"';" ; 
					
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