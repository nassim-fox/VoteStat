


        package sql_connection ; 
        
        
        
          import java.io.* ; 
          
          
        
        
        public class LoadFromFile
        {
        
          public LoadFromFile()
          {
           System.out.println(new VoteLocalhostUrl().getDb()) ;    
                 
            try
            {
              BufferedReader b = new BufferedReader(new FileReader("db_connection")) ;
              
              String db = b.readLine() ; 
              String user_db = b.readLine() ; 
              String pass_db = b.readLine() ; 
              
              VoteLocalhostUrl.setDB("jdbc:mysql:"+db,user_db,pass_db) ; 
              VoteGetSql.setDB("jdbc:mysql:"+db,user_db,pass_db) ; 
              VoteAddSql.setDB("jdbc:mysql:"+db,user_db,pass_db) ; 
              VoteEditSql.setDB("jdbc:mysql:"+db,user_db,pass_db) ; 
              VoteDeleteEditSql.setDB("jdbc:mysql:"+db,user_db,pass_db) ; 
              VoteCreateDb.setDB("jdbc:mysql:"+db,user_db,pass_db) ; 
                  
                      
              b.close() ; 
              
               
            }
            catch(IOException e)
            {
              e.printStackTrace() ; 
            }
          } 
         
        }