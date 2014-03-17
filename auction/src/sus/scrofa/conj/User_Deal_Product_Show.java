package sus.scrofa.conj;

import sus.scrofa.entity.Deal;
import sus.scrofa.entity.Product;
import sus.scrofa.entity.ShowPic;
import sus.scrofa.entity.User;

public class User_Deal_Product_Show {

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ShowPic getShowPic() {
		return showPic;
	}

	public void setShowPic(ShowPic showPic) {
		this.showPic = showPic;
	}

	private User user;
	private Deal deal;
	private Product product;
	private ShowPic showPic;
}
