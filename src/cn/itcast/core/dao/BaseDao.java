package cn.itcast.core.dao;

import java.util.List;

public interface BaseDao<T> {
	
	/**查询allList*/
	public List<T> findAllList();
	/**增加save*/
	public void save(T entity);
	/**删除delete*/
	public void delete(String id);
	/**修改update*/
	public void update(T entity);
	/**查找findId*/
	public T findObjectById(String id);
}
