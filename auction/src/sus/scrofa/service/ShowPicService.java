package sus.scrofa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sus.scrofa.conj.Product_Show;
import sus.scrofa.conj.User_Deal_Product_Show;
import sus.scrofa.dao.CommonDao;
import sus.scrofa.dao.DealDao;
import sus.scrofa.dao.ProductDao;
import sus.scrofa.dao.ShowPicDao;
import sus.scrofa.dao.UserDao;
import sus.scrofa.entity.Deal;
import sus.scrofa.entity.Product;
import sus.scrofa.entity.ShowPic;
import sus.scrofa.entity.User;

@Service
public class ShowPicService extends CommonService<ShowPic> {

	@Override
	public ShowPic add(ShowPic obj) {
		Deal deal = dealDao.findOneByProperty("id", obj.getDealId());
		if (deal == null) {
			return null;
		}
		deal.setStatus(Deal.STATUS_BUYER_COMMENTED);
		dealDao.update(deal);
		return showPicDao.add(obj);
	}

	@Override
	public void delete(Object id) {
		showPicDao.delete(id);
	}

	@Override
	public ShowPic update(ShowPic obj) {
		return showPicDao.update(obj);
	}

	@Override
	public ShowPic findOneByProperty(String name, Object value) {
		return showPicDao.findOneByProperty(name, value);
	}

	/**
	 * 返回User_Deal_Product_Show列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findByPage(int page, int count) {
		Map<String, Object> data = showPicDao.findByPage(page, count);
		if (data.get(CommonDao.KEY_LIST) != null) {
			List<ShowPic> list = (List<ShowPic>) data.get(CommonDao.KEY_LIST);
			List<User_Deal_Product_Show> udpss = new ArrayList<User_Deal_Product_Show>(
					list.size());
			for (ShowPic s : list) {
				User_Deal_Product_Show udps = new User_Deal_Product_Show();

				Deal d = dealDao.findOneByProperty("id", s.getDealId());
				User u = userDao.findOneByProperty("id", d.getUserId());
				Product p = productDao
						.findOneByProperty("id", d.getProductId());

				udps.setUser(u);
				udps.setDeal(d);
				udps.setProduct(p);
				udps.setShowPic(s);
				udpss.add(udps);
			}
			data.put(CommonDao.KEY_LIST, udpss);
		}
		return data;
	}

	public User_Deal_Product_Show findOneByDealId(long dealId) {
		Deal deal = dealDao.findOneByProperty("id", dealId);
		if (deal == null) {
			return null;
		}
		Product product = productDao.findOneByProperty("id",
				deal.getProductId());
		User user = userDao.findOneByProperty("id", deal.getUserId());
		if (product == null || user == null) {
			return null;
		}
		User_Deal_Product_Show udp = new User_Deal_Product_Show();
		udp.setDeal(deal);
		udp.setProduct(product);
		udp.setUser(user);
		return udp;
	}

	public Product_Show findOneById(int id) {
		ShowPic showPic = showPicDao.findOneByProperty("id", id);
		if (showPic == null) {
			return null;
		}
		Deal deal = dealDao.findOneByProperty("id", showPic.getDealId());
		if (deal == null) {
			return null;
		}
		Product product = productDao.findOneByProperty("id",
				deal.getProductId());
		if (product == null) {
			return null;
		}
		Product_Show ps = new Product_Show();
		ps.setProduct(product);
		ps.setShowPic(showPic);
		return ps;
	}

	@Resource
	private ShowPicDao showPicDao;
	@Resource
	private DealDao dealDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private UserDao userDao;
}
