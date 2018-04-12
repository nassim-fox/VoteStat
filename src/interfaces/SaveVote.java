

    package interfaces ; 
    
    
    
    
    import javax.swing.JFrame ; 
    import javax.swing.JPanel ;
    import javax.swing.JButton ; 
    import javax.swing.JCheckBox ; 
    import javax.swing.JRadioButton ; 
    import javax.swing.BoxLayout ; 
    import javax.swing.Box ; 
    import javax.swing.JComponent ; 
    import java.awt.event.* ;  
    import java.awt.Dimension ; 
    
    
    
    public class SaveVote extends JFrame
    {
      private JCheckBox table , graphe , ecrit ; 
      private JButton save , annuler ;
      private JPanel p ;

      public SaveVote()
      {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
        setUndecorated(true) ;
        setLocationRelativeTo(getComponentAt(200,200)) ;

        table = new JCheckBox("tableau de votes ") ;
        graphe = new JCheckBox("statistiques en graphe ") ;
        ecrit = new JCheckBox("statistiques en ecrit") ;

       save = new JButton("enregistrer") ;
          annuler = new JButton("annuler") ;

          annuler.addActionListener(new ActionListener()
          {
              public void actionPerformed( ActionEvent e )
              {
                  setVisible(false) ;
              }
          });

          p = new JPanel() ;

          p.add(CreateLayout()) ;

          add(p) ;

          pack() ;

          setVisible(true);

      }
      private JPanel CreateLayout()
      {
        JPanel inside = new JPanel() ;
        JPanel o = new JPanel() ;

        inside.setLayout(new BoxLayout(inside, BoxLayout.PAGE_AXIS)) ;
        o.setLayout(new BoxLayout(o,BoxLayout.LINE_AXIS));

          inside.add(Box.createVerticalStrut(50)) ;
          inside.add(table) ;
          inside.add(Box.createVerticalStrut(20)) ;
          inside.add(graphe) ;
          inside.add(Box.createVerticalStrut(20)) ;
          inside.add(ecrit) ;
          inside.add(Box.createVerticalStrut(20)) ;
          o.add(Box.createHorizontalStrut(20)) ;
          o.add(save) ;
          o.add(Box.createHorizontalStrut(20)) ;
          o.add(annuler) ;
          inside.add(o) ;

          inside.setPreferredSize(new Dimension(400,250));
        return inside ;

      }
      
    }