/**
 * Project Name:project5
 * File Name:Myjob.java
 * Package Name:cn.hxcomm.p5
 * Date:2016-11-7上午9:49:06
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */


package cn.hxcomm.schedule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;

import cn.hxcomm.bean.Bean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * ClassName: Myjob <br/>
 * Date: 2016-11-7 上午9:49:06 <br/>
 * Description:  
 *
 * @author fangm
 * @version 
 * @see
 */
public class MyJob implements org.quartz.Job{
    public MyJob() {
    }
    
    /** 
     * 获得指定日期的前一天 
     *  
     * @param nowday 
     * @return 
     * @throws Exception 
     */  
    public static String getBeforeDay(String nowday) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yyyy-MM-dd").parse(nowday);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c  
                .getTime());  
        return dayBefore;  
    }  
    
    public void execute(JobExecutionContext context) {
        System.err.println("Hello World!  MyJob is executing.");
        BufferedReader br;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String path = getBeforeDay(new Date().toLocaleString());
            br = new BufferedReader(new FileReader("data/"+path));
            
            String line = null;
            ArrayList<Bean> list = new ArrayList<Bean>();
            while ((line = br.readLine()) != null) {
                if (line.startsWith("INFO")) {
                    String[] split = line.split(" ");
                    //提取时间
                    Date time = df.parse(split[1] + " " + split[2]);
                    //提取业务
                    String service = split[5].startsWith("REQUEST") ? "REQUEST" : "RESPONSE";
                    //提取操作
                    String operate = line.substring(
                            line.indexOf("<req_service>") + "<req_service>".length(),
                            line.indexOf("</req_service>"));
                    //提取IP
                    String IP = line.substring(line.lastIndexOf("[") + 1, line.lastIndexOf("]"));
                    Bean b = new Bean(time,service,operate,IP);
                    list.add(b);
                    
                    
                }
            }
            //将list中的数据存到数据库
            for (Bean bean : list) {
                insert(bean);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
  //数据库的连接
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/project05";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    //插入数据
    private static int insert(Bean bean) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into T_BEANS (BEAN_DATE,BEAN_SERVICE,BEAN_OPERATE,BEAN_IP) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setTimestamp(1,new java.sql.Timestamp(bean.getDate().getTime()));
            pstmt.setString(2, bean.getService());
            pstmt.setString(3, bean.getOperate());
            pstmt.setString(4, bean.getIP());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}

