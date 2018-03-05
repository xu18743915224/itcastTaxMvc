package cn.itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
//http://localhost:8080/itcastTaxMvc/nsfw/user/user_listUI
@Controller
@RequestMapping(value = "/nsfw/user")
public class UserController {

	@Resource 
	UserService userService;
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~A
	@InitBinder("user")  
	public void initBinder1(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("user.");  
	}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//显示列表页面--------------------------------------
	@RequestMapping(value = "/user_listUI")
	public String listUI(ModelMap model){
		List<User> userList=new ArrayList();
		userList=userService.findAllUserList();
		model.put("userList", userList);
		return "nsfw/user/listUI";
	}
	//新增页面-----------------------------------------
	@RequestMapping(value="/user_addUI")
	public String addUI(){		
		return "nsfw/user/addUI";
	}
	//新增保存数据请求-----------------------------------ssss
	/*@RequestMapping(value="/user_add" )
	public String addUser(@ModelAttribute("user") User user){
		userService.saveUser(user);		
		return "redirect:user_listUI";
	}*/
	@RequestMapping(value="/user_add" )
	public String addUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("headImg")MultipartFile headImg,
			String dept,String name,String account,String password,String gender,String email
			,String mobile,String birthday,String state,String memo) throws Exception, IOException{
		User user=new User();
		user.setDept(dept);
		user.setName(name);
		user.setAccount(account);
		user.setPassword(password);
		user.setGender(gender);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setBirthday(birthday);
		user.setState(state);
		user.setMemo(memo);
		//第一步 需要获得 一个地址path 一个名字uuid.jpg
		//String uploadPath="upload/user";  //保存在web下的路径
		String path = request.getServletContext().getRealPath("");//获取项目动态绝对路径
		String uploadPath=path+"upload/user/";  //保存在web下的路径
		String houZhuiMing=headImg.getOriginalFilename().substring(headImg.getOriginalFilename().lastIndexOf("."));//获取图片的后缀名
		String writeFileName=UUID.randomUUID().toString().replace("-", "")+houZhuiMing;	//组合的新的uuid文件名
		//第二步 根据地址和名字创建一个文件 file
		String headImgPath=uploadPath+writeFileName;
		File newFileImg=new File(headImgPath);
		//第三步 将页面获得的图片二进制代码内容写入文件newFileImg
		headImg.transferTo(newFileImg);		
		user.setHeadImg(headImgPath);
		userService.saveUser(user);	
		return "redirect:user_listUI";
	}
	//编辑页面-----------------------------------------
	@RequestMapping(value="/user_editUI")
	public String editUI(ModelMap model,@ModelAttribute("user") User user){
		User findUser=userService.findUserById(user.getId());
		model.put("user", findUser);
		return "nsfw/user/editUI";
	}
	//编辑保存数据请求---------------------------------------
	@RequestMapping(value="/user_edit")
	public String editUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("headImg")MultipartFile headImg,String id,
			String dept,String name,String account,String password,String gender,String email
			,String mobile,String birthday,String state,String memo) throws Exception, IOException{
		User findUser=userService.findUserById(id);
		findUser.setDept(dept);
		findUser.setName(name);
		findUser.setAccount(account);
		findUser.setPassword(password);
		findUser.setGender(gender);
		findUser.setEmail(email);
		findUser.setMobile(mobile);
		findUser.setBirthday(birthday);
		findUser.setState(state);
		findUser.setMemo(memo);
		//第一步 需要获得 一个地址path 一个名字uuid.jpg
		//String uploadPath="upload/user";  //保存在web下的路径
		String path = request.getServletContext().getRealPath("");//获取项目动态绝对路径
		String uploadPath=path+"upload/user/";  //保存在web下的路径
		String houZhuiMing=headImg.getOriginalFilename().substring(headImg.getOriginalFilename().lastIndexOf("."));//获取图片的后缀名
		String writeFileName=UUID.randomUUID().toString().replace("-", "")+houZhuiMing;	//组合的新的uuid文件名
		//第二步 根据地址和名字创建一个文件 file
		String headImgPath=uploadPath+writeFileName;
		File newFileImg=new File(headImgPath);
		//第三步 将页面获得的图片二进制代码内容写入文件newFileImg
		headImg.transferTo(newFileImg);		
		findUser.setHeadImg(headImgPath);
		userService.updateUser(findUser);
		return "redirect:user_listUI";
	}
	//根据id删除数据----------------------------------------
	@RequestMapping(value="/user_delete")
	public String deleteUser(@ModelAttribute("user") User user){
		userService.deleteUser(user.getId());
		return "redirect:user_listUI";
	}
	//根据id数组删除所有数据-----------------------------------
	@RequestMapping(value="/user_deleteSelected")
	public String deleteSelectUser(String[] selectedRow){
		if(selectedRow!=null&&selectedRow.length>0){
			for(String id:selectedRow){
				userService.deleteUser(id);
			}
		}
		return "redirect:user_listUI";
	}
	//新增帐号account效验数据库是否存在-----------------------------------
	@RequestMapping(value="/user_isAccount")
	@ResponseBody
	public Map<String,Object> isAccountUser(@ModelAttribute("user") User user){
		String str=userService.isAccountUser(user.getAccount());
		Map<String,Object> map=new HashMap();
		map.put("msg", str);
		return map;
	}
	//导入Excel~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping("/user_importExcel")
	public String doUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userExcelFile")MultipartFile userExcelFile) throws Exception{
		if(!userExcelFile.isEmpty()){
			//复制文件到指定路径			
			/*FileUtils.copyInputStreamToFile(userExcelFile.getInputStream(), 
			new File("e:\\temp\\",System.currentTimeMillis()+userExcelFile.getOriginalFilename()));*/
			//判断是否是excel是的话我操作 不是的话返回页面
			String houZhuiMing=userExcelFile.getOriginalFilename().substring(userExcelFile.getOriginalFilename().lastIndexOf("."));
			//如果文件后缀名是.xls和.xlsx的时候执行代码 否则 返回list页面
			if(".xls".equals(houZhuiMing)||".xlsx".equals(houZhuiMing)){
				//导入
				userService.importExcel(userExcelFile,userExcelFile.getOriginalFilename(),response);
			}	        
		}
		return "redirect:user_listUI";
	}
	//导出Excel!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value="/user_exportExcel")
	public String exportMatrixLeaderSheet(HttpServletResponse response) throws IOException{  
		//查找用户列表
  		List<User> userList=userService.findAllUserList();
  		//创建导出文件的文件名字name
  		String excelName="用户列表.xls";
  		//设置response响应头header
	    response.setHeader("Content-Disposition","attachment; filename="+new String(excelName.getBytes("UTF-8"),"ISO-8859-1")+".xls");  
	    //设置response响应输出流
	    OutputStream out = response.getOutputStream();  
	    userService.exportExcel(userList,excelName,out);
	    //关闭输出流
	    out.close();  
	    return "redirect:user_listUI";  
	} 
	
	
	
	
	
}
