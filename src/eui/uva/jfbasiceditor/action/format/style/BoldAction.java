package eui.uva.jfbasiceditor.action.format.style;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class BoldAction extends Action {
	JFaceBasicEditor editor;
	
	public BoldAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			super.setChecked(false);
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_bold.png"));
			setImageDescriptor(icon);
			setText("  &Negrita@Ctrl+B");
			setToolTipText("Negrita");
			setDescription("Convertir en negrita el texto seleccionado");
			editor.setStatus("Pulse convertir en negrita el texto seleccionado");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	StyleRange style = new StyleRange();
	public void run(){
		if(this.isChecked()){
			
			style = editor.getStyleRange();
			style.start = 0;
			style.length = editor.getStyledText().getCharCount();
			style.fontStyle += SWT.BOLD;
			editor.fontData.setStyle(style.fontStyle);
			style.font = new Font(editor.getShell().getDisplay(),editor.fontData);
			editor.setStyleRange(style);
		}
		else{
			style = editor.getStyleRange();
			style.fontStyle -= SWT.BOLD;
			editor.fontData.setStyle(style.fontStyle);
			style.font = new Font(editor.getShell().getDisplay(),editor.fontData);
			//editor.getStyledText().setFont(style.font);
			editor.setStyleRange(style);
		}
	}
}
