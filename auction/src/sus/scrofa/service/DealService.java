package sus.scrofa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sus.scrofa.dao.BidHistoryDao;
import sus.scrofa.dao.CommonDao;
import sus.scrofa.dao.DealDao;
import sus.scrofa.dao.ProductDao;
import sus.scrofa.dao.ShowPicDao;
import sus.scrofa.dao.UserDao;
import sus.scrofa.entity.BidHistory;
import sus.scrofa.entity.Deal;
import sus.scrofa.entity.Product;
import sus.scrofa.entity.ShowPic;
import sus.scrofa.entity.User;
import sus.scrofa.conj.User_Bid_Product;
import sus.scrofa.conj.User_Deal_Product_Show;

@Service
public class DealService extends CommonService<Deal> {

	@Override
	public Deal add(Deal obj) {
		if (obj == null) {
			return null;
		}
		bidHistoryDao.deleteByUserProduct(obj.getUserId(), obj.getProductId());
		return dealDao.add(obj);
	}

	@Override
	public void delete(Object id) {
		showPicDao.deleteByProperty("deal_id", id);
		dealDao.delete(id);
	}

	@Override
	public Deal update(Deal obj) {
		return dealDao.update(obj);
	}

	@Override
	public Deal findOneByProperty(String name, Object value) {
		return dealDao.findOneByProperty(name, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findByPage(int page, int count) {
		Map<String, Object> data = dealDao.findByPage(page, count);
		List<Deal> deals = (List<Deal>) data.get(CommonDao.KEY_LIST);
		if (deals != null) {
			List<User_Deal_Product_Show> listDeal = new ArrayList<User_Deal_Product_Show>(
					deals.size());
			for (Deal d : deals) {
				User u = userDao.findOneByProperty("id", d.getUserId());
				Product p = productDao
						.findOneByProperty("id", d.getProductId());
				User_Deal_Product_Show udp = new User_Deal_Product_Show();
				udp.setUser(u);
				udp.setDeal(d);
				udp.setProduct(p);
				listDeal.add(udp);
			}
			data.put(CommonDao.KEY_LIST, listDeal);
		}
		return data;
	}

	/**
	 * 查找某用户的所有订单，KEY_LIST中存的是User_Product_Deal_Show列表
	 * 
	 * @param page
	 * @param count
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findByPageWithUser(int page, int count,
			int userId) {
		Map<String, Object> data = dealDao.findByPageWithUser(page, count,
				userId);
		List<Deal> deals = (List<Deal>) data.get(CommonDao.KEY_LIST);
		if (deals != null) {
			List<User_Deal_Product_Show> listDeal = new ArrayList<User_Deal_Product_Show>(
					deals.size());
			for (Deal d : deals) {
				User u = userDao.findOneByProperty("id", d.getUserId());
				Product p = productDao
						.findOneByProperty("id", d.getProductId());
				ShowPic s = showPicDao.findOneByProperty("deal_id", d.getId());

				User_Deal_Product_Show udps = new User_Deal_Product_Show();
				udps.setUser(u);
				udps.setDeal(d);
				udps.setProduct(p);
				udps.setShowPic(s);

				listDeal.add(udps);
			}
			data.put(CommonDao.KEY_LIST, listDeal);
		}
		return data;
	}

	/**
	 * 查找用户已拍下的产品，待确认成为订单
	 * 
	 * @param page
	 * @param count
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findConfirmByPage(int page, int count, int userId) {
		Map<String, Object> data = bidHistoryDao.findConfirmByPage(page, count,
				userId);
		List<BidHistory> bids = (List<BidHistory>) data.get(CommonDao.KEY_LIST);
		if (bids != null) {
			List<User_Bid_Product> listBid = new ArrayList<User_Bid_Product>(
					bids.size());
			for (BidHistory b : bids) {
				User u = userDao.findOneByProperty("id", b.getUserId());
				Product p = productDao
						.findOneByProperty("id", b.getProductId());
				User_Bid_Product ubp = new User_Bid_Product();
				ubp.setUser(u);
				ubp.setBid(b);
				ubp.setProduct(p);
				listBid.add(ubp);
			}
			data.put(CommonDao.KEY_LIST, listBid);
		}
		return data;
	}

	@Resource
	private DealDao dealDao;
	@Resource
	private UserDao userDao;
	@Resource
	private ProductDao productDao;
	@Resource
	private BidHistoryDao bidHistoryDao;
	@Resource
	private ShowPicDao showPicDao;
}
