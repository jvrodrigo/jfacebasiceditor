package eui.uva.jfbasiceditor.action.help;

import java.io.*;
import java.net.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;

import eui.uva.jfbasiceditor.JFaceBasicEditor;


public class HelpAction_tutorial1 extends Action {
	JFaceBasicEditor editor;
	
	public HelpAction_tutorial1(JFaceBasicEditor w){
		editor = w;
		try
		{
			//Listener listener = new MiMouseListener();
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_tutorial1.gif"));
			setImageDescriptor(icon);
			setText("   &Tutorial 1@Ctrl+1");
			setToolTipText("Tutorial 1");
			setDescription("Tutorial como instalar SWT y JFace");
			editor.setStatus("Pulsa para abrir el tutorial 1 JFaceBasicEditor");
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	Rectangle rectangle = new Rectangle(50,50,500,500);
	public void run(){
		
		Shell shell = new Shell(editor.getShell().getDisplay());
		Image image = new Image(shell.getDisplay(),"icons/sc_tutorial1.gif");
		shell.setText("Tutorial 1");
		shell.setImage(image);
		shell.setBounds(rectangle);
		shell.setLayout(new FillLayout());
		StyleRange[] style = new StyleRange[6];
		style[0]= new StyleRange();
		style[0].font = new Font(shell.getDisplay(), "Times new roman", 16, SWT.BOLD);
		style[0].start = 0;
		style[0].length = 10;
		style[0].underline=true;
		style[1]= new StyleRange();
		style[1].font = new Font(shell.getDisplay(), "Times new roman", 14, SWT.BOLD);
		style[1].start=11;
		style[1].length=40;
		style[2]= new StyleRange();
		style[2].font = new Font(shell.getDisplay(), "Times new roman", 12, SWT.NORMAL | SWT.BOLD);
		style[2].start = 52;
		style[2].length = 100;
		style[3]= new StyleRange();
		style[3].font = new Font(shell.getDisplay(), "Times new roman", 12, SWT.NORMAL);
		style[3].start = 153;
		style[3].length = 290;
		style[4]= new StyleRange();
		style[4].font = style[2].font;
		style[4].start = 444;
		style[4].length = 45;
		style[5]= new StyleRange();
		style[5].font = style[3].font;
		style[5].start = 490;
		style[5].length = 207;
		editor.setStatus("Ha pulsado el tutorial 1 JFaceBasicEditor");
		try{
		StyledText texto = new StyledText(
				shell,
				SWT.MULTI 
				| SWT.WRAP 
				| SWT.V_SCROLL 
				| SWT.H_SCROLL 
				| SWT.LEFT 
				| SWT.BORDER);

		String linea;
		File archivo = new File("textos/tutorial1.txt");
		BufferedReader reader = new BufferedReader(new FileReader(archivo));
		while((linea = reader.readLine())!=null)
		texto.append(linea + "\n");
		texto.setStyleRange(style[0]);
		texto.setStyleRange(style[1]);
		texto.setStyleRange(style[2]);
		texto.setStyleRange(style[3]);
		texto.setStyleRange(style[4]);
		texto.setStyleRange(style[5]);
		texto.setEditable(false);
		}catch(Exception ex){
			ex.printStackTrace();
		};
		shell.open();
		while(!shell.isDisposed()){
			if(!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}
		editor.setStatus("Ha cerrado el tutorial 1 JFaceBasicEditor");
	}
}