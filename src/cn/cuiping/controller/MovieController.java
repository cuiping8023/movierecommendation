package cn.cuiping.controller;

import cn.cuiping.bo.PageBean;
import cn.cuiping.po.Movie;
import cn.cuiping.po.User;
import cn.cuiping.service.MovieService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nevenon 2017/12/24.
 */
public class MovieController extends BaseServlet{
    private MovieService movieService = new MovieService();


    public String findByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		/*
		 * 1.得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
        int pc = getPc(req);
		/*
		 * 2.得到URL
		 */
        String url = getUrl(req);
		/*
		 * 3.获取查询条件，本方法就是bname
		 */
		String name = "";
		if( req.getParameter("name")!= null)
               name = req.getParameter("name");
		/*
		 * 4.使用pc和cid调用service#findByBname得到PageBean
		 */
        PageBean<Movie> pb = movieService.findByName(name, pc);
		/*
		 * 5.给PageBean设置URL，保存PageBean，转发到/jsps/book/list.jsp
		 */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }

    /**
     * 获取当前页码
     * @param req
     * @return
     */
    private int getPc(HttpServletRequest req){
        int pc = 1;
        String param = req.getParameter("pc");
        if(param != null && !param.trim().isEmpty()){
            try {
                pc = Integer.parseInt(param);
            } catch (RuntimeException e) {}
        }
        return pc;
    }
    /**
     * 截取URL，页面中的分页导航中需要使用它作为超链接的目标
     * @param req
     * @return
     */
	/*
	 * http://localhost:8080/goods/BookServlet?method=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + method=findByCategory&cid=xxx&pc=
	 */
    private String getUrl(HttpServletRequest req){
        String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * 如果URL中存在pc参数，截取掉，如果不存在那就不用截取
		 */
        int index = url.lastIndexOf("&pc=");
        if(index != -1){
            url = url.substring(0,index);
        }
        return url;
    }

    public String  getRecommendation(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Integer topN = 10;
        User user = (User)req.getSession().getAttribute("sessionUser");
        Integer type = Integer.valueOf( req.getParameter("type"));
       /*
		 * 1.得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
        int pc = getPc(req);
		/*
		 * 2.得到URL
		 */
        String url = getUrl(req);
		/*
		 * 3.获取查询条件，本方法就是bname
		 */
        String name = "";
        if( req.getParameter("name")!= null)
            name = req.getParameter("name");
		/*
		 * 4.使用pc和cid调用service#findByBname得到PageBean
		 */
        PageBean<Movie> pb = null;
        pb = movieService.getPersonalRecommendation(user,topN,type,pc);

		/*
		 * 5.给PageBean设置URL，保存PageBean，转发到/jsps/book/list.jsp
		 */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }

    public String  getTopicRecommendation(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Integer topN = 10;
        User user = (User)req.getSession().getAttribute("sessionUser");
        Integer type = (Integer) req.getAttribute("type");
        int pc = getPc(req);
        String url = getUrl(req);
		/*
		 * 3.获取查询条件，本方法就是bname
		 */
        String name = "";
        if( req.getParameter("name")!= null)
            name = req.getParameter("name");
		/*
		 * 4.使用pc和cid调用service#findByBname得到PageBean
		 */
        PageBean<Movie> pb = null;
        pb = movieService.getPersonalRecommendation(user,topN,type,pc);

		/*
		 * 5.给PageBean设置URL，保存PageBean，转发到/jsps/book/list.jsp
		 */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }
}
