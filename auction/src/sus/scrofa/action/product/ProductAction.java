package sus.scrofa.action.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.BidHistory;
import sus.scrofa.entity.Product;
import sus.scrofa.entity.User;
import sus.scrofa.service.BidService;
import sus.scrofa.service.ProductService;
import sus.scrofa.service.UserService;

@Controller
@Scope("prototype")
public class ProductAction extends CommonAction {
	/**
	 * 到商品详细信息页面，传入的参数是：商品id；传出的参数是：商品对象product，最高出价bid，最高出价人candit，
	 * 出价历史列表bidList，出价人列表canditList
	 * 
	 * @return
	 */
	public String toDetail() {
		product = productService.findOneByProperty("id", id);
		bid = bidService.findMaxPrice(id);
		if (bid != null) {
			candit = userService.findOneByProperty("id", bid.getUserId());
		}
		return SUCCESS;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BidHistory getBid() {
		return bid;
	}

	public void setBid(BidHistory bid) {
		this.bid = bid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getCandit() {
		return candit;
	}

	public void setCandit(User candit) {
		this.candit = candit;
	}

	public List<BidHistory> getBidList() {
		return bidList;
	}

	public void setBidList(List<BidHistory> bidList) {
		this.bidList = bidList;
	}

	public List<User> getCanditList() {
		return canditList;
	}

	public void setCanditList(List<User> canditList) {
		this.canditList = canditList;
	}

	public BidService getBidService() {
		return bidService;
	}

	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}

	private Product product;
	private int id;
	
	private BidHistory bid;
	private User candit;
	private List<BidHistory> bidList;
	private List<User> canditList;

	@Resource
	private ProductService productService;
	@Resource
	private BidService bidService;
	@Resource
	private UserService userService;

	private static final long serialVersionUID = -5534168302884569222L;

}
