package eui.uva.jfbasiceditor.action.format.font;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class FontSizeDownAction extends Action {
	JFaceBasicEditor editor;
	int size = 12;
	public FontSizeDownAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_shrink.png"));
			setImageDescriptor(icon);
			setText("   &Disminuye el tamaño@Ctrl+R");
			setToolTipText("Disminuye el tamaño de la fuente");
			setDescription("Disminuye el tamaño de la fuente");
			editor.setStatus("Pulse para disminuir el color de la fuente");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	StyleRange style = new StyleRange();
	public void run(){
		if(editor.fontData.getHeight()>5){
			style = editor.getStyleRange();
			style.start = 0;
			style.length = editor.getStyledText().getCharCount();
			editor.fontData.setHeight(editor.fontData.getHeight()-2);
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
