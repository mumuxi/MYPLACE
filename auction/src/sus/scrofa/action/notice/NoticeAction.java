package sus.scrofa.action.notice;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.conj.User_Notice;
import sus.scrofa.service.NoticeService;

@Controller @Scope("prototype")
public class NoticeAction extends CommonAction {

	public String toDetail() {
		user_notice = noticeService.findByNoticeId(id);
		if (user_notice == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public String toList() {
		data = noticeService.findByPage(page, DEFAULT_COUNT);
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User_Notice getUser_notice() {
		return user_notice;
	}

	public void setUser_notice(User_Notice user_notice) {
		this.user_notice = user_notice;
	}

	private int id;
	private User_Notice user_notice;
	@Resource
	private NoticeService noticeService;

	private static final long serialVersionUID = -3917325090441573570L;
}
