package com.example.demo.shiro.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.activity.entity.Menu;

@Mapper
public interface MenuDao extends BaseMapper<Menu>{

    List<Menu> queryMenuList(int pid);
}
