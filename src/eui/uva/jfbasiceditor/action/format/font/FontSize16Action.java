package eui.uva.jfbasiceditor.action.format.font;

import org.eclipse.jface.action.*;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class FontSize16Action extends Action {
	JFaceBasicEditor editor;
	
	public FontSize16Action(JFaceBasicEditor w){
		super("16",AS_RADIO_BUTTON);
		editor = w; 
				
	}
	StyleRange style = new StyleRange();
	public void run(){
		style = editor.getStyleRange();
		editor.fontData.setHeight(16);
		style.font =  new Font(editor.getShell().getDisplay(),editor.fontData);
		editor.getStyledText().setFont(style.font);
		editor.setStyleRange(style);
		
	}
}
