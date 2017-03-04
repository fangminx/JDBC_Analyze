/**
 * Project Name:project5-2
 * File Name:dao.java
 * Package Name:cn.hxcomm.servlet
 * Date:2016-11-7下午4:43:55
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */


package cn.hxcomm.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;


import cn.hxcomm.bean.Bean;
import cn.hxcomm.bean.PageBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * ClassName: dao <br/>
 * Date: 2016-11-7 下午4:43:55 <br/>
 * Description:  
 *
 * @author fangm
 * @version 
 * @see
 */
public class Dao {
  //数据库的连接
    public static  Connection getConn() {
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
    
    
    //分页查询数据，只需要传入要查询的页码即可，返回一个PageBean对象
    public  PageBean getPageList(int curnum) throws Exception {
        Connection conn = getConn();
        int totalRow = getTotalRows();
        PageBean pb = new PageBean(curnum, totalRow);
        InputStream in = new FileInputStream("D:\\huaxia\\Project5ALL\\WebContent\\page\\page.properties");
        Properties prop = new Properties();
        prop.load(in);
        if(prop.containsKey("num")){
            String key = prop.getProperty("num"); // 通过key获取value
            pb.setPerPageRow(Integer.parseInt(key));
        }
            
        
        String sql = "select * from t_beans limit "+pb.getStartIndex()+","+pb.getPerPageRow();
        PreparedStatement pstmt;
        List<Bean> list = new ArrayList<Bean>();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bean b = new Bean();
                int id = rs.getInt(1);
                Timestamp timestamp = rs.getTimestamp(2);
                String service = rs.getString(3);
                String operate = rs.getString(4);
                String IP = rs.getString(5);
                
                //封装到bean，添加到list
                b.setId(id);
                b.setDate(timestamp);
                b.setService(service);
                b.setOperate(operate);
                b.setIP(IP);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pb.setList(list);
        return pb;
    }
    
  
    
    //获得总记录数
    public static int getTotalRows(){
        Connection conn = getConn();
        String sql = "select count(*) from t_beans";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    //删除
    public void delete(int id){
        Connection conn = getConn();
        String sql = "delete from t_beans where id = ?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
  //根据id查Bean
    public Bean findById(int id){
        Connection conn = getConn();
        String sql = "select * from t_beans where id ="+id;
        PreparedStatement pstmt;
        Bean b = null;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
                b = new Bean();
                rs.next();
                Date date = rs.getDate(2);
                String service = rs.getString(3);
                String operate = rs.getString(4);
                String IP = rs.getString(5);
                
                //封装到bean，添加到list
                b.setId(id);
                b.setDate(new java.util.Date(date.getTime()));
                b.setService(service);
                b.setOperate(operate);
                b.setIP(IP);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
        return b;
    }
    
  //插入
    public void insert(Bean bean){
        Connection conn = getConn();
        String sql = "insert into t_beans (BEAN_DATE,BEAN_SERVICE,BEAN_OPERATE,BEAN_IP) values (?,?,?,?)";
        
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setTimestamp(1, new java.sql.Timestamp(bean.getDate().getTime()));
            pstmt.setString(2, bean.getService());
            pstmt.setString(3, bean.getOperate());
            pstmt.setString(4, bean.getIP());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void update(Bean b) {
        Connection conn = getConn();
        String sql = "update t_beans set BEAN_DATE=?,BEAN_SERVICE=?,BEAN_OPERATE=?,BEAN_IP=? where id=?;";
        
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setTimestamp(1, new java.sql.Timestamp(b.getDate().getTime()));
            pstmt.setString(2, b.getService());
            pstmt.setString(3, b.getOperate());
            pstmt.setString(4, b.getIP());
            pstmt.setInt(5, b.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }


    public List<Bean> findByDate(java.util.Date beginDate, java.util.Date endDate) {
        Connection conn = getConn();
        Timestamp begin = new Timestamp(beginDate.getTime());
        Timestamp end = new Timestamp(endDate.getTime());
        
        String sql = "select * from t_beans where BEAN_DATE between '"+begin+"' and '"+end+"';";
        PreparedStatement pstmt;
        List<Bean> list = new ArrayList<Bean>();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bean b = new Bean();
                int id = rs.getInt(1);
                Timestamp timestamp = rs.getTimestamp(2);
                String service = rs.getString(3);
                String operate = rs.getString(4);
                String IP = rs.getString(5);
                
                //封装到bean，添加到list
                b.setId(id);
                b.setDate(timestamp);
                b.setService(service);
                b.setOperate(operate);
                b.setIP(IP);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public List<Bean> getAll() {
        Connection conn = getConn();
        String sql = "select * from t_beans;";
        PreparedStatement pstmt;
        List<Bean> list = new ArrayList<Bean>();
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bean b = new Bean();
                int id = rs.getInt(1);
                Timestamp timestamp = rs.getTimestamp(2);
                String service = rs.getString(3);
                String operate = rs.getString(4);
                String IP = rs.getString(5);
                
                //封装到bean，添加到list
                b.setId(id);
                b.setDate(timestamp);
                b.setService(service);
                b.setOperate(operate);
                b.setIP(IP);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

