package cn.itcast.nsfw.role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "ROLE_USER_TABLE")
public class RoleUserTable {

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator" ,strategy = "uuid")
	private String id;
	
	@Column(name = "USER_ID", nullable = true, length = 64)
	private String userId;
	
	@Column(name = "ROLE_ID", nullable = true, length = 64)
	private String roleId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	

	
	
}
