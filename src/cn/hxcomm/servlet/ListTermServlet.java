package cn.hxcomm.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hxcomm.bean.Bean;
import cn.hxcomm.bean.PageBean;
import cn.hxcomm.dao.Dao;

/**
 * Servlet implementation class ListTermServlet
 */
public class ListTermServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Dao dao = new Dao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTermServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String begin = request.getParameter("begintime");
		String end = request.getParameter("endtime");
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
            Date beginDate = df.parse(begin);
            Date endDate = df.parse(end);
            System.out.println(beginDate);
            System.out.println(endDate);
            if(endDate.before(beginDate)){
                request.setAttribute("err", "结束时间必须在开始时间之后!");
            }
            
            List<Bean> list= dao.findByDate(beginDate,endDate);
            request.setAttribute("list", list);
            request.getRequestDispatcher("listsome.jsp").forward(request, response);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
