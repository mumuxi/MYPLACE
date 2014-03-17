package sus.scrofa.conj;

import sus.scrofa.entity.BidHistory;
import sus.scrofa.entity.User;

public class User_Bid {

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

	private User user;
	private BidHistory bid;
}
