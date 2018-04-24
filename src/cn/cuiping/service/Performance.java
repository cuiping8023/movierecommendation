package cn.cuiping.service;

import cn.cuiping.bo.Result;
import cn.cuiping.dao.UserDao;
import cn.cuiping.po.User;

import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Created by cuiping on 2017/12/28.
 */
public class Performance {

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        MovieService movieService = new MovieService();

        Integer corNum = 0, testNum = 0, recNum = 0,topN=10;
        recNum = topN*273;


       for(Integer userId = 1; userId < 301;userId ++ ){
            User user = userDao.findByUid(userId);
            Result res =  movieService.getRecommendationResult(user,topN,1,1);
            corNum += res.getCorrect();
            testNum += res.getReality();
       }

        DecimalFormat df = new DecimalFormat("0.00");//格式化小数

        System.out.println( "主题扩展" );
        System.out.println( "precision：" +df.format((float)corNum*100/recNum ));
        System.out.println( "recall：" +df.format((float)corNum*100/testNum ) );

        corNum = 0;
        testNum = 0;
        recNum = topN*301;
        for(Integer userId = 1; userId < 301;userId ++  ){
            User user = userDao.findByUid(userId);
            Result res =  movieService.getRecommendationResult(user,topN,0,1);
            corNum += res.getCorrect();
            testNum += res.getReality();
        }
        System.out.println( "LDA主题" );
        System.out.println( "precision：" +df.format((float)corNum*100/recNum ) );
        System.out.println( "recall：" +df.format((float)corNum*100/testNum ) );
    }

}
