

    package sql_connection ; 



		import java.sql.Connection ; 
		import java.sql.DriverManager ; 
		import java.sql.Statement ; 
		import java.sql.ResultSet ; 
		import java.sql.SQLException  ; 
		import java.util.* ;
		import java.text.DateFormat ; 
		import java.text.SimpleDateFormat ; 


		public class VoteAddSql extends VoteLocalhostUrl
		{

			private final static String JDBC_DRIVER =	"com.mysql.jdbc.Driver" ; 
			/*private  static String DB_URL ; 

			private final static String USER = "root" ; 
			private final static String PASS = "" ; 
      */
      private static boolean b = false ; 
      
			public VoteAddSql(String db , String nom , String prenom , String email )
			{
      //  DB_URL = new VoteLocalhostUrl().toString() ; 
			  System.out.println(DB_URL+"                ajout      ") ; 
			  
				Connection c = null ; 
				Statement s = null ; 

				try
				{


					Class.forName("com.mysql.jdbc.Driver") ; 

					c = DriverManager.getConnection(DB_URL+db,USER,PASS) ; 

					System.out.println("connection") ; 

					s = c.createStatement() ; 

					DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ") ; 
					Date dt = new Date() ; 
					String date = dateformat.format(dt) ; 
          
          String sql = "SELECT * FROM  `"+db+"`.`etud` ; " ; 
          
          ResultSet rs = s.executeQuery(sql) ; 
          
          
          while(rs.next())
          {
            if(rs.getString("name").equals(nom) && rs.getString("prenom").equals(prenom))
            {
              javax.swing.JOptionPane.showMessageDialog(null,"voteur existe déjà ","vérifier vote",javax.swing.JOptionPane.INFORMATION_MESSAGE) ; 
              b = true ; 
            } 
            else if(rs.getString("email").equals(email))
            {
              javax.swing.JOptionPane.showMessageDialog(null,"cette adresse est déjà utilisé","vérifier adresse",javax.swing.JOptionPane.INFORMATION_MESSAGE) ; 
              b = true ;  
            }
          }
					
		  		sql = "INSERT INTO `"+db+"`.`etud`(`name`,`prenom`,`email`,`date`) VALUES('"+nom+"','"+prenom+"','"+email+"','"+date+"') ;" ; 
          
          
          if(!b)   
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
			public static boolean getCheckVote()
			{
        return b ; 
			}
		}