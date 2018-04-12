

          package interfaces ; 

					import javax.swing.* ; 
					import javax.swing.table.* ;
					import java.awt.* ;  
					import java.util.Vector ; 







					public class TableComponent extends DefaultTableCellRenderer
					{
					
					
						public Component getTableCellRendererComponent(JTable table,Object val,boolean isselected , boolean hasfocus , 
                                                                int row , int col )
           {
             if(val instanceof JButton )
             return (JButton) val ; 
             else
             return this ; 
             
           }
                                                                
					}