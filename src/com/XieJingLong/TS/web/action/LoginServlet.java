package com.XieJingLong.TS.web.action;

import com.XieJingLong.TS.entity.Admin;
import com.XieJingLong.TS.util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
/*    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        boolean loginSuccess=false;
        Admin adm=null;
        try {
            conn= DBUtil.getConnection();
            DBUtil.beginTransaction(conn);
            String sql="select * from Admin_info where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                loginSuccess=true;
                adm=new Admin();
                adm.setId(rs.getInt("id"));
                adm.setUsername(rs.getString("username"));
                adm.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtil.rollbackTransaction(conn);
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        if (loginSuccess){
            //登陆成功后，判断是否选择记住我
            String rememberMe=request.getParameter("rememberMe");
            if("ok".equals(rememberMe)){
                Cookie cookie1=new Cookie("username",username);
                cookie1.setMaxAge(60);
                cookie1.setPath(request.getContextPath());
                //将cookie发给浏览器
                response.addCookie(cookie1);

                Cookie cookie2=new Cookie("password",password);
                cookie2.setMaxAge(60);
                cookie2.setPath(request.getContextPath());
                //将cookie发给浏览器
                response.addCookie(cookie2);
            }

            HttpSession session=request.getSession();
            session.setAttribute("Admin",adm);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/JSP/failed.jsp");
        }

    }
}
