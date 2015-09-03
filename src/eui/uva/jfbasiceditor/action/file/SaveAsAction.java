package eui.uva.jfbasiceditor.action.file;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class SaveAsAction extends Action {
	JFaceBasicEditor editor;
	public SaveAsAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_saveas.png"));
			setImageDescriptor(icon);
			setText("   G&uardar como...@Ctrl+Shift+S");
			setToolTipText("Guardar como...");
			setDescription("Guardar como <nombre>");
			editor.setStatus("Pulse para guardar con distinto nombre el documento");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.setStatus("Ha pulsado Guardar como... el documento: " + editor.APP_NAME);
		if(editor.saveAsTextToFile()){
			editor.setStatus("Ha guardado como...: " + editor.APP_NAME);
			editor.saveAction.setEnabled(false);
		}else editor.setStatus("Ha cancelado guardar como...: " + editor.APP_NAME); 
	}
}