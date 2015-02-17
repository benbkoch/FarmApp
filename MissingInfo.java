import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class MissingInfo extends Dialog {

	protected Object result;
	protected Shell shlMissingInfo;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MissingInfo(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlMissingInfo.open();
		shlMissingInfo.layout();
		Display display = getParent().getDisplay();
		while (!shlMissingInfo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlMissingInfo = new Shell(getParent(), getStyle());
		shlMissingInfo.setSize(266, 192);
		shlMissingInfo.setText("Missing Info!");
		
		Label lblYouMustComplete = new Label(shlMissingInfo, SWT.NONE);
		lblYouMustComplete.setBounds(46, 35, 182, 13);
		lblYouMustComplete.setText("You must complete all information!");
		
		Button btnOk = new Button(shlMissingInfo, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlMissingInfo.dispose();
			}
		});
		btnOk.setBounds(98, 84, 68, 23);
		btnOk.setText("OK");

	}
}
