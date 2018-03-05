package cn.itcast.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.TestPerson;
import cn.itcast.test.service.TestService;

@Transactional				//事务控制
@Service("testService")					//spring扫描service
public class TestServiceImpl implements TestService{

	@Resource						//从spring容器获取标签
	private TestDao testDao;		//spring中获取到dao
	
	
	@Override
	public List<TestPerson> findAllTestPersonList() {		
		return testDao.findAllList();
	}

	@Override
	public void saveTestPerson(TestPerson testPerson) {
		testDao.save(testPerson);
	}

	@Override
	public void deleteTestPerson(String testPersonId) {
		testDao.delete(testPersonId);
	}

	@Override
	public void updateTestPerson(TestPerson testPerson) {
		testDao.update(testPerson);
	}

	@Override
	public TestPerson findTestPersonById(String testPersonId) {	
		return testDao.findObjectById(testPersonId);
	}

}
