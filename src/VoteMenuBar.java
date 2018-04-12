




				
    //**** Classe Principale ****
    //**** date : du 10 au 12 2017  
    //**** projet Ihm Application de statistiques de Vote 
    //**** nom de l'application : Vote Stat 
    //******************************************************
    //**** email de l'autheur : nassim.bouhadouf@gmail.com 
    //******************************************************



							import javax.swing.Icon;
							import javax.swing.ImageIcon;
							import javax.swing.JMenu ;
							import javax.swing.JMenuBar ; 
							import javax.swing.JMenuItem ; 
							import javax.swing.JOptionPane ;
              import javax.swing.JFileChooser ; 
              import javax.swing.filechooser.FileFilter ; 
              import javax.swing.table.DefaultTableModel ;
              import javax.swing.KeyStroke ;  
              import javax.swing.JLabel ; 
              import javax.swing.JPanel ; 
              import javax.swing.JPasswordField ; 
              import javax.swing.BoxLayout ; 
              
              
							import java.awt.Component;
							import java.awt.Graphics;
							import java.awt.Image;
							import java.awt.event.ActionEvent ;
							import java.awt.event.ActionListener ;
							import java.awt.Toolkit ; 
							import java.lang.Override;

              import java.io.File ; 
              import java.io.FileReader ; 
              
              
              import sql_connection.* ; 
              
              
              
							public class VoteMenuBar extends JMenuBar implements ActionListener
					{
						private JMenu fichier , edition , affichage , about , stat ;
						private JMenuItem save , print , exit , apropo , delete_all , reintialiser , line , bar , rechapp , ouvrir , affreverse , deconnect ;
						private JOptionPane optionpane ;  
						private final static String message_apropo = "test" ; 
            private int i = 0 ; 
             
             
             
             
             
						public VoteMenuBar()
						{
							fichier = new JMenu("fichier") ; 
							edition = new JMenu("edition") ; 
							affichage = new JMenu("affichage") ; 
							about = new JMenu("?") ; 


              stat = new JMenu("afficher statistiques en tant que ") ;   
							
							ouvrir = new JMenuItem("ouvrir table") ; 
							save = new JMenuItem("enregistrer sous ...") ;
							print = new JMenuItem("imprimer") ; 
							deconnect = new JMenuItem("déconnecter") ; 
							exit = new JMenuItem("quitter") ; 
							apropo = new JMenuItem("a propos") ; 
							rechapp = new JMenuItem("recherche approfondie") ; 
							delete_all = new JMenuItem("suprimer tout les votes") ;
							reintialiser = new JMenuItem("reinitialiser la table") ;
							affreverse = new JMenuItem("changer zones de travail") ; 
							line = new JMenuItem("courbe") ; 
							bar = new JMenuItem("diagramme à barres") ; 
							
							
							save.setIcon(new ImageIcon(getClass().getResource("img/SaveAs16.png"))) ;
							print.setIcon(new ImageIcon(getClass().getResource("img/Print16.gif"))) ;
							deconnect.setIcon(new ImageIcon(getClass().getResource("img/exit2.png"))) ;
							exit.setIcon(new ImageIcon(getClass().getResource("img/button_exit1.png"))) ;
							delete_all.setIcon(new ImageIcon(getClass().getResource("img/edit-clear.png"))) ;
							reintialiser.setIcon(new ImageIcon(getClass().getResource("img/reload16.png"))) ; 
							affreverse.setIcon(new ImageIcon(getClass().getResource("img/affichage.png"))) ; 
              stat.setIcon(new ImageIcon(getClass().getResource("img/Bar-Chart-icon.png"))) ; 
              line.setIcon(new ImageIcon(getClass().getResource("img/line_chart16.png"))) ; 
              bar.setIcon(new ImageIcon(getClass().getResource("img/bar_chart16.png"))) ; 
              
              save.setAccelerator(KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())) ; 
              affreverse.setAccelerator(KeyStroke.getKeyStroke('v',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())) ; 
              line.setAccelerator(KeyStroke.getKeyStroke('t',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())) ; 
              bar.setAccelerator(KeyStroke.getKeyStroke('u',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())) ; 
              
							save.addActionListener(this);
							print.addActionListener(this) ; 
							deconnect.addActionListener(this) ; 
							exit.addActionListener(this) ; 
							rechapp.addActionListener(this) ; 
							delete_all.addActionListener(this);
							reintialiser.addActionListener(this);
							affreverse.addActionListener(this) ; 
							line.addActionListener(this) ;
              bar.addActionListener(this) ;
              apropo.addActionListener(this) ;
              
              stat.add(line) ; 
              stat.add(bar) ; 
              
							fichier.add(save) ;
							fichier.add(print) ;
							fichier.add(deconnect) ;
							fichier.add(exit) ;
							edition.add(rechapp) ; 
							edition.add(delete_all) ;
							edition.add(reintialiser) ;
							affichage.add(affreverse) ; 
							affichage.add(stat) ; 
							about.add(apropo) ;

							add(fichier) ; 
							add(edition) ; 
							add(affichage) ; 
							add(about) ; 


						}
						public JMenuBar LoginMenuBar()
						{
              JMenuBar m = new JMenuBar() ; 
              
              JMenu file = new JMenu("fichier") ; 
              
              JMenuItem admin = new JMenuItem("connection administrateur") ; 
              admin.addActionListener(this) ; 
              admin.setIcon(new ImageIcon(getClass().getResource("img/admin16.png"))) ; 
             
              file.add(admin) ; 
              file.add(exit) ;
               
              m.add(file) ; 
              m.add(about) ; 
              
              return m ; 
              
						}
						public void actionPerformed( ActionEvent e )
						{
							String s = e.getActionCommand() ;
							if(s.equals("enregistrer sous ..."))
							{
                JFileChooser f = new JFileChooser() ; 
                f.setDialogTitle("enregistrer sous ") ; 
                
                f.setFileFilter(new FileFilter() {//addChoosableFileFilter(new FileFilter() {
 
                    public String getDescription() {
                    return "PDF Documents (*.pdf)";
                              }
 
                    public boolean accept(File ff)
                     {
                    if (ff.isDirectory())
                     {
                      return true;
                      } 
                      else 
                      {
                      return ff.getName().toLowerCase().endsWith(".pdf");
                          }
                         }
                        });
                 
                 f.setAcceptAllFileFilterUsed(true);
        
                int fselect = f.showSaveDialog(this) ; 
                
                if(fselect==JFileChooser.APPROVE_OPTION)
                {
                  File fs = f.getSelectedFile() ; 
                  
                  PdfSaver p = new PdfSaver(fs.getAbsolutePath().toString()+".pdf") ; 
                  
                 
                }
                
								//interfaces.SaveVote saves = new interfaces.SaveVote() ;
                
							}
							if(s.equals("quitter"))
							{
								System.exit(0) ; 
							}
							if(s.equals("recherche approfondie"))
							{
                
							}
							if(s.equals("suprimer tout les votes"))
							{
               
               String ms = "tout les vôtes présents dans la table de votes \n"
                            +" seront complètement suprimés de la base de donnée\n"+
                            " etes vous sur d'effectuer cette opération ?" ;
							int o = JOptionPane.showConfirmDialog(null,ms,"suprimer tout les votes", JOptionPane.YES_NO_OPTION) ;
              
              if(o==JOptionPane.YES_OPTION)
              {                                                               
							 for(int i = 0 ; i < interfaces.VotePane.getTable().getModel().getRowCount() ; i++ )
                  {
                    
						 
						 String sk = (String)interfaces.VotePane.getTable().getModel().getValueAt(i,0) ;
						 int ski = Integer.parseInt(sk) ;

						 DefaultTableModel m = (DefaultTableModel)interfaces.VotePane.getTable().getModel() ;

						 VoteDeleteEditSql v = new VoteDeleteEditSql("Vote",ski) ;
						// System.out.println(ski+"selected") ;
						 m.removeRow(i) ;
						 i-- ;

						 interfaces.VotePane.setLineChart(new interfaces.VoteLineChart()) ; 
						 
               }
             }
               
         
         		}
							if(s.equals("courbe"))
							{
                interfaces.VoteLineChart.chart_type = interfaces.VoteLineChart.LINE_CHART ; 
                interfaces.VotePane.setLineChart(new interfaces.VoteLineChart()) ; 
							}
							if(s.equals("diagramme à barres")) 
							{
							  interfaces.VoteLineChart.chart_type = interfaces.VoteLineChart.BAR_CHART ; 
							  interfaces.VotePane.setLineChart(new interfaces.VoteLineChart()) ; 
					
							}
							if(s.equals("a propos"))  
							{
								optionpane = new JOptionPane() ; 
								optionpane.showMessageDialog(null,message_apropo) ; 
								VotePropos v = new VotePropos() ; 
								v.setVisible(true) ; 
								
								
							}
							if(s.equals("connection administrateur")) 
							{
                  
                  javax.swing.JPasswordField pass = new javax.swing.JPasswordField() ; 
                  javax.swing.JLabel l = new javax.swing.JLabel("entrer mot de passe administrateur") ; 
                  
                  JPanel p = new JPanel() ; 
                  p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS)) ;
                  p.add(l) ; 
                  p.add(pass) ; 
                  
                  String[] options = new String[]{"valider", "annuler"};


							    int ss = JOptionPane.showOptionDialog(null , p , "connection administrateur",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE, null , 
                                                                  options,options[1]) ; 
                  
                  if(ss==0)
                  {
                    char[] ps = pass.getPassword() ; 
                    char [] psa = {'a','d','m','i','n'} ; 
                    
                    if(java.util.Arrays.equals(ps,psa)) 
                    {
                      
                      Admin a = new Admin() ; 
                      a.setVisible(true) ; 
                      
                      //javax.swing.JFrame f = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(VoteMenuBar.this) ; 
                      Login f = new Login() ; 
                     // f.dispose() ; 
                      f.setVisible(false) ; 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null , "mot de passe erroné" , "erreur connection",JOptionPane.ERROR_MESSAGE) ; 
                  
                    }
                  } 
                  
							}
							if(s.equals("changer zones de travail"))
							{
							
                //interfaces.VotePane.changeLayout() ; 
               if(i%2==0)
               {
                VoteStat.p.removeAll() ;
                interfaces.VotePane.first_creation = true ; 
                VoteStat.p.add(interfaces.VotePane.getOptionalLayout()) ; 
                repaint() ; 
                revalidate() ; 
               i++ ; 
               }
               else
               {
                VoteStat.p.removeAll() ;
                interfaces.VotePane.first_creation = true ; 
                VoteStat.p.add(interfaces.VotePane.CreateLayout()) ; 
                repaint() ; 
                revalidate() ; 
                i++ ; 
               }
							}
							if(s.equals("déconnecter"))
							{
                
                Login l = new Login() ; 
                l.setVisible(true) ; 
                
                javax.swing.JFrame f = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(VoteMenuBar.this) ; 
                f.setVisible(false) ; 
							}
						}
					}