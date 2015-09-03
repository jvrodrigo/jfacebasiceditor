package eui.uva.jfbasiceditor.action.help;

import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.*;
import eui.uva.jfbasiceditor.JFaceBasicEditor;
import java.net.*;

public class HelpAction_web extends Action{
	
	JFaceBasicEditor editor;
	 public HelpAction_web(JFaceBasicEditor w){
		 editor = w;
		 try
		 {
			 ImageDescriptor icon = ImageDescriptor.createFromURL(new URL("file:icons/sc_helpindex.png"));
			 setImageDescriptor(icon);
			 setToolTipText("Visita http://www.java2s.com/Code/Java/CatalogJava.htm");
			 
			 this.setText("   &Web Help@F1");			 
		 }catch(MalformedURLException e)
		 {
			 System.err.println(e.getMessage());
		 }
	 }
	 public void run(){
		 try{
			 editor.setStatus("Ha pulsado el web Help");
			 Runtime aplication = Runtime.getRuntime();
			 aplication.exec("rundll32 url.dll,FileProtocolHandler " + "http://www.java2s.com/Code/Java/CatalogJava.htm");
		 }catch(Exception ex){
				ex.getStackTrace();
		 }
	}
}
