package cn.cuiping.dao;

import cn.cuiping.po.MovieRecord;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiping on 2017/12/27.
 */
public class MovieRecordDao {
    private QueryRunner qr = new TxQueryRunner();
    //获取用户观影记录  根据用户id 和 type
    public List<MovieRecord> getMovieRecordByUserIdAndType(Integer userId, Integer type)throws SQLException {

        String sql = " select * from t_movierecord where userid = ? and type = ? ";
        List<Object> params = new ArrayList<Object>();//参数
        params.add(userId);
        params.add(type);
        List<MovieRecord> beanList = qr.query(sql, new BeanListHandler<MovieRecord>(MovieRecord.class),params.toArray());
        return  beanList;
    }

    public static void main(String[] args) throws SQLException {
        MovieRecordDao movieRecordDao = new MovieRecordDao();
        List<MovieRecord> list = movieRecordDao.getMovieRecordByUserIdAndType(36,1);
        System.out.println(list.size());
    }
}
