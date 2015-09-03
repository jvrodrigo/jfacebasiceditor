package eui.uva.jfbasiceditor.action.format.align;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class AlignCenterAction extends Action {
	JFaceBasicEditor editor;
	public AlignCenterAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			//Listener listener = new MiMouseListener();
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_alignhorizontalcenter.png"));
			setImageDescriptor(icon);
			setText("   &Centrar@Ctrl+H");
			setToolTipText("Centrar el texto");
			setDescription("Centrar el texto seleccionado");
			editor.setStatus("Pulse para centrar el texto seleccionado");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.getStyledText().setAlignment(SWT.CENTER);

	}
}
