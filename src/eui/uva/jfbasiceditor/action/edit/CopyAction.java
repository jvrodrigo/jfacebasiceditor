package eui.uva.jfbasiceditor.action.edit;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class CopyAction extends Action {
	JFaceBasicEditor editor;
	public CopyAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			//Listener listener = new MiMouseListener();
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_copy.png"));
			setImageDescriptor(icon);
			setText("   C&opiar@Ctrl+C");
			setToolTipText("Copiar");
			setDescription("Copiar el texto seleccionado");
			editor.setStatus("Pulse para copiar el texto seleccionado");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.getStyledText().copy();

	}
}
