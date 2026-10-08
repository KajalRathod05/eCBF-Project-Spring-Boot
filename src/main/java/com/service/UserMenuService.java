package com.service;

import java.util.List;

import com.DTOs.auth.MenuModuleDTO;

public interface UserMenuService {

	List<MenuModuleDTO> getUserMenu(Integer userid);

}
