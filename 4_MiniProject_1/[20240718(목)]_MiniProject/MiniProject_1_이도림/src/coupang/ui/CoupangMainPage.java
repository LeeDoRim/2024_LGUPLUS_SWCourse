package coupang.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import coupang.common.DBManager;
import coupang.dao.StoresDao;
import coupang.dao.UserDao;
import coupang.dto.Stores;
import coupang.dto.User;

public class CoupangMainPage extends JFrame {
	// Coupang Home 패널
	private JPanel storesPanel;
	private JPanel customerPanel;
    private JPanel mainPanel;
    
    // Coupang Home Store list 보여줄 패널
    private JTable storesTable;
    private DefaultTableModel storesTableModel;
    
    // Button 생성
    private JButton storeInsertButton, storeEditButton, singnUpButton, loginButton;
    
    // Customer 로그인 필드 
    private JTextField idField, passwordField;
    
    // DAO 객체 생성
    private StoresDao storesDao = new StoresDao();
    private UserDao userDao = new UserDao();

    public CoupangMainPage() {
    	setTitle("Coupang Home");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 메뉴바 생성
        JMenuBar menuBar = new JMenuBar();
        
        JMenu storesMenu = new JMenu("Stores");
        JMenu customerMenu = new JMenu("Customer");
        
        // 메뉴바에 메뉴 등록
        menuBar.add(storesMenu);
        menuBar.add(customerMenu);
        setJMenuBar(menuBar);

        // 각 메뉴의 패널 생성
        // 가게 패널
        storesPanel = new JPanel();
        
        // 가게 테이블 목록 삽입
        storesTableModel = new DefaultTableModel(new Object[] {"store ID", "category ID", "이름", "위치", "연락처", "운영 시간", "최소 주문 금액"}, 0) {
			@Override
            public boolean isCellEditable(int row, int column) { // 목록에서 수정할 수 없게 변경
                return false; // All cells are not editable
            }
		};

		storesTable = new JTable(storesTableModel);
		
		// 스토어 리스트 테이블 칼럼 추가
		listStores();
		
		// Button
		storeInsertButton = new JButton("store 등록");
		storeEditButton = new JButton("store 수정");
		
		// Button 정렬
		JPanel storesButtonPanel = new JPanel();
		storesButtonPanel.add(storeInsertButton);
		storesButtonPanel.add(storeEditButton);
		
		storesPanel.setLayout(new BorderLayout());
		// storesPanel.add(new JLabel("stores Menu"), BorderLayout.NORTH);
		storesPanel.add(new JScrollPane(storesTable), BorderLayout.CENTER);
		storesPanel.add(storesButtonPanel, BorderLayout.SOUTH);

		// storeInsert 버튼 이벤트 처리
		storeInsertButton.addActionListener(e -> { 
			// AddBookDialog를 띄운다.
		StoreInsertDialog storeInsertDialog = new StoreInsertDialog(this, this.storesTableModel);
		storeInsertDialog.setVisible(true);
		});
		
		// storeEdit 버튼 이벤트 처리
		storeEditButton.addActionListener(e -> { 
			// table에 선택된 row가 있으면 AddBookDialog를 띄운다.
			// table에 선택된 row
			int selectedRow = storesTable.getSelectedRow();
			if( selectedRow >= 0) {
				EditStoreDialog editStoreDialog = new EditStoreDialog(this, this.storesTableModel, selectedRow);
				editStoreDialog.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Store를 선택하세요.");
			}
		}); 
		
		// 스토어 목록 더블 클릭시 해당 스토어 제품 다이얼로그 띄움
		// 테이블에서 마우스 이벤트 처리 메소드 추가
		storesTable.addMouseListener(new MouseAdapter() {
			// 마우스 더블 클릭이 되면 edit 팝업을 띄운다.
			@Override
			public void mouseClicked(MouseEvent e) {
				// double click
				if (e.getClickCount() == 2) { // 클릭 2회 = 더블 클릭
					int selectedRow = storesTable.getSelectedRow();
					if (selectedRow >= 0) {
						// 선택된 스토어 메뉴 띄우는 코드
						// EditBookDialog editDialog = new EditBookDialog(BookManager.this, tableModel, selectedRow);
						CoupangMenuDialog menuDialog = new CoupangMenuDialog(CoupangMainPage.this, storesTableModel, selectedRow);
						menuDialog.setVisible(true);
					}
				}
			}
		});
		
		
		// =============================================================

        // 회원 패널
        customerPanel = new JPanel();
        customerPanel.add(new JLabel("Customer Panel"));
        
        // 로그인 입력 패널
        JPanel loginInputPanel = new JPanel();
        loginInputPanel.setLayout(new GridLayout(2,2));
        
        // fileld
        idField = new JTextField();
        passwordField = new JTextField();
        
        loginInputPanel.add(new JLabel("아이디 : "));
        loginInputPanel.add(idField);
        loginInputPanel.add(new JLabel("이메일 : "));
        loginInputPanel.add(passwordField);
        
        // 로그인 패널
        JPanel loginPanel = new JPanel();
        
        // button
        loginButton = new JButton("로그인");
        singnUpButton = new JButton("회원가입");
        
        loginPanel.add(loginInputPanel);
        loginPanel.add(loginButton);
        
        customerPanel.setLayout(new BorderLayout());
        customerPanel.add(loginPanel, BorderLayout.CENTER);
        customerPanel.add(singnUpButton, BorderLayout.SOUTH);
        
		singnUpButton.addActionListener(e -> {
			UserInsertDialog userInsertDialog = new UserInsertDialog(this);
			userInsertDialog.setVisible(true);
		});
		
		loginButton.addActionListener(e -> {
			Integer userId = Integer.parseInt(idField.getText());
			User user = detailUser(userId);
			
			// 로그인 실패 (회원이 없는 경우)
			if(user == null ) {
				JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다.", "아이디 확인",JOptionPane.ERROR_MESSAGE );
			}
			
			EditUserDialog editUserDialog = new EditUserDialog(this, idField);
			editUserDialog.setVisible(true);
		});
        
        // ================================================================

        // 메인 패널(메뉴바?)에 메뉴 패널 삽입
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(storesPanel, "Stores");
        mainPanel.add(customerPanel, "Customer");

        // Add Main Panel to Frame
        add(mainPanel);

        // lambda X <- functional interface X
        storesMenu.addMouseListener(new MouseAdapter() {
        	// 스토어 메뉴 선택 시 showPanel을 보여줌
            @Override
            public void mouseClicked(MouseEvent e) {
            	// showPanel : 메인 패널에 등록한 이름 ( mainPanel.add(storesPanel, "Stores");에서 두번째 파라미터 )를 보여주는 함수
            	showPanel("Stores");

            }
        });
        
        // lambda X <- functional interface X
        customerMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	showPanel("Customer");
            }
        });

        // Show default panel
        showPanel("Stores");
        
    } // 생성자 end

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }
    
    // =========================================================================
    // 메소드 추가
    
    // 스토어 테이블 모든 칼럼 삭제
    private void clearTable() {
    	storesTableModel.setRowCount(0);
	}
    
    // 전체 Stores 리스트 조회
    private void listStores() {
		// 현재 tableModel을 정리하고 처리
		clearTable();
		
		List<Stores> storesList = storesDao.listStores();
		
		for (Stores store : storesList) {
			storesTableModel.addRow(new Object[] {store.getStoreId(), store.getCategoryId(), store.getStoreName(), store.getLocation(), store.getTell(), store.getOperatingHours(), store.getMinPrice()});
		}
	}
    
    Stores detailStore(int storeId) {
		return storesDao.detailStore(storeId);
	}
    
    void insertStores(Stores stores) {
		int ret = storesDao.insertStores(stores);
		if(ret == 1) {
			listStores();
		}
	}
    
    void updateStore(Stores stores) {
		int ret = storesDao.updateStore(stores);
		
		if(ret == 1) {
			listStores();
		}
	}
	
	void deleteStore(int storeId) {
		int ret = storesDao.deleteStore(storeId);
		
		if(ret == 1) {
			listStores();
		}
	}
	
	// User 추가
	void insertUser(User user) {
		userDao.insertUser(user);
	}
	
	void updateUser(User user) {
		int ret = userDao.updateUser(user);
		
	}
	
	void deleteUser(int userId) {
		int ret = userDao.deleteUser(userId);
	}

	// 회원 1명 정보 확인
	User detailUser(int userId) {
		return userDao.detailUser(userId);
	}

	// ==================================================================================
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CoupangMainPage().setVisible(true);
            }
        });
    }
}
