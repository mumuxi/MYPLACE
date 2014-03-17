package sus.scrofa.service;

import java.util.Map;

public abstract class CommonService<T> {

	public abstract T add(T obj);

	public abstract void delete(Object id);

	public abstract T update(T obj);
	
	public abstract T findOneByProperty(String name, Object value);

	public abstract Map<String, Object> findByPage(int page, int count);

}
