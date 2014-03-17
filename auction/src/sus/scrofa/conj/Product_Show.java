package sus.scrofa.conj;

import sus.scrofa.entity.Product;
import sus.scrofa.entity.ShowPic;

public class Product_Show {

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

	private Product product;
	private ShowPic showPic;
}
