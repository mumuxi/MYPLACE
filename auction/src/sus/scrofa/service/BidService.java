package sus.scrofa.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sus.scrofa.dao.BidHistoryDao;
import sus.scrofa.entity.BidHistory;

@Service
public class BidService extends CommonService<BidHistory> {

	@Override
	public BidHistory add(BidHistory obj) {
		return bidHistoryDao.add(obj);
	}

	@Override
	public void delete(Object id) {
		bidHistoryDao.delete(id);
	}
	
	public void deleteByUserProduct(int userId, int productId) {
		bidHistoryDao.deleteByUserProduct(userId, productId);
	}

	@Override
	public BidHistory update(BidHistory obj) {
		return bidHistoryDao.update(obj);
	}

	@Override
	public BidHistory findOneByProperty(String name, Object value) {
		return bidHistoryDao.findOneByProperty(name, value);
	}

	@Override
	public Map<String, Object> findByPage(int page, int count) {
		return bidHistoryDao.findByPage(page, count);
	}

	public BidHistory findMaxPrice(int productId) {
		return bidHistoryDao.findMaxPrice(productId);
	}
	
	public int findBidCount(int productId) {
		return bidHistoryDao.findBidCount(productId);
	}

	@Resource
	private BidHistoryDao bidHistoryDao;
}
