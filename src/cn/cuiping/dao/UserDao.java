package cn.cuiping.dao;

import cn.cuiping.po.User;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
/**
 * Created by nevenon 2017/12/24.
 */

/**
 * 用户模块持久层
 * @author Administrator
 *
 */
public class UserDao {

    private QueryRunner qr = new TxQueryRunner(); //简化数据库操作
    /**
     * 按uid
     * @param uid
     * @param
     * @return
     * @throws SQLException
     */
    public User findByUid(Integer uid) throws SQLException{
        String sql = "select * from t_user where uid = ?";
        return qr.query(sql,new BeanHandler<User>(User.class),uid);
    }

    /**
     * 修改密码
     * @param uid
     * @param password
     * @throws SQLException
     */
    public void updatePassword(String uid,String password) throws SQLException{
        String sql = "update t_user set loginpass=? where uid=?";
        qr.update(sql,password,uid);

    }
    /*
     * 按用户名和密码查询
     */
    public User findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException{
        String sql = "select * from t_user where loginname=? and loginpass=?";
        return qr.query(sql,new BeanHandler<User>(User.class),loginname,loginpass);
    }


    /*
     * 修改用户状态
     */
    public void updateStatus(String uid,boolean status) throws SQLException{
        String sql = "update t_user set status=? where uid=?";
        qr.update(sql,status,uid);
    }
    /*
     * 校验用户名是否注册
     */
    public boolean ajaxValidateLoginname(String loginname) throws SQLException{
        String sql = "select count(1) from t_user where loginname=?";
        Number number = (Number)qr.query(sql, new ScalarHandler(), loginname);
        return number.intValue()==0;
    }


}
