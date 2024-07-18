package coupang.dto;

public class Order {
	private int orderId;
	private int storeId;
	private int userId2;
	private String orderDate;
	private String orderStatus;
	private int totalPrice;
	private int autoIncrement;
	private int userId;
	
	public Order() {}
	public Order(int orderId, int storeId, int userId2, String orderDate, String orderStatus, int totalPrice,
			int autoIncrement, int userId) {
		super();
		this.orderId = orderId;
		this.storeId = storeId;
		this.userId2 = userId2;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.autoIncrement = autoIncrement;
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getUserId2() {
		return userId2;
	}

	public void setUserId2(int userId2) {
		this.userId2 = userId2;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(int autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", storeId=" + storeId + ", userId2=" + userId2 + ", orderDate="
				+ orderDate + ", orderStatus=" + orderStatus + ", totalPrice=" + totalPrice + ", autoIncrement="
				+ autoIncrement + ", userId=" + userId + "]";
	}
	
	
}
