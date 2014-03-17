package sus.scrofa.conj;

import sus.scrofa.entity.Product;

public class Bid_Product {

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 最高出价，若无出价，则为商品的最低价
	 */
	private double max;
	/**
	 * 拍卖的商品
	 */
	private Product product;
	/**
	 * 出价次数
	 */
	private int count;
}
