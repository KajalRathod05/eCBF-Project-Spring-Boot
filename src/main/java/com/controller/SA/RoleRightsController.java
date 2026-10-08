package com.controller.SA;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTOs.SA.ModuleMasterDTO;
import com.DTOs.SA.RoleRightsRequest;
import com.DTOs.SA.RoleRightsResponse;
import com.service.SA.RoleRightsService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/rolerights")
@RestController
@RequiredArgsConstructor
public class RoleRightsController {

	private final RoleRightsService roleRightsService;
	
	@PreAuthorize("@permissionService.hasPermission(authentication, 2, 'ADD')")
	@PostMapping("/addRoleRights")
    public ResponseEntity<Map<String, Object>> addRoleRights(@RequestBody RoleRightsRequest roleRightsRequest) {
    	
		System.out.println("role name: "+roleRightsRequest.getRolename()); 	
    	Map<String, Object> response = new HashMap<>();
    	roleRightsService.addRole(roleRightsRequest);
        response.put("status", "success");
        response.put("message", "Role added successfully!");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllRoleRights")
    public ResponseEntity<Map<String, Object>> getAllRoleRights() {
        
		Map<String, Object> response = new HashMap<>();
        List<RoleRightsResponse> roleRights = roleRightsService.getAllRoleRights();
        response.put("status", "success");
        response.put("message", "RoleRights fetched successfully!");
        response.put("roleRights", roleRights);

        return ResponseEntity.ok(response);
    }
	
	@PreAuthorize("@permissionService.hasPermission(authentication, 2, 'VIEW')")
    @GetMapping("/getRoleRights/{roleid}")
    public ResponseEntity<Map<String, Object>> getRoleRights(@PathVariable Long roleid) {
        
		Map<String, Object> response = new HashMap<>();
        RoleRightsResponse roleRight = roleRightsService.getRoleRightsById(roleid);
        response.put("status", "success");
        response.put("message", "RoleRight fetched successfully!");
        response.put("roleRight", roleRight);

        return ResponseEntity.ok(response);
    }

	@PreAuthorize("@permissionService.hasPermission(authentication, 2, 'EDIT')")
    @PutMapping("/updateRoleRights/{roleid}")
    public ResponseEntity<Map<String, Object>> updateRoleRights(@PathVariable Long roleid, 
                                                 @RequestBody RoleRightsRequest roleRightsRequest) {
    	
		Map<String, Object> response = new HashMap<>();
    	roleRightsService.updateRoleRights(roleid, roleRightsRequest);        
        response.put("status", "success");
        response.put("message", "RoleRights updated successfully!");
        
        return ResponseEntity.ok(response);
    }
    
    @PreAuthorize("@permissionService.hasPermission(authentication, 2, 'DELETE')")
    @DeleteMapping("/deleteRoleRights/{roleid}")
    public ResponseEntity<Map<String, Object>> deleteRoleRights(@PathVariable Long roleid) {
    	
    	Map<String, Object> response = new HashMap<>();
    	roleRightsService.deleteRoleRights(roleid);    
        response.put("status", "success");
        response.put("message", "RoleRights deleted successfully!");
        
        return ResponseEntity.ok(response);       
    }
    
    @GetMapping("/getActiveRoles")
    public ResponseEntity<Map<String, Object>> getActiveRoles() {
    	
        Map<String, Object> response = new HashMap<>();
        List<RoleRightsResponse> roles = roleRightsService.getActiveRoles();        
        response.put("message", "roles fetched successfully!");  
        response.put("status", "success");
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getModulesWithMasters")
    public ResponseEntity<Map<String, Object>> getModulesWithMasters() {

    	Map<String, Object> response = new HashMap<>();
        List<ModuleMasterDTO> moduleMasterlist = roleRightsService.getModulesWithMasters();
        response.put("message", "Modules fetched successfully!");  
        response.put("status", "success");
        response.put("modules", moduleMasterlist);

        return ResponseEntity.ok(response);
    }
}
