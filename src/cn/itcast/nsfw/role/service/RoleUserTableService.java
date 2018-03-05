package cn.itcast.nsfw.role.service;

import java.util.List;

import cn.itcast.nsfw.role.entity.RoleUserTable;

public interface RoleUserTableService {
	/**查询allList*/
	public List<RoleUserTable> findAllList();
	/**增加save*/
	public void save(RoleUserTable entity);
	/**删除delete*/
	public void delete(String id);
	/**修改update*/
	public void update(RoleUserTable entity);
	/**查找findId*/
	public RoleUserTable findObjectById(String id);
}
