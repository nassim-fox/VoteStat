

    //**** Classe Principale ****
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
		import javax.swing.Timer ; 
		import java.awt.Image ;  
		import java.awt.event.ActionEvent;  
		import java.awt.event.ActionListener ; 
		import java.awt.Toolkit ; 
		import java.awt.EventQueue ; 
		

    import interfaces.VotePane ; 
    import sql_connection.* ; 

			public class VoteStat extends JFrame
			{
			
        public static VotePane p ; 
        private Timer t ; 
        private int i = 0 ;  
        
				public VoteStat()
				{	
				
				    VoteCreateDb vb = new VoteCreateDb("vote") ; 

					//setSize(getHeight(),getWidth()) ; 
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
					setExtendedState(JFrame.MAXIMIZED_BOTH) ; 
					setTitle("Vote Stats") ; 
					
					VoteScreenSave vs = new VoteScreenSave() ; 
					add(vs) ;
					 
					 

					
					
					
				  p = new VotePane() ; 
					p.repaint() ; 
					p.revalidate() ; 
					
					
					
					t = new Timer(100,new ActionListener()
					{
            public void actionPerformed( ActionEvent e)
            {
              if(i>10)
              {
                vs.setVisible(false) ; 
                add(p) ; 

                t.stop() ; 
              }
              
              System.out.println(i) ; 
              i++ ; 
            }
					}) ; 
					
					t.start() ; 
					
					setJMenuBar(new VoteMenuBar()) ; 
					
					java.net.URL url = ClassLoader.getSystemResource("img/statistics-market-icon26.png");//new URL("img/camera.png");
          Toolkit kit = Toolkit.getDefaultToolkit();
          Image img = kit.createImage(url);
          //getFrames().
          setIconImage(img);

				}
				public static void main(String [] args)
				{
					EventQueue.invokeLater(new Runnable()
						{
							public void run()
							{
								VoteStat e = new VoteStat() ; 

								e.setVisible(true) ; 
							}
						}) ; 
				}
			}