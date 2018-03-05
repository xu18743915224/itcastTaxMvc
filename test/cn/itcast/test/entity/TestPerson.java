package cn.itcast.test.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TEST_PERSON")
public class TestPerson implements Serializable {
	
	/*-----------------------------------设置属性-------------------------------*/
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator" ,strategy = "uuid")
	private String id;													//主键id
	
	@Column(name = "NAME", nullable = false, length = 32)
	private String name;												//属性name
	
	/*-----------------------------------无参构造方法--------------------------------*/
	public TestPerson() {
	}
	/*-----------------------------------全参构造方法--------------------------------*/
	public TestPerson(String id, String name) {
		this.id = id;
		this.name = name;
	}
	/*-----------------------------------一个参数构造方法------------------------------*/
	public TestPerson(String name) {
		this.name = name;
	}
	/*-----------------------------------get set ---------------------------------*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
