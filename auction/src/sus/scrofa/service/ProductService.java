package sus.scrofa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sus.scrofa.conj.Bid_Product;
import sus.scrofa.dao.BidHistoryDao;
import sus.scrofa.dao.CommonDao;
import sus.scrofa.dao.DealDao;
import sus.scrofa.dao.ProductDao;
import sus.scrofa.dao.ShowPicDao;
import sus.scrofa.entity.Deal;
import sus.scrofa.entity.Product;

@Service
public class ProductService extends CommonService<Product> {

	@Override
	public Product add(Product obj) {
		return productDao.add(obj);
	}

	@Override
	public void delete(Object id) {
		bidHistoryDao.deleteByProperty("product_id", id);
		
		List<Deal> deals = dealDao.findByProperties(new String[] { "product_id" },
				new Object[] { id });
		if (deals != null) {
			for (Deal d : deals) {
				showPicDao.deleteByProperty("deal_id", d.getId());
			}
		}
		dealDao.deleteByProperty("product_id", id);
		
		productDao.delete(id);
	}

	@Override
	public Product update(Product obj) {
		return productDao.update(obj);
	}

	@Override
	public Product findOneByProperty(String name, Object value) {
		if (value == null) {
			return null;
		}
		return productDao.findOneByProperty(name, value);
	}

	@Override
	public Map<String, Object> findByPage(int page, int count) {
		return productDao.findByPage(page, count);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> findHotByPage(int page, int count) {
		Map<String, Object> dataHot = productDao.findHotByPage(page, count);
		if (dataHot.get(CommonDao.KEY_LIST) != null) {
			List<Product> list = (List<Product>) dataHot
					.get(CommonDao.KEY_LIST);
			List<Bid_Product> bps = new ArrayList<Bid_Product>(list.size());
			for (Product p : list) {
				Bid_Product bp = new Bid_Product();
				count = bidHistoryDao.findBidCount(p.getId());
				bp.setProduct(p);
				bp.setCount(count);
				if (count == 0) {
					bp.setMax(p.getMinPrice());
				} else {
					bp.setMax(bidHistoryDao.findMaxPrice(p.getId()).getPrice());
				}
				bps.add(bp);
			}
			dataHot.put(CommonDao.KEY_LIST, bps);
		}
		return dataHot;
	}

	public Map<String, Object> findRightByPage(int page, int count) {
		return productDao.findRightByPage(page, count);
	}

	@Resource
	private ProductDao productDao;
	@Resource
	private DealDao dealDao;
	@Resource
	private ShowPicDao showPicDao;
	@Resource
	private BidHistoryDao bidHistoryDao;
}
