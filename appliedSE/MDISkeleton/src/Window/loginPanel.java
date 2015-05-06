package Window;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import userRemote.SessionRemote;
import Model.Authenticator;
import Model.Session;
import Model.UserModel;

/*
 * Class serves a dual purpose as a login and registration panel.
 * The action parameter determines which version the panel runs in
 */
public class loginPanel extends ChildPanel implements ActionListener{

	private JLabel name;
	private JLabel password;
	private JLabel email;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField emailField;
	
	private JButton actionButton;
	
	
	public loginPanel(win m, int action) {
		super(m);
		

		
		name = new JLabel("User Name");
		nameField = new JTextField();
		
		password = new JLabel("Password");
		passwordField = new JTextField();
		
		// action = 0, login
		// action = 1, register
		if(action == 0){
			this.setPreferredSize(new Dimension(300,100));
			contentPanel.setLayout(new GridLayout(3,2));
			actionButton = new JButton("Login");
			actionButton.addActionListener(this);
			actionButton.setActionCommand("login");
			contentPanel.add(name);
			contentPanel.add(nameField);
			contentPanel.add(password);
			contentPanel.add(passwordField);
			contentPanel.add(actionButton);
			
		}else if(action == 1){
			this.setPreferredSize(new Dimension(300,120));
			email = new JLabel("email");
			emailField = new JTextField();
			
			contentPanel.setLayout(new GridLayout(4,2));
			actionButton = new JButton("register");
			actionButton.addActionListener(this);
			actionButton.setActionCommand("register");
			contentPanel.add(name);
			contentPanel.add(nameField);
			contentPanel.add(password);
			contentPanel.add(passwordField);
			contentPanel.add(email);
			contentPanel.add(emailField);
			contentPanel.add(actionButton);
			
		}
		// TODO Auto-generated constructor stub
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
			case "login":
				SessionRemote s;
				try{
					s = Authenticator.login(nameField.getText(), passwordField.getText());
					if(s != null){
						master.getController().setSession(s);
						master.openInventory();
						master.openPartList();
						int perm = s.getPermissions();
						if(perm == 1 || perm ==3){
							master.openProduct();
						}
						master.displayChildMessage("Logged in");
					}else{
						master.displayChildMessage("Failed to login: Not valid");
					}
				}catch(Exception ex){
					master.displayChildMessage("Failed to login: Error with Server");
				}
				break;
			case "register":
				try{
					UserModel m = new UserModel(nameField.getText(), emailField.getText());
					master.getController().myDB.register(m, passwordField.getText());
					master.displayChildMessage("Registered");
				}catch(Exception ex){
					master.displayChildMessage("Failed to Register");
				}
				break;

		}
	}

}
