package cn.hxcomm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hxcomm.dao.Dao;

/**
 * Servlet implementation class DeleteSomeServlet
 */
public class DeleteSomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Dao dao = new Dao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid[] = request.getParameterValues("userid");
        System.out.println(userid==null);
        if (userid != null) {
            for (int i = 0; i < userid.length; i++) {
                int id = Integer.parseInt(userid[i]);
                   dao.delete(id);
            }
        }
        request.getRequestDispatcher("/listServlet").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
