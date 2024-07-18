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
import coupang.dto.User;

public class UserInsertDialog extends JDialog {
	private JTextField userIdField, nameField, emailField, phoneField, registerDateField;
	private JButton addButton;
	// private DefaultTableModel tableModel; // 간단한 처리는 객체를 생성하지 않고 받은 파라미터로 처리해도 된다.
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	public UserInsertDialog(CoupangMainPage parent) {
		// this.tableModel = tableModel;
		
		setTitle("Customer 추가");
		setSize(300, 200);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		// input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2));
		
		// field
		userIdField = new JTextField();
		nameField = new JTextField();
		emailField = new JTextField();
		phoneField = new JTextField();
		registerDateField = new JTextField();
		
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
		addButton = new JButton("가입하기");
		
		buttonPanel.add(addButton);
		
		// add inputPanel, buttonPanel to Dialog
		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		// add button actionListner
		addButton.addActionListener(e -> {
			int userId = Integer.parseInt(userIdField.getText());
			String name = nameField.getText();
			String email = emailField.getText();
			String phone = phoneField.getText();
			String registerDate = registerDateField.getText();
			
			parent.insertUser(new User(userId, name, email, phone, registerDate));
			
			dispose();
		});
	}
}
