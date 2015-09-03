package eui.uva.jfbasiceditor.action.format.color;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class BackColorAction extends Action {
	JFaceBasicEditor editor;
	public BackColorAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_backcolor.png"));
			setImageDescriptor(icon);
			setText("   Fondo del caracter@Ctrl+T");
			setToolTipText("Fondo del caracter");
			setDescription("Elige el color de fondo del caracter");
			editor.setStatus("Pulse para elegir el color de fondo del caracter");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	StyleRange style = new StyleRange();
	public void run(){
		Color color = new Color(editor.getShell().getDisplay(), 255, 0, 0);
		ColorDialog dialog = new ColorDialog(editor.getShell());
		if (color != null) dialog.setRGB(color.getRGB());
		RGB rgb = dialog.open();
        if (rgb != null) {
        	if (color != null) color.dispose();
        	color = new Color(editor.getShell().getDisplay(), rgb);
        	style = editor.getStyleRange();
            style.start = 0;
            style.length = editor.getStyledText().getCharCount();
            style.background = color;
            editor.setStyleRange(style);
        }
	}
}
