import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import project.InsertUpdateDelete;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JTextField answerTextField;
	private JTextField addressTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnExit = new JButton("x");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to close app?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					System.exit(0);
				}
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(616, 0, 41, 33);
		contentPane.add(btnExit);
		
		JLabel lblSignUp = new JLabel("Sign up");
		lblSignUp.setForeground(Color.CYAN);
		lblSignUp.setFont(new Font("Dialog", Font.BOLD, 30));
		lblSignUp.setBounds(263, 110, 147, 65);
		contentPane.add(lblSignUp);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setForeground(Color.CYAN);
		lblName.setBackground(Color.WHITE);
		lblName.setBounds(55, 207, 139, 15);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEmail.setForeground(Color.CYAN);
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setBounds(55, 234, 139, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPassword.setForeground(Color.CYAN);
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(55, 261, 139, 15);
		contentPane.add(lblPassword);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(209, 205, 393, 19);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(209, 232, 393, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(209, 259, 393, 19);
		contentPane.add(passwordTextField);
		
		JLabel lblSecurityQuestion = new JLabel("Security question");
		lblSecurityQuestion.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSecurityQuestion.setForeground(Color.CYAN);
		lblSecurityQuestion.setBackground(Color.WHITE);
		lblSecurityQuestion.setBounds(55, 297, 139, 15);
		contentPane.add(lblSecurityQuestion);
		
		JComboBox sqComboBox = new JComboBox();
		sqComboBox.setModel(new DefaultComboBoxModel(new String[] {"What is your name?", "What is your pet's name?", "Who are you?"}));
		sqComboBox.setBounds(209, 290, 393, 30);
		contentPane.add(sqComboBox);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAnswer.setForeground(Color.CYAN);
		lblAnswer.setBackground(Color.WHITE);
		lblAnswer.setBounds(55, 342, 139, 15);
		contentPane.add(lblAnswer);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAddress.setForeground(Color.CYAN);
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setBounds(55, 374, 139, 15);
		contentPane.add(lblAddress);
		
		answerTextField = new JTextField();
		answerTextField.setBounds(209, 341, 393, 19);
		contentPane.add(answerTextField);
		answerTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(209, 372, 393, 19);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = nameTextField.getText();
				String email = emailTextField.getText();
				String password = passwordTextField.getText();
				String sq = (String) sqComboBox.getSelectedItem();
				String answer = answerTextField.getText();
				String address = addressTextField.getText();
				
				if (name.equals("") || email.equals("") || password.equals("") || answer.equals("") || address.equals("")) {
					JOptionPane.showMessageDialog(null, "All fields are required.");
				}
				else {
					String Query;
					Query = "insert into users values('"+name+"', '"+email+"', '"+password+"', '"+sq+"', '"+answer+"', '"+address+"', 'false')";
					InsertUpdateDelete.setData(Query, "Registered successfully!");
					setVisible(false);
					new signup().setVisible(true);
				}
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(209, 431, 87, 25);
		contentPane.add(btnNewButton);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new login().setVisible(true);
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBounds(318, 431, 78, 25);
		contentPane.add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new forgotPassword().setVisible(true);
			}
		});
		btnForgotPassword.setForeground(Color.WHITE);
		btnForgotPassword.setBackground(Color.DARK_GRAY);
		btnForgotPassword.setBounds(410, 431, 192, 25);
		contentPane.add(btnForgotPassword);
		
		JLabel lblNewLabel = new JLabel("background");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("/home/user2/Pictures/Wallpapers/1.jpg"));
		lblNewLabel.setBounds(0, 0, 657, 511);
		contentPane.add(lblNewLabel);
	}
}
