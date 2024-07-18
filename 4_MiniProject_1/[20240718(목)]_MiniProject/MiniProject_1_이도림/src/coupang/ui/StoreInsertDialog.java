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

import coupang.dto.Stores;

public class StoreInsertDialog extends JDialog {
	private JTextField storeIdField, categoryIdField, storeNameField, locationField, tellField, operatingHoursField, minPriceField;
	private JButton addButton;
	// private DefaultTableModel tableModel; // 간단한 처리는 객체를 생성하지 않고 받은 파라미터로 처리해도 된다.
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	public StoreInsertDialog(CoupangMainPage parent, DefaultTableModel storesTableModel) {
		// this.tableModel = tableModel;
		
		setTitle("Store 추가");
		setSize(300, 200);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7,2));
		
		// field
		storeIdField = new JTextField();
		categoryIdField = new JTextField();
		storeNameField = new JTextField();
		locationField = new JTextField();
		tellField = new JTextField();
		operatingHoursField = new JTextField();
		minPriceField = new JTextField();
		
		
		// add field with label
		inputPanel.add(new JLabel("store Id"));
		inputPanel.add(storeIdField);
		inputPanel.add(new JLabel("category Id"));
		inputPanel.add(categoryIdField);
		inputPanel.add(new JLabel("store 이름"));
		inputPanel.add(storeNameField);
		inputPanel.add(new JLabel("위치"));
		inputPanel.add(locationField);
		inputPanel.add(new JLabel("연락처"));
		inputPanel.add(tellField);
		inputPanel.add(new JLabel("운영 시간"));
		inputPanel.add(operatingHoursField);
		inputPanel.add(new JLabel("최소 주문 금액"));
		inputPanel.add(minPriceField);
				
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
			int storeId = Integer.parseInt(storeIdField.getText());
			int categoryId = Integer.parseInt(categoryIdField.getText());
			String storeName = storeNameField.getText();
			String location = locationField.getText();
			String tell = tellField.getText();
			String operatingHours = operatingHoursField.getText();
			int minPrice = Integer.parseInt(minPriceField.getText());
			
			parent.insertStores(new Stores(storeId, categoryId, storeName, location, tell, operatingHours, minPrice));
			
			dispose();
		});
	}
}
