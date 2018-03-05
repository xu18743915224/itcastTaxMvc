package cn.itcast.nsfw.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.nsfw.role.dao.RoleUserTableDao;
import cn.itcast.nsfw.role.entity.RoleUserTable;
import cn.itcast.nsfw.role.service.RoleUserTableService;

@Transactional
@Service
public class RoleUserTableServiceImpl implements RoleUserTableService{
	
	@Resource
	private RoleUserTableDao roleUserTableDao;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@Override
	public List<RoleUserTable> findAllList() {
		
		return roleUserTableDao.findAllList();
	}

	@Override
	public void save(RoleUserTable entity) {
		roleUserTableDao.save(entity);
		
	}

	@Override
	public void delete(String id) {
		roleUserTableDao.delete(id);
		
	}

	@Override
	public void update(RoleUserTable entity) {
		roleUserTableDao.update(entity);
		
	}

	@Override
	public RoleUserTable findObjectById(String id) {
		
		return roleUserTableDao.findObjectById(id);
	}

}
