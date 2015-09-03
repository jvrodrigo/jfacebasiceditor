package eui.uva.jfbasiceditor.action.format.color;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class BackgroundColorAction extends Action {
	JFaceBasicEditor editor;
	public BackgroundColorAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			//Listener listener = new MiMouseListener();
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_backgroundcolor.png"));
			setImageDescriptor(icon);
			setText("   C&olor Fondo@Ctrl+Y");
			setToolTipText("Color del fondo");
			setDescription("Elige el color del fondo");
			editor.setStatus("Pulse para elegir el color del fondo");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		Color color = new Color(editor.getShell().getDisplay(), 255, 0, 0);
		ColorDialog dialog = new ColorDialog(editor.getShell());
		if (color != null) dialog.setRGB(color.getRGB());
        RGB rgb = dialog.open();
        if (rgb != null) {
          if (color != null) color.dispose();
          color = new Color(editor.getShell().getDisplay(), rgb);
          editor.getStyledText().setBackground(color);
        }
	}
}
