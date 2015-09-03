package eui.uva.jfbasiceditor.action.file;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyledText;
import eui.uva.jfbasiceditor.JFaceBasicEditor;
import eui.uva.jfbasiceditor.mylistener.MyMouseListener;

public class NewAction extends Action implements IAction{
	JFaceBasicEditor editor;
	File file;
	public NewAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_adddirect.png"));
			setImageDescriptor(icon);
			setText("   &Nuevo@Ctrl+N");
			setToolTipText("Nuevo documento");
			setDescription("Crear un documento Nuevo");
			clearListeners();
			addListenerObject(new MyMouseListener());
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		if(editor.handleChangesBeforeDiscard()){
			editor.setStatus("Ha pulsado Crear un nuevo documento vacio");
			editor.setFile(null);
			StyledText t = editor.getStyledText();
			t.setText("");
			editor.setStyledText(t);
			editor.saveAction.setEnabled(false);
			editor.hasUnsavedChanges = false;
			editor.getShell().setText("Nuevo Documento");
			editor.setAPP_NAME("Nuevo Documento");
		}
	}
	/*public void runWithEvent(Event event){
		System.out.println("Por finsadassad");
		switch (event.type){			
		case SWT.MouseDown:
			System.out.println("Por fin");
		break;
		case SWT.MouseMove:
			System.out.println("Por fin213");
		default:System.out.println("Por fin....."); 
			break;
		}
	}*/
}
