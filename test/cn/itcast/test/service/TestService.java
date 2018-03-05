package cn.itcast.test.service;

import java.util.List;

import cn.itcast.test.entity.TestPerson;

public interface TestService {

	/**查询allList*/
	public List<TestPerson> findAllTestPersonList();
	/**增加save*/
	public void saveTestPerson(TestPerson testPerson);
	/**删除delete*/
	public void deleteTestPerson(String testPersonId);
	/**修改update*/
	public void updateTestPerson(TestPerson testPerson);
	/**查找findId*/
	public TestPerson findTestPersonById(String testPersonId);
}
