import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageRoom = new JButton("Manage room");
		btnManageRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new manageRoom().setVisible(true);
			}
		});
		btnManageRoom.setBackground(Color.CYAN);
		btnManageRoom.setBounds(169, 81, 142, 25);
		contentPane.add(btnManageRoom);
		
		JButton btnCheckIn = new JButton("Check in");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new checkIn().setVisible(true);
			}
		});
		btnCheckIn.setBackground(Color.LIGHT_GRAY);
		btnCheckIn.setForeground(Color.WHITE);
		btnCheckIn.setBounds(333, 81, 117, 25);
		contentPane.add(btnCheckIn);
		
		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.setForeground(Color.WHITE);
		btnCheckOut.setBackground(Color.DARK_GRAY);
		btnCheckOut.setBounds(477, 81, 117, 25);
		contentPane.add(btnCheckOut);
		
		JButton btnBill = new JButton("Bill");
		btnBill.setBackground(Color.GREEN);
		btnBill.setBounds(620, 81, 117, 25);
		contentPane.add(btnBill);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBackground(Color.ORANGE);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to log out?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					setVisible(false);
					new login().setVisible(true);
				}
			}
		});
		btnLogOut.setBounds(700, 0, 105, 25);
		contentPane.add(btnLogOut);
		
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
		btnX.setBounds(817, 0, 61, 25);
		contentPane.add(btnX);
		
		JLabel lblBackgroundhome = new JLabel("backgroundHome");
		lblBackgroundhome.setIcon(new ImageIcon("/home/user2/Pictures/1.jpg"));
		lblBackgroundhome.setBounds(0, 0, 878, 445);
		contentPane.add(lblBackgroundhome);
	}
}
