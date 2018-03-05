package cn.itcast.nsfw.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "USER")
public class User implements Serializable {
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator" ,strategy = "uuid")
	private String id;
	
	@Column(name = "DEPT", nullable = true, length = 64)
	private String dept;
	
	@Column(name = "ACCOUNT", nullable = false, length = 64)
	private String account;
	
	@Column(name = "NAME", nullable = false, length = 64)
	private String name;
	
	@Column(name = "PASSWORD", nullable = false, length = 64)
	private String password;
	
	@Column(name = "HEAD_IMG", nullable = true, length = 512)
	private String headImg;
	
	@Column(name = "GENDER", nullable = true, length = 64)
	private String gender;
	
	@Column(name = "STATE", nullable = true, length = 64)
	private String state;
	
	@Column(name = "MOBILE", nullable = true, length = 64)
	private String mobile;
	
	@Column(name = "EMAIL", nullable = true, length = 64)
	private String email;
	
	@Column(name = "BIRTHDAY", nullable = true, length = 64)
	private String birthday;
	
	@Column(name = "MEMO", nullable = true, length = 64)
	private String memo;
	
	//用户状态
	public static String USER_STATE_VALID = "1";//有效
	public static String USER_STATE_INVALID = "0";//无效
	
	public User() {
	}
	public User(String id, String dept, String account, String name, String password, String headImg, String gender, String state, String mobile, String email, String birthday, String memo) {
		this.id = id;
		this.dept = dept;
		this.account = account;
		this.name = name;
		this.password = password;
		this.headImg = headImg;
		this.gender = gender;
		this.state = state;
		this.mobile = mobile;
		this.email = email;
		this.birthday = birthday;
		this.memo = memo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	

}
