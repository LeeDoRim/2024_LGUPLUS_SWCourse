package coupang.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import coupang.dto.Menu;
import coupang.dto.Stores;

public class MenuInsertDialog extends JDialog {
	private JTextField menuIdField, storeIdField, nameField, descriptionField, priceField, categoryField, imageUrlField;
	private JButton addButton;
	// private DefaultTableModel tableModel; // 간단한 처리는 객체를 생성하지 않고 받은 파라미터로 처리해도 된다.
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	public MenuInsertDialog(CoupangMenuDialog parent, DefaultTableModel menuTableModel) {
		// this.tableModel = tableModel;
		
		setTitle("Store Menu 추가");
		setSize(300, 200);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7,2));
		
		// field
		menuIdField = new JTextField();
		storeIdField = new JTextField();
		nameField = new JTextField();
		descriptionField = new JTextField();
		priceField = new JTextField();
		categoryField = new JTextField();
		imageUrlField = new JTextField();
		
		
		// add field with label
		inputPanel.add(new JLabel("menu Id"));
		inputPanel.add(menuIdField);
		inputPanel.add(new JLabel("store Id"));
		inputPanel.add(storeIdField);
		inputPanel.add(new JLabel("menu 이름"));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("설명"));
		inputPanel.add(descriptionField);
		inputPanel.add(new JLabel("가격"));
		inputPanel.add(priceField);
		inputPanel.add(new JLabel("카테고리"));
		inputPanel.add(categoryField);
		inputPanel.add(new JLabel("이미지 URL"));
		inputPanel.add(imageUrlField);
				
		// button panel
		JPanel buttonPanel = new JPanel();
		
		// button
		addButton = new JButton("등록하기");
		
		buttonPanel.add(addButton);
		
		// add inputPanel, buttonPanel to Dialog
		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		// add button actionListner
		addButton.addActionListener(e -> {
			int menuId = Integer.parseInt(menuIdField.getText());
			int storeId = Integer.parseInt(storeIdField.getText());
			String name = nameField.getText();
			String description = descriptionField.getText();
			int price = Integer.parseInt(priceField.getText());
			String category = categoryField.getText();
			String imageUrl = imageUrlField.getText();
			
			parent.insertMenu(new Menu(menuId, storeId, name, description, price, category, imageUrl));
			
			dispose();
		});
	}
}
