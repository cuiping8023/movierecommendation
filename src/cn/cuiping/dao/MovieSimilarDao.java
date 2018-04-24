package cn.cuiping.dao;

import cn.cuiping.po.MovieSimilar;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiping on 2017/12/27.
 */
public class MovieSimilarDao {
    private  QueryRunner qr = new TxQueryRunner();
    //获取最大N个相似电影列表
    public  List<MovieSimilar> getMaxNSimilarValue(Integer movieId,Integer n,Integer type)throws SQLException {
        String sql = "";
        if(type ==1)
            sql = " select * from t_topicsimilar where movieAId = ? or movieBId = ? ORDER BY similar desc limit ?";
        else
        sql = " select * from t_similar where movieAId = ? or movieBId = ? ORDER BY similar desc limit ?";
        List<Object> params = new ArrayList<Object>();//参数
        params.add(movieId);
        params.add(movieId);
        params.add(n);
        List<MovieSimilar> beanList = qr.query(sql, new BeanListHandler<MovieSimilar>(MovieSimilar.class),params.toArray());
        return  beanList;
    }
    public static void main(String[] args) throws SQLException {
        MovieSimilarDao similarDao = new MovieSimilarDao();
        List<MovieSimilar> beanList = similarDao.getMaxNSimilarValue(1291873,10,1);
        System.out.println(beanList.size());

    }
}
