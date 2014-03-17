package sus.scrofa.action.deal;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.BidHistory;
import sus.scrofa.entity.Deal;
import sus.scrofa.entity.Product;
import sus.scrofa.entity.User;
import sus.scrofa.service.BidService;
import sus.scrofa.service.DealService;
import sus.scrofa.service.ProductService;
import sus.scrofa.service.UserService;

@Controller
@Scope("prototype")
public class DealAction extends CommonAction {

	/**
	 * 转到订单列表，传入的参数：page，userId；传出的参数：data
	 * 
	 * @return 若用户未登录，则返回ERROR，查找成功则返回SUCCESS
	 */
	public String toList() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		int userId = (Integer) session.get(SESSION_USER_ID);
		data = dealService.findByPageWithUser(page, DEFAULT_COUNT, userId);
		return SUCCESS;
	}

	/**
	 * 转到已拍下商品，但未生成订单的列表，待确认，传入的参数：page；传出的参数：data
	 * 
	 * @return 若用户未登录，则返回ERROR，查找成功则返回SUCCESS
	 */
	public String toConfirm() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		int userId = (Integer) session.get(SESSION_USER_ID);
		data = dealService.findConfirmByPage(page, DEFAULT_COUNT, userId);
		return SUCCESS;
	}

	/**
	 * 确定生成订单，传入的参数：出价记录bidId
	 * 
	 * @return
	 */
	public String confirm() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		BidHistory bid = bidService.findOneByProperty("id", bidId);
		if (bid == null) {
			return PARAM_ERROR;
		}
		Product product = productService.findOneByProperty("id",
				bid.getProductId());
		User user = userService.findOneByProperty("id", bid.getUserId());
		Date now = new Date();
		Deal deal = new Deal();
		deal.setId(now.getTime());
		deal.setUserId(user.getId());
		deal.setProductId(product.getId());
		deal.setPrice(bid.getPrice());
		deal.setStatus(Deal.STATUS_CREATED);
		deal.setCreateTime(now);
		deal = dealService.add(deal);
		if (deal == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 买家付款，传入的参数：dealId
	 * 
	 * @return
	 */
	public String pay() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		Deal deal = dealService.findOneByProperty("id", dealId);
		if (deal == null || deal.getStatus() != Deal.STATUS_CREATED) {
			return PARAM_ERROR;
		}
		deal.setStatus(Deal.STATUS_PAID);
		deal.setPayTime(new Date());
		deal = dealService.update(deal);
		if (deal == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 买家确认收货，传入的参数：dealId
	 * @return
	 */
	public String receive() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		Deal deal = dealService.findOneByProperty("id", dealId);
		if (deal == null || deal.getStatus() != Deal.STATUS_DELIVERED) {
			return PARAM_ERROR;
		}
		deal.setStatus(Deal.STATUS_RECEIVED);
		deal.setReceiveTime(new Date());
		deal = dealService.update(deal);
		if (deal == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public long getDealId() {
		return dealId;
	}

	public void setDealId(long dealId) {
		this.dealId = dealId;
	}

	private int bidId;
	private long dealId;

	@Resource
	private DealService dealService;
	@Resource
	private BidService bidService;
	@Resource
	private ProductService productService;
	@Resource
	private UserService userService;
	private static final long serialVersionUID = -1524829904476076691L;

}
