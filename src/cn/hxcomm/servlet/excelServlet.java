package cn.hxcomm.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import cn.hxcomm.bean.Bean;
import cn.hxcomm.dao.Dao;

/**
 * Servlet implementation class excelServlet
 */
public class excelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       static Dao dao = new Dao();
    /**
     * @see HttpServlet#HttpServlet()
     */
       public static void excelExport(OutputStream os){
           try{
           //WritableWorkbook wwb=Workbook.createWorkbook(new File(targetfile));

           WritableWorkbook wwb = Workbook.createWorkbook(os);

           WritableSheet ws = wwb.createSheet("第一页",0);

           Label label = new Label(0,0,"id");
           ws.addCell(label);
           label = new Label(1,0,"时间");
           ws.addCell(label);
           label = new Label(2,0,"服务");
           ws.addCell(label);
           label = new Label(3,0,"接口");
           ws.addCell(label);
           label = new Label(4,0,"IP值");
           ws.addCell(label);
           
           
           List<Bean> list = dao.getAll();
           
           for(int i=0;i<list.size();i++){
            label=new Label(0,i+1,list.get(i).getId()+"");
            ws.addCell(label);
            label=new Label(1,i+1,list.get(i).getDates());
            ws.addCell(label);
            label=new Label(2,i+1,list.get(i).getService());
            ws.addCell(label);
            label=new Label(3,i+1,list.get(i).getOperate());
            ws.addCell(label);
            label=new Label(4,i+1,list.get(i).getIP());
            ws.addCell(label);
           }

           wwb.write();
           wwb.close();

           }catch(Exception e){
           System.out.println("生成信息表(Excel格式)时出错：");
              e.printStackTrace();
           }
           }
       
    public excelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String date = df.format(new Date());
	    String fileName=date+".xls";
		
	    String filepath="D:/"+fileName;
	    File fileWrite=new File(filepath);
	    try {
	     fileWrite.createNewFile();
	     OutputStream os=new FileOutputStream(fileWrite);
	     excelExport(os);
	    } catch (IOException e) {
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
