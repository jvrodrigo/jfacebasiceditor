package eui.uva.jfbasiceditor.action.edit;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.action.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eui.uva.jfbasiceditor.JFaceBasicEditor;

public class SearchAction extends Action {
	JFaceBasicEditor editor;
	public SearchAction(JFaceBasicEditor w){
		editor = w; 
		try
		{
			ImageDescriptor icon = ImageDescriptor.createFromURL(new
				 URL("file:icons/sc_recsearch.png"));
			setImageDescriptor(icon);
			setText("   &Buscar@Ctrl+F");
			setToolTipText("Buscar");
			setDescription("Busca el texto");
			
		}catch(MalformedURLException e)
		{
			System.err.println(e.getMessage());
		}
	}
	Shell shell;
	Text text;
	public static final int SHELL_TRIM = SWT.CLOSE | SWT.TITLE | SWT.MIN | SWT.MAX | SWT.RESIZE;
	public void run(){
		editor.setStatus("Ha pulsado buscar...");
		shell = new Shell(editor.getShell().getDisplay(),SWT.SHELL_TRIM & (~SWT.MIN) & (~SWT.MAX)& (~SWT.RESIZE));
		Image image = new Image(shell.getDisplay(),"icons/sc_recsearch.png");
		shell.setText("Buscar");
		shell.setImage(image);
		shell.setBounds(400,300,400,130);
		shell.setLayout(new FillLayout());
		Composite composite = new Composite(shell,SWT.BORDER);
		GridLayout layout = new GridLayout();
		layout.numColumns=2;
		composite.setLayout(layout);
		Label label = new Label(composite,SWT.LEFT);
		label.setText("Introduce la palabra a buscar:");
		GridData gridData = new GridData();
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		label = new Label(composite,SWT.LEFT);
		label.setText("Buscar: ");
		text = new Text(composite,SWT.SINGLE | SWT.BORDER);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		text.setLayoutData(gridData);
		text.setFocus();
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.END;
		gridData.horizontalSpan=1;
		Button okBottom = new Button(composite, 0);
		okBottom.setText("Buscar");
		okBottom.setAlignment(SWT.CENTER);
		okBottom.setLayoutData(gridData);
		okBottom.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_SEARCH | SWT.CANCEL);
				messageBox.setText("Buscar");
				String cadena, patron;
				cadena = editor.getStyledText().getText().toLowerCase();
				patron = text.getText().toLowerCase();
				System.out.println(cadena);
				System.out.println(patron);
				if(cadena.indexOf(patron)!=-1)
				{
					int cant = 0;
					while (cadena.indexOf(patron) > -1)
					{
						cadena = cadena.substring(cadena.indexOf(patron)+text.getText().length(),cadena.length());
						cant++;
					}
					messageBox.setMessage("La palabra " + text.getText() + " esta en el texto" + 
							"\nOcurrencias: " + cant + " veces");
				}
				else{
					messageBox.setMessage("La palabra " + text.getText() + " no encontrada");
				}
				/*System.out.println();
				System.out.println(editor.getStyledText().getText());
				System.out.println(text.getText());
			    messageBox.setMessage("La palabra " + text.getText() + " no encontrada");*/
			    
			    messageBox.open();
			}
		});
		Button cancelBottom = new Button(composite, SWT.PUSH);
		cancelBottom.setText("Cerrar");
		cancelBottom.setLayoutData(gridData);
		cancelBottom.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				shell.close();
			}
		});
		shell.open();
		while(!shell.isDisposed()){			
			if(!shell.getDisplay().readAndDispatch())				
				editor.getShell().setEnabled(false);
		}
		editor.getShell().setEnabled(true);
		editor.getShell().setActive();
		editor.setStatus("Ha cerrado Buscar:...");
	}
}

