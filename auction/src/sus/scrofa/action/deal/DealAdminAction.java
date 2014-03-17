package sus.scrofa.action.deal;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.Deal;
import sus.scrofa.service.DealService;

@Controller
@Scope("prototype")
public class DealAdminAction extends CommonAction {

	/**
	 * 转到订单列表，传入的参数：page，传出的参数：data
	 * 
	 * @return 若用户未登录，则返回ERROR，查找成功则返回SUCCESS
	 */
	public String toList() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		data = dealService.findByPage(page, DEFAULT_COUNT);
		return SUCCESS;
	}

	/**
	 * 商家发货，传入的参数：dealId
	 * 
	 * @return
	 */
	public String deliver() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		Deal deal = dealService.findOneByProperty("id", dealId);
		if (deal == null || deal.getStatus() != Deal.STATUS_PAID) {
			return PARAM_ERROR;
		}
		deal.setStatus(Deal.STATUS_DELIVERED);
		deal.setDeliverTime(new Date());
		deal = dealService.update(deal);
		if (deal == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}
	
	public String delete() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		dealService.delete(dealId);
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

	private static final long serialVersionUID = -1524829904476076691L;

}
