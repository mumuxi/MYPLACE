package sus.scrofa.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import sus.scrofa.action.product.ProductAdminAction;
import sus.scrofa.entity.Product;
import sus.scrofa.service.ProductService;

public class TestProduct extends TestBase {

	@Test
	public void testAdd() throws ParseException {
		ProductService productService = cxt.getBean(ProductService.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Product p = new Product();
		p.setName("xxx");
		p.setArea("xxx");
		p.setAttach("xxx");
		p.setOwner(1);
		p.setImages(ProductAdminAction.DEFAULT_PRODUCT_IMAGE);
		p.setMinPrice(45);
		p.setStartTime(sdf.parse("2013-1-1 1:5:20"));
		p.setFinishTime(sdf.parse("2013-3-1 2:6:30"));
		
		p = productService.add(p);
		if (p == null) {
			System.out.println("failed.");
		} else {
			System.out.println("added - " + p.getId());
		}
	}
	
	@Test
	public void testFind() {
		ProductService productService = cxt.getBean(ProductService.class);
		Product p = productService.findOneByProperty("id", 9);
		System.out.println(p.getName() + " " + p.getStartTime());
	}

}
