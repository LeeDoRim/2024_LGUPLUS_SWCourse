package coupang.dto;

public class Stores {
	private int storeId;
	private int categoryId;
	private String storeName;
	private String location;
	private String tell;
	private String operatingHours;
	private int minPrice;
	
	public Stores() {}
	public Stores(int storeId, int categoryId, String storeName, String location, String tell, String operatingHours,
			int minPrice) {
		super();
		this.storeId = storeId;
		this.categoryId = categoryId;
		this.storeName = storeName;
		this.location = location;
		this.tell = tell;
		this.operatingHours = operatingHours;
		this.minPrice = minPrice;
	}
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getOperatingHours() {
		return operatingHours;
	}
	public void setOperatingHours(String operatingHours) {
		this.operatingHours = operatingHours;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	
	@Override
	public String toString() {
		return "Stores [storeId=" + storeId + ", categoryId=" + categoryId + ", storeName=" + storeName + ", location="
				+ location + ", tell=" + tell + ", operatingHours=" + operatingHours + ", minPrice=" + minPrice + "]";
	}
	

}
