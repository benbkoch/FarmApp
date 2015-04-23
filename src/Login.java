import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;


public class Login {

	protected Shell shell;
	private Text txtUser;
	private Text txtPassword;
	private Text user;
	private Text pass;
	private Text txtPrescript;
	private Text txtIncorrectUsernamepassword;

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
		txtUser.setBounds(66, 174, 90, 26);
		
		txtPassword = new Text(shell, SWT.NONE);
		txtPassword.setText("PASSWORD:");
		txtPassword.setBounds(66, 221, 90, 26);
		
		user = new Text(shell, SWT.BORDER);
		user.setBounds(172, 168, 211, 32);
		
		pass = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		pass.setBounds(172, 218, 211, 32);
		
		txtPrescript = new Text(shell, SWT.NONE);
		txtPrescript.setFont(SWTResourceManager.getFont("Segoe UI Semilight", 36, SWT.NORMAL));
		txtPrescript.setText("prescript+");
		txtPrescript.setBounds(111, 52, 285, 83);
		
		Button login = new Button(shell, SWT.NONE);
		login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String username = user.getText();
				String password = pass.getText();
				
				DatabaseConnection.createConnection();
				Professional p = DatabaseConnection.attemptLogin(username, password);
				DatabaseConnection.shutdown();
				
				if (p == null){
					txtIncorrectUsernamepassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					user.setText("");
					pass.setText("");
					
				} else {
					try {
						txtIncorrectUsernamepassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						Farm windowF = new Farm();
						windowF.open(p);
					} catch (Exception eF) {
						eF.printStackTrace();
					}
					
				}
			}
		});
		login.setBounds(205, 264, 90, 30);
		login.setText("LOGIN");
		
		txtIncorrectUsernamepassword = new Text(shell, SWT.NONE);
		txtIncorrectUsernamepassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtIncorrectUsernamepassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtIncorrectUsernamepassword.setEditable(false);
		txtIncorrectUsernamepassword.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		txtIncorrectUsernamepassword.setText("Incorrect username/password");
		txtIncorrectUsernamepassword.setBounds(185, 145, 180, 17);
		shell.setTabList(new Control[]{user, pass, login, txtUser, txtPassword, txtPrescript, txtIncorrectUsernamepassword});
	
		
		

	}
}
