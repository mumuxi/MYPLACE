package sus.scrofa.conj;

import sus.scrofa.entity.Product;
import sus.scrofa.entity.User;

public class User_Product {

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private User user;
	private Product product;
}
