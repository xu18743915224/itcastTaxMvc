package cn.itcast.nsfw.role.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.core.constant.Constant;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.role.entity.RoleSon;
import cn.itcast.nsfw.role.service.RoleService;
import cn.itcast.nsfw.role.service.RoleUserTableService;
import cn.itcast.nsfw.user.entity.User;

@Controller
@RequestMapping(value="/nsfw/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	@Resource
	private RoleUserTableService roleUserTableService;
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		@InitBinder("role")  
		public void initBinder1(WebDataBinder binder) {  
		    binder.setFieldDefaultPrefix("role.");  
		}
	//-----------------------------------------------------------list集合页面
	@RequestMapping(value="/role_listUI")
	public String listUI(ModelMap model){
		List<Role> roleList=roleService.findAllList();	//查找role角色集合list
		Map privilegeMap=Constant.PRIVILEGE_MAP;		//加载权限集合 map
		model.put("roleList", roleList);
		model.put("privilegeMap", privilegeMap);
		return "nsfw/role/listUI";
	}
	//-----------------------------------------------------------新增页面add
	@RequestMapping(value="/role_addUI")
	public String addUI(ModelMap model){
		//添加角色权限(可以有两种 1数据库字典表获取 2本地的常量池获取 )这里选择常量池获取
		Map privilegeMap=Constant.PRIVILEGE_MAP;		//加载权限集合 map
		model.put("privilegeMap",privilegeMap );
		return "nsfw/role/addUI";
	}
	//-----------------------------------------------------------新增页面保存add
	@RequestMapping(value="/role_add")
	public String add(@ModelAttribute("role") Role role,String privilegeIds){
		String[] strs=privilegeIds.split(",");
		for(int i=0,len=strs.length;i<len;i++){
		    role.getRoleSons().add(new RoleSon(strs[i].toString()));
		}
		roleService.save(role);
		return "redirect:role_listUI";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
