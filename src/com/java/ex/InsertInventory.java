package com.java.ex;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.java.data.InventDAO;
import com.java.data.InventDTO;

public class InsertInventory extends JFrame {

	private PreparedStatement pst;
	private Connection con;
	private ResultSet rs;

	private JTextField txtiOffname;
	private JTextField txtiName;
	private JTextField txtidate;
	private JTextField txtiCode;
	private JTextField txtiAmount;
	private JTextField txtiPrice;
	private JButton btnSelect;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTable DbGrid;
	private JScrollPane scrollPane;

	InventDAO inv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertInventory frame = new InsertInventory();
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
	public InsertInventory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 800);
		getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panelNorth = new JPanel();
		getContentPane().add(panelNorth, "name_56973965214794");
		panelNorth.setLayout(null);

		txtiCode = new JTextField();
		txtiCode.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		txtiCode.setBounds(137, 110, 170, 30);
		txtiCode.setColumns(10);
		panelNorth.add(txtiCode);

		txtiAmount = new JTextField();
		txtiAmount.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		txtiAmount.setBounds(137, 245, 170, 30);
		txtiAmount.setColumns(10);
		panelNorth.add(txtiAmount);

		txtiPrice = new JTextField();
		txtiPrice.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		txtiPrice.setBounds(137, 292, 170, 30);
		txtiPrice.setColumns(10);
		panelNorth.add(txtiPrice);

		JLabel label = new JLabel("\uC81C\uD488 \uCF54\uB4DC");
		label.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		label.setBounds(50, 110, 75, 30);
		panelNorth.add(label);

		JLabel label_1 = new JLabel("\uC7AC\uACE0\uBA85");
		label_1.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		label_1.setBounds(50, 152, 75, 30);
		panelNorth.add(label_1);

		JLabel lblNewLabel = new JLabel("\uC804\uCCB4 \uC218\uB7C9");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 245, 75, 30);
		panelNorth.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uAE08\uC561");
		lblNewLabel_1.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		lblNewLabel_1.setBounds(50, 298, 75, 24);
		panelNorth.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("등록 날짜");
		lblNewLabel_2.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		lblNewLabel_2.setBounds(50, 203, 75, 30);
		panelNorth.add(lblNewLabel_2);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(321, 69, 1011, 600);
		panelNorth.add(scrollPane);

		DbGrid = new JTable();
		DbGrid.setFont(new Font("함초롬돋움", Font.BOLD, 13));
		DbGrid.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) DbGrid.getModel();

				int selectedRowIndex = DbGrid.getSelectedRow();

				txtiOffname.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txtiCode.setText(model.getValueAt(selectedRowIndex, 1).toString());
				txtiName.setText(model.getValueAt(selectedRowIndex, 2).toString());
				txtidate.setText(model.getValueAt(selectedRowIndex, 3).toString());
				txtiAmount.setText(model.getValueAt(selectedRowIndex, 4).toString());
				txtiPrice.setText(model.getValueAt(selectedRowIndex, 5).toString());

			}

		});
		scrollPane.setViewportView(DbGrid);
		DbGrid.setFillsViewportHeight(true);

		DbGrid.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "지점명", "제품 코드", "제품명", "등록 날짜", "재고량", "가격" }) {
					Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,
							Integer.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		txtidate = new JTextField();
		txtidate.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		txtidate.setColumns(10);
		txtidate.setBounds(137, 200, 170, 30);
		panelNorth.add(txtidate);

		txtiOffname = new JTextField();
		txtiOffname.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		txtiOffname.setColumns(10);
		txtiOffname.setBounds(137, 68, 170, 30);
		panelNorth.add(txtiOffname);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1340, 57);
		panelNorth.add(menuBar);

		btnInsert = new JButton("제품 등록");
		btnInsert.setBackground(Color.LIGHT_GRAY);
		menuBar.add(btnInsert);
		btnInsert.setFont(new Font("함초롬돋움", Font.BOLD, 18));

		btnUpdate = new JButton("제품 수정");
		menuBar.add(btnUpdate);
		btnUpdate.setFont(new Font("함초롬돋움", Font.BOLD, 18));

		btnDelete = new JButton("제품 삭제");
		menuBar.add(btnDelete);
		btnDelete.setFont(new Font("함초롬돋움", Font.BOLD, 18));

		btnSelect = new JButton("새로 고침");
		menuBar.add(btnSelect);
		btnSelect.setFont(new Font("함초롬돋움", Font.BOLD, 18));

		txtiName = new JTextField();
		txtiName.setBounds(137, 155, 170, 30);
		panelNorth.add(txtiName);
		txtiName.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		txtiName.setColumns(10);

		JLabel label_3 = new JLabel("지점명");
		label_3.setBounds(50, 71, 75, 30);
		panelNorth.add(label_3);
		label_3.setFont(new Font("함초롬돋움", Font.BOLD, 18));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel DBGrid = (DefaultTableModel) DbGrid.getModel();

				inv = new InventDAO();

				ArrayList<InventDTO> bean = inv.select();
				DBGrid.setRowCount(0);
				for (InventDTO beans : bean) {
					DBGrid.addRow(new Object[] { beans.getiOffName(), beans.getiCode(), beans.getiName(),
							beans.getiDate(), beans.getiAmount(), beans.getiPrice() });
					System.out.println(beans);
				}

				JOptionPane.showMessageDialog(null, "검색을 완료했습니다.");

				clear();

			}
		});
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());

				DefaultTableModel model3 = (DefaultTableModel) DbGrid.getModel();

				InventDAO dataDelete = new InventDAO();

				int row = DbGrid.getSelectedRow();

				if (row < 0) {
					return;
				}

				if (txtiOffname.getText().equals("") || txtiCode.getText().equals("") || txtiName.getText().equals("")
						|| txtidate.getText().equals("") || txtiAmount.getText().equals("")
						|| txtiPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "열을 입력하여주세요");

				} else {
					dataDelete.delete((String) model3.getValueAt(row, 1).toString());

					JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
				}

				dataDelete.delete(txtiOffname.getText());

				model3.setRowCount(0);

				dataDelete.select();

				ArrayList<InventDTO> bean = inv.select();

				for (InventDTO beans : bean) {
					model3.addRow(new Object[] { beans.getiOffName(), beans.getiCode(), beans.getiName(),
							beans.getiDate(), beans.getiAmount(), beans.getiPrice() });
					System.out.println(beans);
				}
				clear();
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(e.getActionCommand());

				DefaultTableModel model2 = (DefaultTableModel) DbGrid.getModel();

				InventDAO dataUpdate = new InventDAO();

				int row = DbGrid.getSelectedRow();

				if (row < 0) {
					return;
				}

				if (txtiOffname.getText().equals("") || txtiCode.getText().equals("") || txtiName.getText().equals("")
						|| txtidate.getText().equals("") || txtiAmount.getText().equals("")
						|| txtiPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "ㅇㅇ");

				} else {
					dataUpdate.update((String) model2.getValueAt(row, 0).toString(),
							(String) model2.getValueAt(row, 1).toString(),
							(String) model2.getValueAt(row, 2).toString(),
							(String) model2.getValueAt(row, 3).toString(),
							Integer.parseInt(model2.getValueAt(row, 4).toString()),
							Integer.parseInt(model2.getValueAt(row, 5).toString()));

					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
				}

				dataUpdate.update(txtiOffname.getText(), txtiCode.getText(), txtiName.getText(), txtidate.getText(),
						Integer.parseInt(txtiAmount.getText()), Integer.parseInt(txtiPrice.getText()));

				model2.setRowCount(0);
				dataUpdate.select();

				ArrayList<InventDTO> bean = inv.select();

				for (InventDTO beans : bean) {
					model2.addRow(new Object[] { beans.getiOffName(), beans.getiCode(), beans.getiName(),
							beans.getiDate(), beans.getiAmount(), beans.getiPrice() });
					System.out.println(beans);
				}
				clear();
			}
		});
		btnInsert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model2 = (DefaultTableModel) DbGrid.getModel();

				InventDAO insertDAO = new InventDAO();

				if (txtiOffname.getText().equals("") || txtiCode.getText().equals("") || txtiName.getText().equals("")
						|| txtiAmount.getText().equals("")
						|| txtiPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 입력하세요!");

				} else {
					insertDAO.insert(txtiOffname.getText(), txtiCode.getText(), txtiName.getText(),
							Integer.parseInt(txtiAmount.getText()), Integer.parseInt(txtiPrice.getText()));
					JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.");
				}
				clear();
			}
		});

	}

	public void clear() {
		txtiOffname.setText("");
		txtiCode.setText("");
		txtiName.setText("");
		txtidate.setText("");
		txtiAmount.setText("");
		txtiPrice.setText("");
	}
}
