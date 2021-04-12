import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.sql.*;
import project.*;
import javax.swing.JPasswordField;

public class forgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField sqTextField;
	private JTextField answerTextField;
	String email;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgotPassword frame = new forgotPassword();
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
	public forgotPassword() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblForgotPassword = new JLabel("Forgot password");
		lblForgotPassword.setForeground(Color.CYAN);
		lblForgotPassword.setFont(new Font("Dialog", Font.BOLD, 20));
		lblForgotPassword.setBounds(197, 104, 201, 46);
		contentPane.add(lblForgotPassword);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(Color.CYAN);
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEmail.setBounds(72, 167, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblSecurityQuestion = new JLabel("Security question");
		lblSecurityQuestion.setForeground(Color.CYAN);
		lblSecurityQuestion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSecurityQuestion.setBounds(72, 206, 145, 15);
		contentPane.add(lblSecurityQuestion);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setForeground(Color.CYAN);
		lblAnswer.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAnswer.setBounds(72, 240, 70, 15);
		contentPane.add(lblAnswer);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setForeground(Color.CYAN);
		lblNewPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewPassword.setBounds(72, 278, 133, 15);
		contentPane.add(lblNewPassword);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(255, 162, 217, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		sqTextField = new JTextField();
		sqTextField.setBounds(255, 202, 217, 19);
		contentPane.add(sqTextField);
		sqTextField.setColumns(10);
		
		answerTextField = new JTextField();
		answerTextField.setBounds(255, 236, 217, 19);
		contentPane.add(answerTextField);
		answerTextField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int check = 0;
				String securityQuestion = sqTextField.getText();
				String answer = answerTextField.getText();
				String newpassword = passwordTextField.getText();
				if(answer.equals("") || newpassword.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Please, fill answer and new password fiels.");
				}else {
					/*see: secQ and securityQuestion connection*/
					ResultSet rs = Select.getData("select *from users where email = '"+email+"' and secQ = '"+securityQuestion+"' and answer = '"+answer+"' ");
					try {
						if(rs.next()) {
							check = 1;
							InsertUpdateDelete.setData("update users set password = '"+newpassword+"' where email = '"+email+"' ", "Password set successfully!");
							setVisible(false);
							new forgotPassword().setVisible(true);
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				if(check==0) {
					JOptionPane.showMessageDialog(null, "Incorrect answer.");
				}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.DARK_GRAY);
		btnSave.setBounds(72, 324, 100, 25);
		contentPane.add(btnSave);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new signup().setVisible(true);
			}
		});
		btnSignup.setForeground(Color.WHITE);
		btnSignup.setBackground(Color.DARK_GRAY);
		btnSignup.setBounds(253, 324, 85, 25);
		contentPane.add(btnSignup);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new login().setVisible(true);
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBounds(385, 324, 85, 25);
		contentPane.add(btnLogin);
		
		JButton btnX = new JButton("x");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close app?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					System.exit(0);
				}
			}
		});
		btnX.setBackground(Color.RED);
		btnX.setBounds(551, 0, 56, 30);
		contentPane.add(btnX);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int check = 0;
				email = emailTextField.getText();
				if(email.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "E-mail field is empty");
				}
				else {
					ResultSet rs = Select.getData("select *from users where email = '"+email+"' ");
					try {
						if(rs.next()) {
							check = 1;
							sqTextField.setEditable(false);
							emailTextField.setEditable(false);
							sqTextField.setText(rs.getString(4));
						}
					}catch(Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				if(check==0) {
					JOptionPane.showMessageDialog(null, "Incorrect e-mail");
				}
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.DARK_GRAY);
		btnSearch.setBounds(495, 162, 85, 20);
		contentPane.add(btnSearch);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(255, 276, 217, 19);
		contentPane.add(passwordTextField);
		
		JLabel lblBackgroundfp = new JLabel("backgroundFP");
		lblBackgroundfp.setIcon(new ImageIcon("/home/user2/Pictures/1.jpg"));
		lblBackgroundfp.setBounds(0, 0, 607, 427);
		contentPane.add(lblBackgroundfp);
	}

}
