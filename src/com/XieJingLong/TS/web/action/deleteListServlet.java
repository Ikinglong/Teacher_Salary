package com.XieJingLong.TS.web.action;

import com.XieJingLong.TS.util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        Connection conn=null;
        PreparedStatement ps=null;
        int count=0;
        try {
            conn= DBUtil.getConnection();
            DBUtil.beginTransaction(conn);
            String sql="delete from tea_info where id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            count=ps.executeUpdate();
            DBUtil.commitTransaction(conn);
        } catch (SQLException e) {
            DBUtil.rollbackTransaction(conn);
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }

        if(count==1){
            //delete success!redirect to the page which show the information
            response.sendRedirect(request.getContextPath()+"/list");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/JSP/failed.jsp");
        }

    }

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }*/
}
