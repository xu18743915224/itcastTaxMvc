package cn.itcast.nsfw.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	
	public String isAccountUser(String account) {
		String hql="FROM User WHERE account=?";
		Query query=getSession().createQuery(hql);		
		query.setParameter(0, account);
		List<User> list=query.list();
		if(list!=null&&list.size()>0){
			return "true";
		}
		return "false";
	}

}
