package sus.scrofa.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sus.scrofa.conj.User_Notice;
import sus.scrofa.dao.NoticeDao;
import sus.scrofa.dao.UserDao;
import sus.scrofa.entity.Notice;
import sus.scrofa.entity.User;

@Service
public class NoticeService extends CommonService<Notice> {

	@Override
	public Notice add(Notice obj) {
		return noticeDao.add(obj);
	}

	@Override
	public void delete(Object id) {
		noticeDao.delete(id);
	}

	@Override
	public Notice update(Notice obj) {
		return noticeDao.update(obj);
	}

	@Override
	public Notice findOneByProperty(String name, Object value) {
		return noticeDao.findOneByProperty(name, value);
	}

	@Override
	public Map<String, Object> findByPage(int page, int count) {
		return noticeDao.findByPage(page, count);
	}

	public User_Notice findByNoticeId(int id) {
		Notice notice = noticeDao.findOneByProperty("id", id);
		if (notice == null) {
			return null;
		}
		User user = userDao.findOneByProperty("id", notice.getPublisher());
		if (user == null) {
			return null;
		}
		User_Notice un = new User_Notice();
		un.setNotice(notice);
		un.setUser(user);
		return un;
	}

	@Resource
	private NoticeDao noticeDao;
	@Resource
	private UserDao userDao;

}
