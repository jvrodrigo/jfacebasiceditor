package eui.uva.jfbasiceditor.action.file;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyledText;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class NewAction_C extends Action {
	JFaceBasicEditor editor;
	File file;
	public NewAction_C(JFaceBasicEditor w){
		editor = w;
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_adddirect_C.png"));
			setImageDescriptor(icon);
			setText("   Nuevo (*.c)");
			setToolTipText("Nuevo documento (*.c)");
			setDescription("Crear un documento Nuevo (*.c)");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	public void run(){
		editor.setStatus("Ha pulsado Crear un nuevo documento C (*.c)");
		if(editor.handleChangesBeforeDiscard()){
			editor.setFile(null);
			StyledText t = editor.getStyledText();
			t.setText("#include<stdio.h>\nvoid main(){\n\n}");
			editor.setStyledText(t);
			editor.saveAction.setEnabled(false);
			editor.hasUnsavedChanges = false;
			editor.getShell().setText("Nuevo Documento.c");
			editor.setAPP_NAME("Nuevo Documento.c");
		}
	}
}