package eui.uva.jfbasiceditor;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

@SuppressWarnings("deprecation")
public abstract class ControlContributionFontS extends ContributionItem implements IContributionItem {
	protected abstract Control createControl(Composite parent);

	public final void fill(Composite parent) {
		createControl(parent);
	}
	public final void fill(Menu parent, int index){
		Assert.isTrue(false, "Can't add a control to a menu");
	}
	public final void fill(ToolBar parent, int index){
		Control control = createControl(parent);
		ToolItem item = new ToolItem(parent, SWT.BORDER | SWT.VERTICAL | SWT.SEPARATOR | SWT.HORIZONTAL | SWT.FILL);
		item.setControl(control);
		item.setWidth(50);
	}
}
