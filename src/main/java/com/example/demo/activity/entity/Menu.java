package com.example.demo.activity.entity;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;


public class Menu {

    /**
     * 菜单ID
     */
    private int menuId;
    /**
     * 父菜单ID
     */
    private int pId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单名称
     */
    private String pMenuName;
    /**
     * 菜单url
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如user:list;user:create)
     */
    private String perms;
    /**
     * 类型 0：目录，1：菜单，2：按钮
     */
    private int type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private int orderNum;
    /**
     * 子节点
     */
    @TableField(exist=false)
    private List<Menu> list = new ArrayList<Menu>();
    /**
     * 
     * @author lit
     * @desc:
     * @date:  2020年9月4日 下午7:38:19  
     *
     * @return
     */
    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getpMenuName() {
        return pMenuName;
    }
    public void setpMenuName(String pMenuName) {
        this.pMenuName = pMenuName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPerms() {
        return perms;
    }
    public void setPerms(String perms) {
        this.perms = perms;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public List<Menu> getList() {
        return list;
    }
    public void setList(List<Menu> list) {
        this.list = list;
    }
    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", pId=" + pId + ", menuName=" + menuName + ", pMenuName=" + pMenuName
                + ", url=" + url + ", perms=" + perms + ", type=" + type + ", icon=" + icon + ", orderNum=" + orderNum
                + ", list=" + list + "]";
    }
    
    
    
}
