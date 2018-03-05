package cn.itcast.nsfw.user.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
import cn.itcast.nsfw.user.util.ExcelPOIUtil;
@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public List<User> findAllUserList() {
		
		return userDao.findAllList();
	}

	@Override
	public void saveUser(User entity) {
		userDao.save(entity);
		
	}

	@Override
	public void deleteUser(String id) {
		userDao.delete(id);
		
	}

	@Override
	public void updateUser(User entity) {
		userDao.update(entity);
		
	}

	@Override
	public User findUserById(String id) {
		
		return userDao.findObjectById(id);
	}

	@Override
	public String isAccountUser(String account) {
		return userDao.isAccountUser(account);
	}

	@Override
	public void importExcel(MultipartFile userExcelFile, String userExcelFileName,
			HttpServletResponse response) {
		//拿到excel的 user的集合  循环遍历一个一个的保存到数据库中
		List list=ExcelPOIUtil.daoRuExcel(userExcelFile,userExcelFileName,response);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					int count=0;
					User user=(User) list.get(i);
					//但是 要判断帐号的唯一性 如果该帐号存在那么 不保存数据库 如果该帐号 不存在执行保存方法
					List<User> userList=userDao.findAllList();
					if(userList!=null&&userList.size()>0){
						for(int j=0;j<userList.size();j++){
							User user1=(User) userList.get(j);
							if(user1.getAccount().equals(user.getAccount())){
								count++;
							}
						}
					}
					if(count<=0){
						userDao.save(user);
					}
				}
			}
	}

	@Override
	public void exportExcel(List<User> userList, String excelName,
			OutputStream out) {
		ExcelPOIUtil.daoChuExcel(userList,excelName,out);
		
	}

}
