package sus.scrofa.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao<T> {
	
	public T add(T obj);

	public void delete(Object id);
	
	public void deleteByProperty(String name, Object value);

	public T update(T obj);

	public T findOneByProperty(String name, Object value);
	
	public T findLastAdd();

	public List<T> findByProperties(String[] names, Object[] values);
	
	public Map<String, Object> findByPage(int page, int count);
	
	public List<Object[]> findByPropertiesSpec(String[] names, Object[] values);
	
	public static final int DEFAULT_EACH_PAGE_COUNT = 10;
	public static final String KEY_TOTAL_PAGE = "_total_page";
	public static final String KEY_TOTAL_RECORD = "_total_record";
	public static final String KEY_LIST = "_list";
}
