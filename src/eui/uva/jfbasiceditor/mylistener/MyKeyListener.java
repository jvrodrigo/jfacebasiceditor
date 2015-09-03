package eui.uva.jfbasiceditor.mylistener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class MyKeyListener implements Listener{
	
	public void handleEvent (Event event){
		switch (event.type){			
		case SWT.KeyDown:
			System.out.print(event.character);
		break;
		default:break;
		}
	}
}
