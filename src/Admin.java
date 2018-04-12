

 
    //******************************************************
    //**** email de l'autheur : nassim.bouhadouf@gmail.com 
    //******************************************************
    //**** date : du 10 au 12 2017  
    //**** projet Ihm Application de statistiques de Vote 
    //**** nom de l'application : Vote Stat 
    //*******************************************




		import javax.swing.JFrame ; 
		import javax.swing.JPanel ; 
		import javax.swing.JButton ; 
		import javax.swing.JMenuBar ;
		import java.awt.Image ;  
		import java.awt.event.ActionEvent;  
		import java.awt.event.ActionListener ;
		import java.awt.Dimension ; 
		import java.awt.Toolkit ; 
		import java.awt.EventQueue ; 
		

    import interfaces.VotePane ; 
    import interfaces.AdminPane ; 



			public class Admin extends JFrame
			{
			
        public AdminPane p ; 
        

				public Admin ()
				{	
					//setSize(getHeight(),getWidth()) ; 
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE) ; 
					setTitle("VoteStat - authentification") ; 
					setResizable(false) ; 
					
          Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize() ;
          int widths = (int)ssize.getWidth() ; 
          int heights = (int)ssize.getHeight() ; 
          
					
					AdminPane p = new AdminPane() ;  ; 
					
					add(p) ; 

            VoteMenuBar m = new VoteMenuBar() ; 
            
            setJMenuBar(m.LoginMenuBar()) ; 
					//setJMenuBar(new VoteMenuBar()) ; 
					
					java.net.URL url = ClassLoader.getSystemResource("img/statistics-market-icon26.png");//new URL("img/camera.png");
          Toolkit kit = Toolkit.getDefaultToolkit();
          Image img = kit.createImage(url);
          //getFrames().
          setIconImage(img);
          
         
					
          pack() ; 
          
           setLocation(widths/2-p.getWidth()/2,(heights-p.getHeight())/2) ; 
					

				}
				public void createMenuBar()
				{
          
				} 
				public static void main(String [] args)
				{
					EventQueue.invokeLater(new Runnable()
						{
							public void run()
							{
								Login e = new Login() ; 

								e.setVisible(true) ; 
							}
						}) ; 
				}
			}