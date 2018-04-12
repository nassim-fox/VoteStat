
  

      
    //******************************************************
    //**** email de l'autheur : nassim.bouhadouf@gmail.com 
    //******************************************************
    //**** date : du 10 au 12 2017  
    //**** projet Ihm Application de statistiques de Vote 
    //**** nom de l'application : Vote Stat 
    //*******************************************
    
      //************************* interface principale de l'application  **************************
      
        package interfaces ; 



					import javax.swing.JPanel ; 
					import javax.swing.JButton ; 
					import javax.swing.BoxLayout ; 
					import javax.swing.Box ;  
					import javax.swing.JTable ;
					import javax.swing.table.DefaultTableModel ;
					import javax.swing.table.DefaultTableCellRenderer ;   
					import javax.swing.JTextField ;
					import javax.swing.JLabel ; 
					import javax.swing.border.* ; 
					import javax.swing.JComboBox ; 
					import javax.swing.Timer ; 
					import javax.swing.JScrollPane ; 
					import javax.swing.Action ; 
					import javax.swing.AbstractAction ;
					import javax.swing.table.TableModel;
					import javax.swing.text.html.* ;
					import javax.swing.ImageIcon ; 
					import javax.swing.Icon ;
					import java.awt.event.* ; 
					import java.awt.BorderLayout ;
					import java.awt.FlowLayout ;
					import java.awt.Dimension ;
					import java.awt.Toolkit ;  
					import java.awt.Graphics ; 
					import java.awt.Color ; 
					import java.awt.Rectangle ; 
					import java.awt.Point ;
					import java.awt.Font ; 
					import java.awt.FontFormatException ; 
					import java.awt.GraphicsEnvironment ; 
					import java.lang.Integer;
					import java.util.* ;
          
          

          import java.net.URISyntaxException ; 
          import java.net.URL ; 
          
          import java.io.File ; 
          import java.io.InputStream ; 
          import java.io.BufferedInputStream ; 
          import java.io.FileInputStream ; 
          import java.io.InputStreamReader ; 
          import java.io.IOException ; 
          import java.io.FileNotFoundException ;
          
          
          import sql_connection.* ;

          import styles.StyleSheetA ;


					public class VotePane extends JPanel
					{

            private static final String TABLE_FONT = "./fonts/Aller_Rg.ttf" ; 
            
						private static JTable table ;
						private static JScrollPane scrollpane ;
            private static JLabel name ;
						private static FormPane form ;
						private static JPanel  inside , b_stat_pane ; //, pform ;
						private static JPanel p ;
						public static DefaultTableModel mod ;
						private static Action delete ;
						private static Stats stats ;       
            private JTextField search ; 
            private static JComboBox<String> ordre ;    // ComboBox pour choisir l'ordre du tableau 
            private final String [] list_ordres  = { "ordre par id","ordre par nom","ordre par prenom","ordre par date"} ; 
            private static VoteGetSql voteGetSql ; // declaration de la classe d'accés à la base de donnée **********************************
						static JPanel [] o ;

						private static int mouve_form , mouve_stat , mouve_b_stat , xform , yform  , xm  , tmpi ;
						private static int click ;
						private static JButton b ;           //***** bouton supprimer ***********
						private static JButton af  ;         //***** bouton afficher statistiques ***********
						Rectangle r , rn ;
						Point pn , pm ;
						JPanel sp ;
						public static boolean first_creation = true ;

            private final static String appName = "Vote" ;

						private static VoteLineChart voteline ;

						private static Timer t , t2 ;

						public VotePane()
						{
              
              
              //** liaison avec feuille de style css **** 
						  HTMLEditorKit h = (new StyleSheetA("styles/StylesA.css")).getHTMLEditorKit() ;
              
              //** ajout de l'interface de la forme 
							form = new FormPane() ;

							 p = new JPanel() ;
						   p.setBackground(new Color(200, 200, 200)) ;
							//p.setPreferredSize(new Dimension(600, 400)) ;

							//p.setBounds(1600,0,0,0) ;

                            stats = new Stats() ; //*** useless 

                           // p.add(stats) ;

							p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
							voteline = new VoteLineChart() ;
							p.add(Box.createHorizontalStrut(10)) ;
                            p.add(voteline) ;

             
             
							p.setMinimumSize(new Dimension(0, voteline.getWidth())) ;

              
              search = new JTextField() ;/*{ //** useless
              
              Icon icon=new ImageIcon("img/search24.png");
 
                protected void paintComponent(final Graphics g) {
                  super.paintComponent(g);
                  icon.paintIcon(null, g, 0, 0);
                  }
              } ; */
              
							setBackground(new Color(0,0,0)) ;

							name = new JLabel("<html><font style=' color : black ; font-size : 30pt ;  '  >vote</font></html>") ;


					   	   b = new JButton("Suprimer") ;
                 b.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/delete24.png")))  ;
                 b.setBackground(new Color(250,255,250)) ;
                 //b.setEnabled(false) ; 
                 
                 af = new JButton("Afficher statistiques") ;
                  af.setForeground(new Color(0,47,85)) ; 
                  
                  //initialisation de la liste d'ordre ***********************************
                  ordre = new JComboBox<>(list_ordres) ; 
                  ordre.setMaximumSize(new Dimension(100,20)) ; 
                  ordre.addItemListener(new OrdreAction()) ; 
                 
                //initialisation de la base de donnée ****************************************
                voteGetSql = new VoteGetSql("Vote") ; 
                 
						    CreateTable() ; // creation de la table *************************

						   	add(CreateLayout()) ; // creation du layout *************************
         
         
                

							  pn = new Point(0,0) ;
							  r = new Rectangle() ;
							  pm = new Point(0,0) ;
							  rn = new Rectangle() ;



      
                //************ animation de l'affichage des statistiques **************


                click = 1 ;

						    t = new Timer(10,new ActionListener()
							{
								public void actionPerformed( ActionEvent e )
								{
                  //o[0].setBounds(i,pn.y,xform,yform) ;
                  					p.setBounds(mouve_stat,0,xm,600) ;
									//o[0].setLocation(i,50) ;
									form.setLocation(mouve_form, 0) ;
									//sp.setLocation(mouve_form,sp.getY()) ;

									//b_stat_pane.setLocation(mouve_b_stat,0) ;
									//System.out.println(i+" "+pn.y+" "+xform+" "+yform) ;
									af.setBounds(mouve_b_stat,af.getY(),af.getWidth(),af.getHeight()) ; //first try
                  
                  if(mouve_form>-20)
                  {
                  
									mouve_form-=12 ;
									mouve_stat-=25 ;
									mouve_b_stat-=25 ;
									xm+=20 ;
								  }
									if(mouve_form<-20)
									{
									  
									  t.stop() ;
                  }
                   
								}
							}) ;

							 t2 = new Timer(10,new ActionListener()
							{
								public void actionPerformed( ActionEvent e )
								{

                  //o[0].setBounds(i,pn.y,xform,yform) ;
									p.setBounds(mouve_stat,0,xm,400) ;
									//o[0].setLocation(i,50) ;
									form.setLocation(mouve_form,0) ;
									//sp.setLocation(mouve_form,sp.getY()) ;

									//b_stat_pane.setLocation(mouve_b_stat,0) ;
									af.setBounds(mouve_b_stat,af.getY(),af.getWidth(),af.getHeight()) ; //first try
                  
                  if(mouve_form<tmpi-100)
                  {
                  mouve_form+=12 ;
									mouve_stat+=25 ;
									mouve_b_stat+=25 ;
									xm-=20 ;
									}
									if(mouve_form>tmpi-100)
									{
										t2.stop() ;

                  }
                  
                  
                  
								}
							}) ;

							//b
							af.setContentAreaFilled(false) ;

							af.addActionListener(new ActionListener() {
													 public void actionPerformed(ActionEvent e) {

														 if (first_creation) {
															 o[0].add(p); // first try
															 p.setPreferredSize(new Dimension(0, 400));
                            
														 }
														 //p.setVisible(true) ;

														 //p.setOpaque(true) ;
														 stats.setVisible(true);
														 //p.setVisible(true) ;

														 pn = null ; //p.getLocationOnScreen();
														 r = null ; //p.getGraphicsConfiguration().getBounds();

														 pm = null ; //form.getLocationOnScreen();
														 rn = null ; //form.getGraphicsConfiguration().getBounds();


														 if (click % 2 != 0) {
															 //p.setVisible(true);
															 o[0].revalidate();
															 o[0].repaint();
															 af.setText("masquer  statistiques");
															 stats.setVisible(true);
															 
															 mouve_stat = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 200;//pform.getX() ;


                                                                 p.setBounds(0,0,mouve_stat,0);

															 //mouve_b_stat = b_stat_pane.getX() + b_stat_pane.getWidth() ;
															 mouve_b_stat = mouve_stat - af.getWidth(); //p.getX()  ;
															 mouve_form = form.getWidth() - 300;
															 tmpi = mouve_form;
                              if(first_creation)
                              {
                                  System.out.println("first creation") ; 
                                  mouve_form = 407 ;
                                  tmpi = mouve_form;
                             
                                  first_creation = false ; 
                              }
                              
                            //  System.out.println(mouve_form) ;
                             // System.out.println(mouve_stat) ;
                             // System.out.println(xm) ;
                              
															t.start();
														 } else {

															 af.setText("afficher statistiques");
															 mouve_stat = p.getX();

															 //mouve_b_stat = b_stat_pane.getX() + b_stat_pane.getWidth() ;
															 mouve_b_stat = p.getX() - af.getWidth();
															 mouve_form = form.getX();
															 t2.start();
															 
															 
															
														 }


									click++;
								}

							}) ;
              
              //*** action pour le bouton suprimer ******
              b.addActionListener(new ActionListener()
              {
                public void actionPerformed( ActionEvent e )
                {


                  int k = 0 ;

                  for(int i = 0 ; i < table.getModel().getRowCount() ; i++ )
                  {
                     if((Boolean)table.getModel().getValueAt(i,6))
                     {
                    //ButtonColumn buttonColumn = new ButtonColumn(table, delete, 5);
                    //buttonColumn.setMnemonic(KeyEvent.VK_D);

                    k++ ;

                    String sk = (String)table.getModel().getValueAt(i,0) ;
                    int ski = Integer.parseInt(sk) ;

                     DefaultTableModel m = (DefaultTableModel)table.getModel() ;

                     VoteDeleteEditSql v = new VoteDeleteEditSql("Vote",ski) ;
                     System.out.println(ski+"selected") ;
                     m.removeRow(i) ;     
                     i-- ;

              
					 }


                }
					System.out.println(k) ;
					//***  ajout de l'interface de graphe 
					setLineChart(new VoteLineChart()) ;
					
              }
              }) ;


							//t2.start() ;


						}
						public static JPanel CreateLayout()
						{

                

                p.setOpaque(false) ;
                stats.setVisible(false) ;
                //p.setVisible(false) ;
                Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize() ;
                double widths = ssize.getWidth() ;

						    inside = new JPanel() ;
							o = new JPanel[4] ;




							inside.setPreferredSize(new Dimension((int)widths,(int)ssize.getHeight()-100)) ;

							inside.setLayout(new BoxLayout(inside,BoxLayout.PAGE_AXIS)) ;

							for(int i = 0 ; i < 4 ; i++ )
							{
								o[i] = new JPanel() ;
								o[i].setLayout(new BoxLayout(o[i],BoxLayout.LINE_AXIS)) ;

							}

               
               b_stat_pane = new JPanel() ;
               //b_stat_pane.setLayout(new BorderLayout()) ;
               //b_stat_pane.setLayout(new BoxLayout(b_stat_pane,BoxLayout.PAGE_AXIS)) ;
               b_stat_pane.setPreferredSize(new Dimension(100,200)) ;

               //b_stat_pane.add(af,BorderLayout.WEST) ;
               //b_stat_pane.setBounds(-500,100,0,0) ;
              //p.add(b_stat_pane) ;
              //p.setOpaque(false) ;
              
              JPanel lsf = new JPanel()
              {
                Icon icon=new ImageIcon(getClass().getClassLoader().getResource("img/search24.png"));
 
                protected void paintComponent(final Graphics g) {
                  super.paintComponent(g);
                  icon.paintIcon(null, g, 0, 0);
                  }
              } ;
              
              lsf.setPreferredSize(new Dimension(20,20)) ;
              
              name.setBounds(10, 10, 100, 20) ;
              
							inside.add(Box.createVerticalStrut(50)) ;
							/*o[2].add(Box.createHorizontalStrut(200)) ;
							o[2].add(name) ;
							o[2].add(Box.createHorizontalStrut(200)) ; 
							o[2].add(lsf) ;
							o[2].add(Box.createHorizontalStrut(10)) ;
							o[2].add(search) ; 
							inside.add(o[2]) ;*/
							inside.add(Box.createVerticalStrut(50)) ;
							o[0].add(Box.createHorizontalStrut(100)) ;
							o[0].add(form) ;
							//JPanel ZB2 = new JPanel() ;
							//ZB2.setOpaque(false);
							//ZB2.setPreferredSize(new Dimension(400,0));
							o[0].add(Box.createHorizontalStrut(50)) ;  // between stat and form
							//b_stat_pane.add(Box.createVerticalStrut(200)) ;
              //b_stat_pane.add(af) ;
              //o[0].add(b_stat_pane) ;
              o[0].add(af) ; // first try ******************************************************************
              //o[0].add(p) ; // first try

							inside.add(o[0]) ;
						/*	sp = new JPanel() ; // using a new pane instead of box.lina_axi *****************
							sp.add(form) ;


							sp.add(af) ;
							sp.add(p) ;   //******************************************************************
							*/inside.add(Box.createVerticalStrut(50)) ;
							inside.add(b) ;
							inside.add(Box.createVerticalStrut(20)) ;
							//inside.add(scrollpane) ;

              o[0].setPreferredSize(new Dimension(inside.getWidth(),275)) ;
							o[0].setBackground(new Color(255,255,255)) ;//(20,200,200)) ;
							inside.setBackground(new Color(255,255,255)) ;
              
              o[3].add(Box.createHorizontalStrut(600)) ; 
              JLabel tvote = new JLabel("Table de votes") ; 
              tvote.setForeground(new Color(255,255,255)) ; 
              
              o[3].add(tvote) ;
							o[3].add(Box.createHorizontalStrut(580)) ; 
							o[3].add(ordre) ;
							o[3].add(Box.createVerticalStrut(20));
							o[3].setBackground(new Color(12,84,135,50)) ; 
							inside.add(o[3]) ;
							o[1].add(scrollpane) ;
							o[1].add(b) ; //********** ajout du bouton suprimer ***********
							//o[1].add(Box.createVerticalStrut(10));
							
							//o[1].add(buttons) ; 
              inside.add(o[1]) ;
               
               inside.setOpaque(false) ;
               o[0].setOpaque(false) ; 
               
               o[1].setOpaque(false) ; 

							//p.setVisible(false);
							//add(inside) ;
              
              return inside ; 
          
        

						}
						
						//******************* pour changer les zones de travail ****************
						
						public static JPanel getOptionalLayout()
						{
						 p.setOpaque(false) ;
                stats.setVisible(false) ;
                //p.setVisible(false) ;
                Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize() ;
                double widths = ssize.getWidth() ;

						    JPanel inside2 = new JPanel() ;
							o = new JPanel[4] ;
              
              inside2.setPreferredSize(new Dimension((int)widths,(int)ssize.getHeight()-100)) ;

							inside2.setLayout(new BoxLayout(inside2,BoxLayout.PAGE_AXIS)) ;

							for(int i = 0 ; i < 4 ; i++ )
							{
								o[i] = new JPanel() ;
								o[i].setLayout(new BoxLayout(o[i],BoxLayout.LINE_AXIS)) ;

							}


               b_stat_pane = new JPanel() ;
               b_stat_pane.setPreferredSize(new Dimension(100,200)) ;

                  
              JPanel lsf = new JPanel()
              {
                Icon icon=new ImageIcon(getClass().getClassLoader().getResource("img/search24.png"));
 
                protected void paintComponent(final Graphics g) {
                  super.paintComponent(g);
                  icon.paintIcon(null, g, 0, 0);
                  }
              } ;
              
              lsf.setPreferredSize(new Dimension(20,20)) ;
              
              name.setBounds(10, 10, 100, 20) ;
              
							inside2.add(Box.createVerticalStrut(50)) ;
				  		inside2.add(Box.createVerticalStrut(50)) ;
				  		
				  		 //**************** la partie table de votes *************************************
              o[3].add(Box.createHorizontalStrut(600)) ; 
              JLabel tvote = new JLabel("Table de votes") ; 
              tvote.setForeground(new Color(255,255,255)) ; 
              
              o[3].add(tvote) ;
							o[3].add(Box.createHorizontalStrut(580)) ; 
							o[3].add(ordre) ;
							o[3].add(Box.createVerticalStrut(20));
							o[3].setBackground(new Color(12,84,135,50)) ; 
							inside2.add(o[3]) ;
							o[1].add(scrollpane) ;
						
               o[1].add(b) ;  // ajout du bouton suprimer
							 
              inside2.add(o[1]) ;
              inside2.add(Box.createVerticalStrut(50)) ; 
              
							//**************** la parite formulaire statistiques ************************************
							o[0].add(Box.createHorizontalStrut(100)) ;
							o[0].add(form) ; // ajout du formulaire
							o[0].add(Box.createHorizontalStrut(50)) ;  // between stat and form
						  o[0].add(af) ; // ajout du bouton afficher statistiques 
						  
							inside2.add(o[0]) ;
						  
						  inside2.add(Box.createVerticalStrut(50)) ;
							inside2.add(Box.createVerticalStrut(20)) ;
							
						  o[0].setPreferredSize(new Dimension(inside2.getWidth(),275)) ;
							o[0].setBackground(new Color(255,255,255)) ;
							inside2.setBackground(new Color(255,255,255)) ;
              
             
               
               inside2.setOpaque(false) ;
               o[0].setOpaque(false) ; 
               
               o[1].setOpaque(false) ; 
               
              
							//add(inside) ;
              return inside2 ; 
          
              }
						private static void CreateTable()
						{

							table = new JTable() ;

              TableComponent tt ;

              table.setDefaultRenderer(JButton.class,new TableComponent()) ;
              
              //alignement du texte du tableau dans le centre ******************************
              DefaultTableCellRenderer cr = new DefaultTableCellRenderer() ;
              cr.setHorizontalAlignment(JLabel.CENTER) ; 
              
              table.setDefaultRenderer(String.class,cr) ;
              //****************************************************************************
              
              String [] s = {"id","nom","prenom","email","date de vote","",""} ;
              //String [][] ss = {{"s","","","","",""},{"s","ss","ss","","",""}} ;
             // List<Object[]> so = new VoteGetSql("Vote").getInf() ;

        
              Object[][] ss = new VoteGetSql("Vote").getInf() ;

              
              mod = new TableDeVoteB(ss,s) ;
							table.setModel(mod) ; //new TableDeVoteB(ss,s)) ;
               
               
              

             
							delete = new AbstractAction() {
								public void actionPerformed(ActionEvent e) {
									JTable table = (JTable)e.getSource();
									int modelRow = Integer.valueOf( e.getActionCommand() );
              TableModel md = table.getModel() ;
              FormPane.setTextField(md.getValueAt(modelRow,1).toString(),
											md.getValueAt(modelRow, 2).toString(),
												md.getValueAt(modelRow,3).toString() ,
													Integer.parseInt(md.getValueAt(modelRow,0).toString())) ;
				form.repaint() ;
					form.revalidate() ;
                  
                  if(mouve_form<0)
                  {
                  
                  t.start() ; 
                  }
                  else
                  {
                    mouve_b_stat = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - af.getWidth() ;//pform.getX() ;
										
										tmpi = mouve_form;
                            	 
                  t2.start() ;
                  }
                }
              };

              ButtonColumn buttonColumn = new ButtonColumn(table, delete, 5);
              buttonColumn.setMnemonic(KeyEvent.VK_D);

              //table.setBackground(new Color(200,200,255)) ;
              table.setBackground(new Color(12,84,135)) ;
              table.getTableHeader().setBackground(new Color(0,47,80)) ;
              table.getTableHeader().setForeground(new Color(255,255,255)) ;
              table.setSelectionBackground(new Color(100,100,200)) ; 
              table.setSelectionBackground(new Color(255,255,255)) ; 
              table.setForeground(Color.WHITE) ;
              table.setShowVerticalLines(false) ; 
              table.setBorder(new LineBorder(new Color(0,0,0),1,false)) ; 
              
               
              
              Font f ; 
              
              try {
                GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
                
                //URL fontURL = VotePane.class.getResource(TABLE_FONT);
                
               
                InputStream fontStream =  VotePane.class.getResourceAsStream("/Aller_Rg.ttf") ;
         //new FileInputStream("/bin/Aller_Rg.ttf") ; 
                //VotePane.class.getResourceAsStream("C://Users/programmeur/Documents/tp ihm projet/JAVA3/Aller_Rg.ttf") ;
         
          
                // VotePane.class.getResourceAsStream(TABLE_FONT) ; //new BufferedInputStream( new InputStreamReader(VotePane.class.getResourceAsStream(TABLE_FONT)));
               
        
             //   f = Font.createFont(Font.TRUETYPE_FONT, new File(TABLE_FONT)) ;//new File(VotePane.class.getResourceAsStream(TABLE_FONT))) ;              
                                                                 //getResource(TABLE_FONT).toURI())) ; 
                
                
                 f = Font.createFont(Font.TRUETYPE_FONT, fontStream) ; //new File(TABLE_FONT)) ;
                // System.out.println(VotePane.class.getResource("Aller_Rg.ttf").toString()) ; 
                 
                 ge.registerFont(f);
                 table.setFont(f.deriveFont(16.0f)) ; 
              
                 
                  } 
                  catch (IOException|FontFormatException e) 
                  {
                          e.printStackTrace() ; 
                   }
                /*  catch(URISyntaxException e)
                {
                        e.printStackTrace() ; 
                }*/
              
             
							scrollpane = new JScrollPane(table) ;
							scrollpane.setPreferredSize(new Dimension(1200,400)) ;
							scrollpane.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder()) ;
							scrollpane.setBorder(javax.swing.BorderFactory.createEmptyBorder()) ;
							scrollpane.setOpaque(false) ;
							scrollpane.getViewport().setOpaque(false) ;
              
              table.getColumnModel().getColumn(0).setMaxWidth(40) ;
              table.getColumnModel().getColumn(6).setMaxWidth(40) ;
              table.getColumnModel().getColumn(5).setMaxWidth(100) ;
              
              
						}
						//*** ajout à la table de vote 
             public static void addToTable(Object[] row)
             {
              mod.addRow(row) ;
             }
             public static JTable getTable()
             {
                return table ;
             }
             public void setTable(JTable t)
             {
                  table = t ;

                  CreateTable() ;


              }
              //****** recharge les statistiques 
             public static void setStatis(Stats s)
             {
               stats.setVisible(false) ;
			   stats = s ;

              // p.add(stats) ;

               if(click%2!=0)
               {
               stats.setVisible(false) ;
               //p.setVisible(false) ;
               } 
              // p.revalidate() ; 
              // p.repaint() ; 

             }
             
			 public  static void setLineChart(VoteLineChart v )
			 {
				 voteline.setVisible(false) ;
				 voteline = v ;
         
         int vs = p.getHeight() ; 
            
				 p.add(voteline) ;
				 
				 p.setBounds(1600,0,0,400) ;
         
         //t.start() ;
         
         if(mouve_form<0)
                  {
                 
                  t.start() ; 
                  }
                  else
                  {
                    mouve_b_stat = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - af.getWidth() ;//pform.getX() ;
										
										tmpi = mouve_form;
                            	 
                  t2.start() ;
                  }

			 }
			 //******** fonction pour la recharge statique de la table des votes ************************
			 public static void loadTable()
			 {
        scrollpane.setVisible(false) ; 
        b.setVisible(false) ; 
        CreateTable() ; 
        scrollpane.setVisible(true) ;
        o[1].add(scrollpane) ;
        b.setVisible(true) ;  
				o[1].add(b) ;
        
         if(mouve_form<0)
                  {
                 
                  t.start() ; 
                  }
                  else
                  {
                    mouve_b_stat = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - af.getWidth() ;//pform.getX() ;
										
										tmpi = mouve_form;
                            	 
                  t2.start() ;
                  }
                  
			 }
			 
			 //**** fonction d'appel pour la recherche rapide ************************
			 public static void searchTable(String nom,String prenom , String email )
			 {
			   
          int ir ; 
          if(table.getSelectedRow()>=0)
          ir = getRow(table.getSelectedRow(),nom,prenom,email) ;
          else
          ir = getRow(-1,nom,prenom,email) ; 
          table.setRowSelectionInterval(ir,ir);
          table.scrollRectToVisible(new Rectangle(table.getCellRect(ir, 0, true)));
          
          javax.swing.JOptionPane.showMessageDialog(null," id : "+table.getModel().getValueAt(ir,0)
                                                          +"\n nom : "+table.getModel().getValueAt(ir,1)+
                                                          "\n prenom : "+table.getModel().getValueAt(ir,2)+
                                                          "\n email : "+table.getModel().getValueAt(ir,3) 
                                                        ,"vote cherché",javax.swing.JOptionPane.INFORMATION_MESSAGE) ; 
			 }
			 
			 //**** algorithme de recherche rapide ***********************************************
			 private static int getRow(int m , String nom , String prenom ,String email)
			 {
			   //** chercher le nombre maximum d'information donnée *********
			   
			  int v [] = new int[table.getModel().getRowCount()] ; 
			    
        for(int i = 0 ; i < table.getModel().getRowCount() ; i++)
        {
          
          boolean n , p , e ;
          
          if(table.getModel().getValueAt(i,1).equals(nom))
          {
            v[i]++ ; 
          }
          if(table.getModel().getValueAt(i,2).equals(prenom))
          { 
            v[i]++ ; 
          }
          if(table.getModel().getValueAt(i,3).equals(email))
          {
            v[i]++ ;
          }
         
          
          
          }
          
          int max = v[0] ; 
          TableModel ms = table.getModel() ; 
          
          for(int i = 1 ; i < v.length ; i++)
          {
            if(v[i]>max)
            max = v[i] ; 
          }
          
         
        
        
          for( int i = m + 1 ; i < v.length ; i++)
          {
            boolean a = ms.getValueAt(i,1).equals(nom) ; 
            boolean b = ms.getValueAt(i,2).equals(prenom) ; 
            boolean c = ms.getValueAt(i,3).equals(email) ; 
            boolean anull = nom.isEmpty() ; 
            boolean bnull =  prenom.isEmpty() ; 
            boolean cnull =  email.isEmpty() ; 
            
            
            if(v[i]==max)
            {
              switch(max)
              {
                case 1 : 
                 if((a&&bnull&&cnull)||(anull&&b&&cnull)||(anull&&bnull&&c))
                 return i ;
                 break ; 
                case 2 : 
                 if((a&&b&&cnull)||(a&&bnull&&c)||(anull&&b&&c))
                 return i ;
                 break ; 
                case 3 : 
                if(a&&b&&c)
                 return i ;
                 break ; 
              }
            }
          }
          
          if(m==-1)
          {
           javax.swing.JOptionPane.showMessageDialog(null,"vote non trouvé","vote non trouvé",javax.swing.JOptionPane.INFORMATION_MESSAGE) ;
           return -1 ; 
          }
          //**** retour toujours avec le cas ou aucune case n'est selectionnée 
          return getRow(-1,nom,prenom,email) ;
        }
        public static void changeLayout()
        {
         
         // inside.removeAll() ; 
         
         //inside = getOptionalLayout() ;
    
                            
         }
        //***** peindre l'arrière plan  
        public void paintComponent(Graphics g)
        {
          super.paintComponent(g) ; 
          
          g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("img/background_im.jpg")).getImage(),0,0,null) ;
          
        }
        private class OrdreAction implements ItemListener     //******** class pour les action de la liste d'ordre   *********************
        {
          public void itemStateChanged( ItemEvent e)
          {
             //String s = e.getActionCommand() ;
             String s = "" ; 
             if ( e.getStateChange () == ItemEvent.SELECTED )
             s = e.getItem().toString() ;
            /**/
            //actions pour les elements de la liste d'ordre 
            /**/
            
            if(s.equals(list_ordres[0]))
            {
               voteGetSql.setOrdre("vote","id") ; 
             
               loadTable() ; 
               repaint() ; 
               revalidate() ; 
            }
            if(s.equals(list_ordres[1]))
            {
                voteGetSql.setOrdre("vote","name") ; 
               VotePane.loadTable() ; 
            }
            if(s.equals(list_ordres[2]))
            {
                voteGetSql.setOrdre("vote","prenom") ; 
               loadTable() ; 
            }
            if(s.equals(list_ordres[3]))
            {
                voteGetSql.setOrdre("vote","email") ; 
               loadTable() ; 
            }
          }
        }
					}
