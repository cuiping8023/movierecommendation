package cn.cuiping.po;
/**
 * 用户模块实体类
 * @author cuiping
 *
 */
/*
 * 属性哪里来
 * 1.t_user表：因为我们需要把t_user表查询出的数据封装到User对象中
 * 2.该模块所有表单：因为我们需要把表单数据封装到User对象中
 *
 */
public class User {
	//对应数据库表
	private Integer uid;          //主键
	private String loginname;
	private String loginpass;
	private String username;
	private boolean status;  //状态

	
	//注册表单
	private String reloginpass;//确认密码
	private String verifyCode;//验证码
	
	public String getReloginpass() {
		return reloginpass;
	}
	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	//修改密码表单
	private String newpass;//新密码
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass="
				+ loginpass + ", email=" + username + ", status=" + status
				+ "]";
	}	
}
