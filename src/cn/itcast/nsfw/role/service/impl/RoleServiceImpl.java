package cn.itcast.nsfw.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.nsfw.role.dao.RoleDao;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	//==============================================================
	@Override
	public List<Role> findAllList() {
		
		return roleDao.findAllList();
	}

	@Override
	public void save(Role entity) {
		roleDao.save(entity);
		
	}

	@Override
	public void delete(String id) {
		roleDao.delete(id);
		
	}

	@Override
	public void update(Role entity) {
		roleDao.update(entity);
		
	}

	@Override
	public Role findObjectById(String id) {
		
		return roleDao.findObjectById(id);
	}

}
