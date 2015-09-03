package eui.uva.jfbasiceditor.action.edit;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class SelectAllAction extends Action {
	JFaceBasicEditor editor;
	public SelectAllAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_selectall.png"));
			setImageDescriptor(icon);
			setText("   &Seleccionar todo@Ctrl+E");
			setToolTipText("Seleccionar todo el texto");
			setDescription("Pulse para seleccionar todo el texto");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
			editor.getStyledText().selectAll();
	}
}