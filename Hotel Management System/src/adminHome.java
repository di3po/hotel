import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import project.*;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import java.awt.Component;

public class adminHome extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminHome frame = new adminHome();
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
	public adminHome() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				ResultSet rs = Select.getData("select *from users");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				try {
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(7)});
					}
					rs.close();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnX.setBounds(822, 0, 56, 25);
		contentPane.add(btnX);
		
		JButton btnLogOut = new JButton("log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to log out?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0) {
					setVisible(false);
					new login().setVisible(true);
				}
			}
		});
		btnLogOut.setBackground(Color.ORANGE);
		btnLogOut.setBounds(716, 0, 94, 25);
		contentPane.add(btnLogOut);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome, admin!");
		lblWelcomeAdmin.setForeground(Color.BLUE);
		lblWelcomeAdmin.setBackground(Color.WHITE);
		lblWelcomeAdmin.setFont(new Font("Nimbus Roman No9 L", Font.BOLD, 30));
		lblWelcomeAdmin.setBounds(310, 0, 258, 40);
		contentPane.add(lblWelcomeAdmin);
		
		JLabel lblSearchByName = new JLabel("Search by name or e-mail:");
		lblSearchByName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSearchByName.setBounds(12, 47, 231, 17);
		contentPane.add(lblSearchByName);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(261, 46, 359, 19);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameOrEmail = searchTextField.getText(); 
				ResultSet rs = Select.getData("select *from users where name like '%"+nameOrEmail+"%' or email like '%"+nameOrEmail+"%' ");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				try {
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(7)});
					}
					rs.close();
				}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		btnSearch.setBounds(632, 43, 117, 25);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new adminHome().setVisible(true);
			}
		});
		btnClear.setBounds(761, 43, 105, 25);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerifyInputWhenFocusTarget(false);
		scrollPane.setEnabled(false);
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane.setBounds(12, 76, 737, 353);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setBackground(Color.WHITE);
		table.setName("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "E-mail", "Security Question", "Address", "Status"
			}
		) 
			{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		JButton btnChange = new JButton("Change");
		btnChange.setActionCommand("change                                                                                                                                            ");
		btnChange.setAlignmentY(Component.TOP_ALIGNMENT);
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String name = model.getValueAt(index, 0).toString();
				String email = model.getValueAt(index, 1).toString();
				String status = model.getValueAt(index, 4).toString();
				if(status.equals("true")) {
					status = "false";
				}else { status="true"; }
				try {
					int ans = JOptionPane.showConfirmDialog(null, "Do you want to change status of '"+name+"' and email='"+email+"' ?", "Select", JOptionPane.YES_NO_OPTION);
					if(ans == 0) {
						InsertUpdateDelete.setData("update users set status = '"+status+"' where name = '"+name+"' and email = '"+email+"' ", "Status changed successfully.");
						setVisible(false);
						new adminHome().setVisible(true);
					}
				} catch(Exception ex) {JOptionPane.showMessageDialog(null, ex);}
			}
		});
		btnChange.setHorizontalTextPosition(SwingConstants.CENTER);
		btnChange.setBounds(761, 115, 105, 25);
		contentPane.add(btnChange);
		
		JLabel lblChangeStatus = new JLabel("Change status");
		lblChangeStatus.setBounds(759, 80, 107, 40);
		contentPane.add(lblChangeStatus);
	}
}
