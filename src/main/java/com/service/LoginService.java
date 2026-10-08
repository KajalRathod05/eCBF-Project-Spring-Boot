package com.service;

import com.DTOs.auth.UserLoginRequest;

public interface LoginService {


	void userRegistration(UserLoginRequest userLoginRequest);

	void resetPassword(UserLoginRequest userLoginRequest);

}
