package sus.scrofa.action.product;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.BidHistory;
import sus.scrofa.entity.Product;
import sus.scrofa.service.BidService;
import sus.scrofa.service.ProductService;

@Controller
@Scope("prototype")
public class BidAction extends CommonAction {

	/**
	 * 用户出价时调用，需要传进的参数是价格和商品id，存储在BidHistory对象中
	 * 
	 * @return 
	 *         SUCCESS表示出价成功，ERROR表示未登录，INPUT表示传进的参数格式有误，PRICE_ERROR表示出价小于该商品的当前出价
	 *         ，TIME_LTD表示拍卖结束
	 */
	public String bid() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}

		Product p = productService.findOneByProperty("id", bid.getProductId());

		Date now = new Date();
		Date finish = p.getFinishTime();
		Date start = p.getStartTime();
		if (DATE_TIME_FORMAT_1.format(now).compareTo(
				DATE_TIME_FORMAT_1.format(finish)) > 0) {
			// 判断出价是否超时
			return TIME_LTD;
		}
		if (DATE_TIME_FORMAT_1.format(now).compareTo(
				DATE_TIME_FORMAT_1.format(start)) < 0) {
			// 判断拍卖是否开始，前台控制好的话，该情况不会发生，但后台还是应该控制一下
			return TIME_PRE;
		}

		BidHistory max = bidService.findMaxPrice(bid.getProductId());
		if (max == null) {
			// 表示未出价，用户的出价至少是商品的最低出价
			if (bid.getPrice() < p.getMinPrice()) {
				return PRICE_ERROR;
			}
		} else {
			// 表示有出价记录，用户的出价增幅必须在1到300元之间，这个数字以后再改
			if (bid.getPrice() - max.getPrice() < MIN_INCRESE
					|| bid.getPrice() - max.getPrice() > MAX_INCRESE) {
				return PRICE_ERROR;
			}
		}
		// 出价正确，可以出价
		bid.setBidTime(now);
		bid.setUserId((Integer) session.get(SESSION_USER_ID));
		bid = bidService.add(bid);
		return SUCCESS;
	}

	public BidHistory getBid() {
		return bid;
	}

	public void setBid(BidHistory bid) {
		this.bid = bid;
	}

	public static final double MIN_INCRESE = 1;
	public static final double MAX_INCRESE = 300;

	private BidHistory bid;
	@Resource
	private BidService bidService;
	@Resource
	private ProductService productService;
	private static final long serialVersionUID = 2993569601245292322L;
}
