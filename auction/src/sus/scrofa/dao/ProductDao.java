package sus.scrofa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import sus.scrofa.entity.Product;

@Repository
public class ProductDao implements CommonDao<Product> {

	@Override
	public Product add(Product obj) {
		String sql = "INSERT INTO product "
				+ "(owner, name, images, area, min_price, start_time, finish_time, attach) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		if (this.executeUpdate(obj, sql) == false) {
			return null;
		}
		return this.findLastAdd();
	}

	@Override
	public void delete(Object id) {
		this.deleteByProperty("id", id);
	}

	@Override
	public void deleteByProperty(String name, Object value) {
		String sql = "DELETE FROM product WHERE " + name + " = '" + value
				+ "'";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product update(Product obj) {
		String sql = "UPDATE product SET "
				+ "owner = ?, name = ?, images = ?, area = ?, min_price = ?, start_time = ?, finish_time = ?, attach = ? "
				+ "WHERE id = " + obj.getId();
		this.executeUpdate(obj, sql);
		return this.findOneByProperty("id", obj.getId());
	}

	@Override
	public Product findLastAdd() {
		String sql = "SELECT * FROM product WHERE id = (SELECT MAX(id) FROM product)";
		List<Product> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Product findOneByProperty(String name, Object value) {
		String sql = "SELECT * FROM product WHERE " + name + " = '" + value
				+ "'";
		List<Product> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<Product> findByProperties(String[] names, Object[] values) {
		String sql = "SELECT * FROM product WHERE ";
		for (int i = 0; i < names.length; ++i) {
			if (i == 0) {
				sql += names[i] + " = '" + values[i] + "' ";
			} else {
				sql += "AND " + names[i] + " = '" + values[i] + "' ";
			}
		}
		return jdbcTemplate.query(sql, new AllOverResultSetExtractor());
	}

	@Override
	public Map<String, Object> findByPage(int page, int count) {
		String sql = "SELECT * FROM product ORDER BY start_time DESC, finish_time ASC";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 正在热拍
	 * 
	 * @param page
	 * @param count
	 * @return
	 */
	public Map<String, Object> findHotByPage(int page, int count) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new java.util.Date());
		String sql = "SELECT * FROM product WHERE start_time <= '" + now
				+ "' AND finish_time >= '" + now
				+ "' ORDER BY start_time DESC, finish_time ASC";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 即将进行
	 * 
	 * @param page
	 * @param count
	 * @return
	 */
	public Map<String, Object> findRightByPage(int page, int count) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new java.util.Date());
		String sql = "SELECT * FROM product WHERE start_time > '" + now
				+ "' ORDER BY start_time DESC, finish_time ASC";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 已经结束
	 * 
	 * @param page
	 * @param count
	 * @return
	 */
	public Map<String, Object> findFinishByPage(int page, int count) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new java.util.Date());
		String sql = "SELECT * FROM product WHERE finish_time < '" + now
				+ "' ORDER BY start_time DESC, finish_time ASC";
		return this.findByPage(page, count, sql);
	}

	@Override
	public List<Object[]> findByPropertiesSpec(String[] names, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, Object> findByPage(final int page, final int count,
			final String sql) {
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<Map<String, Object>>() {
					@Override
					public Map<String, Object> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						Map<String, Object> data = new HashMap<String, Object>();

						rs.last();
						// 计算总记录数
						int totalRecord = rs.getRow();
						data.put(KEY_TOTAL_RECORD, totalRecord);
						// 计算总页数
						int totalPage = totalRecord % count == 0 ? totalRecord
								/ count : totalRecord / count + 1;
						data.put(KEY_TOTAL_PAGE, totalPage);
						// 迭代记录列表
						int i = 0;
						int lastEnd = count * (page - 1);
						if (lastEnd == 0) {
							rs.beforeFirst();
						} else if (lastEnd < 0 || lastEnd >= totalRecord) {
							rs.last();
						} else {
							rs.absolute(lastEnd);
						}
						List<Product> list = new LinkedList<Product>();
						while (rs.next() && ++i <= count) {
							list.add(fetchAll(rs));
						}
						data.put(KEY_LIST, list.size() == 0 ? null : list);
						return data;
					}
				});
	}

	/**
	 * SQL语句可以是INSERT，和UPDATE，占位的参数是除主键外的所有字段
	 * 
	 * @param obj
	 *            从obj中获得值
	 * @param sql
	 *            带参数占位符的INSERT或UPDATE语句
	 * @return
	 */
	private boolean executeUpdate(Product obj, String sql) {
		try {
			jdbcTemplate.execute(new AllOverPreparedStatementCreator(obj, sql),
					new PreparedStatementCallback<Integer>() {
						@Override
						public Integer doInPreparedStatement(
								PreparedStatement ps) throws SQLException,
								DataAccessException {
							return ps.executeUpdate();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private Product fetchAll(ResultSet rs) throws SQLException {
		Product obj = new Product();
		obj.setId(rs.getInt("id"));
		obj.setOwner(rs.getInt("owner"));
		obj.setName(rs.getString("name"));
		obj.setImages(rs.getString("images"));
		obj.setArea(rs.getString("area"));
		obj.setAttach(rs.getString("attach"));
		obj.setMinPrice(rs.getDouble("min_price"));
		obj.setStartTime(new java.util.Date(rs.getTimestamp("start_time")
				.getTime()));
		obj.setFinishTime(new java.util.Date(rs.getTimestamp("finish_time")
				.getTime()));
		return obj;
	}

	/**
	 * 除主键外，其他参数全部设置一遍
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverPreparedStatementCreator implements
			PreparedStatementCreator {
		public AllOverPreparedStatementCreator(Product obj, String sql) {
			super();
			this.obj = obj;
			this.sql = sql;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, obj.getOwner());
			pstmt.setString(2, obj.getName());
			pstmt.setString(3, obj.getImages());
			pstmt.setString(4, obj.getArea());
			pstmt.setDouble(5, obj.getMinPrice());
			pstmt.setTimestamp(6, new Timestamp(obj.getStartTime().getTime()));
			pstmt.setTimestamp(7, new Timestamp(obj.getFinishTime().getTime()));
			pstmt.setString(8, obj.getAttach());
			return pstmt;
		}

		private Product obj;
		private String sql;

	}

	/**
	 * 获取所有字段，构造一个Product对象，根据记录数返回Product列表
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverResultSetExtractor implements
			ResultSetExtractor<List<Product>> {
		@Override
		public List<Product> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			List<Product> list = new LinkedList<Product>();
			while (rs.next()) {
				Product obj = fetchAll(rs);
				list.add(obj);
			}
			return list;
		}
	}

	@Resource
	private JdbcTemplate jdbcTemplate;

}
