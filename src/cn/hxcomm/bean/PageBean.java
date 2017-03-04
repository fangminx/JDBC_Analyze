/**
 * Project Name:project5-2
 * File Name:PageBean.java
 * Package Name:cn.hxcomm.servlet
 * Date:2016-11-8上午9:56:51
 * Copyright (c) 2016, China Link Communications LTD All Rights Reserved.
 *
 */

package cn.hxcomm.bean;

import java.util.List;


/**
 * ClassName: PageBean <br/>
 * Date: 2016-11-8 上午9:56:51 <br/>
 * Description:  
 *
 * @author fangm
 * @version 
 * @see
 */
public class PageBean {
    private int curnum = 1;//用户要跳转的页面数值，默认为1

    private int totalRow;//数据库有多少行数据，需要dao查

    private int perPageRow = 10;//每页显示多少行，可配置，默认10

    private int totalPage;//总页数

    private int startIndex;//dao的limit语句第一个参数

    private List<Bean> list;//存放dao分页查询的数据

    private int next;//获取下一页
    private int before;//获取上一页

    //构造
    public PageBean(int curnum, int totalRow) {
        this.curnum = curnum;
        this.totalRow = totalRow;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getCurnum() {
        return curnum;
    }

    public void setCurnum(int curnum) {
        this.curnum = curnum;
    }

    public int getPerPageRow() {
        return perPageRow;
    }

    public void setPerPageRow(int perPageRow) {
        this.perPageRow = perPageRow;
    }

    public int getTotalPage() {
        return totalRow / perPageRow == 0 ? totalRow / perPageRow : (totalRow / perPageRow) + 1;
    }

    public int getStartIndex() {
        return (curnum - 1) * perPageRow;
    }

    public List<Bean> getList() {
        return list;
    }

    public void setList(List<Bean> list) {
        this.list = list;
    }

    public int getNext() {
        return curnum + 1 == totalPage ? totalPage : curnum + 1;
    }

    public int getBefore() {
        return curnum - 1 == 0 ? 0 : curnum - 1;
    }

}
