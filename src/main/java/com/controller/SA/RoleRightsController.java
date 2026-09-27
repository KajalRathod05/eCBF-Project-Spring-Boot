package com.controller.SA;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.DTOs.SA.RoleRightsRequest;
import com.DTOs.SA.RoleRightsResponse;
import com.service.SA.RoleRightsService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/rolerights")
@RestController
@RequiredArgsConstructor
public class RoleRightsController {

	private final RoleRightsService roleRightsService;
	
	@PostMapping("/addRoleRights")
    public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody RoleRightsRequest roleRightsRequest) {
    	System.out.println("role name: "+roleRightsRequest.getRolename());
    	
    	Map<String, Object> response = new HashMap<>();
    	roleRightsService.addRole(roleRightsRequest);
        
        response.put("status", "success");
        response.put("message", "Role added successfully!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRoleRights")
    public ResponseEntity<Map<String, Object>> getRoleRights() {
        Map<String, Object> response = new HashMap<>();
        List<RoleRightsResponse> roleRights = roleRightsService.getAllRoleRights();

        response.put("status", "success");
        response.put("message", "RoleRights fetched successfully!");
        response.put("roleRights", roleRights);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateRoleRights/{roleid}")
    public ResponseEntity<Map<String, Object>> updateRoleRights(@PathVariable Long roleid, 
                                                 @RequestBody RoleRightsRequest roleRightsRequest) {
    	Map<String, Object> response = new HashMap<>();
    	roleRightsService.updateRoleRights(roleid, roleRightsRequest);
        
        response.put("status", "success");
        response.put("message", "RoleRights updated successfully!");
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/deleteRoleRights/{roleid}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long roleid) {
    	
    	Map<String, Object> response = new HashMap<>();
    	roleRightsService.deleteRoleRights(roleid);    
        response.put("status", "success");
        response.put("message", "RoleRights deleted successfully!");
        return ResponseEntity.ok(response);
        
    }
}
