import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;


public class Login {

	protected Shell shell;
	private Text txtUser;
	private Text txtPassword;
	private Text user;
	private Text pass;
	private Text txtPrescript;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(502, 363);
		shell.setText("SWT Application");
		
		txtUser = new Text(shell, SWT.NONE);
		txtUser.setText("USERNAME:");
		txtUser.setBounds(79, 174, 90, 26);
		
		txtPassword = new Text(shell, SWT.NONE);
		txtPassword.setText("PASSWORD:");
		txtPassword.setBounds(79, 221, 90, 26);
		
		user = new Text(shell, SWT.BORDER);
		user.setBounds(185, 168, 211, 32);
		
		pass = new Text(shell, SWT.BORDER);
		pass.setBounds(185, 218, 211, 32);
		
		txtPrescript = new Text(shell, SWT.NONE);
		txtPrescript.setFont(SWTResourceManager.getFont("Segoe UI Semilight", 36, SWT.NORMAL));
		txtPrescript.setText("prescript+");
		txtPrescript.setBounds(111, 52, 285, 83);

	}
}
