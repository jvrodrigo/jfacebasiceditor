package eui.uva.jfbasiceditor.action.exit;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.*;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

import java.io.File;
import java.net.*;

public class ExitAction extends Action{

	JFaceBasicEditor editor;
	File file;
	public ExitAction(JFaceBasicEditor w){
		editor = w;
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:icons/sc_quit.png"));
			setImageDescriptor(icon);
			setToolTipText("Salir de la aplicación");
			setText("   &Salir@Ctrl+Q");						 
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		if(editor.handleChangesBeforeDiscard());
		editor.close();
	}	 	 
}
