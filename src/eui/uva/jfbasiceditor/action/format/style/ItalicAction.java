package eui.uva.jfbasiceditor.action.format.style;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class ItalicAction extends Action {
	JFaceBasicEditor editor;
	
	public ItalicAction(JFaceBasicEditor w){
		editor = w;
		try
		{
			super.setChecked(false);
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_italic.png"));
			setImageDescriptor(icon);
			setText("  Curs&iva@Ctrl+I");
			setToolTipText("Cursiva");
			setDescription("Convertir en cursiva el texto seleccionado");
			editor.setStatus("Pulse convertir en cursiva el texto seleccionado");
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
			style.fontStyle += SWT.ITALIC;
			editor.fontData.setStyle(style.fontStyle);
			style.font = new Font(editor.getShell().getDisplay(),editor.fontData);
			editor.getStyledText().setFont(style.font);
			editor.setStyleRange(style);
		}
		else{
			style = editor.getStyleRange();
			style.fontStyle -= SWT.ITALIC;
			editor.fontData.setStyle(style.fontStyle);
			style.font = new Font(editor.getShell().getDisplay(),editor.fontData);
			editor.getStyledText().setFont(style.font);
			editor.setStyleRange(style);
		}
	}
}
