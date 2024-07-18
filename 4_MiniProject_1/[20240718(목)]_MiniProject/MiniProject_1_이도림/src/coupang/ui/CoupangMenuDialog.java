package coupang.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import coupang.dao.MenuDao;
import coupang.dto.Menu;
import coupang.dto.Stores;

public class CoupangMenuDialog extends JDialog {
	private JTable menuTable; // grid ui component
	private DefaultTableModel menuTableModel;	// grid 형태의 data롤 표현
	
	// 버튼 생성
	private JButton orderButton, menuInsertButton, menuEditButton;	
	
	// DAO 객체 생성
	private MenuDao menuDao = new MenuDao();
	
	private Integer storeId;
	
	// 부모 BookManager table 화면에 내용을 띄우기 위하여 생성자에서 부모의 tableModel 객체를 받는다.
	// 선택된 row의 데이터를 보여주기 위하여 선택된 row index를 받는다.
	public CoupangMenuDialog(CoupangMainPage parent, DefaultTableModel storesTableModel, int rowIndex) {
		// this.tableModel = tableModel;
		
		setTitle("Store Menu");
		setSize(650, 400);
		setLayout(new BorderLayout()); 
		setLocationRelativeTo(parent); // 부모 창의 가운데 팝업이 열리도록 설정한다.
		
		storeId = (Integer) storesTableModel.getValueAt(rowIndex, 0);
		
		 // 가게 테이블 목록 삽입
		menuTableModel = new DefaultTableModel(new Object[] {"menu ID", "store ID", "이름", "설명", "가격", "카테고리", "이미지 URL"}, 0) {
			@Override
            public boolean isCellEditable(int row, int column) { // 목록에서 수정할 수 없게 변경
                return false; // All cells are not editable
            }
		};

		menuTable = new JTable(menuTableModel);
		
		// 스토어 리스트 테이블 칼럼 추가
		listMenu(storeId);
		
		
		// button
		orderButton = new JButton("주문하기");
		menuInsertButton = new JButton("menu 등록");
		menuEditButton = new JButton("menu 수정");
		
		// button panel
		JPanel menuButtonPanel = new JPanel();
		menuButtonPanel.add(orderButton);
		menuButtonPanel.add(menuInsertButton);
		menuButtonPanel.add(menuEditButton);

		// add inputPanel, buttonPanel to Dialog
		add(new JScrollPane(menuTable), BorderLayout.CENTER);
		add(menuButtonPanel, BorderLayout.SOUTH);
		
		// menuInsert 버튼 이벤트 처리
		menuInsertButton.addActionListener(e -> {
			MenuInsertDialog menuInsertDialog = new MenuInsertDialog(this, this.menuTableModel);
			menuInsertDialog.setVisible(true);
		});

		// menuEditButton 버튼 이벤트 처리
		menuEditButton.addActionListener(e -> {
			// table에 선택된 row가 있으면 AddBookDialog를 띄운다.
			// table에 선택된 row
			int selectedRow = menuTable.getSelectedRow();
			if (selectedRow >= 0) {
				EditMenuDialog editMenuDialog = new EditMenuDialog(this, this.menuTableModel, selectedRow);
				editMenuDialog.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Menu를 선택하세요.");
			}
		});

		// orderButton 버튼 이벤트 처리
//		orderButton.addActionListener(e -> {
//			// table에 선택된 row가 있으면 AddBookDialog를 띄운다.
//			// table에 선택된 row
//			int selectedRow = menuTable.getSelectedRow();
//			if (selectedRow >= 0) {
//				OrderInsertDialog orderInsertDialog = new OrderInsertDialog(this, selectedRow);
//				orderInsertDialog.setVisible(true);
//			} else {
//				JOptionPane.showMessageDialog(this, "Menu를 선택하세요.");
//			}
//		});

		// 스토어 목록 더블 클릭시 해당 스토어 제품 다이얼로그 띄움
		// 테이블에서 마우스 이벤트 처리 메소드 추가
		menuTable.addMouseListener(new MouseAdapter() {
			// 마우스 더블 클릭이 되면 edit 팝업을 띄운다.
			@Override
			public void mouseClicked(MouseEvent e) {
				// double click
				if (e.getClickCount() == 2) { // 클릭 2회 = 더블 클릭
					int selectedRow = menuTable.getSelectedRow();
					if (selectedRow >= 0) {
						// 선택된 스토어 메뉴 띄우는 코드
						EditMenuDialog editMenuDialog = new EditMenuDialog(CoupangMenuDialog.this, menuTableModel,
								selectedRow);
						editMenuDialog.setVisible(true);
					}
				}
			}
		});
	}

	// =========================================================================
    // 메소드 추가
    
    // 스토어 테이블 모든 칼럼 삭제
    private void clearTable() {
    	menuTableModel.setRowCount(0);
	}
    
    // 전체 Stores 리스트 조회
    private void listMenu(int storeId) {
		// 현재 tableModel을 정리하고 처리
		clearTable();
		
		List<Menu> menuList = menuDao.listMenu(storeId);
		
		for (Menu menu : menuList) {
			menuTableModel.addRow(new Object[] {menu.getMenuId(), menu.getStoreId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getCategory(), menu.getImageUrl()});
		}
	}
    
    Menu detailMenu(int menuId) {
		return menuDao.detailMenu(menuId);
	}
    
    void insertMenu(Menu menu) {
		int ret = menuDao.insertMenu(menu);
		if(ret == 1) {
			listMenu(storeId);
		}
	}
    
    void updateMenu(Menu menu) {
		int ret = menuDao.updateMenu(menu);
		
		if(ret == 1) {
			listMenu(storeId);
		}
	}
	
	void deleteMenu(int menuId) {
		int ret = menuDao.deleteMenu(menuId);
		
		if(ret == 1) {
			listMenu(storeId);
		}
	}
    
    
}
