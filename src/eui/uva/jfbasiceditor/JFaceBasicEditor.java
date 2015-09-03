package eui.uva.jfbasiceditor;
import eui.uva.jfbasiceditor.action.file.*;
import eui.uva.jfbasiceditor.action.edit.*;
import eui.uva.jfbasiceditor.action.format.align.AlignCenterAction;
import eui.uva.jfbasiceditor.action.format.align.AlignLeftAction;
import eui.uva.jfbasiceditor.action.format.align.AlignRightAction;
import eui.uva.jfbasiceditor.action.format.color.BackColorAction;
import eui.uva.jfbasiceditor.action.format.color.BackgroundColorAction;
import eui.uva.jfbasiceditor.action.format.color.FontColorAction;
import eui.uva.jfbasiceditor.action.format.font.*;
import eui.uva.jfbasiceditor.action.format.style.BoldAction;
import eui.uva.jfbasiceditor.action.format.style.ItalicAction;
import eui.uva.jfbasiceditor.action.format.style.ParaSpaceDecreaseAction;
import eui.uva.jfbasiceditor.action.format.style.ParaSpaceIncreaseAction;
import eui.uva.jfbasiceditor.action.format.style.UnderLineAction;
import eui.uva.jfbasiceditor.action.help.*;
import eui.uva.jfbasiceditor.action.exit.*;
import eui.uva.jfbasiceditor.mylistener.MyKeyListener;
import java.io.*;
import org.eclipse.swt.SWT;
import org.eclipse.jface.window.*;
import org.eclipse.jface.action.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

public class JFaceBasicEditor extends ApplicationWindow{
	public JFaceBasicEditor() {
		super(null);
		setDefaultImage(image);
	}
	// Imagen de la cabezera
	final Image image = new Image(Display.getCurrent(),"icons/principal.png");
	public Image getImage(){
		return image;
	}
	StyledText text;
	StyleRange style = new StyleRange();
	public StyleRange getStyleRange(){
		return style;
	}
	public void setStyleRange(StyleRange style){
		text.setStyleRange(style);
		this.style = style;
	}
	public StyledText getStyledText(){
		return text;
	}
	public void setStyledText(StyledText text) {
		this.text = text;
	}
	public FontData fontData = new FontData();

	public String APP_NAME = "JFaceBasicEditor v1.0";
	public String setAPP_NAME(String APP_NAME){
		return this.APP_NAME = APP_NAME;
	}
	//The recent directory
	private String lastOpenDirectory;
	// Is there any changes since last saving action?
	public boolean hasUnsavedChanges;

	// The file associated with current text content.
	File file;
	public void setFile(File f){
		file = f;
	}
	public boolean handleChangesBeforeDiscard() {
		if(!hasUnsavedChanges)
			return true;
		MessageBox messageBox = new MessageBox(this.getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
	    messageBox.setMessage("¿Quieres guardar los cambios" + (file == null ? " del archivo?" : " de " + file.getName()+ "?"));
	    messageBox.setText("Mensaje de dialogo");
	    int ret = messageBox.open();
	    if(ret == SWT.YES) {
	      return saveTextToFile();
	    }else if(ret == SWT.NO) {
	      return true;
	    }else{
	      return false;
	    }
	}
	public boolean saveAsTextToFile(){
		FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
		dialog.setFilterNames(new String[] { "Batch file (.bat)","C file (.c)","Datos (.dat)","Documento HTML (.html)","Texto plano (.txt)", "All Files (*.*)" });
		dialog.setFilterExtensions(new String[] { "*.bat","*.c","*.dat","*.html","*.txt","*.*" }); // Windows
		dialog.setFileName(APP_NAME);
		if(file!=null){
			dialog.setFileName(file.getName());
		}
		if (lastOpenDirectory != null)
			dialog.setFilterPath(lastOpenDirectory);
			String selectedFile = dialog.open();

		if (selectedFile == null) {
			log("Accion cancelada: Guardar como... el texto en el fichero");
			return false;
		}
		file = new File(selectedFile);
		lastOpenDirectory = file.getParent();
		try{
			FileWriter writer = new FileWriter(file);
			writer.write(text.getText());
			writer.close();
			log("El texto se ha guardado en el fichero: " + file);
			hasUnsavedChanges = false;
			this.getShell().setText(setAPP_NAME(file.getName()));
			return true;
		}catch(IOException e) {
			log("Fallo en Guardar como... el texto en un fichero: " + file);
			log(e.toString());
		}
		return false;
	}
	public boolean saveTextToFile() {
		if (file == null) {
			FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
			dialog.setFilterNames(new String[] { "Batch file (.bat)","C file (.c)","Datos (.dat)","Documento HTML (.html)","Texto plano (.txt)", "All Files (*.*)" });
			dialog.setFilterExtensions(new String[] { "*.bat","*.c","*.dat","*.html","*.txt","*.*" }); // Windows
			dialog.setFileName(APP_NAME);
			if (lastOpenDirectory != null)
				dialog.setFilterPath(lastOpenDirectory);
			String selectedFile = dialog.open();
			if (selectedFile == null) {
				log("Accion cancelada: Guardar el texto en un fichero");
				return false;
			}
			file = new File(selectedFile);
			lastOpenDirectory = file.getParent();
		    }
		try{
			FileWriter writer = new FileWriter(file);
			writer.write(text.getText());
			writer.close();
			log("El texto se ha guardado en el fichero: " + file);
			this.getShell().setText(setAPP_NAME(file.getName()));
			hasUnsavedChanges = false;
			return true;
		}catch(IOException e) {
			log("Fallo al guardar el texto en el fichero: " + file);
			log(e.toString());
		}
		return false;
	}
	public boolean loadTextFromFile() {
		FileDialog dialog = new FileDialog(this.getShell(), SWT.OPEN);
		if (lastOpenDirectory != null)
			dialog.setFilterPath(lastOpenDirectory);
		String selectedFile = dialog.open();
		if (selectedFile == null) {
			log("Accion cancelada: Cargar el texto desde un fichero");
			return false;
		}
		file = new File(selectedFile);
		lastOpenDirectory = file.getParent();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			text.setText(sb.toString());
			
			this.getShell().setText(setAPP_NAME(file.getName()));
			hasUnsavedChanges = false;
			saveAction.setEnabled(false);
			return true;
		}catch(IOException e) {
			log("Fallo al abrir el texto del fichero: " + file);
			log(e.toString());
		}
		return false;
	}
	
	// Actions
	IAction newAction = new NewAction(this);
	IAction newAction_C = new NewAction_C(this);
	IAction openAction = new OpenAction(this);
	public Action saveAction = new SaveAction(this);
	IAction saveAsAction = new SaveAsAction(this);
	IAction printAction = new PrintAction(this);
	IAction exitAction = new ExitAction(this);	
	
	IAction selectAllAction = new SelectAllAction(this);
	IAction searchAction = new SearchAction(this);
	IAction cutAction = new CutAction(this);
	IAction copyAction = new CopyAction(this);
	IAction pasteAction = new PasteAction(this);
	
	IContributionItem fontChooserCombo;
	IContributionItem fontSizeChooserCombo;
	
	public IAction boldAction = new BoldAction(this);
	public IAction italicAction = new ItalicAction(this);
	public IAction underLineAction = new UnderLineAction(this);
	
	public IAction fontSize10Action = new FontSize10Action(this);
	public IAction fontSize12Action = new FontSize12Action(this);
	public IAction fontSize14Action = new FontSize14Action(this);
	public IAction fontSize16Action = new FontSize16Action(this);
	
	IAction fontSizeUpAction = new FontSizeUpAction(this);
	IAction fontSizeDownAction = new FontSizeDownAction(this);
	
	IAction alignLeftAction = new AlignLeftAction(this);
	IAction alignRightAction = new AlignRightAction(this);
	IAction alignCenterAction = new AlignCenterAction(this);
	
	public IAction paraSpaceDecreaseAction = new ParaSpaceDecreaseAction(this);
	public IAction paraSpaceIncreaseAction = new ParaSpaceIncreaseAction(this);
	
	IAction fontColor = new FontColorAction(this);
	IAction backgroundColor = new BackgroundColorAction(this);
	IAction backColorAction = new BackColorAction(this); 
	
	IAction helpAction_t1 = new HelpAction_tutorial1(this);
	IAction helpAction_web = new HelpAction_web(this);
	
	Font font;
	public FontData data;
	Rectangle rectangle = new Rectangle(0,0,500,500);
	public Control createContents(Composite parent){
		Shell shell = this.getShell();
		shell.setBounds(this.getShell().getDisplay().getClientArea());
		shell.setLocation(shell.getLocation());
		shell.setText(APP_NAME);
		text = new StyledText(
				shell,		
				SWT.MULTI
				| SWT.WRAP
				| SWT.BORDER
				| SWT.H_SCROLL
				| SWT.V_SCROLL
				| SWT.DOWN
				| SWT.LEFT
				| SWT.CENTER
				| SWT.RIGHT
				| SWT.NORMAL
				| SWT.ITALIC
				| SWT.BOLD
				);
		text.setLayoutData(new GridData(GridData.FILL_BOTH));
		data = getFont().getFontData()[0];
		data.setName("Arial");
		data.setHeight(12);
		data.setStyle(SWT.NORMAL);
		font = new Font(shell.getDisplay(),data);
		fontData = data;
		style.font = font;
		style.length = getStyledText().getCharCount();
		style.rise = 0;
		text.setFont(style.font);
		text.setStyleRange(style);
		text.addListener(SWT.KeyDown, new MyKeyListener());
		text.setText("Bienvenido a DIU\nJFaceBasicEditor_v1.0\nAutor: Jose Vicente Rodrigo Delgado");
		text.addModifyListener(new ModifyListener() {
	    	public void modifyText(ModifyEvent e) {
	    		hasUnsavedChanges = true;
	    		saveAction.setEnabled(true);
	    		style.length = getStyledText().getCharCount();
	    		setStyleRange(style);
	    	}
	    });
		Image imageCut = new Image(Display.getCurrent(),"icons/sc_cut.png");
		Image imageCopy = new Image(Display.getCurrent(),"icons/sc_copy.png");
		Image imagePaste = new Image(Display.getCurrent(),"icons/sc_paste.png");
		Image imageDelete = new Image(Display.getCurrent(),"icons/sc_delete.png");
		Image imageSelectAll = new Image(Display.getCurrent(),"icons/sc_selectall.png");
		Menu popupMenu = new Menu(text);
		MenuItem cutItem = new MenuItem(popupMenu, SWT.NONE);
		cutItem.setText(" Cortar");
		cutItem.setImage(imageCut);
		cutItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				text.cut();
			}
		});
		MenuItem copyItem = new MenuItem(popupMenu, SWT.NONE);
	    copyItem.setText(" Copiar");
	    copyItem.setImage(imageCopy);
	    copyItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				text.copy();
			}
	    });
	    MenuItem pasteItem = new MenuItem(popupMenu, SWT.NONE);
	    pasteItem.setText(" Pegar");
	    pasteItem.setImage(imagePaste);
	    pasteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				text.paste();
			}
	    });
	    Separator separator = new Separator();
	    separator.fill(popupMenu,3);
	    MenuItem deleteItem = new MenuItem(popupMenu, SWT.NONE);
	    deleteItem.setText(" Borrar");
	    deleteItem.setImage(imageDelete);
	    deleteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				text.replaceTextRange(text.getSelectionRange().x, text.getSelectionRange().y, "");
			}
	    });
	    separator.fill(popupMenu,5);
	    MenuItem selectAllItem = new MenuItem(popupMenu, SWT.NONE);
	    selectAllItem.setText(" Seleccionar todo");
	    selectAllItem.setImage(imageSelectAll);
	    selectAllItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				text.selectAll();
			}
	    });
	    text.setMenu(popupMenu);
		setStyledText(text);
		return shell;
	}
	protected MenuManager createMenuManager()
	{
	  	MenuManager menuBar = new MenuManager();
	  	MenuManager fileMenu = new MenuManager(" &Archivo ");
	  	MenuManager newMenu = new MenuManager("   &Nuevo");
	  	MenuManager editMenu = new MenuManager(" &Editar");
	  	MenuManager formatMenu = new MenuManager(" &Formato");
	  	MenuManager styleMenu = new MenuManager(" E&stilo");
	  	MenuManager colorMenu = new MenuManager(" &Color");
	  	MenuManager helpMenu = new MenuManager(" A&yuda");
	  	MenuManager fontMenu = new MenuManager(" &Tamaño");
	  	Separator separator = new Separator();
	  	
	  	fileMenu.add(newMenu);
	  	newMenu.add(newAction);
	  	newMenu.add(newAction_C);
	  	fileMenu.add(openAction);
	  	fileMenu.add(saveAsAction);
	  	fileMenu.add(saveAction);
	  	fileMenu.add(separator);
	  	fileMenu.add(printAction);
	  	fileMenu.add(separator);
	  	fileMenu.add(exitAction);
	  	
		editMenu.add(searchAction);
		editMenu.add(separator);
	  	editMenu.add(cutAction);
	  	editMenu.add(copyAction);
	  	editMenu.add(pasteAction);
	  	editMenu.add(separator);
	  	editMenu.add(selectAllAction);
	  	
	  	styleMenu.add(boldAction);
	  	styleMenu.add(italicAction);
	  	styleMenu.add(underLineAction);
	  	
	  	fontMenu.add(fontSize10Action);
	  	fontMenu.add(fontSize12Action);
	  	fontMenu.add(fontSize14Action);
	  	fontMenu.add(fontSize16Action);
	  	
	  	colorMenu.add(fontColor);
	  	colorMenu.add(backColorAction);
	  	colorMenu.add(backgroundColor);
	  	
	  	formatMenu.add(styleMenu);
	  	formatMenu.add(fontMenu);
	  	formatMenu.add(colorMenu); 	
	  	
	  	helpMenu.add(helpAction_t1);
	  	helpMenu.add(separator);
	  	helpMenu.add(helpAction_web);

	  	menuBar.add(fileMenu);
	  	menuBar.add(editMenu);
	  	menuBar.add(formatMenu);
	  	menuBar.add(helpMenu);
	  	menuBar.add(separator);
	  
	  	return menuBar;
	}
	protected CoolBarManager createCoolBarManager(int style){
		CoolBarManager coolbar = new CoolBarManager(style);
		Separator separator = new Separator();
		IToolBarManager toolBarManagerFile = new ToolBarManager(style);
		IToolBarManager toolBarManagerEdit = new ToolBarManager(style);
		IToolBarManager toolBarManagerFormat = new ToolBarManager(style);
		IToolBarManager toolBarManagerAlign = new ToolBarManager(style);
		IToolBarManager toolBarManagerHelp = new ToolBarManager(style);
		IToolBarManager toolBarManagerFontSize = new ToolBarManager(style);
		IToolBarManager toolBarManagerColor = new ToolBarManager(style);
		IToolBarManager toolBarManagerExit = new ToolBarManager(style);
		
		fontChooserCombo = new ControlContributionFontC(){
		    protected Control createControl(Composite parent) {
		      final Combo combo = new Combo(parent,SWT.READ_ONLY | SWT.DROP_DOWN |SWT.SCROLL_LINE);
		      //combo.setText("Elige la Fuente");
		      combo.setToolTipText("Cambia la fuente");
		      String[] items = {"Arial","Comic Sans MS","Courier","Lucida Console","Times New Roman","Ubuntu"};
		      combo.setItems(items);
		      combo.select(0);
		      combo.setText("Fuentes");
		      //combo.addMouseTrackListener(new MyMouseListener());
		      //new MyMouseListener(
		      combo.addMouseTrackListener(new MouseTrackListener(){
				public void mouseEnter(MouseEvent e) {
					setStatus("Pulsa para elegir el tipo de fuente");
				}
				public void mouseExit(MouseEvent e) {
					
					setStatus("");
				}
				public void mouseHover(MouseEvent e) {
					setStatus("Pulsa para elegir el tipo de fuente");
				}
		      });
		      combo.addSelectionListener(new SelectionAdapter() {
		    	  public void widgetSelected(SelectionEvent e){
		    		  if(combo.getText().equals("Arial")){
		    			  fontData.setName("Arial");
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());
		    		  }
		    		  else if(combo.getText().equals("Comic Sans MS")){
		    			  fontData.setName("Comic sans MS");
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());
		    		  }
		    		  else if(combo.getText().equals("Courier")){
		    			  fontData.setName("Courier");
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());
		    		  }
		    		  else if(combo.getText().equals("Lucida Console")){
		    			  fontData.setName("Lucida Console");
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());
		    		  }
		    		  else if(combo.getText().equals("Times New Roman")){
		    			  fontData.setName("Times New Roman");
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());
		    		  }
		    		  else if(combo.getText().equals("Ubuntu")){
		    			  fontData.setName("Ubuntu");
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());
		    		  }
		    	  }
		      });
		      return combo;
		    }
		};
		fontSizeChooserCombo = new ControlContributionFontS() {
		    protected Control createControl(Composite parent) {
		      final Combo combo = new Combo(parent,SWT.READ_ONLY);
		      combo.setToolTipText("Cambia el tamaño de la fuente");
		      String[] items = {"6","8","10","12","14","16","18","20","22","24","26","28","30","48","72"}; 
		      combo.setItems(items);
		      combo.select(3);
		      combo.setText("Tamaño");
		      combo.addMouseTrackListener(new MouseTrackListener(){
					public void mouseEnter(MouseEvent e) {
						setStatus("Pulsa para elegir el tamaño de la fuente");
					}
					public void mouseExit(MouseEvent e) {
						
						setStatus("");
					}
					public void mouseHover(MouseEvent e) {
						setStatus("Pulsa para elegir el tamaño de la fuente");
					}
			      });
		      combo.addSelectionListener(new SelectionAdapter(){
		    	  public void widgetSelected(SelectionEvent e){
		    		  switch(Integer.parseInt(combo.getText())){
		    		  case 6:
		    			  fontData.setHeight(6);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 8:
		    			  fontData.setHeight(8);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 10:
		    			  fontData.setHeight(10);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 12:
		    			  fontData.setHeight(12);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 14:
		    			  fontData.setHeight(14);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 16:
		    			  fontData.setHeight(16);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 18:
		    			  fontData.setHeight(18);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 20:
		    			  fontData.setHeight(20);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 22:
		    			  fontData.setHeight(22);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 24:
		    			  fontData.setHeight(24);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 26:
		    			  fontData.setHeight(26);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 28:
		    			  fontData.setHeight(28);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 30:
		    			  fontData.setHeight(30);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 48:
		    			  fontData.setHeight(48);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  case 72:
		    			  fontData.setHeight(72);
		    			  getStyleRange().font = new Font(e.display,fontData);
		    			  getStyledText().setFont(getStyleRange().font);
		    			  setStyleRange(getStyleRange());break;
		    		  default:break;
		    		  }
		    	  }
		      });
		      return combo;
		    }
		};
				
		toolBarManagerFile.add(newAction);
		toolBarManagerFile.add(openAction);
		toolBarManagerFile.add(saveAsAction);
		toolBarManagerFile.add(saveAction);		
		
		toolBarManagerEdit.add(cutAction);
		toolBarManagerEdit.add(copyAction);
		toolBarManagerEdit.add(pasteAction);
		
		toolBarManagerFormat.add(fontChooserCombo);
		toolBarManagerFormat.add(separator);
		toolBarManagerFormat.add(fontSizeChooserCombo);
		toolBarManagerFormat.add(separator);
		toolBarManagerFormat.add(boldAction);
		toolBarManagerFormat.add(italicAction);
		toolBarManagerFormat.add(underLineAction);
		toolBarManagerFormat.add(separator);
		toolBarManagerFormat.add(fontSizeDownAction);
		toolBarManagerFormat.add(fontSizeUpAction);
		toolBarManagerFormat.add(separator);
		toolBarManagerFormat.add(alignLeftAction);
		toolBarManagerFormat.add(alignCenterAction);
		toolBarManagerFormat.add(alignRightAction);
		toolBarManagerFormat.add(separator);
		toolBarManagerFormat.add(paraSpaceDecreaseAction);
		toolBarManagerFormat.add(paraSpaceIncreaseAction);
		toolBarManagerFormat.add(separator);
		toolBarManagerFormat.add(fontColor);
		toolBarManagerFormat.add(backColorAction);
		toolBarManagerFormat.add(backgroundColor);
		
		toolBarManagerHelp.add(helpAction_t1);
		toolBarManagerHelp.add(helpAction_web);

		toolBarManagerExit.add(exitAction);
		
		coolbar.add(toolBarManagerFile);
		coolbar.add(toolBarManagerEdit);
		coolbar.add(toolBarManagerFormat);
		coolbar.add(toolBarManagerAlign);
		coolbar.add(toolBarManagerFontSize);
		coolbar.add(toolBarManagerColor);
		coolbar.add(toolBarManagerHelp);
		coolbar.add(toolBarManagerExit);
		
		return coolbar;
	}
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLine = new StatusLineManager();
		statusLine.add(newAction);
		statusLine.add(newAction_C);
		statusLine.add(exitAction);
		return statusLine;
	}
	void log(String message) {
		System.out.println(message);
	}
	public static void main(String[] args) {
		JFaceBasicEditor demo = new JFaceBasicEditor();
		demo.setBlockOnOpen(true);
		demo.addMenuBar();
		demo.addCoolBar(SWT.WRAP | SWT.FLAT);
		demo.addStatusLine();
		demo.open();
		Display.getCurrent().dispose();
	}
}