package eui.uva.jfbasiceditor.action.format.style;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyleRange;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class UnderLineAction extends Action {
	JFaceBasicEditor editor;
	
	public UnderLineAction(JFaceBasicEditor w){
		editor = w;
		try
		{
			super.setChecked(false);
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_underline.png"));
			setImageDescriptor(icon);
			setText("  &Subrayado@Ctrl+S");
			setToolTipText("Subrayado");
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
			style.underline = true;
			editor.setStyleRange(style);
		}
		else{
			style = editor.getStyleRange();
			style.underline = false;
			editor.setStyleRange(style);
		}
	}
}
