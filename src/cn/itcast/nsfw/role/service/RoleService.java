package cn.itcast.nsfw.role.service;

import java.util.List;

import cn.itcast.nsfw.role.entity.Role;

public interface RoleService {
	/**查询allList*/
	public List<Role> findAllList();
	/**增加save*/
	public void save(Role entity);
	/**删除delete*/
	public void delete(String id);
	/**修改update*/
	public void update(Role entity);
	/**查找findId*/
	public Role findObjectById(String id);
}
