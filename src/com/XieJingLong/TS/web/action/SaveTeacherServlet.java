package com.XieJingLong.TS.web.action;

import com.XieJingLong.TS.util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SaveTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检验是否登陆
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Admin") == null) {
            response.sendRedirect(request.getContextPath() + "/JSP/loginIntercept.jsp");
        } else {
            //获取教师信息
            request.setCharacterEncoding("UTF-8");
            String realname = request.getParameter("realname");
            String sex = request.getParameter("sex");
            String post = request.getParameter("post");

            //JDBC连接数据库保存数据
            Connection conn = null;
            PreparedStatement ps = null;
            int ResultCount = 0;
            try {
                conn = DBUtil.getConnection();
                DBUtil.beginTransaction(conn);
                String sql = "insert into tea_info(realname,sex,post) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, realname);
                ps.setString(2, sex);
                ps.setString(3, post);
                ResultCount = ps.executeUpdate();
                DBUtil.commitTransaction(conn);
            } catch (Exception e) {
                DBUtil.rollbackTransaction(conn);
                e.printStackTrace();
            } finally {
                DBUtil.close(conn, ps, null);
            }

            //保存成跳转成功页面
            if (ResultCount == 1) {
                response.sendRedirect(request.getContextPath() + "/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/JSP/failed.jsp");
            }
        }
    }
}
