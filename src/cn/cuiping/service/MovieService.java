package cn.cuiping.service;

import cn.cuiping.bo.PageBean;
import cn.cuiping.bo.Result;
import cn.cuiping.dao.MovieDao;
import cn.cuiping.dao.MovieRecordDao;
import cn.cuiping.dao.MovieSimilarDao;
import cn.cuiping.po.Movie;
import cn.cuiping.po.MovieRecord;
import cn.cuiping.po.MovieSimilar;
import cn.cuiping.po.User;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by nevenon 2017/12/24.
 */
public class MovieService {

    private MovieDao movieDao = new MovieDao();
    private MovieRecordDao movieRecordDao = new MovieRecordDao();
    private MovieSimilarDao movieSimilarDao = new MovieSimilarDao();
    /**
     * 按图书模糊查询
     * @param name
     * @param pc
     * @return
     */
    public PageBean<Movie> findByName(String name, int pc){
        try {
            return movieDao.findByName(name, pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PageBean<Movie> getPersonalRecommendation(User user,Integer topN,Integer type,int pc) throws SQLException {
        Integer num = 0;
       List<MovieRecord> trainList =  movieRecordDao.getMovieRecordByUserIdAndType(user.getUid(),0);
        System.out.println("登录名："+user.getLoginname()+" 昵称："+user.getUsername()+" 训练观影记录" );
       for(MovieRecord mr : trainList ){
           System.out.println(mr.toString());
       }
       List<MovieRecord> testList = movieRecordDao.getMovieRecordByUserIdAndType(user.getUid(),1);
        System.out.println("登录名："+user.getLoginname()+" 昵称："+user.getUsername()+" 测试观影列表" );
        for(MovieRecord mr : testList ){
            System.out.println(mr.toString());
        }
       Map<Integer,Double> movieMap = new HashMap<>();
       for( MovieRecord movieRecord : trainList){
          List<MovieSimilar> list = movieSimilarDao.getMaxNSimilarValue(movieRecord.getMovieId(),topN,type);
          for(MovieSimilar ms : list){
              Double similar =  ms.getSimilar()*movieRecord.getRating();
              Integer movieId = null;
              if(ms.getMovieAId() == movieRecord.getMovieId())
                  movieId = ms.getMovieBId();
              else
                  movieId = ms.getMovieAId();
              if(movieMap.get(movieId) == null)
                  movieMap.put(movieId,similar);
              if(movieMap.get(movieId)!= null && movieMap.get(movieId) < similar )
                  movieMap.put(movieId,similar);
          }
       }
        Map<Integer,Double> movieTopNMap = getMapTopNOrderByValue(movieMap,topN);

        StringBuffer movieIds = new StringBuffer("");
        System.out.println("成功推荐电影： ");
        for(Integer key : movieTopNMap.keySet()){
            movieIds.append(key+",");
            for(MovieRecord test :testList){
                if(key.equals(test.getMovieId())) {
                    num++;
                    System.out.println(test.getMovieId()+" "+test.getMovieName()+"  ");
                }
            }
        }
        System.out.println();
        if(movieIds.length() != 0)
           movieIds.deleteCharAt(movieIds.length()-1);

        System.out.println("准确率：" + (num*100)/topN);
        System.out.println("召回率：" + (num*100)/testList.size());
        return movieDao.findByIds(movieIds.toString(),pc);

    }

    /**
     * 求Map<K,V>中Value(值)的最大值
     * @param map
     * @return
     */
    public static Map<Integer, Double> getMapTopNOrderByValue(Map<Integer, Double> map,Integer topN) {
        Map<Integer,Double> resultMap = new HashMap<>();
        Map<Integer, Double> sortMap =  sortMapByValue(map);
        Iterator<Map.Entry<Integer, Double>> iter = sortMap.entrySet().iterator();
        Map.Entry<Integer, Double> tmpEntry = null;
        while (iter.hasNext() && resultMap.size() < topN ){
          tmpEntry = iter.next();
          resultMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
              return  resultMap;
}
    /**
     * 使用 Map按value进行排序
     * @param map
     * @return
     */
    public static Map<Integer, Double> sortMapByValue(Map<Integer, Double> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Integer, Double> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<Integer, Double>> entryList = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());
        Collections.sort(entryList, new MapValueComparator());
        Iterator<Map.Entry<Integer, Double>> iter = entryList.iterator();
        Map.Entry<Integer, Double> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    public Result getRecommendationResult(User user, Integer topN, Integer type, int pc) throws SQLException {
        Integer num = 1;
        List<MovieRecord> trainList =  movieRecordDao.getMovieRecordByUserIdAndType(user.getUid(),0);
        List<MovieRecord> testList = movieRecordDao.getMovieRecordByUserIdAndType(user.getUid(),1);
        Map<Integer,Double> movieMap = new HashMap<>();
        for( MovieRecord movieRecord : trainList){
            List<MovieSimilar> list = movieSimilarDao.getMaxNSimilarValue(movieRecord.getMovieId(),topN,type);
            for(MovieSimilar ms : list){
                Double similar =  ms.getSimilar()*movieRecord.getRating();
                Integer movieId = null;
                if(ms.getMovieAId() == movieRecord.getMovieId())
                    movieId = ms.getMovieBId();
                else
                    movieId = ms.getMovieAId();
                if(movieMap.get(movieId) == null)
                    movieMap.put(movieId,similar);
                if(movieMap.get(movieId)!= null && movieMap.get(movieId) < similar )
                    movieMap.put(movieId,similar);
            }
        }
        if(movieMap.size()!= 0) {
            Map<Integer, Double> movieTopNMap = getMapTopNOrderByValue(movieMap, topN);
            for (Integer key : movieTopNMap.keySet()) {
                for (MovieRecord test : testList) {
                    if (key.equals(test.getMovieId())) {
                        num=num+1;
                    }
                }
            }
        }

        Result res = new Result();
        res.setCorrect(num);
        res.setReality(testList.size());

        return res;

    }
}

//比较器类
   class MapValueComparator implements Comparator<Map.Entry<Integer, Double>> {
    public int compare(Map.Entry<Integer, Double> me1, Map.Entry<Integer, Double> me2) {
        return me2.getValue().compareTo(me1.getValue());
    }
}

