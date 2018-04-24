package cn.cuiping.dao;

import cn.cuiping.bo.Expression;
import cn.cuiping.bo.PageBean;
import cn.cuiping.bo.PageConstants;
import cn.cuiping.po.Movie;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nevenon 2017/12/24.
 */
public class MovieDao {
    private QueryRunner qr = new TxQueryRunner();
    /**
     * Admin操作-----删除电影
     * @param bid
     * @throws SQLException
     */
    public void delete(String bid) throws SQLException {
        String sql = "delete from t_book where bid=?";
        qr.update(sql,bid);
    }

    /**
     * 按名称查询
     * @param
     * @return
     * @throws SQLException
     */
    public Movie findByBid(String name) throws SQLException{
        String sql = "select * from t_movie b where b.movieid=?";
        //一行记录中，包含了很多的book属性，还有一个cid属性
        Map<String, Object> map = qr.query(sql, new MapHandler(),name);
        //把Map中除了cid以外的其他属性映射到Book对象中
        Movie movie = CommonUtils.toBean(map,Movie.class);
        return movie;
    }
    /**
     * 按电影名模糊查询
     * @param name
     * @param pc
     * @return
     * @throws SQLException
     */
    public PageBean<Movie> findByName(String name, int pc) throws SQLException{
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("moviename", "like", "%" + name + "%"));
        return findByCriteria(exprList, pc);
    }
    public PageBean<Movie> findByIds(String ids, int pc) throws SQLException{
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("movieId", "in", ids));
        return findByCriteria(exprList, pc);
    }
//    /**
//     * 多条件组合查询
//     * @param criteria
//     * @param pc
//     * @return
//     * @throws SQLException
//     */
//    public PageBean<Book> findByCombination(Book criteria,int pc) throws SQLException{
//        List<Expression> exprList = new ArrayList<Expression>();
//        exprList.add(new Expression("bname", "like", "%" +criteria.getBname() + "%"));
//        exprList.add(new Expression("author", "like", "%" +criteria.getAuthor() + "%"));
//        exprList.add(new Expression("press", "like", "%" +criteria.getPress() + "%"));
//        return findByCriteria(exprList, pc);
//    }
//
//
    private PageBean<Movie> findByCriteria(List<Expression> exprList,int pc) throws SQLException{
		/*
		 * 1.得到ps
		 * 2.得到tr
		 * 3.得到beanList
		 * 4.创建PageBean,返回
		 *
		 */
		/*
		 * 1.得到ps
		 */
        int ps = PageConstants.PAGE_SIZE;//每页记录数
		/*
		 * 2.通过exprList来生成where子句
		 */
        StringBuffer whereSql = new StringBuffer(" where 1=1");
        List<Object> params = new ArrayList<Object>();//SQL中有问号，它是对应问号的值
        for(Expression expr : exprList){
			/*
			 * 添加一个条件上
			 * 1)以and开头
			 * 2)条件的名称
			 * 3)条件的运算符，可以是=、！=、<、>...is null,is null 没有值
			 * 4)如果条件不是is null，再追加问号，然后再向params中添加一个与问号对应的值  OK！
			 */
            whereSql.append(" and ").append(expr.getName()).append(" ").append(expr.getOperator())
                    .append(" ");
            if(!expr.getOperator().equals("is null") && !expr.getOperator().equals("in")  ){
                whereSql.append("?");
                params.add(expr.getValue());//有问号必须有一个参数与其对应
            }
            if(expr.getOperator().equals("in")){
                whereSql.append("(");
                 String[] values = expr.getValue().split(",");
                 for(String value : values){
                     whereSql.append("?,");
                     params.add(Integer.valueOf(value));
                 }
                whereSql.deleteCharAt(whereSql.length()-1);
                whereSql.append(")");
            }
        }
        //System.out.println(whereSql);  测试……
        //System.out.println(params);
		/*
		 * 3.总记录数
		 * select count(*) from t_book where
		 */
        String sql = "select count(*) from t_movie" + whereSql;
        Number number = (Number)qr.query(sql,new ScalarHandler(),params.toArray());
        int tr = number.intValue();//得到总记录数
		/*
		 * 4.得到beanList其实就是多个对象,即当前页记录 (页面数据)
		 */
        sql = "select * from t_movie" + whereSql + "  limit ?,?";
        params.add((pc-1) * ps);//当前页首行记录的下标
        params .add(ps);//一共查询几行，就是每页记录数
        List<Movie> beanList = qr.query(sql, new BeanListHandler<Movie>(Movie.class),params.toArray());
		/*
		 * 5.创建PageBean,设置对应参数
		 */
        PageBean<Movie> pb = new PageBean<Movie>();
		/*
		 * 其中PageBean没有URL，这个任务有servlet完成
		 */
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);
        pb.setBeanList(beanList);
        return pb;

    }


    /**
     * 测试数据
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        MovieDao movieDaoDao = new MovieDao();
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("moviename", "=", "机器之血"));
        PageBean<Movie> moviePage =  movieDaoDao.findByCriteria(exprList, 1);
        System.out.print(moviePage.getBeanList().size());
        for( Movie movie : moviePage.getBeanList()) {
            System.out.println(movie.getMovieName());
        }

    }
}
