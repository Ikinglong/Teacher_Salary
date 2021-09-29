package com.XieJingLong.TS.web.action;

import com.XieJingLong.TS.util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class updateListServlet extends HttpServlet {
/*  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String tid=request.getParameter("id");
        String realname=request.getParameter("realname");
        String sex=request.getParameter("sex");
        String post=request.getParameter("post");

        //JDBC连接数据库修改数据
        Connection conn=null;
        PreparedStatement ps=null;
        int ResultCount=0;
        try{
            conn= DBUtil.getConnection();
            DBUtil.beginTransaction(conn);
            String sql="update tea_info set realname=?,sex=?,post=? where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,realname);
            ps.setString(2,sex);
            ps.setString(3,post);
            ps.setString(4,tid);
            ResultCount=ps.executeUpdate();
            DBUtil.commitTransaction(conn);
        }catch (Exception e){
            DBUtil.rollbackTransaction(conn);
            e.printStackTrace();
        }finally{
            DBUtil.close(conn,ps,null);
        }

        //修改成跳转成功页面
        if(ResultCount==1){
            response.sendRedirect(request.getContextPath()+"/list");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/JSP/failed.jsp");
        }
    }
}
