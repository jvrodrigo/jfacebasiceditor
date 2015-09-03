package eui.uva.jfbasiceditor.mylistener;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;

public class MyMouseListener implements MouseTrackListener{

	
	public void helpRequested(HelpEvent e) {
		System.out.println(e.toString());
		
	}

	@Override
	public void mouseEnter(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExit(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseHover(MouseEvent e) {
		System.out.println(e.toString());
		
	}
	
	
	/*public void propertyChange(PropertyChangeEvent evt) {

		System.out.print(evt.getOldValue());
		String propertyName = evt.getProperty();
        if ("focusOwner".equals(propertyName)){
        	System.out.print("Focus");
        } else if ("focusedWindow".equals(propertyName)){
        	System.out.print("FocusWindows");
        }
        else System.out.print("Hola estoy aqui");
        System.out.println("Pulse en el texto para escribir");
	}*/
}
