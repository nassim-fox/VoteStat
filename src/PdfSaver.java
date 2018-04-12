



            
    //******************************************************
    //**** email de l'autheur : nassim.bouhadouf@gmail.com 
    //******************************************************
    //**** date : du 10 au 12 2017  
    //**** projet Ihm Application de statistiques de Vote 
    //**** nom de l'application : Vote Stat 
    //*******************************************


      //*********** sauvegarde pdf 

              
              import java.io.FileOutputStream ; 
              import java.io.IOException ; 
              import java.awt.Color ; 
              import java.util.Date ; 
              import com.itextpdf.text.Document ; 
              import com.itextpdf.text.DocumentException ;
              import com.itextpdf.text.Paragraph ; 
              import com.itextpdf.text.Phrase ; 
              import com.itextpdf.text.Rectangle ; 
              import com.itextpdf.text.Element ; 
              import com.itextpdf.text.FontFactory ; 
              import com.itextpdf.text.pdf.PdfPTable ;
              import com.itextpdf.text.pdf.PdfPTableEvent ;
              import com.itextpdf.text.pdf.PdfPCell ;  
              import com.itextpdf.text.pdf.PdfContentByte ;
              import com.itextpdf.text.pdf.PdfWriter ; 
              import com.itextpdf.text.BaseColor ; 
              
              
              import java.text.DateFormat ; 
              import java.text.SimpleDateFormat ; 

              
              import interfaces.sql_connection.* ;
              
              
              
              
              public class PdfSaver implements PdfPTableEvent
              {
                public PdfSaver(String name)
                {
                  createPdf(name) ; 
                }
                private void createPdf(String name)
                {
                
                  Document pdfs = new Document() ;
                  
                  try
                  {
                    PdfWriter.getInstance(pdfs,new FileOutputStream(name)) ;
                    pdfs.open() ; 

                    
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy   HH:mm ") ; 
                    Date dt = new Date() ; 
                    String date = df.format(dt) ; 
                    
                    Phrase pdate = new Phrase(10f,date,FontFactory.getFont(FontFactory.COURIER, 10f)) ;  
                    
                    pdfs.add(pdate) ; 
                    
                    Paragraph legend = new Paragraph(new Phrase(30f,"table de votes",FontFactory.getFont(FontFactory.HELVETICA, 30f))) ; 
                    legend.setAlignment(Element.ALIGN_CENTER) ; 
                    
                    pdfs.add(legend) ; 
                    
                    
                    PdfPTable stable = new PdfPTable(5) ; 
                    
                    stable.setWidths(new int[]{1,2,2,3,3}) ;
                    
                    PdfPCell cell = new PdfPCell() ; 
                    
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER) ; 
                    cell.setBackgroundColor(BaseColor.CYAN);
                    
                    
                    
                    
                    stable.addCell("id") ; 
                    stable.addCell("nom") ; 
                    stable.addCell("prenom") ; 
                    stable.addCell("email") ;
                    stable.addCell("date") ; 
                    
                    
                   
                    sql_connection.VoteGetSql v = new sql_connection.VoteGetSql("Vote") ; 
                    
                    Object [][] s = v.getInf() ; 
                    
                    for(int i = 0 ; i < s.length ; i++)
                    {
                      for(int j = 0 ; j < 5 ; j++)
                      {
                      
                      
                        cell = new PdfPCell(new Paragraph(new Phrase(10f,s[i][j].toString(),FontFactory.getFont(FontFactory.COURIER, 10f)))) ; 
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER) ; 
                        cell.setBackgroundColor(BaseColor.CYAN);
                    
                        
                        stable.addCell(cell) ;
                      }
                      
                      
                    }
                    
                    
                    
                    
                    pdfs.add(stable) ; 
                     
                     
                     
                    pdfs.close() ;
                    
                    
                    
                  }
                  catch(IOException e)
                  {
                    e.printStackTrace() ; 
                  }
                  catch(DocumentException e)
                  {
                    e.printStackTrace() ;
                  }
                
                }
              
              public void tableLayout(PdfPTable table, float[][] widths, float[] heights,
                  int headerRows, int rowStart, PdfContentByte[] canvases) {
                
                
                int columns;
                Rectangle rect;
                int footer = widths.length - table.getFooterRows();
                int header = table.getHeaderRows() - table.getFooterRows() + 1;
                
                
                  for (int row = header; row < footer; row += 2) {
                    columns = widths[row].length - 1;
                    rect = new Rectangle(widths[row][0], heights[row],
                     widths[row][columns], heights[row + 1]);
                      
                      rect.setBackgroundColor(BaseColor.YELLOW);
                    
                      rect.setBorder(Rectangle.NO_BORDER);
                      canvases[PdfPTable.BASECANVAS].rectangle(rect);
        }
    }
                
              }