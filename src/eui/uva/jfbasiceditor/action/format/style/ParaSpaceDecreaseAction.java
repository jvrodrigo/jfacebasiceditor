package eui.uva.jfbasiceditor.action.format.style;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyleRange;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class ParaSpaceDecreaseAction extends Action {
	JFaceBasicEditor editor;
	
	public ParaSpaceDecreaseAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_paraspacedecrease.png"));
			setImageDescriptor(icon);
			setText("  &Disminuye el espacio@Ctrl+-");
			setToolTipText("Disminuye el interlineado");
			setDescription("Disminuye el interlineado del texto");
			//editor.setStatus("Pulse convertir disminuir el interlineado del texto");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	StyleRange style = new StyleRange();
	public void run(){
		if(editor.getStyleRange().rise > -30){
			style = editor.getStyleRange();
			style.length = editor.getStyledText().getCharCount();
			style.rise = style.rise - 2;
			editor.setStyleRange(style);
		}
		
		if(editor.getStyleRange().rise < 30){
			editor.paraSpaceIncreaseAction.setEnabled(true);
		}
		
		if(editor.getStyleRange().rise == -30) setEnabled(false);
		
	}
}
