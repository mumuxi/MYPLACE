package sus.scrofa.entity;

import java.util.Date;

public class ShowPic {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDealId() {
		return dealId;
	}

	public void setDealId(long dealId) {
		this.dealId = dealId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public static final int STATUS_HIDE = 0;
	public static final int STATUS_SHOW = 1;

	private int id;
	private long dealId;
	private String content;
	private String images;
	private Date publishTime;
	private int status;
}
