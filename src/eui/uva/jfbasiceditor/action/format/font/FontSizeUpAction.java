package eui.uva.jfbasiceditor.action.format.font;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class FontSizeUpAction extends Action {
	JFaceBasicEditor editor;
	
	public FontSizeUpAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_grow.png"));
			setImageDescriptor(icon);
			setText("   &Aumentar tama�o@Ctrl+R");
			setToolTipText("Aumenta el tama�o de la fuente");
			setDescription("Aumenta el tama�o de la fuente");
			editor.setStatus("Pulse para aumentar el tama�o de la fuente");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	StyleRange style = new StyleRange();
	public void run(){
		if(editor.fontData.getHeight()<97){
			style = editor.getStyleRange();
			style.start = 0;
			style.length = editor.getStyledText().getCharCount();
			editor.fontData.setHeight(editor.fontData.getHeight()+2);
			style.font = new Font(editor.getShell().getDisplay(),editor.fontData);
			editor.getStyledText().setFont(style.font);
			editor.setStyleRange(style);
			editor.fontSize10Action.setChecked(false);
			editor.fontSize12Action.setChecked(false);
			editor.fontSize14Action.setChecked(false);
			editor.fontSize16Action.setChecked(false);
		}
	}
	
}