package coupang.dto;

public class Menu {
	private int menuId;
	private int storeId;
	private String name;
	private String description;
	private int price;
	private String category;
	private String imageUrl;
	
	public Menu() {}
	public Menu(int menuId, int storeId, String name, String description, int price, String category, String imageUrl) {
		super();
		this.menuId = menuId;
		this.storeId = storeId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.imageUrl = imageUrl;
	}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", storeId=" + storeId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", category=" + category + ", imageUrl=" + imageUrl + "]";
	}
	

}
