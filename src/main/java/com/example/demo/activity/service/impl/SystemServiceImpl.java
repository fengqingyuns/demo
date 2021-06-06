package com.example.demo.activity.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.activity.dao.RocIdUserMapper;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.activity.service.SystemService;
@Service
public class SystemServiceImpl extends ServiceImpl<RocIdUserMapper, RocIdUser> implements SystemService{

	@Autowired
	private RocIdUserMapper rocIdUserMapper;
	@Override
	public RocIdUser getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return rocIdUserMapper.selectById(userId);
	}
	@Override
	public Set<String> getPermissionByUserId(String userId) {
		 Set<String> permsSet = new HashSet<>();
		List<String> permissions = rocIdUserMapper.selectPermissionsByUserId(userId);
		if(permissions != null && permissions.size() > 0) {
			for(String perms : permissions) {
				if(StringUtils.isBlank(perms)) {
					continue;
				}
				permsSet.addAll(Arrays.asList(perms.trim().split(",")));
			}
		}
		return null;
	}

}
