package com.XieJingLong.TS.web.action;

import com.XieJingLong.TS.entity.Teacher;
import com.XieJingLong.TS.util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证用户是否已经登陆
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Admin") == null) {
            response.sendRedirect(request.getContextPath() + "/JSP/loginIntercept.jsp");
        }
        else {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            List<Teacher> teacherList = new ArrayList<>();
            try {
                conn = DBUtil.getConnection();
                DBUtil.beginTransaction(conn);
                String sql = "select id,realname,sex,post from tea_info";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String realname = rs.getString("realname");
                    String sex = rs.getString("sex");
                    String post = rs.getString("post");

                    Teacher teacher = new Teacher();
                    teacher.setId(id);
                    teacher.setRealname(realname);
                    teacher.setSex(sex);
                    teacher.setPost(post);

                    teacherList.add(teacher);
                }
            } catch (Exception e) {
                e.printStackTrace();
                DBUtil.rollbackTransaction(conn);
            } finally {
                DBUtil.close(conn, ps, rs);
            }

            //forward
            request.setAttribute("teacherList", teacherList);
            request.getRequestDispatcher("/JSP/list.jsp").forward(request, response);
        }
    }
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
