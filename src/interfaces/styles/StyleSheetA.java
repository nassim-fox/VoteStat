



	
		package styles ; 


						

				import javax.swing.text.* ;
				import javax.swing.text.html.* ;
				import java.io.BufferedReader ; 
				import java.io.InputStream ; 
				import java.io.InputStreamReader ; 
				import java.io.FileInputStream ; 
				import java.io.File ; 
				import java.io.FileNotFoundException ; 
				import java.io.IOException ;
        import java.net.URISyntaxException ;  
        
				public class StyleSheetA 
				{	

					private HTMLEditorKit h ; 
					private StyleSheet s ; 


					public StyleSheetA(String f)
					{

					    s = new StyleSheet() ; 

						try
						{
						
						InputStream i  = ClassLoader.getSystemResourceAsStream(f) ;  // new FileInputStream(new File(getClass().getResourceAsStream(f))) ; 
						
						
						BufferedReader b = new BufferedReader(new InputStreamReader(i)) ; 

						s.loadRules(b,null) ; 

						
						}
						catch(IOException e)
						{
							e.printStackTrace() ; 
						}
						
						h = new HTMLEditorKit() ; 
						h.setStyleSheet(s) ; 

					}

					public HTMLEditorKit getHTMLEditorKit()
					{
						return this.h ; 
					}
					public StyleSheet getStyleSheet()
					{
						return this.s ; 
					}
				}