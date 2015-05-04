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
	/**
	 * Group 1:
	 * Travis Thompson
	 * Mana Sugano
	 * Ben Koch
	 * Maki Okawa
	 * 
	 * CECS 343 - Introduction to Software Engineering
	 *
	 * 
	 * 
	 */

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
			//Launch login window.
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
		//Create shell for GUI.
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(502, 363);
		shell.setText("SWT Application");
		
		//Create text.
		txtUser = new Text(shell, SWT.NONE);
		txtUser.setText("USERNAME:");
		txtUser.setBounds(66, 174, 90, 26);
		
		//Create text.
		txtPassword = new Text(shell, SWT.NONE);
		txtPassword.setText("PASSWORD:");
		txtPassword.setBounds(66, 221, 90, 26);
		
		//Create text.
		user = new Text(shell, SWT.BORDER);
		user.setBounds(172, 168, 211, 32);
		
		//Create text.
		pass = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		pass.setBounds(172, 218, 211, 32);
		
		//Create text.
		txtPrescript = new Text(shell, SWT.NONE);
		txtPrescript.setFont(SWTResourceManager.getFont("Segoe UI Semilight", 36, SWT.NORMAL));
		txtPrescript.setText("prescript+");
		txtPrescript.setBounds(111, 52, 285, 83);
		
		//Create login button.
		Button login = new Button(shell, SWT.NONE);
		login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Get input username and password.
				String username = user.getText();
				String password = pass.getText();
				
				//Connect to database to test username and password.
				DatabaseConnection.createConnection();
				Professional p = DatabaseConnection.attemptLogin(username, password);
				DatabaseConnection.shutdown();
				
				//If failed login:
				if (p == null){
					txtIncorrectUsernamepassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					user.setText("");
					pass.setText("");
					
				//If successful login:
				} else {
					try {
						txtIncorrectUsernamepassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						//Open doctor/pharmacist window.
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
		
		//Create text.
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
