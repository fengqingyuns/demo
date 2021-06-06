package com.example.demo.activity.service;

import java.util.Set;

import com.example.demo.activity.entity.RocIdUser;

public interface SystemService {

	RocIdUser getUserByUserId(String userId);
	
	Set<String> getPermissionByUserId(String userId);
}
