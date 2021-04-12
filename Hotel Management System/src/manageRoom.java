import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import project.*;
import java.sql.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class manageRoom extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField roomNoTextField;
	private JTextField priceTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageRoom frame = new manageRoom();
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
	public manageRoom() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select *from room");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				try {
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
					}
					rs.close();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		setLocation(new Point(50, 109));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManageRoom = new JLabel("Manage room");
		lblManageRoom.setFont(new Font("Dialog", Font.BOLD, 20));
		lblManageRoom.setBounds(12, 12, 161, 24);
		contentPane.add(lblManageRoom);
		
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
		btnX.setBounds(813, 0, 69, 25);
		contentPane.add(btnX);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 632, 376);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Room #", "Room type", "Bed", "Price", "Status"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblRoom = new JLabel("Room #");
		lblRoom.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRoom.setBounds(662, 55, 70, 15);
		contentPane.add(lblRoom);
		
		roomNoTextField = new JTextField();
		roomNoTextField.setBounds(755, 53, 115, 19);
		contentPane.add(roomNoTextField);
		roomNoTextField.setColumns(10);
		
		JLabel lblRoomType = new JLabel("Room type");
		lblRoomType.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRoomType.setBounds(662, 93, 84, 15);
		contentPane.add(lblRoomType);
		
		JComboBox roomTypeComboBox = new JComboBox();
		roomTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"AC", "Non-AC"}));
		roomTypeComboBox.setBounds(755, 88, 115, 24);
		contentPane.add(roomTypeComboBox);
		
		JLabel lblBed = new JLabel("Bed");
		lblBed.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBed.setBounds(662, 129, 70, 15);
		contentPane.add(lblBed);
		
		JComboBox bedComboBox = new JComboBox();
		bedComboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		bedComboBox.setBounds(755, 124, 115, 24);
		contentPane.add(bedComboBox);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrice.setBounds(662, 162, 70, 15);
		contentPane.add(lblPrice);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(755, 160, 115, 19);
		contentPane.add(priceTextField);
		priceTextField.setColumns(10);
		
		JButton btnAddRoom = new JButton("Add room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String roomNo = roomNoTextField.getText();
				String roomType = (String) roomTypeComboBox.getSelectedItem();
				String bed = (String) bedComboBox.getSelectedItem();
				String price = priceTextField.getText();
				
				String Query = "insert into room values ('"+roomNo+"', '"+roomType+"', '"+bed+"', '"+price+"', 'Not Booked')";
				InsertUpdateDelete.setData(Query, "Successfully updated.");
				setVisible(false);
				new manageRoom().setVisible(true);
			}
		});
		btnAddRoom.setBounds(711, 205, 107, 25);
		contentPane.add(btnAddRoom);
	}
}
