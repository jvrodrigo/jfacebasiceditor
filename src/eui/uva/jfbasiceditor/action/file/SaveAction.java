package eui.uva.jfbasiceditor.action.file;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class SaveAction extends Action {
	JFaceBasicEditor editor;
	public SaveAction(JFaceBasicEditor w){
		editor = w;
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_save.png"));
			setImageDescriptor(icon);
			setText("   &Guardar@Ctrl+G");
			setToolTipText("Guardar");
			setDescription("Guardar el documento actual");	
			setEnabled(false);
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.setStatus("Ha pulsado Guardar el documento: " + editor.APP_NAME);
		if(editor.saveTextToFile()){
			editor.setStatus("Ha guardado: " + editor.APP_NAME);
			setEnabled(false);
		}
		else{
			editor.setStatus("No ha guardado: " + editor.APP_NAME);
		}
	}
}