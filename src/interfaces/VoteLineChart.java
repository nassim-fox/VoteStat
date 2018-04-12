



        package interfaces ; 
        


      import javax.swing.JPanel ;
      import org.jfree.chart.ChartFactory ;
      import org.jfree.chart.ChartPanel ; 
      import org.jfree.chart.JFreeChart ;
      import org.jfree.ui.ApplicationFrame ; 
      import org.jfree.ui.RefineryUtilities ;
      import org.jfree.chart.plot.PlotOrientation ; 
      import org.jfree.data.category.DefaultCategoryDataset ;
      import org.jfree.chart.plot.CategoryPlot ;
      import org.jfree.chart.axis.CategoryAxis ;
      import org.jfree.chart.axis.NumberAxis ;
      import org.jfree.chart.axis.NumberTickUnit ;
      
      
      
      
      import javax.swing.BoxLayout ; 
      import javax.swing.Box ; 
      
      import java.awt.Dimension;
      import java.lang.Integer;
      import java.lang.Object;
      import java.lang.System;
      import java.lang.StringBuilder ;

      import sql_connection.* ;
      
      
      
      public class VoteLineChart extends JPanel 
      {
        private final static String ChartTitle = " statstiques de votes" ; 
        private VoteGetSql v ; 
        private Object [][] v_stat ;
        private Object[][] m ;
        private static ChartPanel c ; 
        private JFreeChart chart ; 
        public static int chart_type = 1 ; 
        public static final int BAR_CHART = 0 ;
        public static final int LINE_CHART = 1 ;
        
        public VoteLineChart()
        {
          
         setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS)) ; 
          
          
          if(chart_type==BAR_CHART)
          chart = ChartFactory.createBarChart(ChartTitle,"jours","votes",CreateData(),PlotOrientation.VERTICAL,true,true,false) ; 
          else if(chart_type==LINE_CHART)
          chart = ChartFactory.createLineChart(ChartTitle,"jours","votes",CreateData(),PlotOrientation.VERTICAL,true,true,false) ; 
         
         //l'axe des Y ne contient que des entiers
          CategoryPlot plot = chart.getCategoryPlot();
          CategoryAxis xAxis = plot.getDomainAxis();
          NumberAxis nvote = (NumberAxis)plot.getRangeAxis();
          nvote.setStandardTickUnits(nvote.createIntegerTickUnits());
          
          //l'axe des Y s'incremente par 1
          nvote.setTickUnit(new NumberTickUnit(1));

           
         c = new ChartPanel(chart) ; 
         c.setMinimumSize(new Dimension(600,275)) ;//(350, 300)) ;


            
            add(c) ;
           setPreferredSize(new Dimension(600,275)) ;//(350, 300)) ;


         
        }
        private DefaultCategoryDataset CreateData()
        {
          DefaultCategoryDataset ds = new DefaultCategoryDataset() ; 
          
          v = new VoteGetSql("Vote") ; 
         
          v_stat = v.getInf() ;


            for(int i = 0 ;  i < v_stat.length ; i++ )
            {
                String s = v_stat[i][4].toString() ; //m[i][0].toString() ;
                v_stat[i][4] = s.substring(0,10) ;
            }

            m = createVal() ;


      /*ds.addValue( 15, v_stat[0][0].toString() , v_stat[0][1].toString() );
      ds.addValue( 30 , "schools" , "1980" );
      ds.addValue( 60 , "schools" ,  "1990" );
      ds.addValue( 120 , "schools" , "2000" );
      ds.addValue( 240 , "schools" , "2010" );
      ds.addValue(300, "schools", "2014");
       */

            int k = 15 ;

            for(int i = 0 ; i < m.length ; i++ )
            {
                System.out.println(i+" "+m[i][0]+" "+m[i][1]);

                ds.addValue(Integer.parseInt(m[i][1].toString()),"nombre de votes par jour",m[i][0].toString()) ;
                k += 15 ;
            }

      return ds ; 
        }
        private Object[][] createVal()
        {
            int l = 0 ;


            for(int i = 0 ; i < v_stat.length ; i++ )
            {
                boolean b = false ;

                for(int j = i + 1 ; j < v_stat.length ; j++)
                {
                    if(v_stat[j][4].equals(v_stat[i][4]))
                    {

                        b = true ;
                    }
                }
                if(!b) {
                    System.out.println(i+" "+v_stat[i][4]);

                    l++;
                }



            }

            Object[][] ms = new Object[l][2] ;

            System.out.println(l+"  "+ms.length);

            int ls = 0 ;

            for(int i = 0 ; i < v_stat.length ; i++ )
            {
                int k = 1 ;


                if(!check(ms,v_stat[i][4])&&ls<ms.length) {

                    ms[ls][0] = v_stat[i][4] ;

                    for (int j = i+1 ; j < v_stat.length ; j++ )
                    {
                        if(v_stat[i][4].equals(v_stat[j][4]))
                        k++ ;
                    }

                    ms[ls][1] = k ;

                    ls++ ;

                }


            }



            return ms ;
        }
          private boolean check(Object[][] ms , Object s)
          {
              for(int i = 0 ; i < ms.length ; i++)
              {
                  Object mss = ms[i][0] ;

                  if(mss!=null&&mss.equals(s))//ms[i][0]==s)//.equals(s))
                  return true ;
              //
               }


              return false ;
          }
          public void setChart(VoteLineChart c)
          {
            this.c.setVisible(false) ;
            add(c) ;
            
          }
          public void addValToChart(String s)
          {
            DefaultCategoryDataset ds = CreateData() ; 
            if(m[m.length-1][0].toString().equals(s))
            ds.addValue(Integer.parseInt(m[m.length-1][1].toString()),"",s) ; 
            else
            ds.addValue(1,"",s) ; 
            
            chart = ChartFactory.createLineChart(ChartTitle,"jours","votes",ds,PlotOrientation.VERTICAL,true,true,false) ; 
         
          c.setVisible(false) ; 
         
          c = new ChartPanel(chart) ; 
          c.setMinimumSize(new Dimension(600,275)) ;//(350, 300)) ;
        
          c.setVisible(true) ;
              
            add(c) ;
            
            repaint() ; 
            revalidate() ; 
            
          }
      }