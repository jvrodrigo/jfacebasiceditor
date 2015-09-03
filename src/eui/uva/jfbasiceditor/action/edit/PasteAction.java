package eui.uva.jfbasiceditor.action.edit;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class PasteAction extends Action {
	JFaceBasicEditor editor;
	
	public PasteAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_paste.png"));
			setImageDescriptor(icon);
			setText("   &Pegar@Ctrl+V");
			setToolTipText("Pegar");
			setDescription("Pegar el texto copiado/cortado");
			editor.setStatus("Pulse para pegar el texto copiado/cortado");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
			editor.getStyledText().paste();
	}
}