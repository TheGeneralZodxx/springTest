package com.angel.dao;
import com.angel.entity.UserInfo;
public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
}