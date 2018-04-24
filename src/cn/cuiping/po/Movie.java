package cn.cuiping.po;

/**
 * Created by nevenon 2017/12/24.
 */
public class Movie {
    //电影编号
    private Integer movieId;
    //电影名称
    private String movieName;
    //电影评分
    private Double rate;
    //电影图片
    private String img;
    private Double rating;


    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
