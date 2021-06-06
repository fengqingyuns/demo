package com.example.demo.activity.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MasterDao {
   // Master selectOne(Master master);
   // List<Master> select();
   // void save(Master master);
  //  void delete(Master master);
  //  void update(Master master);
    List<String> queryAllPerms(String userId);
    
    List<Integer> queryMenuIdsByUserId(String userId);
}
