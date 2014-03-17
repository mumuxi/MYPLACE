package sus.scrofa.conj;

import sus.scrofa.entity.BidHistory;
import sus.scrofa.entity.Product;
import sus.scrofa.entity.User;

public class User_Bid_Product {

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BidHistory getBid() {
		return bid;
	}

	public void setBid(BidHistory bid) {
		this.bid = bid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private User user;
	private BidHistory bid;
	private Product product;
}
