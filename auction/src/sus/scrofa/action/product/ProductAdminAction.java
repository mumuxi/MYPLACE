package sus.scrofa.action.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.Product;
import sus.scrofa.service.ProductService;
import sus.scrofa.util.SimpleDateTime;
import sus.scrofa.util.Validator;

@Controller
@Scope("prototype")
public class ProductAdminAction extends CommonAction {

	public String toList() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		page = page <= 0 ? 1 : page;
		count = count <= 0 ? DEFAULT_COUNT : count;
		data = productService.findByPage(page, count);
		return SUCCESS;
	}

	public String toDetail() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		product = productService.findOneByProperty("id", id);
		return SUCCESS;
	}

	public String add() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}

		// 保存图片
		String url = super.saveTo("/upload/image/product");
		if (Validator.isNull(url)) {
			// 现在无图片
			product.setImages(DEFAULT_PRODUCT_IMAGE);
		} else {
			product.setImages(url);
		}

		// 设置起拍时间和结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			product.setStartTime(sdf.parse(startTime.getYear() + "-"
					+ startTime.getMonth() + "-" + startTime.getDate() + " "
					+ startTime.getHour() + ":" + startTime.getMinute() + ":"
					+ startTime.getSecond()));
			product.setFinishTime(sdf.parse(finishTime.getYear() + "-"
					+ finishTime.getMonth() + "-" + finishTime.getDate() + " "
					+ finishTime.getHour() + ":" + finishTime.getMinute() + ":"
					+ finishTime.getSecond()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 保存
		product = productService.add(product);
		if (product == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}
	
	public String delete() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		productService.delete(id);
		return SUCCESS;
	}

	/**
	 * 需要由前台传进的参数有id，和product（其id属性为空）和起拍时间，结束时间，图片文件
	 * 
	 * @return
	 */
	public String update() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		Product tmp = productService.findOneByProperty("id", id);
		if (tmp == null) {
			return PARAM_ERROR;
		}

		// 设置product.id
		product.setId(id);

		// 对于图片，要删除原来的图片，再保存新的图片，图片可能有多张，每张由逗号隔开，整体由方括号包含
		// 1. 原来无图片，现在有图片
		// 2. 原来无图片，现在无图片，不太可能发生，因为之前有默认图片了
		// 3. 原来有图片，现在有图片
		// 4. 原来有图片，现在无图片
		String url = super.saveTo("/upload/image/product");
		if (Validator.isNull(url)) {
			// 现在无图片
			if (Validator.isNull(tmp.getImages())) {
				// 情况2
				product.setImages(DEFAULT_PRODUCT_IMAGE);
			} else {
				// 情况4
				product.setImages(tmp.getImages());
			}
		} else {
			String oldLogo = tmp.getImages();
			super.deleteFile(oldLogo);
			// 情况1，3
			product.setImages(url);
		}

		// 设置起拍时间和结束时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			product.setStartTime(sdf.parse(startTime.getYear() + "-"
					+ startTime.getMonth() + "-" + startTime.getDate() + " "
					+ startTime.getHour() + ":" + startTime.getMinute() + ":"
					+ startTime.getSecond()));
			product.setFinishTime(sdf.parse(finishTime.getYear() + "-"
					+ finishTime.getMonth() + "-" + finishTime.getDate() + " "
					+ finishTime.getHour() + ":" + finishTime.getMinute() + ":"
					+ finishTime.getSecond()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		product = productService.update(product);
		if (product == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(SimpleDateTime startTime) {
		this.startTime = startTime;
	}

	public SimpleDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(SimpleDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public static final String DEFAULT_PRODUCT_IMAGE = "/images/product/default_logo.jpg";

	private Product product;
	private int id;
	private SimpleDateTime startTime;
	private SimpleDateTime finishTime;

	@Resource
	private ProductService productService;
	private static final long serialVersionUID = 498508748875904842L;

}
