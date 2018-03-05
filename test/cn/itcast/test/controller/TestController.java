package cn.itcast.test.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.test.entity.TestPerson;
import cn.itcast.test.service.TestService;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Resource
	private TestService testService;
	
	@RequestMapping(value = "/testIndex")
	public String testIndex(ModelMap model){
		List<TestPerson> testPersonList=testService.findAllTestPersonList();
		model.put("testPersonList", testPersonList);
		return "test/testIndex";
		
	}
}
