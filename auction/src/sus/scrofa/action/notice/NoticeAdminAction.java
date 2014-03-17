package sus.scrofa.action.notice;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.conj.User_Notice;
import sus.scrofa.entity.Notice;
import sus.scrofa.service.NoticeService;

@Controller
@Scope("prototype")
public class NoticeAdminAction extends CommonAction {

	public String toList() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		page = page <= 0 ? 1 : page;
		count = count <= 0 ? DEFAULT_COUNT : count;
		data = noticeService.findByPage(page, count);
		return SUCCESS;
	}

	public String toDetail() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		user_notice = noticeService.findByNoticeId(id);
		if (user_notice == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public String add() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}

		// 设置发布时间和更新时间
		Date now = new Date();
		notice.setPublishTime(now);
		notice.setUpdateTime(now);

		// 设置发布者
		int userId = (Integer) session.get(SESSION_USER_ID);
		notice.setPublisher(userId);

		// 设置默认显示状态
		notice.setStatus(Notice.STATUS_SHOW);

		// 设置默认置顶级别
		notice.setTopLevel(Notice.TOP_LEVEL_NORMAL);

		// 保存
		notice = noticeService.add(notice);
		if (notice == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public String delete() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}

		noticeService.delete(id);
		return SUCCESS;
	}

	public String update() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		Notice tmp = noticeService.findOneByProperty("id", id);
		if (tmp == null) {
			return PARAM_ERROR;
		}

		// 设置notice.id
		notice.setId(tmp.getId());
		// 设置发布者
		notice.setPublisher(tmp.getPublisher());
		// 设置发布时间
		notice.setPublishTime(tmp.getPublishTime());
		// 设置更新时间
		Date now = new Date();
		notice.setUpdateTime(now);

		notice = noticeService.update(notice);
		if (notice == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public User_Notice getUser_notice() {
		return user_notice;
	}

	public void setUser_notice(User_Notice user_notice) {
		this.user_notice = user_notice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Notice notice;
	private User_Notice user_notice;
	private int id;

	@Resource
	private NoticeService noticeService;
	private static final long serialVersionUID = -8921496807754465892L;
}
