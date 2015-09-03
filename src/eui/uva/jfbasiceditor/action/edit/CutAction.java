package eui.uva.jfbasiceditor.action.edit;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class CutAction extends Action {
	JFaceBasicEditor editor;
	
	public CutAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			//Listener listener = new MiMouseListener();
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_cut.png"));
			setImageDescriptor(icon);
			setText("   Co&rtar@Ctrl+X");
			setToolTipText("Cortar");
			setDescription("Cortar el texto seleccionado");
			editor.setStatus("Pulse para cortar el texto seleccionado");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
			editor.getStyledText().cut();
	}
}
