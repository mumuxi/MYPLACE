package sus.scrofa.conj;

import sus.scrofa.entity.Notice;
import sus.scrofa.entity.User;

public class User_Notice {

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	private User user;
	private Notice notice;
}
