

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


		public class VoteGetSql extends VoteLocalhostUrl
		{
      
		  private int id ; 
		  private String nom , prenom , date ; 
		  private List<Object[]> ls ; 
		  private Object[][] inf ;
		  private int i = 0 ; 
		  
			private final static String JDBC_DRIVER =	"com.mysql.jdbc.Driver" ; 
		 
      private static String sql = "SELECT * FROM `Vote`.`etud` ;" ; 
      
			public VoteGetSql(String db)
			{
			   
        getDB(db) ; 
			}
			private void getDB(String db)
			{
        		Connection c = null ; 
				Statement s = null ; 


         ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("img/update.png")) ;
            
            
				try
				{


					Class.forName("com.mysql.jdbc.Driver") ; 

					c = DriverManager.getConnection(DB_URL+db,USER,PASS) ; 

					System.out.println("connection "+DB_URL+db) ; 

					s = c.createStatement() ; 

					 
					
					ResultSet rs = s.executeQuery(sql) ;
					
				
					while(rs.next())
					{ 
             
            
             i++ ; 
          }
          
				  rs = s.executeQuery(sql) ;
					
					ls = new ArrayList<Object[]>() ; //new Object[100][5] ; 
					
					inf = new Object[i][7] ; 
					i = 0 ; 
					
					while(rs.next())
					{ 
             if(rs.getString("id")!=null)
             {
             inf[i][0]= rs.getString("id") ; 
             inf[i][1]= rs.getString("name") ; 
             inf[i][2] = rs.getString("prenom") ; 
             inf[i][3] = rs.getString("email") ; 
             inf[i][4] = rs.getString("date") ; 
             inf[i][5] = im ; 
             inf[i][6] = false ; 
             
             //ls.add(inf) ; 
             
            
             
             i++ ; 
             }
            
					}
					
					
					
				}
				catch(SQLException e ) 
				{
					e.printStackTrace() ;
						javax.swing.JOptionPane.showMessageDialog(null,"SQLException , erreur connection au serveur \n *le serveur s'est peut être éteint ou déconnecté "                                                                      ,"erreur",javax.swing.JOptionPane.ERROR_MESSAGE) ; 
					
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
						javax.swing.JOptionPane.showMessageDialog(null,"SQLException , erreur lors de la fermiture de la connection ","erreur",javax.swing.JOptionPane.                                                                    ERROR_MESSAGE) ; 
					
					}
				}
			}
			public Object[][] getInf() //List<Object[]> getInf()
			{
        return this.inf ; //ls ; 
			}
			public Object[] getInfLine() 
			{
			if(i>0)
        return this.inf[i-1] ; 
        else
        return null ; 
			}
			public void setOrdre(String db,String s)
			{
        sql = "SELECT * FROM `vote`.`etud` ORDER BY `"+s+"` ; " ; 
        getDB(db) ; 
			}
		}