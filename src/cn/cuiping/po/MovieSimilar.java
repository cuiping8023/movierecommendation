package cn.cuiping.po;

/**
 * Created by nevenon 2017/12/27.
 */
public class MovieSimilar {
    private Integer movieAId;
    private Integer movieBId;
    private Double similar;

    public Integer getMovieAId() {
        return movieAId;
    }

    public void setMovieAId(Integer movieAId) {
        this.movieAId = movieAId;
    }

    public Integer getMovieBId() {
        return movieBId;
    }

    public void setMovieBId(Integer movieBId) {
        this.movieBId = movieBId;
    }

    public Double getSimilar() {
        return similar;
    }

    public void setSimilar(Double similar) {
        this.similar = similar;
    }
}
