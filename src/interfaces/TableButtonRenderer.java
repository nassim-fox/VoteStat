




            package interfaces ;
            
            
            import javax.swing.JButton ;
            import javax.swing.JTable ; 
            import javax.swing.table.* ; 
            import javax.swing.UIManager ; 
            import java.awt.Component ; 
            import java.awt.Color ; 
            import java.awt.* ; 
            


            public class TableButtonRenderer extends JButton implements TableCellRenderer
            {
                public TableButtonRenderer()
                {
                    setOpaque(true) ; 
                }
                public Component getTableCellRendererComponent(
		JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if (isSelected)
		{
			renderButton.setForeground(table.getSelectionForeground());
	 		renderButton.setBackground(table.getSelectionBackground());
		}
		else
		{
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if (hasFocus)
		{
			renderButton.setBorder( focusBorder );
		}
		else
		{
			renderButton.setBorder( originalBorder );
		}

//		renderButton.setText( (value == null) ? "" : value.toString() );
		if (value == null)
		{
			renderButton.setText( "" );
			renderButton.setIcon( null );
		}
		else if (value instanceof Icon)
		{
			renderButton.setText( "" );
			renderButton.setIcon( (Icon)value );
		}
		else
		{
			renderButton.setText( value.toString() );
			renderButton.setIcon( null );
		}

		return renderButton;
	}
            }