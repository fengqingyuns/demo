package com.example.demo.activity.util;

import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import com.github.pagehelper.PageInfo;

public class PageResult {

    /**  总页数*/
    private int pages;
    /**  总条数*/
    private long total;
    /**  每页的大小*/
    private int pageSize;

    /** 当前页面下标 */
    private int curPageIndex;
    
    Object object;

    /**
     * 生成分页数据信息 -- 只包含分页信息的字段
     */
    public static PageResult pageResult(PageInfo pageInfo){
        PageResult retObj=new PageResult();

        if(null == pageInfo){
            return retObj;
        }

        retObj.setTotal(pageInfo.getTotal());
        retObj.setPages(pageInfo.getPages());
        retObj.setPageSize(pageInfo.getPageSize());
        retObj.setCurPageIndex(pageInfo.getPageNum());

        return retObj;

    }
    
    public static PageResult pageResult(AggregatedPage  page){
        PageResult retObj=new PageResult();

        if(null == page){
            return retObj;
        }
        retObj.setTotal(page.getTotalElements());
        retObj.setPages(page.getTotalPages());
        retObj.setPageSize(page.getSize());
        retObj.setCurPageIndex(page.getNumber());

        return retObj;

    }
    
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPageIndex() {
        return curPageIndex;
    }

    public void setCurPageIndex(int curPageIndex) {
        this.curPageIndex = curPageIndex;
    }
    
    
}
