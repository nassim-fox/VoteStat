




        
              
              import javax.swing.JFrame ; 
              import javax.swing.JPanel ; 
              import javax.swing.JButton ; 
              import javax.swing.JTextField ; 
              import javax.swing.JPasswordField ;
              import javax.swing.JLabel ; 
              import javax.swing.BoxLayout ; 
              import javax.swing.Box ; 
              import javax.swing.Timer ; 
              import javax.swing.ImageIcon ;
              import javax.swing.JOptionPane ; 
              import javax.swing.SwingUtilities ;  
              import java.awt.*;
              import java.awt.event.* ; 
              
              
            
              
              public class LoginPane extends JPanel  
              {
                private JButton confirm ; 
                private JTextField nom ;
                private JPasswordField pass ; 
                private JLabel l_nom , l_pass ; 
                private JPanel inside ; 
                private JLabel ps ; 
                private int i ; 
                private JLabel err ; 
                
                
                public LoginPane()
                {
                
                
                  setBackground(new Color(200,200,20,20)) ;
                  setPreferredSize(new Dimension(500,400)) ; 
                   
                  nom = new JTextField() ; 
                  pass = new JPasswordField() ; 
                  
                  nom.setPreferredSize(new Dimension(200,20)) ; 
                  pass.setPreferredSize(new Dimension(200,20)) ; 
                  
                  l_nom = new JLabel("utilisateur") ; 
                  l_pass = new JLabel("mot de passe") ; 
                  
                  l_nom.setForeground(new Color(255,255,255))  ; 
                  l_pass.setForeground(new Color(255,255,255))  ; 
                  
                  l_nom.setFont(l_nom.getFont().deriveFont(18.0f)) ; 
                  l_pass.setFont(l_pass.getFont().deriveFont(18.0f)) ; 
                  
                  
                  confirm = new JButton("connecter") ; 
                  confirm.addActionListener( new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                        err.setVisible(false) ; 
                        
                        char [] pass2 = {'u','s','e','r'} ; 
                        
                        if(nom.getText().toString().equals("user")&&java.util.Arrays.equals(pass.getPassword(),pass2))
                        {
                         //*** création d'un thread pour la connection à l'application 
                         Thread t = new Thread()
                         {
                          public void run()
                          { 
                            super.run() ; 
                            
                            
                            VoteStat e = new VoteStat() ; 
                            e.setVisible(true) ; 
                            
                            JFrame f = (JFrame) SwingUtilities.getWindowAncestor(LoginPane.this) ; 
                            f.dispose() ; 
                            
                          }
                         } ; 
                         
                         inside.setVisible(false) ; 
                         add(createWaitLayout()) ;  
                          
                          
                         t.start() ; 
                     
                          
                        }
                        else
                        {
                          err.setVisible(true) ; 
                        }
                  }
                  }
                  ) ; 
                  
                  createLayout() ;
                   
                  
                }
                private void createLayout()
                {
                  inside = new JPanel() ; 
                   
                  
                  
                  inside.setLayout(new BoxLayout(inside,BoxLayout.PAGE_AXIS)) ; 
                  
                  inside.add(Box.createVerticalStrut(50)) ;
                  JPanel ligne_erreur = new JPanel() ; 
                  ligne_erreur.setLayout(new BoxLayout(ligne_erreur,BoxLayout.LINE_AXIS)) ; 
                  err = new JLabel("vérifier l'identifiant ou le mot de passe") ; 
                  err.setVisible(false) ; 
                    
                  err.setForeground(new Color(255,255,255)) ; 
                  ligne_erreur.setBackground(new Color(255,50,50,200))  ; 
                  ligne_erreur.add(err) ; 
                  inside.add(ligne_erreur) ; 
                  inside.add(Box.createVerticalStrut(10)) ; 

                  JPanel ligne_utilisateur = new JPanel() ; 
                  ligne_utilisateur.setLayout(new BoxLayout(ligne_utilisateur,BoxLayout.LINE_AXIS)) ; 
                  ligne_utilisateur.add(Box.createHorizontalStrut(50)) ;  
                  ligne_utilisateur.add(l_nom) ;
                  ligne_utilisateur.add(Box.createHorizontalStrut(30)) ; 
                  ligne_utilisateur.add(nom) ; 
                  ligne_utilisateur.setOpaque(false) ; 
                  
                                   inside.add(Box.createVerticalStrut(50)) ;

                  inside.add(ligne_utilisateur) ; 
                  
                  JPanel ligne_pass = new JPanel() ; 
                  ligne_pass.setLayout(new BoxLayout(ligne_pass,BoxLayout.LINE_AXIS)) ; 
                  ligne_pass.add(Box.createHorizontalStrut(50)) ;  
                  ligne_pass.add(l_pass) ;
                  ligne_pass.add(Box.createHorizontalStrut(10)) ; 
                  ligne_pass.add(pass) ; 
                  ligne_pass.setOpaque(false) ; 
                  
                                    inside.add(Box.createVerticalStrut(50)) ;

                  inside.add(ligne_pass) ; 
                  
                                    inside.add(Box.createVerticalStrut(50)) ;


                  inside.add(confirm) ; 
                  
                  inside.add(Box.createVerticalStrut(20)) ; 
                  inside.add(Box.createVerticalStrut(20)) ; 
                  
                  inside.setOpaque(false) ; 
                  
                   add(inside) ;  
                  
                  
                }
                private JPanel createWaitLayout()
                {
                  JPanel inside2 = new JPanel() ; 
                  
                  inside2.setOpaque(false) ; 
                  
                  inside2.setLayout(new BoxLayout(inside2,BoxLayout.PAGE_AXIS)) ;
                  
                  JPanel o = new JPanel() ; 
                  
                  o.setLayout(new BoxLayout(o,BoxLayout.LINE_AXIS)) ; 
                  
                  
                  
                  inside2.add(Box.createVerticalStrut(250)) ; 
                  JLabel wait = new JLabel("attendez un moment s'il vous plait ... ") ; 
                  wait.setForeground(new Color(255,255,255)) ; 
                  wait.setFont(wait.getFont().deriveFont(18.0f)) ; 
                  
                  o.add(wait) ; 
                  
                  o.setOpaque(false) ; 
                  
                  inside2.add(o) ; 
                  
                  return inside2 ; 
                   
                }
                public void paintComponent(Graphics g)
                {
                  super.paintComponent(g) ; 
                  
                  g.drawImage(new ImageIcon(getClass().getResource("img/login.png")).getImage(),0,0,null) ; 
                }          
                
              
              }
              

                    