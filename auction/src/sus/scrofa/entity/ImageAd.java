package sus.scrofa.entity;

import java.util.Date;

public class ImageAd {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public int getPublisher() {
		return publisher;
	}

	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public static final int SHOW_ORDER_HIDE = 0;

	public static final int LOCATION_LOOP = 1;
	public static final int LOCATION_LEFT_FLOAT = 2;
	public static final int LOCATION_RIGHT_FLOAT = 3;

	private int id;
	private String image;
	private String title;
	private String alt;
	private int showOrder;
	private int publisher;
	private String link;
	private int location;
	private Date publishTime;
}
