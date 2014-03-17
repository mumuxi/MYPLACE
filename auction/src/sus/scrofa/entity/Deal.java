package sus.scrofa.entity;

import java.util.Date;

public class Deal {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public static final int STATUS_CREATED = 1;
	public static final int STATUS_PAID = 2;
	public static final int STATUS_DELIVERED = 3;
	public static final int STATUS_RECEIVED = 4;
	public static final int STATUS_BUYER_COMMENTED = 5;
	public static final int STATUS_OWNER_COMMENTED = 6;
	public static final int STATUS_BOTH_COMMENTED = 7;

	private long id;
	private int userId;
	private int productId;
	private double price;
	private int status;
	private Date createTime;
	private Date payTime;
	private Date deliverTime;
	private Date receiveTime;
}
