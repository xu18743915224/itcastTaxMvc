package cn.itcast.nsfw.role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "ROLE_SON")
public class RoleSon {

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator" ,strategy = "uuid")
	private String id;
	
	@Column(name = "NAME", nullable = true, length = 64)
	private String name;
	  
	//多对一(比喻多个亲儿子对着一个父亲的set属性)@JoinColumn与@column类似，指定映射的数据库字段  
	//updatable=false很关键，如果没有它，在级联删除的时候就会报错(反转的问题)
	@ManyToOne(targetEntity = Role.class)  
	@JoinColumn(name="ROLE_ID",updatable=false)  
	private Role role;

	public RoleSon(String name) {
		super();
		this.name = name;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}  
	
	
	
}
