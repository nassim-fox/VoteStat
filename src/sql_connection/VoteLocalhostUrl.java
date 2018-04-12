


        package sql_connection ; 
        
        
        
        
        
        
        
        public class VoteLocalhostUrl
        {
        
        
          protected static String DB_URL  = "jdbc:mysql://localhost/" ; 
          
          protected static String USER = "root" ; 
          protected static String PASS = "" ; 
          
          public VoteLocalhostUrl()
          {
          
          }
          public static void setDB(String db,String user,String pass)
          {
            DB_URL = db ; 
            USER = user ; 
            if(pass.equals(""))
            PASS = "" ; 
            else
            PASS = pass ; 
            
          }
          public String getDb()
          {
            return this.DB_URL ; 
          }
          
        }