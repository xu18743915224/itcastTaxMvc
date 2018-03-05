package cn.itcast.test.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import cn.itcast.test.common.dao.TestBaseDao;

public class TestBaseDaoImpl<T> implements TestBaseDao<T>{
	
	@Resource										//使用Resource标签到spring里获取sessionFactory
	private SessionFactory sessionFactory;
														
	private Session getSession() {					//或得到的sessionFactory调用.getCurrentSession()方法	
		return sessionFactory.getCurrentSession();	//放回session对象以供我们使用session去操作数据库
	}												//getCurrentSession创建的线程会在事务回滚或事物提交后自动关闭,而openSession必须手动关闭	

	/**-----------该方法就是获取本类的T的对象比如T=(User)那么调用本类BaseDaoImpl对象的this.getClass().getGenericSuperclass()方法
	 * -----------获取到对象ParameterizedType pt调用该方法的.getActualTypeArguments()[0]即可拿到T对象即User然后赋值非clazz-----------start*/
	private Class<T> clazz;			//创建属性Class的clazz	
	public TestBaseDaoImpl(){			//构造方法 该对象被初始化的时候执行该方法
		ParameterizedType pt=(ParameterizedType) getClass().getGenericSuperclass();	//调用Class
		//获取到的第一个下表0即使我们索要的类
		clazz=(Class<T>) pt.getActualTypeArguments()[0];
	}
	/**---------------------------------------------------------------------------------------------------------------end*/
	@Override
	public List<T> findAllList() {
		String hql="FROM "+clazz.getName();			//clazz即拿到的对象调用clazz的getName()即可拿到该对象的名字  
		Query query=getSession().createQuery(hql);		
		return query.list();
		//return this.getSession().createCriteria(User.class).list();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
		
	}

	@Override
	public void delete(String id) {
		getSession().delete(findObjectById(id));
		//this.getSession().createQuery("delete User where id=?").setParameter(0, id).executeUpdate();
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
		
	}

	@Override
	public T findObjectById(String id) {
		String hql="FROM +"+clazz.getName()+" WHERE id=?";
		Query query=getSession().createQuery(hql);		
		return (T) query.setParameter(0, id).uniqueResult();
		//return (User) this.getSession().createQuery("from User where id=?").setParameter(0, id).uniqueResult();
	}

}
