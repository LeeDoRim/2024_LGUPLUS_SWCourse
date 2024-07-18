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

import coupang.dto.User;

public class EditUserDialog extends JDialog {
	private JTextField userIdField, nameField, emailField, phoneField, registerDateField;
	private JButton orderListButton, storeUpdateButton, storeDeleteButton;
	// private DefaultTableModel tableModel; // 간단한 처리는 객체를 생성하지 않고 받은 파라미터로 처리해도 된다.
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	// 선택된 row의 데이터를 보여주기 위하여 선택된 row index를 받는다.
	public EditUserDialog(CoupangMainPage parent, JTextField idField) {
		// this.tableModel = tableModel;
		
		setTitle("회원 정보");
		setSize(650, 400);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		// 선택된 book의 bookId로 book table에서 조회
		Integer userId = Integer.parseInt(idField.getText());
		
		User user = parent.detailUser(userId);
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2));
		
		// field
		userIdField = new JTextField(String.valueOf(userId));  // 정수 -> 문자열
		userIdField.setEditable(false); // bookIdField는 수정하지 못하도록 변경
		nameField = new JTextField(String.valueOf(user.getName()));  
		emailField = new JTextField(String.valueOf(user.getEmail())); 
		phoneField = new JTextField(user.getPhone());
		registerDateField = new JTextField(user.getRegisterDate());
		registerDateField.setEditable(false);
		
		// add field with label
		inputPanel.add(new JLabel("아이디"));
		inputPanel.add(userIdField);
		inputPanel.add(new JLabel("이름"));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("이메일"));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("연락처"));
		inputPanel.add(phoneField);
		inputPanel.add(new JLabel("가입일"));
		inputPanel.add(registerDateField);
		
		// button panel
		JPanel buttonPanel = new JPanel();
		
		// button
		orderListButton = new JButton("주문보기");
		storeUpdateButton = new JButton("수정");
		storeDeleteButton = new JButton("삭제");
		
		buttonPanel.add(orderListButton);
		buttonPanel.add(storeUpdateButton);
		buttonPanel.add(storeDeleteButton);

		// add inputPanel, buttonPanel to Dialog
		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		// update, delete button actionListner
		storeUpdateButton.addActionListener(e -> {
			int ret = JOptionPane.showConfirmDialog(this, "수정할까요?", "수정 확인", JOptionPane.YES_NO_OPTION);
            if( ret == JOptionPane.YES_OPTION ) {
            	String name = nameField.getText();
            	String email = emailField.getText();
            	String phone = phoneField.getText();
            	String registerDate = registerDateField.getText();
            	
            	// 위쪽에 선언된(선택된 row에서) 변수를 사용한다.
            	parent.updateUser(new User(userId, name, email, phone, registerDate)); 
            	dispose();
            }
		});
		
		
		storeDeleteButton.addActionListener(e -> {
			int ret = JOptionPane.showConfirmDialog(this, "삭제할까요?", "삭제 확인", JOptionPane.YES_NO_OPTION);
            if( ret == JOptionPane.YES_OPTION ) {
            	
            	parent.deleteUser(userId); // 위쪽에 선언된(선택된 row에서) 변수를 사용한다.
            	dispose();
            }
		});
	}
}
