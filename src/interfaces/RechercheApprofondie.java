



      package interfaces ; 
      
      import javax.swing.JFrame ; 
      import javax.swing.JPanel ; 
      import javax.swing.JButton ; 
      import javax.swing.Box ; 
      import java.awt.BoxLayout ; 
      import java.awt.event.* ; 
      import java.awt.Dimension ; 
      
      
      public class RechercheApprofondie extends JFrame 
      {
        private JButton recherche , annuler  ; 
        
        public RechercheApprofondie()
        {
          setLocationRelativeTo(null) ; 
          setUndecorated(true) ; 
          
          
        }
      
        private class RechercheApprofondiePane extends JPanel implements ActionListener 
        {
          
          public void createLayout()
          {
            JPanel i = new JPanel() ; 
            i.setLayout(new BoxLayout(i,BoxLayout.PAGE_AXIS)) ; 
            
            JPanel [] o = new JPanel[6] ; 
            
            for(int i = 0 ; i < 6 ; i++ )
            {
            
              o[i] = new JPanel() ; 
              o[i].setLayout(new BoxLayout(o[i],BoxLayout.Line_AXIS)) ; 
            }
            
            i.add(Box.createHorizontalStrut(50)) ; 
            o[0].add(id_l) ; 
            o[0].add(Box.createVerticalStrut(20)) ; 
            o[0].add(id_t) ; 
            i.add(Box.createHorizontalStrut(20)) ; 
            o[1].add(nom_l) ; 
            o[1].add(Box.createVerticalStrut(20)) ; 
            o[1].add(nom_t) ; 
            i.add(Box.createHorizontalStrut(20)) ; 
            o[2].add(prenom_l) ; 
            o[2].add(Box.createVerticalStrut(20)) ; 
            o[2].add(prenom_t) ; 
            i.add(Box.createHorizontalStrut(20)) ; 
            o[3].add(email_l) ; 
            o[3].add(Box.createVerticalStrut(20)) ; 
            o[3].add(email_t) ; 
            i.add(Box.createHorizontalStrut(20)) ; 
            o[4].add(date_l) ; 
            o[4].add(Box.createVerticalStrut(20)) ; 
            o[4].add(date_t) ; 
            i.add(Box.createHorizontalStrut(20)) ; 
            
          }
          public void actionPerformed( ActionEvent e )
          {
            
          }  
        }
      }