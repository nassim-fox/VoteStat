

          package interfaces ; 

					import javax.swing.JTable ; 
					import javax.swing.JButton ; 
					import javax.swing.JCheckBox ; 
					import javax.swing.table.* ; 
					import java.util.Vector ; 







					public class TableDeVoteB extends DefaultTableModel
					{

						private String [] s = {"nom","prenom","email","date de vote"} ; 
						private String [][] ss = {{"s","","","","",""}} ;

						private int row , col ; 
            
            public TableDeVoteB(Object[][] row , Object[] col)
            {
              
              /*for(int i = 0 ; i < s.length ; i++)
              {
                addColumn(s[i]) ; 
              }
              for(int i = 0 ; i < ss.length ; i++) 
              {
                addRow(ss[i]) ; 
              }*/
              
              super(row,col) ;
               
            }
						/*public int getRowCount()
						{
							this.row =  1 ; 
							return this.row ; 
						}
						public int getColumnCount()
						{
							this.col =  6 ; 

							return this.col ; 
						} */
						 public Class getColumnClass(int col) {
              //Vector v = (Vector) dataVector.elementAt(0);
              switch(col)
              {
                case 0 : case 1 : case 2 : case 3 : case 4 :  
                return String.class ; 
                case 5 :
                return JButton.class ; 
                case 6 : 
                return Boolean.class ; 
                
              }
              
                
               return String.class ; 
                
             // return v.elementAt(col).getClass();
              }
             
						public Object getValueAt(int row , int col)
						{
							return super.getValueAt(row,col) ; 
						}
						public boolean getIsCellEditable(int row,int col)
						{
							return row < 3  ; 
						}
						
					}