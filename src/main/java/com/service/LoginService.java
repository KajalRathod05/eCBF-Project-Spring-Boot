package com.service;

import com.model.Userlogin;

public interface LoginService {


	void userRegistration(Userlogin login);

	void resetPassword(Userlogin login);

}
