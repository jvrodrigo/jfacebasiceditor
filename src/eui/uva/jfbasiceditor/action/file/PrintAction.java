package eui.uva.jfbasiceditor.action.file;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class PrintAction extends Action {
	JFaceBasicEditor editor;
	public PrintAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_print.png"));
			setImageDescriptor(icon);
			setText("   &Imprimir@Ctrl+P");
			setToolTipText("Imprimir");
			setDescription("Imprimir el texto");
			
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.setStatus("Ha pulsado imprimir");
		MessageBox messageBox = new MessageBox(editor.getShell(), SWT.ICON_WARNING | SWT.OK);
		messageBox.setText("Imprimir");
		messageBox.setMessage("No hay impresoras instaladas en el Sistema\n             Pulse OK para continuar");
		messageBox.open();
	}
}