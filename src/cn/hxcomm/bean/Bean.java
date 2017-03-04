/**
 * Project Name:project5
 * File Name:Bean.java
 * Package Name:cn.hxcomm.p5
 * Date:2016-11-7上午9:44:05
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */


package cn.hxcomm.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: Bean <br/>
 * Date: 2016-11-7 上午9:44:05 <br/>
 * Description:  
 *
 * @author fangm
 * @version 
 * @see
 */
public class Bean {
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private Date date;
    private String service;
    private String operate;
    private String IP;
    
    public Bean(){
        
    }
    
    public Bean(Date time, String service, String operate, String IP) {
        this.date = time;
        this.service = service;
        this.operate = operate;
        this.IP = IP;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public String getOperate() {
        return operate;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }
    public String getIP() {
        return IP;
    }
    public void setIP(String iP) {
        IP = iP;
    }
    public String getDates(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(this.getDate());
    }
}

