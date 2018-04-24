package cn.cuiping.controller;

import cn.cuiping.po.User;
import cn.cuiping.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by nevenon 2017/12/24.
 */
public class LoginController extends BaseServlet {
    private UserService userService = new UserService();
    /**
     * 退出功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String quit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        return "r:/jsps/user/login.jsp";
    }

    /*
     * 登陆方法
     */
    public String login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
        User user = null;
        user = userService.login(formUser);
        if(user == null)
          return "r:/index.jsp";//重定向到主页
        //保存用户到session中，许加强理解！
        req.getSession().setAttribute("sessionUser",user);
        //获取用户名保存到cookie中
        String loginname = user.getLoginname();
        loginname = URLEncoder.encode(loginname,"utf-8");
        Cookie cookie = new Cookie("loginname", loginname);
        cookie.setMaxAge(60 * 60 * 24 * 10);//保存十天
        resp.addCookie(cookie);
        RequestDispatcher rd = req.getRequestDispatcher("MovieServlet?method=findByName");
        rd.forward(req, resp);
        return "";//重定向到主页
    }
}
