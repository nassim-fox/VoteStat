

    package sql_connection ; 


    
    import javax.swing.ImageIcon ;
    
		import java.sql.Connection ; 
		import java.sql.DriverManager ; 
		import java.sql.Statement ; 
		import java.sql.ResultSet ; 
		import java.sql.SQLException  ; 
		import java.util.* ;
		import java.text.DateFormat ; 
		import java.text.SimpleDateFormat ; 


		public class VoteCreateDb extends VoteLocalhostUrl
		{
      
		 
		  private final static String JDBC_DRIVER =	"com.mysql.jdbc.Driver" ; 
		/*	private static String DB_URL  ; 

			private final static String USER = "root" ; 
			private final static String PASS = "" ; 
*/
			public VoteCreateDb(String db)
			{
			//DB_URL = new VoteLocalhostUrl().toString() ; 
			  
				Connection c = null ; 
				Statement s = null ; 

				try
				{


					Class.forName("com.mysql.jdbc.Driver") ; 

					c = DriverManager.getConnection(DB_URL,USER,PASS) ; 

					System.out.println("connection") ; 

					s = c.createStatement() ; 

					String sql ="SHOW databases like '"+db+"' ; " ; 
					
					ResultSet rs = s.executeQuery(sql) ;
					
          int r = 0 ; 
          
					if(rs.last())
					{
            r = rs.getRow() ; 
					}
          
          if(r==0)
          {
            sql = " CREATE DATABASE "+db+" ; " ; 
            s.executeUpdate(sql) ; 
            System.out.println("db crée ") ; 
            sql = " Create TABLE "+db+".etud ( ID int NOT NULL AUTO_INCREMENT PRIMARY KEY "+
                   ", name varchar(20) NOT NULL , prenom varchar(20) NOT NULL , email varchar(50) NOT NULL , date varchar(50) NOT NULL  ) ;  " ; 
            s.executeUpdate(sql) ; 
            System.out.println("table crée ") ;
                     
          }
				 
					
					
				}
				catch(SQLException e ) 
				{
					e.printStackTrace() ;
					javax.swing.JOptionPane.showMessageDialog(null,"SQLException , aucun serveur n'a été trouvé \n  le serveur actuelle "+DB_URL+" est soit déconnecté ou n'éxiste pas \n *essayer de changer le serveur ou de le reconnecter","erreur",javax.swing.JOptionPane.ERROR_MESSAGE) ; 
					System.exit(0) ; 
				}
				catch(Exception e)
				{
					e.printStackTrace() ;
					javax.swing.JOptionPane.showMessageDialog(null,"Exception , erreur création de la bdd ","erreur",javax.swing.JOptionPane.ERROR_MESSAGE) ; 
					System.exit(0) ;
					 
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