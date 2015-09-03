package eui.uva.jfbasiceditor.action.format.align;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class AlignLeftAction extends Action {
	JFaceBasicEditor editor;
	public AlignLeftAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			//Listener listener = new MiMouseListener();
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_alignleft.png"));
			setImageDescriptor(icon);
			setText("   &Izquierda@Ctrl+H");
			setToolTipText("Alinear a la izquierda");
			setDescription("Alinear a la izquierda el texto seleccionado");
			editor.setStatus("Pulse para alinear a la izquierda el texto seleccionado");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.getStyledText().setAlignment(SWT.LEFT);
	}
}