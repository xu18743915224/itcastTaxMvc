package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import cn.itcast.nsfw.user.entity.User;

public interface UserService {

	/**查询allList*/
	public List<User> findAllUserList();
	/**增加save*/
	public void saveUser(User entity);
	/**删除delete*/
	public void deleteUser(String id);
	/**修改update*/
	public void updateUser(User entity);
	/**查找findId*/
	public User findUserById(String id);
	/**新增帐号account效验数据库是否存在*/
	public String isAccountUser(String account);
	/**导入excel到数据库保存
	 * @param response */
	public void importExcel(MultipartFile userExcelFile, String userExcelFileName, HttpServletResponse response);
	/**导出excel的方法*/
	public void exportExcel(List<User> userList, String excelName,OutputStream out);
}
