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

import coupang.dto.Stores;

public class EditStoreDialog extends JDialog {
	private JTextField storeIdField, categoryIdField, storeNameField, locationField, tellField, operatingHoursField, minPriceField;
	private JButton storeUpdateButton, storeDeleteButton;
	// private DefaultTableModel tableModel; // 간단한 처리는 객체를 생성하지 않고 받은 파라미터로 처리해도 된다.
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	// 선택된 row의 데이터를 보여주기 위하여 선택된 row index를 받는다.
	public EditStoreDialog(CoupangMainPage parent, DefaultTableModel storesTableModel, int rowIndex) {
		// this.tableModel = tableModel;
		
		setTitle("Store 수정");
		setSize(300, 200);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		// 선택된 book의 bookId로 book table에서 조회
		Integer storeId = (Integer) storesTableModel.getValueAt(rowIndex, 0);
		
		Stores store = parent.detailStore(storeId);
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7,2));
		
		// field
		storeIdField = new JTextField(String.valueOf(storeId));  // 정수 -> 문자열
		storeIdField.setEditable(false); // bookIdField는 수정하지 못하도록 변경
		categoryIdField = new JTextField(String.valueOf(store.getCategoryId()));  // 정수 -> 문자열
		storeNameField = new JTextField(store.getStoreName());
		locationField = new JTextField(store.getLocation());
		tellField = new JTextField(store.getTell());  // 정수 -> 문자열
		operatingHoursField = new JTextField(store.getOperatingHours());
		minPriceField = new JTextField(String.valueOf(store.getMinPrice()));  // 정수 -> 문자열
		
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
            	int categoryId = Integer.parseInt(categoryIdField.getText());
            	String storeName = storeNameField.getText();
            	String location = locationField.getText();
            	String tell = tellField.getText();
            	String operatingHours = operatingHoursField.getText();
            	int minPrice = Integer.parseInt(minPriceField.getText());
            	
            	// 위쪽에 선언된(선택된 row에서) 변수를 사용한다.
            	parent.updateStore(new Stores(storeId, categoryId, storeName, location, tell, operatingHours, minPrice)); 
            	dispose();
            }
		});
		
		
		storeDeleteButton.addActionListener(e -> {
			int ret = JOptionPane.showConfirmDialog(this, "삭제할까요?", "삭제 확인", JOptionPane.YES_NO_OPTION);
            if( ret == JOptionPane.YES_OPTION ) {
            	
            	parent.deleteStore(storeId); // 위쪽에 선언된(선택된 row에서) 변수를 사용한다.
            	dispose();
            }
		});
	}
}
