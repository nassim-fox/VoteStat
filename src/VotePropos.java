




    
    //******************************************************
    //**** email de l'autheur : nassim.bouhadouf@gmail.com 
    //******************************************************
    //**** date : du 10 au 12 2017  
    //**** projet Ihm Application de statistiques de Vote 
    //**** nom de l'application : Vote Stat 
    //*******************************************


      //********* fenetre à propos
      
        
          
            
                import javax.swing.JFrame ; 
                import javax.swing.JPanel ;
                import javax.swing.JButton ; 
                import javax.swing.JLabel ; 
                import javax.swing.GroupLayout ; 
                import javax.swing.JComponent ;
                import javax.swing.BoxLayout ; 
                import javax.swing.Box ;  
                import javax.swing.ImageIcon ; 
                import java.awt.Image ; 
                import java.awt.event.* ;
                import java.awt.Graphics ; 
                import java.awt.Dimension ; 
                 
                
                
                
                
                
                public class VotePropos extends JFrame
                {
                  
                  private ImageIcon im ; 
                  private JLabel l , l2 ; 
                  private JButton b ; 
                  
                  public VotePropos()
                  {
                      setSize(351,397) ; 
                      setLocationRelativeTo(null) ; 
                      setUndecorated(true) ; 
                      
                      b = new JButton("ok") ; 
                      b.setPreferredSize(new Dimension(100,20)) ; 
                      
                      b.addActionListener( new ActionListener()
                      {
                        public void actionPerformed( ActionEvent e)
                        {
                          setVisible(false) ; 
                        }
                      }
                      ) ; 
                     
                      l = new JLabel("") ;
                     
                      l2 = new JLabel("") ;
                     
                     im = new ImageIcon(getClass().getClassLoader().getResource("img/about.png")) ; 
                     
                     createLayout(l,l2,b) ;
               }
                  private void createLayout(JComponent ... arg)
                  {
                    JPanel p = new JPanel()
                    {
                      public void paintComponent(Graphics g)
                      {
                        super.paintComponent(g) ; 
                        
                        g.drawImage(im.getImage(),0,0,null) ; 
                      
                      }
                    } ; 
                   /* GroupLayout g = new GroupLayout(p) ; 
                    p.setLayout(g) ; 
                    
                    
                    g.setAutoCreateGaps(true) ; 
                    
                    
                    
                    g.setHorizontalGroup(g.createParallelGroup()
                                                          .addGroup(g.createSequentialGroup()
                                                                        .addComponent(arg[0]).addComponent(arg[1])).addComponent(arg[2])) ; 
                    g.setVerticalGroup(g.createSequentialGroup()
                                                        .addGroup(g.createParallelGroup()
                                                                    .addComponent(arg[0]).addComponent(arg[1])).addComponent(arg[2])) ; 
                    
                    add(p) ;
                   */
                   p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS)) ;
                   p.setOpaque(false) ; 
                   
                   p.add(Box.createVerticalStrut(50)) ; 
                   p.add(l) ; 
                   p.add(Box.createVerticalStrut(20)) ; 
                   p.add(l2) ; 
                   p.add(Box.createVerticalStrut(250)) ; 
                   JPanel bt = new JPanel() ; 
                   bt.setLayout(new BoxLayout(bt,BoxLayout.LINE_AXIS)) ; 
                   bt.add(Box.createHorizontalStrut(180)) ; 
                   bt.add(b) ; 
                   bt.setOpaque(false) ; 
                   
                   p.add(bt) ; 
                   
                   add(p) ; 
                   
                   
                  }
                  
                }