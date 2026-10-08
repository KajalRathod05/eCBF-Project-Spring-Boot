package com.controller.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTOs.auth.MenuModuleDTO;
import com.service.UserMenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class UserMenuController {
	
	private final UserMenuService userMenuService;
	
	@GetMapping("/{userid}")
    public ResponseEntity<Map<String, Object>> getUserMenu(@PathVariable Integer userid) {

		 Map<String, Object> response = new HashMap<>();
		 
        List<MenuModuleDTO> menu = userMenuService.getUserMenu(userid);
        
        response.put("message", "Modules fetched successfully!");  
        response.put("status", "success");
        response.put("modules", menu);

        return ResponseEntity.ok(response);
    }

}
