package cn.hxcomm.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hxcomm.bean.Bean;
import cn.hxcomm.dao.Dao;

/**
 * Servlet implementation class UpdateSuccessServlet
 */
public class UpdateSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Dao dao = new Dao();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String spage = request.getParameter("page");
	    
	    String sid = request.getParameter("id");
	    String stime = request.getParameter("time");
	    Date date = null;
        try {
            date = df.parse(stime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    String sservice = request.getParameter("service");
	    String sinter = request.getParameter("interface");
	    String sip = request.getParameter("ip");
	    Bean b = new Bean();
	    b.setId(Integer.parseInt(sid));
	    b.setDate(date);
	    b.setService(sservice);
	    b.setOperate(sinter);
	    b.setIP(sip);
	    
	   dao.update(b);
	   System.out.println(spage);
	   
	   request.getRequestDispatcher("/listServlet?page="+spage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doGet(request, response);
	}

}
