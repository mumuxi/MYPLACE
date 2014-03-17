package sus.scrofa.action.dispatch;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.service.NoticeService;
import sus.scrofa.service.ProductService;
import sus.scrofa.service.ShowPicService;

@Controller
@Scope("prototype")
public class DispatchAction extends CommonAction {
	/**
	 * 转到前台，需要传出的参数有，新闻公告，图片新闻，正在热拍，即将拍卖，玩家晒图
	 * 
	 * @return
	 */
	public String toIndex() {
		// 正在热拍
		dataHot = productService.findHotByPage(1, 8);
		// 即将拍卖
		dataRight = productService.findRightByPage(1, 8);
		// 玩家晒图
		dataShow = showPicService.findByPage(1, 8);
		// 新闻公告
		dataNotice = noticeService.findByPage(1, 10);
		return SUCCESS;
	}

	/**
	 * 需要传入的参数有：page
	 */
	public String toHot() {
		dataHot = productService.findHotByPage(page, DEFAULT_COUNT);

		return SUCCESS;
	}

	/**
	 * 转到即将拍卖列表，传出的参数有：
	 * 
	 * @return
	 */
	public String toRight() {
		return SUCCESS;
	}

	/**
	 * 仅转发，永远返回SUCCESS
	 */
	@Override
	public String execute() {
		return SUCCESS;
	}

	public Map<String, Object> getDataHot() {
		return dataHot;
	}

	public void setDataHot(Map<String, Object> dataHot) {
		this.dataHot = dataHot;
	}

	public Map<String, Object> getDataRight() {
		return dataRight;
	}

	public void setDataRight(Map<String, Object> dataRight) {
		this.dataRight = dataRight;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public Map<String, Object> getDataNotice() {
		return dataNotice;
	}

	public void setDataNotice(Map<String, Object> dataNotice) {
		this.dataNotice = dataNotice;
	}

	public Map<String, Object> getDataShow() {
		return dataShow;
	}

	public void setDataShow(Map<String, Object> dataShow) {
		this.dataShow = dataShow;
	}

	/**
	 * 正在热拍
	 */
	private Map<String, Object> dataHot;
	/**
	 * 即将拍卖
	 */
	private Map<String, Object> dataRight;
	/**
	 * 玩家晒图
	 */
	private Map<String, Object> dataShow;

	/**
	 * 新闻公告
	 */
	private Map<String, Object> dataNotice;

	private int lastPage;

	@Resource
	private ProductService productService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private ShowPicService showPicService;
	private static final long serialVersionUID = -3962751334598724789L;
}
