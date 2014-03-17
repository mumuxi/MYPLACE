package sus.scrofa.action.show;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.conj.Product_Show;
import sus.scrofa.conj.User_Deal_Product_Show;
import sus.scrofa.entity.ShowPic;
import sus.scrofa.service.ShowPicService;

@Controller
@Scope("prototype")
public class ShowAction extends CommonAction {

	/**
	 * 对某个订单添加晒图，传入的参数：dealId
	 * 
	 * @return 添加成功则返回SUCCESS，参数错误返回PARAM_ERROR，用户未登录返回ERROR
	 */
	public String toAdd() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		udp = showPicService.findOneByDealId(dealId);

		if (udp == null) {
			return PARAM_ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 处理添加晒图，传入的参数：showPic，images
	 * 
	 * @return 添加成功则返回SUCCESS，参数错误返回PARAM_ERROR，用户未登录返回ERROR
	 */
	public String add() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		String url = this.saveTo("/upload/image/show");
		showPic.setImages(url);
		showPic.setPublishTime(new Date());
		showPic.setStatus(ShowPic.STATUS_SHOW);
		showPic = showPicService.add(showPic);
		if (showPic == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 查看晒图详细信息，传入的参数：id
	 * 
	 * @return
	 */
	public String toDetail() {
		ps = showPicService.findOneById(id);
		if (ps == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public ShowPic getShowPic() {
		return showPic;
	}

	public void setShowPic(ShowPic showPic) {
		this.showPic = showPic;
	}

	public User_Deal_Product_Show getUdp() {
		return udp;
	}

	public void setUdp(User_Deal_Product_Show udp) {
		this.udp = udp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDealId() {
		return dealId;
	}

	public void setDealId(long dealId) {
		this.dealId = dealId;
	}

	public Product_Show getPs() {
		return ps;
	}

	public void setPs(Product_Show ps) {
		this.ps = ps;
	}

	private ShowPic showPic;
	private int id;
	private User_Deal_Product_Show udp;
	private Product_Show ps;
	private long dealId;

	@Resource
	private ShowPicService showPicService;

	private static final long serialVersionUID = 1943295781261506174L;

}
