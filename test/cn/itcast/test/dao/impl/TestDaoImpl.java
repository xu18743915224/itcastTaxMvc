package cn.itcast.test.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.test.common.dao.impl.TestBaseDaoImpl;
import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.TestPerson;
@Repository
public class TestDaoImpl extends TestBaseDaoImpl<TestPerson> implements TestDao{

}
