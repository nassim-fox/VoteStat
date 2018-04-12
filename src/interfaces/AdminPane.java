


    

        package interfaces ; 
              
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
              import javax.swing.JTable ; 
              import javax.swing.table.* ;
              import javax.swing.JScrollPane ;  
              
              import java.awt.*;
              import java.awt.event.* ; 
              
              import java.io.PrintWriter ; 
              import java.io.BufferedWriter ; 
              import java.io.FileWriter ; 
              import java.io.IOException ; 
              
              
              
            
              
              public class AdminPane extends JPanel  
              {
                private JButton confirm ; 
                private JTextField nom ;
                private JPasswordField pass ; 
                private JLabel l_nom , l_pass ; 
                private JPanel inside ; 
                private JLabel ps ; 
                private int i ; 
                private JLabel err ; 
                private JTable table ; 
                private JScrollPane scrollpane ; 
                
                
                public AdminPane()
                {
                
                
                  setBackground(new Color(200,200,20,20)) ;
                  setPreferredSize(new Dimension(500,400)) ; 
                   
                  nom = new JTextField() ; 
                  pass = new JPasswordField() ; 
                  
                  nom.setPreferredSize(new Dimension(200,20)) ; 
                  pass.setPreferredSize(new Dimension(200,20)) ; 
                  
                  l_nom = new JLabel("utilisateur  ") ; 
                  l_pass = new JLabel("mot de passe") ; 
                  
                  l_nom.setForeground(new Color(255,255,255))  ; 
                  l_pass.setForeground(new Color(255,255,255))  ; 
                  
                  l_nom.setFont(l_nom.getFont().deriveFont(18.0f)) ; 
                  l_pass.setFont(l_pass.getFont().deriveFont(18.0f)) ; 
                  
              
                  confirm = new JButton("ajouter utilisateur") ; 
                  confirm.addActionListener( new ActionListener()
                  {
                    public void actionPerformed(ActionEvent e)
                    {
                       
                  }
                  }
                  ) ; 
                   
                  createTable() ; 
                  createLayout() ;
                   
                  
                }
                private void createLayout()
                {
                  inside = new JPanel() ; 
                   
                  
                  
                  inside.setLayout(new BoxLayout(inside,BoxLayout.PAGE_AXIS)) ; 
                  
                  inside.add(Box.createVerticalStrut(10)) ; 

                  JPanel ligne_table = new JPanel() ; 
                  ligne_table.setLayout(new BoxLayout(ligne_table,BoxLayout.LINE_AXIS)) ; 
                  ligne_table.add(Box.createHorizontalStrut(50)) ;  
                  ligne_table.add(table) ;
                  ligne_table.add(Box.createHorizontalStrut(30)) ; 
                  ligne_table.setOpaque(false) ; 
                  
                                   inside.add(Box.createVerticalStrut(20)) ;

                  inside.add(ligne_table) ; 
                                    
                                   inside.add(Box.createVerticalStrut(20)) ;

                  JPanel ligne_utilisateur = new JPanel() ; 
                  ligne_utilisateur.setLayout(new BoxLayout(ligne_utilisateur,BoxLayout.LINE_AXIS)) ; 
                  ligne_utilisateur.add(Box.createHorizontalStrut(50)) ;  
                  ligne_utilisateur.add(l_nom) ;
                  ligne_utilisateur.add(Box.createHorizontalStrut(10)) ; 
                  ligne_utilisateur.add(nom) ; 
                  ligne_utilisateur.setOpaque(false) ; 
                  
                                    
                  inside.add(ligne_utilisateur) ; 
                  
                                    inside.add(Box.createVerticalStrut(10)) ;


                  JPanel ligne_pass = new JPanel() ; 
                  ligne_pass.setLayout(new BoxLayout(ligne_pass,BoxLayout.LINE_AXIS)) ; 
                  ligne_pass.add(Box.createHorizontalStrut(50)) ;  
                  ligne_pass.add(l_pass) ;
                  ligne_pass.add(Box.createHorizontalStrut(10)) ; 
                  ligne_pass.add(pass) ; 
                  ligne_pass.setOpaque(false) ; 
                  

                  inside.add(ligne_pass) ; 
                  
                                    inside.add(Box.createVerticalStrut(20)) ;


                  inside.add(confirm) ; 
                  
                  inside.add(Box.createVerticalStrut(20)) ; 
                  inside.add(Box.createVerticalStrut(20)) ; 
                  
                  inside.add(changeDbPane()) ; 
                  
                  
                  inside.setOpaque(false) ; 
                 
                   add(inside) ;  
                  
                  
                } 
                public void createTable()
                {
                  
                  table = new JTable() ; 
                  
                  TableDeVoteB mod ;
                 
                 
                  table.setDefaultRenderer(JButton.class,new TableComponent()) ;
                  
					        //alignement du texte du tableau dans le centre ******************************
                  DefaultTableCellRenderer cr = new DefaultTableCellRenderer() ;
                  cr.setHorizontalAlignment(JLabel.CENTER) ; 
                
                  table.setDefaultRenderer(String.class,cr) ;
                  //****************************************************************************
              
                  String [] s = {"utilisateur","mot de passe"} ;
                  String [][] ss = {{"user","user"},{"user2","pass"}} ; 
                  
                  mod = new TableDeVoteB(ss,s) ;
                  table.setModel(mod) ;
                  
                   
                  
                  
                  table.setBackground(new Color(12,84,135)) ;
                  table.getTableHeader().setBackground(new Color(0,47,80)) ;
                  table.getTableHeader().setForeground(new Color(255,255,255)) ;
                  table.setSelectionBackground(new Color(100,100,200)) ; 
                  table.setSelectionBackground(new Color(255,255,255)) ; 
                  table.setForeground(Color.WHITE) ;
                  table.setShowVerticalLines(false) ; 
              
               
               
                	scrollpane = new JScrollPane(table) ;
                  scrollpane.setPreferredSize(new Dimension(300,400)) ;
                  scrollpane.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder()) ;
                  scrollpane.setBorder(javax.swing.BorderFactory.createEmptyBorder()) ;
                  scrollpane.setOpaque(false) ;
                  scrollpane.getViewport().setOpaque(false) ;
              
              

        
                }
                private JPanel changeDbPane()
                {
                  JPanel p = new JPanel() ; 
                  
                  p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS)) ;
                  
                  
                  JPanel [] o = new JPanel[3] ; 
                   
                  for(int i = 0 ; i < 3 ; i++ )
                  {
                    o[i] = new JPanel() ; 
                    o[i].setLayout(new BoxLayout(o[i],BoxLayout.LINE_AXIS)) ; 
                    
                  }
                  
                  JLabel db_l = new JLabel("  url du serveur ") ;
                  JLabel user_db_l = new JLabel("  identifiant ") ;
                  JLabel pass_db_l = new JLabel("  mot de pass ") ;
                  
                  db_l.setPreferredSize(new Dimension(100,20)) ; 
                  user_db_l.setPreferredSize(new Dimension(100,20)) ; 
                  pass_db_l.setPreferredSize(new Dimension(100,20)) ; 
                  
                  
                  o[0].add(db_l) ; 
                  o[0].add(Box.createHorizontalStrut(20)) ; 
                  o[0].add(user_db_l) ; 
                  o[0].add(Box.createHorizontalStrut(20)) ; 
                  o[0].add(pass_db_l) ; 
                  o[0].add(Box.createHorizontalStrut(20)) ; 
                  
                  JTextField db = new JTextField() ; 
                  JTextField user_db = new JTextField() ; 
                  JPasswordField pass_db = new JPasswordField() ; 
                  
                  db.setPreferredSize(new Dimension(100,20)) ; 
                  user_db.setPreferredSize(new Dimension(100,20)) ; 
                  pass_db.setPreferredSize(new Dimension(100,20)) ; 
                  
                  
                  o[1].add(db) ; 
                  o[1].add(Box.createHorizontalStrut(20)) ; 
                  o[1].add(user_db) ; 
                  o[1].add(Box.createHorizontalStrut(20)) ; 
                  o[1].add(pass_db) ; 
                  o[1].add(Box.createHorizontalStrut(20)) ; 
                  
                  
                  JButton change_db = new JButton("actualiser la base de donnée") ; 
                  change_db.addActionListener(new ActionListener()
                  {
                    public void actionPerformed( ActionEvent e )
                    {
                      String db_s = "jdbc:mysql:" ; 
                      
                      String s = "" ; 
                      
                      for(int i = 0 ; i < pass_db.getPassword().length ; i++ )
                      {
                        s = s + pass_db.getPassword()[i] ; 
                      }
                      sql_connection.VoteLocalhostUrl.setDB(db.getText().toString(),user_db.getText().toString(),s) ; 
                      sql_connection.VoteLocalhostUrl v = new sql_connection.VoteLocalhostUrl() ; 
                      sql_connection.VoteGetSql.setDB(db_s+db.getText().toString(),user_db.getText().toString(),s) ; 
                      sql_connection.VoteAddSql.setDB(db_s+db.getText().toString(),user_db.getText().toString(),s) ; 
                      sql_connection.VoteEditSql.setDB(db_s+db.getText().toString(),user_db.getText().toString(),s) ; 
                      sql_connection.VoteDeleteEditSql.setDB(db_s+db.getText().toString(),user_db.getText().toString(),s) ; 
                      sql_connection.VoteCreateDb.setDB(db_s+db.getText().toString(),user_db.getText().toString(),s) ; 
                      
                      javax.swing.JOptionPane.showMessageDialog(null," les paramètre de la connection à la base de donnée ont changée avec succées ",
                                                                            "serveur changé",javax.swing.JOptionPane.INFORMATION_MESSAGE) ; 
                                            
                          try
                          {
                          PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("db_connection"))) ; 
                          p.println(db.getText().toString()) ; 
                          p.println(user_db.getText().toString()) ; 
                          p.println(s) ; 
                          
                          p.close() ; 
                          
                          }
                          catch(IOException es)
                          {
                            es.printStackTrace() ; 
                          }
                                                                      
                    }
                  }
                  ) ; 
                  
                  o[2].add(change_db) ; 
                  
                  
                  p.add(o[0]) ; 
                  p.add(Box.createVerticalStrut(20)) ;
                  p.add(o[1]) ; 
                  p.add(Box.createVerticalStrut(20)) ;
                  p.add(o[2]) ; 
                  p.add(Box.createVerticalStrut(20)) ; 
                  
                  p.add(change_db) ; 
                  
                  return p ; 
                   
                }
                public void paintComponent(Graphics g)
                {
                  super.paintComponent(g) ; 
                  
                 // g.drawImage(new ImageIcon(getClass().getResource("img/admin.png")).getImage(),0,0,null) ; 
                }          
                
              
              }
              

                    