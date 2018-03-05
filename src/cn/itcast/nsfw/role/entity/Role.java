package cn.itcast.nsfw.role.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator" ,strategy = "uuid")
	private String id;
	
	@Column(name = "NAME", nullable = true, length = 64)
	private String name;
	
	@Column(name = "STATE", nullable = true, length = 64)
	private String state;
	
	//一对多(比喻一个父亲的set属性对着多个亲儿子)@JoinColumn与@column类似，指定映射的数据库字段 
	//updatable=false很关键，如果没有它，在级联删除的时候就会报错(反转的问题)  
	@OneToMany(targetEntity=RoleSon.class,cascade=CascadeType.ALL)  
	@Fetch(FetchMode.JOIN)  
	@JoinColumn(name="ROLE_SON_ID",updatable=false)  
	private Set<RoleSon> RoleSons = new HashSet<RoleSon>();
	
	//角色状态
	public static String ROLE_STATE_VALID = "1";//有效
	public static String ROLE_STATE_INVALID = "0";//无效
	
	public Role() {
	}
	public Role(String id, String name, String state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<RoleSon> getRoleSons() {
		return RoleSons;
	}

	
}
