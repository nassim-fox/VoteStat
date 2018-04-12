
          
           package interfaces ; 

					import javax.swing.JPanel ;
					import javax.swing.JButton ; 
					import javax.swing.BoxLayout ; 
					import javax.swing.Box ; 
					import javax.swing.JTextField ;
					import javax.swing.JLabel ;
					import javax.swing.JTable ;  
					import javax.swing.table.DefaultTableModel ;
					import javax.swing.text.html.* ;
					import javax.swing.ImageIcon ; 
					import javax.swing.JOptionPane ; 
					import java.awt.event.ActionEvent ; 
					import java.awt.event.ActionListener ; 
					import java.awt.event.MouseEvent ; 
					import java.awt.event.MouseAdapter ; 
					import java.awt.Dimension ; 
					import java.awt.Color ;
					import java.lang.Override;

					import javax.swing.JFrame ;
          
          import java.util.regex.Pattern ; 
          import java.util.regex.Matcher ;  
          
          import sql_connection.VoteAddSql ; // importation des class gérant les modification de la base de donnée 
          import sql_connection.VoteGetSql ;
		  import sql_connection.VoteEditSql ;
          
          import styles.StyleSheetA ;   //importation du style css eventuellement utilisé pour les JLabel


					public class FormPane extends JPanel  
					{	
						private static JTextField t_nom , t_prenom , t_email  ; //, t_date ;
						private JLabel l_nom , l_prenom , l_email ; // , l_date ; 
						private static JButton confirm , update , cancel , search ;
						private JPanel inside ; 
						private VoteAddSql v ;  
            //private TableDeVote table ; 
            			private static int update_id ;
            			
          
						public FormPane()
						{
							initForm() ; 
						}
						private void initForm()
						{
						
              HTMLEditorKit h = (new StyleSheetA("styles/StylesA.css")).getHTMLEditorKit() ;  // lien pour le fichier css

							//setPreferredSize(new Dimension(500,400)) ; 
							//setBackground(new Color(20,100,10)) ; 
							setOpaque(false) ; 
                
                 // création des JLabel
						    l_nom = new JLabel("<html><font class='formlb'>Nom</font></html>") ;             
						    l_prenom = new JLabel("<html><font class='formlb'>Prenom</font></html>") ; 
						    l_email = new JLabel("<html><font class='formlb'>Email</font></html>") ; 
              
 							l_nom.setPreferredSize(new Dimension(50,20)) ; 
 							l_prenom.setPreferredSize(new Dimension(50,20)) ; 
 							l_email.setPreferredSize(new Dimension(50,20)) ; 
              
              //création des JTextField
							t_nom = new JTextField() ; 
							t_prenom = new JTextField() ; 
							t_email = new JTextField() ; 


							t_nom.setPreferredSize(new Dimension(250,20)) ; 
              t_prenom.setPreferredSize(new Dimension(250,20)) ; 
              t_email.setPreferredSize(new Dimension(250,20)) ; 
              
              t_nom.setToolTipText("[A-Za-z]+ , seulement des lettres ") ; 
              t_prenom.setToolTipText("[A-Za-z]+ , seulement des lettres ") ; 
              t_email.setToolTipText("ex : simple@exemple.com") ; 
              
              //createion des JButton
							update = new JButton("Modifier") ;   //bouton modification 
							cancel = new JButton("Annuler") ;    //bouton annulation de la modification  
              
              update.setBackground(new Color(250,255,250)) ; 
              cancel.setBackground(new Color(250,255,250)) ; 
              
              //ajout d'une icone dans un bouton 
              update.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/update_button.png"))) ;

							update.setVisible(false) ;
							cancel.setVisible(false);
              
              update.addMouseListener(new MouseClick(update)) ; 
							update.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
                  
                  //création d'un objet de classe pour éditer la table et modifier un vote 
									VoteEditSql votee = new VoteEditSql("Vote",
																		t_nom.getText(),
																			t_prenom.getText(),
																				t_email.getText(),
																					update_id) ;
									
									//recharger la table dans le l'interface principale												
									VotePane.loadTable() ;
									
									//changer la visibilité des boutons après la modfication 			
									confirm.setVisible(true) ;
									search.setVisible(true) ;
									update.setVisible(false) ;
									cancel.setVisible(false) ;

									t_nom.setText("");
									t_prenom.setText("");
									t_email.setText("");
                  
                  
									repaint() ;
									revalidate() ;
									
									//recharger le graphe dans l'interface principale
									VotePane.setLineChart(new VoteLineChart()) ; 
					
								}
							});
							cancel.addMouseListener(new MouseClick(cancel)) ; 
							cancel.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {

									t_nom.setText("");
									t_prenom.setText("");
									t_email.setText("");

									confirm.setVisible(true) ;
									search.setVisible(true) ; 
									update.setVisible(false) ;
									cancel.setVisible(false) ;
									repaint() ;
									revalidate() ;
									
									VotePane.setLineChart(new VoteLineChart()) ; 
					
								}
							});
							
							//********** bouton d'ajout ***********************************************************
							confirm = new JButton("Ajouter") ; 
							//confirm.setContentAreaFilled(false) ; 
							confirm.setBackground(new Color(250,255,250)) ; 
              
							confirm.addMouseListener(new MouseClick(confirm)) ;
							confirm.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/add24.png"))) ;
							confirm.addActionListener(new ActionListener()
							{
								public void actionPerformed( ActionEvent e )
								{
									String	nom = t_nom.getText() ;  
									String 	prenom = t_prenom.getText() ;
									String 	email = t_email.getText() ; 
                    
                    t_nom.setBackground(new Color(255,255,255)) ;
                    t_prenom.setBackground(new Color(255,255,255)) ;
                    t_email.setBackground(new Color(255,255,255)) ;
                        
                  //***** si les inputs ne sont pas vides ***************
                  if(!nom.isEmpty()&&!prenom.isEmpty()&&!email.isEmpty()) 
                  {
                  
                   Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
                   Matcher mat = pattern.matcher(email);
                   
                   //************ si l'email n'est pas valide ***************
                  if(!mat.matches()){
                          t_email.setBackground(new Color(255,200,200)) ;
                        
                         JOptionPane.showMessageDialog(null,"le format de l'adresse email n'est pas valide \n * essayer un format conforme à xxxx@xxx.xxx",                                     "Verrifier champs",JOptionPane.WARNING_MESSAGE) ; 
                          
                         
                         
                  }
                  else
                  {
                  //******* si les informations contiennent des chiffres *********
                  if(nom.matches(".*\\d.*")||nom.matches(".*[\\,;:?%(\\*\\+].*")||prenom.matches(".*\\d.*")||prenom.matches(".*[\\,;:?%(\\*\\+].*"))
                  { 
                          t_nom.setBackground(new Color(255,200,200)) ;
                          t_prenom.setBackground(new Color(255,200,200)) ;
                        
                        JOptionPane.showMessageDialog(null,"le nom et le prenom ne doivent être formés que de lettres alphabetiques \n *[A-Z][a-z] "+
                                      ", ni chiffres  ni symboles","Verrifier champs",JOptionPane. WARNING_MESSAGE) ; 
                  }
                  else
                  {
                  // appel à la classe d'ajout 
								  v = new VoteAddSql("Vote",nom,prenom,email) ; 
                  
                  //charger les vote depuis la base de donnée avec un SELECT
                  VoteGetSql vs = new VoteGetSql("Vote") ; 
                  vs.setOrdre("vote","id") ; 
                  
                  // si le vote n'existe pas déjà dans la base de donnée ( getCheckVote porte le résultat de la vérification 
                                                                          // de l'existance du vote depuis VoteAddSql ) 
                  // afficher le nouveau vote dans la table de l'interface 
                  if(!VoteAddSql.getCheckVote()) 
                  VotePane.addToTable(vs.getInfLine()) ; 
                  
                   
                  VotePane.setStatis(new Stats()) ; 
          
                  //recharger le graphe   
                  VotePane.setLineChart(new VoteLineChart()) ;
              
                  t_nom.setText("") ; 
                  t_prenom.setText("") ; 
                  t_email.setText("") ;
                  }
                  }
                  } 
                  else
                  {
                    t_nom.setBackground(new Color(255,200,200)) ;
                    t_prenom.setBackground(new Color(255,200,200)) ;
                    t_email.setBackground(new Color(255,200,200)) ;
                        
                    JOptionPane.showMessageDialog(null,"Tout les champs doivent être remplis!","Verrifier champs",JOptionPane.WARNING_MESSAGE) ; 
                  }
                  //VotePane.setTable(new VotePane().getTable().setModel(mod));
                
                  
							}
							
							
							}) ; 
							
							//*****************************************************************************
							
							//************* bouton de recherche rapide *****************************
							search = new JButton("") ; 
							search.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/search24.png"))) ; 
							
							search.addActionListener(new ActionListener()
							{
                  public void actionPerformed( ActionEvent e )
                  {
                    VotePane.searchTable(t_nom.getText(),t_prenom.getText(),t_email.getText()) ; 
                  }
							}) ; 
             //************************************************************************
           
            
                         
							CreateLayout() ; 
						}							
						private void CreateLayout()
						{

						    inside = new JPanel() ; 
							JPanel [] o = new JPanel[4] ; 
              
              
              
							inside.setLayout(new BoxLayout(inside,BoxLayout.PAGE_AXIS)) ;
          
              //inside.setBackground(new Color(20,20,255,50)) ; 
              inside.setBackground(new Color(12,84,135,50)) ; 
              
							for(int i= 0 ; i < 4 ; i++ )
							{
								o[i] = new JPanel() ; 
								o[i].setLayout(new BoxLayout(o[i],BoxLayout.LINE_AXIS)) ; 
                o[i].setOpaque(false) ; 
                
							} 

              
							inside.add(Box.createVerticalStrut(40)) ; 
							//i.add() ; // ajout label pour notifications d'erreurs 
							//i.add(Box.createVerticalStrut(20)) ;
							
							JLabel lb = new JLabel("Formulaire de vote") ; 
							lb.setForeground(new Color(255,255,255)) ; 
							lb.setFont(lb.getFont().deriveFont(16.0f)) ; 
							
							inside.add(lb) ;
							inside.add(Box.createVerticalStrut(10)) ; 
							o[0].add(Box.createHorizontalStrut(20)) ; 
							o[0].add(l_nom) ;
							o[0].add(Box.createHorizontalStrut(20)) ;//l_email.getY()+20)) ; 
							o[0].add(t_nom) ;
							o[0].add(Box.createHorizontalStrut(20));
							inside.add(o[0]) ; 
							inside.add(Box.createVerticalStrut(20)) ;
							o[1].add(Box.createHorizontalStrut(14)) ;
							o[1].add(l_prenom) ; 
							o[1].add(Box.createHorizontalStrut(20));
							o[1].add(t_prenom) ; 
							o[1].add(Box.createHorizontalStrut(20));
							inside.add(o[1]) ;
							inside.add(Box.createVerticalStrut(20)) ;
							o[2].add(Box.createHorizontalStrut(20)) ;
							o[2].add(l_email) ; 
							o[2].add(Box.createHorizontalStrut(20));
							o[2].add(t_email) ;
							o[2].add(Box.createHorizontalStrut(20));
						
							inside.add(o[2]) ;  
							inside.add(Box.createVerticalStrut(20)) ;
							o[3].add(Box.createHorizontalStrut(20)) ; 
							o[3].add(Box.createHorizontalStrut(80));
							o[3].add(confirm) ;
							o[3].add(Box.createHorizontalStrut(50));
							o[3].add(search) ;
							o[3].add(update) ;
							o[3].add(Box.createHorizontalStrut(20)) ;
							o[3].add(cancel) ;

							inside.add(o[3]) ; 
							inside.add(Box.createVerticalStrut(20)) ;
							
							/*o[4].add(l_nom) ; 
							o[4].add(Box.createHorizontalStrut(20)) ; 
							o[4].add(t_nom) ; 
							i.add(o[4]) ; 
							i.add(Box.createVerticalStrut(20)) ; 
							i.add(confirm) ; 
							*/
              
              
              add(inside) ; 

							
						}
						public JPanel getForm()
						{
							return this.inside ; 
						}
						public static void setTextField(String nom , String prenom, String email , int id ) {
							t_nom.setText(nom) ;
							t_prenom.setText(prenom) ;
							t_email.setText(email) ;
              
							confirm.setVisible(false) ;
							search.setVisible(false) ; 
							update.setVisible(true) ;
							cancel.setVisible(true) ;
							update_id = id ;

						}
						
						private class MouseClick extends MouseAdapter
						{
              JButton b ; 
              
              public MouseClick(JButton b)
              {
                this.b = b ; 
              }
              public void mouseEntered( MouseEvent e)
                {
                //  confirm.setContentAreaFilled(true) ; 
                  b.setBackground(new Color(200,255,200)) ; 
                }
                 public void mouseExited( MouseEvent e)
                {
                //  confirm.setContentAreaFilled(false) ; 
                  b.setBackground(new Color(250,255,250)) ; 
              
                }
						}
						
						/*public static void main(String [] args)
						{
							JFrame f = new JFrame() ; 
							f.setSize(500,400) ; 

							f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 



							f.add(new FormPane()) ; 


							f.setVisible(true) ; 





						} */


					}