package coupang.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import coupang.dto.Menu;
import coupang.dto.Stores;

public class EditMenuDialog extends JDialog {
	private JTextField menuIdField, storeIdField, nameField, descriptionField, priceField, categoryField, imageUrlField;
	private JButton storeUpdateButton, storeDeleteButton;
	// private DefaultTableModel tableModel; // 간단한 처리는 객체를 생성하지 않고 받은 파라미터로 처리해도 된다.
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	// 선택된 row의 데이터를 보여주기 위하여 선택된 row index를 받는다.
	public EditMenuDialog(CoupangMenuDialog parent, DefaultTableModel menuTableModel, int rowIndex) {
		// this.tableModel = tableModel;
		
		setTitle("Menu 수정");
		setSize(300, 200);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		// 선택된 book의 bookId로 book table에서 조회
		Integer menuId = (Integer) menuTableModel.getValueAt(rowIndex, 0);
		
		Menu menu = parent.detailMenu(menuId);
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7,2));
		
		// field
		menuIdField = new JTextField(String.valueOf(menuId));  // 정수 -> 문자열
		menuIdField.setEditable(false); // bookIdField는 수정하지 못하도록 변경
		storeIdField = new JTextField(String.valueOf(menu.getStoreId()));  // 정수 -> 문자열
		nameField = new JTextField(menu.getName());  // 정수 -> 문자열
		descriptionField = new JTextField(menu.getDescription());
		priceField = new JTextField(String.valueOf(menu.getPrice()));
		categoryField = new JTextField(menu.getCategory()); 
		imageUrlField = new JTextField(menu.getImageUrl());  
		
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
		storeUpdateButton = new JButton("수정");
		storeDeleteButton = new JButton("삭제");
		
		buttonPanel.add(storeUpdateButton);
		buttonPanel.add(storeDeleteButton);

		// add inputPanel, buttonPanel to Dialog
		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		// update, delete button actionListner
		storeUpdateButton.addActionListener(e -> {
			int ret = JOptionPane.showConfirmDialog(this, "수정할까요?", "수정 확인", JOptionPane.YES_NO_OPTION);
            if( ret == JOptionPane.YES_OPTION ) {
            	int storeId = Integer.parseInt(storeIdField.getText());
            	String name = nameField.getText();
            	String description = descriptionField.getText();
            	int price = Integer.parseInt(priceField.getText());
            	String category = categoryField.getText();
            	String imageUrl = imageUrlField.getText();
            	
            	// 위쪽에 선언된(선택된 row에서) 변수를 사용한다.
            	parent.updateMenu(new Menu(menuId, storeId, name, description, price, category, imageUrl)); 
            	dispose();
            }
		});
		
		
		storeDeleteButton.addActionListener(e -> {
			int ret = JOptionPane.showConfirmDialog(this, "삭제할까요?", "삭제 확인", JOptionPane.YES_NO_OPTION);
            if( ret == JOptionPane.YES_OPTION ) {
            	
            	parent.deleteMenu(menuId); // 위쪽에 선언된(선택된 row에서) 변수를 사용한다.
            	dispose();
            }
		});
	}
}
