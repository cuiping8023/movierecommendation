package cn.cuiping.service;

import java.sql.SQLException;
import cn.cuiping.dao.UserDao;
import cn.cuiping.po.User;
import org.omg.CORBA.UserException;
//import org.apache.commons.dbutils.handlers.ScalarHandler;
/**
 * 用户模块业务层
 * @author Administrator
 *
 */

public class UserService {
	private UserDao userDao = new UserDao();

	/*
	 * 登陆功能
	 */
	public User login(User user){
		try {
			return userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 用户名注册校验
	 */
	public boolean ajaxValidateLoginname(String loginname){
		try {
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
  


