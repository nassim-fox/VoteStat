



      package interfaces ;
      
      
      
      import javax.swing.JPanel ;
      import javax.swing.JLabel ; 
      import javax.swing.JButton ; 
      import javax.swing.BoxLayout ; 
      import javax.swing.Box; 
      import java.awt.Dimension ; 
      import java.awt.Color ; 
      
      import sql_connection.VoteGetSql ; 
      
      
      public class Stats extends JPanel
      {
        
        private VoteGetSql vote ; 
        private JLabel dernier_vote ; 
        
        public Stats()
        {
          initS() ; 
        }
        private void initS()
        {
          vote = new VoteGetSql("Vote") ; 
          
          Object o [] = vote.getInfLine() ; 
          
          if(o!=null)
          dernier_vote = new JLabel("le dernier vote : "+o[0]+"  "+o[1]+"   "+o[2]+"   "+o[3]+"   "+o[4]) ; 
           else
            dernier_vote = new JLabel("") ; 
          
          
          CreateLayout()  ;
          
        }
        private void CreateLayout()
        {
          
          JPanel inside = new JPanel() ; 
          JPanel o = new JPanel() ; 
          
          inside.setLayout(new BoxLayout(inside,BoxLayout.PAGE_AXIS)) ; 
          o.setLayout(new BoxLayout(o,BoxLayout.LINE_AXIS)) ;
          
          o.add(Box.createHorizontalStrut(100)) ; 
          o.add(dernier_vote) ; 
          inside.add(o) ;
          
          add(inside) ; 
        }
      }