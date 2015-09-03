package eui.uva.jfbasiceditor.action.format.style;


import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyleRange;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class ParaSpaceIncreaseAction extends Action {
	JFaceBasicEditor editor;
	
	public ParaSpaceIncreaseAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_paraspaceincrease.png"));
			setImageDescriptor(icon);
			setText("  &Aumenta el espacio@Ctrl++");
			setToolTipText("Aumenta el interlineado");
			setDescription("Aumenta el interlineado del texto");
			editor.setStatus("Pulse convertir aumentar el interlineado del texto");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	StyleRange style = new StyleRange();
	public void run(){
		if(editor.getStyleRange().rise < 30){
			style = editor.getStyleRange();
			style.length = editor.getStyledText().getCharCount();
			style.rise = style.rise + 2;
			editor.setStyleRange(style);
		}
		
		if(editor.getStyleRange().rise > -30){
			editor.paraSpaceDecreaseAction.setEnabled(true);
		}
		
		if(editor.getStyleRange().rise == 30) setEnabled(false);
		
		
	}
}
