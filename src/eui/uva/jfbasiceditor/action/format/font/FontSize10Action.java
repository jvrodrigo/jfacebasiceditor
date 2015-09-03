package eui.uva.jfbasiceditor.action.format.font;

import org.eclipse.jface.action.*;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class FontSize10Action extends Action {
	JFaceBasicEditor editor;
	
	public FontSize10Action(JFaceBasicEditor w){
		super("10",AS_RADIO_BUTTON);
		editor = w;
		
	}
	StyleRange style = new StyleRange();
	public void run(){  
		style = editor.getStyleRange();
		editor.fontData.setHeight(10);
		style.font =  new Font(editor.getShell().getDisplay(),editor.fontData);
		editor.setStyleRange(style);
		editor.getStyledText().setFont(style.font);
	}
	
}
