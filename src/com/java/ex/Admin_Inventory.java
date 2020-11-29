package com.java.ex;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.java.data.InventDAO;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class Admin_Inventory extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Inventory frame = new Admin_Inventory();
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
	public Admin_Inventory() {
		setTitle("\uC7AC\uACE0\uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(135, 206, 250), 2, true),
				new LineBorder(new Color(135, 206, 250), 2, true)));
		panel_1.setBackground(new Color(135, 206, 250));
		panel_1.setBounds(336, 171, 500, 250);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtId = new JTextField();
		txtId.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		txtId.setBounds(292, 48, 194, 40);
		panel_1.add(txtId);
		txtId.setColumns(10);

		pass = new JPasswordField();
		pass.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		pass.setBounds(292, 118, 194, 40);
		panel_1.add(pass);

		JButton button = new JButton("\uB85C\uADF8\uC778");
		button.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		button.setBounds(292, 188, 194, 50);
		panel_1.add(button);

		JLabel lblNewLabel = new JLabel("Login_");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD, 45));
		lblNewLabel.setBounds(49, 26, 163, 62);
		panel_1.add(lblNewLabel);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InventDAO dao = new InventDAO();
				InsertInventory next = new InsertInventory();

				String id = txtId.getText();
				String pw = pass.getPassword().toString();

				dao.login(Integer.parseInt(txtId.getText()), pass.getPassword().toString());

				JOptionPane.showMessageDialog(null, "로그인 성공");

				dispose();

				next.setVisible(true);
			}
		});

	}
}
