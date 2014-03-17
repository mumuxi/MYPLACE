package sus.scrofa.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sus.scrofa.action.user.UserAction;
import sus.scrofa.dao.BidHistoryDao;
import sus.scrofa.dao.DealDao;
import sus.scrofa.dao.NoticeDao;
import sus.scrofa.dao.ProductDao;
import sus.scrofa.dao.ShowPicDao;
import sus.scrofa.dao.UserDao;
import sus.scrofa.entity.Deal;
import sus.scrofa.entity.User;
import sus.scrofa.util.Validator;

@Service
public class UserService extends CommonService<User> {

	@Override
	public User add(User obj) {
		if (obj == null) {
			return null;
		}
		if (Validator.isNull(obj.getLogo())) {
			obj.setLogo(UserAction.DEFAULT_USER_LOGO);
		}
		return userDao.add(obj);
	}

	@Override
	public void delete(Object id) {
		List<Deal> deals = dealDao.findByProperties(new String[] { "user_id" },
				new Object[] { id });
		if (deals != null) {
			for (Deal d : deals) {
				showPicDao.deleteByProperty("deal_id", d.getId());
			}
		}
		dealDao.deleteByProperty("user_id", id);

		bidHistoryDao.deleteByProperty("user_id", id);

		productDao.deleteByProperty("owner", id);
		productDao.deleteByProperty("buyer", id);

		noticeDao.deleteByProperty("publisher", id);
		userDao.delete(id);
	}

	@Override
	public User update(User obj) {
		if (obj == null) {
			return null;
		}
		return userDao.update(obj);
	}

	@Override
	public User findOneByProperty(String name, Object value) {
		if (Validator.isNull(name) || value == null) {
			return null;
		}
		return userDao.findOneByProperty(name, value);
	}

	@Override
	public Map<String, Object> findByPage(int page, int count) {
		return userDao.findByPage(page, count);
	}

	@Resource
	private UserDao userDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private DealDao dealDao;
	@Resource
	private ShowPicDao showPicDao;
	@Resource
	private BidHistoryDao bidHistoryDao;
	@Resource
	private NoticeDao noticeDao;
}
