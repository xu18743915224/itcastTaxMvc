package cn.itcast.nsfw.user.dao;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User>{
	
	/**新增帐号account效验数据库是否存在*/
	String isAccountUser(String account);

}
