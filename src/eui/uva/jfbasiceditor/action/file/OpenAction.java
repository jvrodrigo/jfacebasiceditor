package eui.uva.jfbasiceditor.action.file;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class OpenAction extends Action{
	JFaceBasicEditor editor;
	public OpenAction(JFaceBasicEditor w){
		editor = w;
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
			URL("file:icons/sc_open.png"));
			setImageDescriptor(icon);
			setToolTipText("Abrir un archivo");
			setText("   Ab&rir@Ctrl+O");			 
		}catch(MalformedURLException e){
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.setStatus("Ha pulsado Abrir un archivo");
		if(editor.handleChangesBeforeDiscard()){
			if(editor.loadTextFromFile())
				editor.setStatus("Ha abierto el documento: " + editor.APP_NAME);
			else editor.setStatus("Ha cancelado abrir un documento");;
		}
    }
	void log(String message) {
		System.out.println(message);
	}
}
