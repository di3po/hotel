import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import project.Select;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.CYAN);
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 25));
		lblLogin.setBounds(274, 100, 109, 30);
		contentPane.add(lblLogin);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(Color.CYAN);
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEmail.setBounds(116, 156, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.CYAN);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(116, 194, 83, 15);
		contentPane.add(lblPassword);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(230, 152, 312, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(230, 192, 312, 19);
		contentPane.add(passwordTextField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int check = 0;
				String email = emailTextField.getText();
				String password = passwordTextField.getText();
				if(email.equals("") || password.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "All fields are required.");
				}
				else if(email.equals("din") && password.equals("admin")) {
					check = 1;
					setVisible(false);
					new adminHome().setVisible(true);
				}
				else {
					ResultSet rs = Select.getData("select *from users where email = '"+email+"' and password = '"+password+"' ");
					try {
						if(rs.next()) {
							check = 1;
							if(rs.getString(7).equals("true")) {
								setVisible(false);
								new home().setVisible(true);
							}else {JOptionPane.showMessageDialog(null, "Wait for admin's approval.");}
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				if(check==0) {
					JOptionPane.showMessageDialog(null, "Incorrect email or password.");
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBounds(116, 260, 83, 25);
		contentPane.add(btnLogin);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new signup().setVisible(true);
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setBackground(Color.DARK_GRAY);
		btnSignup.setBounds(230, 260, 132, 25);
		contentPane.add(btnSignup);
		
		JButton btnForgotPassword = new JButton("Forgot password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new forgotPassword().setVisible(true);
			}
		});
		btnForgotPassword.setForeground(Color.WHITE);
		btnForgotPassword.setBackground(Color.DARK_GRAY);
		btnForgotPassword.setBounds(374, 260, 168, 25);
		contentPane.add(btnForgotPassword);
		
		JButton btnNewButton = new JButton("x");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close app?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					System.exit(0);
				}
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(581, 0, 41, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblBackgroundlogin = new JLabel("backgroundLogin");
		lblBackgroundlogin.setIcon(new ImageIcon("/home/user2/Pictures/Wallpapers/1.jpg"));
		lblBackgroundlogin.setBounds(0, 0, 622, 364);
		contentPane.add(lblBackgroundlogin);
	}
}
