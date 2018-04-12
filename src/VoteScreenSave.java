



      
    //******************************************************
    //**** email de l'autheur : nassim.bouhadouf@gmail.com 
    //******************************************************
    //**** date : du 10 au 12 2017  
    //**** projet Ihm Application de statistiques de Vote 
    //**** nom de l'application : Vote Stat 
    //*******************************************

    //************* écran de démarage

        
        import javax.swing.JPanel ;
        import javax.swing.ImageIcon ;  
        import java.awt.Graphics ; 
        import java.awt.Image ; 
        import java.awt.Color ;
        
        
        
        
        public class VoteScreenSave extends JPanel
        {
        
          public VoteScreenSave()
          {
            setBackground(new Color(100,100,200)) ; 
            
          }
          
          public void paintComponent(Graphics g)
          {
            super.paintComponent(g) ; 
            
            g.drawImage(new ImageIcon(getClass().getResource("img/screensave.jpg")).getImage(),0,0,null) ;
            
            
             
          }
         
        } 