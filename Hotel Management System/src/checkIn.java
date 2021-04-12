import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

import java.sql.*;

import project.*;

public class checkIn extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField mobnoTextField;
	private JTextField nationalityTextField;
	private JTextField emailTextField;
	private JTextField idProofTextField;
	private JTextField addressTextField;
	private static JTextField checkInDateTextField;
	private static JTextField priceTextField;
	
	String bed;
	String roomType;
	String roomNo;
	String price;
	private JComboBox roomNoComboBox;
	private JComboBox bedComboBox;
	private JComboBox roomTypeComboBox;
	
	public void roomDetails() {
		roomNoComboBox.removeAllItems();
		priceTextField.setText("");
		bed = (String) bedComboBox.getSelectedItem();
		roomType = (String) roomTypeComboBox.getSelectedItem();
		try {
			ResultSet rs = Select.getData("select *from room where bed = '"+bed+"' and roomType = '"+roomType+"' and status = 'Not Booked' ");
			while(rs.next()) {
				roomNoComboBox.addItem(rs.getString(1));
				
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "ex");
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkIn frame = new checkIn();
					frame.setVisible(true);
					checkInDateTextField.setEditable(false);
					priceTextField.setEditable(false);
					SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
					Calendar calendar = Calendar.getInstance();
					checkInDateTextField.setText(myFormat.format(calendar.getTime()));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public checkIn() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckIn = new JLabel("Check in");
		lblCheckIn.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCheckIn.setBounds(12, 12, 116, 24);
		contentPane.add(lblCheckIn);
		
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
		btnX.setBounds(804, 0, 74, 25);
		contentPane.add(btnX);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(12, 81, 70, 15);
		contentPane.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(114, 79, 203, 19);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblMobNumber = new JLabel("Mob. number");
		lblMobNumber.setBounds(12, 134, 93, 15);
		contentPane.add(lblMobNumber);
		
		mobnoTextField = new JTextField();
		mobnoTextField.setBounds(114, 132, 203, 19);
		contentPane.add(mobnoTextField);
		mobnoTextField.setColumns(10);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setBounds(12, 191, 93, 15);
		contentPane.add(lblNationality);
		
		nationalityTextField = new JTextField();
		nationalityTextField.setBounds(114, 189, 203, 19);
		contentPane.add(nationalityTextField);
		nationalityTextField.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(12, 250, 70, 15);
		contentPane.add(lblGender);
		
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		genderComboBox.setBounds(114, 245, 203, 24);
		contentPane.add(genderComboBox);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(12, 305, 70, 15);
		contentPane.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(114, 303, 203, 19);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblIdProof = new JLabel("ID proof");
		lblIdProof.setBounds(374, 83, 70, 15);
		contentPane.add(lblIdProof);
		
		idProofTextField = new JTextField();
		idProofTextField.setBounds(462, 81, 203, 19);
		contentPane.add(idProofTextField);
		idProofTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(374, 136, 70, 15);
		contentPane.add(lblAddress);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(462, 134, 203, 19);
		contentPane.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblCheckInDate = new JLabel("Check in date (today)");
		lblCheckInDate.setBounds(374, 193, 166, 15);
		contentPane.add(lblCheckInDate);
		
		checkInDateTextField = new JTextField();
		checkInDateTextField.setBounds(558, 191, 107, 19);
		contentPane.add(checkInDateTextField);
		checkInDateTextField.setColumns(10);
		
		
		JLabel lblBed = new JLabel("Bed");
		lblBed.setBounds(683, 81, 53, 15);
		contentPane.add(lblBed);
		
		JComboBox bedComboBox = new JComboBox();
		bedComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				roomDetails();
			}
		});
		bedComboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		bedComboBox.setBounds(760, 76, 106, 24);
		contentPane.add(bedComboBox);
		
		JLabel lblRoomType = new JLabel("Room type");
		lblRoomType.setBounds(683, 134, 75, 15);
		contentPane.add(lblRoomType);
		
		JComboBox roomTypeComboBox = new JComboBox();
		roomTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				roomDetails();
			}
		});
		roomTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"AC", "Non-AC"}));
		roomTypeComboBox.setBounds(760, 129, 106, 24);
		contentPane.add(roomTypeComboBox);
		
		JLabel lblRoom = new JLabel("Room #");
		lblRoom.setBounds(683, 191, 70, 15);
		contentPane.add(lblRoom);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(683, 250, 70, 15);
		contentPane.add(lblPrice);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(760, 248, 106, 19);
		contentPane.add(priceTextField);
		priceTextField.setColumns(10);
		
		JButton btnAlloteRoom = new JButton("Allote room");
		btnAlloteRoom.setBounds(711, 292, 117, 25);
		contentPane.add(btnAlloteRoom);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new checkIn().setVisible(true);
			}
		});
		btnClear.setBounds(711, 341, 117, 25);
		contentPane.add(btnClear);
		
		JComboBox roomNoComboBox = new JComboBox();
		roomNoComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				roomNo = (String) roomNoComboBox.getSelectedItem();
				try {
					ResultSet rs = Select.getData("select *from room where roomNo = '"+roomNo+"' ");
					while(rs.next()) {
						priceTextField.setText(rs.getString(4));
						
					}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		roomNoComboBox.setBounds(760, 186, 106, 24);
		contentPane.add(roomNoComboBox);
	}
}
