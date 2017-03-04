package cn.hxcomm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import cn.hxcomm.bean.PageBean;
import cn.hxcomm.dao.Dao;


/**
 * Servlet implementation class ListServlet
 */
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Dao dao = new Dao();
    /**
     * Default constructor. 
     */
    public ListServlet() {
        
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String curnum = request.getParameter("page");
        int cur;
        if(curnum==null){
            cur =1;
        }else{
            cur=Integer.parseInt(curnum);
        }
        PageBean pageBean = null;
        try {
            pageBean = dao.getPageList(cur);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        request.setAttribute("pageBean", pageBean);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
