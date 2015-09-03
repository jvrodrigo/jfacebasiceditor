package eui.uva.jfbasiceditor.mylistener;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class MyMouseTrackListener implements MouseTrackListener{
	JFaceBasicEditor editor;
	public MyMouseTrackListener(JFaceBasicEditor w){
		editor = w;
	}
	public void mouseEnter(MouseEvent e) {
		editor.setStatus("Pulse en el texto para escribir");
	}
	public void mouseExit(MouseEvent e) {
		editor.setStatus("");	
	}
	public void mouseHover(MouseEvent e) {
		editor.setStatus("Pulse en el texto para escribir");
	}
}

