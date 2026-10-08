package com.serviceImpl.SA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.DTOs.SA.MasterItemDTO;
import com.DTOs.SA.ModuleMasterDTO;
import com.DTOs.SA.RoleRightsRequest;
import com.DTOs.SA.RoleRightsRequest.UserRightsDTO;
import com.DTOs.SA.RoleRightsResponse;
import com.DTOs.SA.RoleRightsResponse.UserRightsResDTO;
import com.exception.GlobalException;
import com.model.SA.UserRightsMST;
import com.model.ModuleMST;
import com.model.PageTypeMST;
import com.model.SA.RoleRightsMST;
import com.repository.ModuleRepository;
import com.repository.PageTypeRepository;
import com.repository.SA.RoleRightsRepository;
import com.service.SA.RoleRightsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleRightsServiceImpl implements RoleRightsService{
	
	private final RoleRightsRepository roleRightsRepository;
	private final ModuleRepository moduleRepository;
	private final PageTypeRepository pageTypeRepository;

	@Override
	@Transactional
	public void addRole(RoleRightsRequest roleRightsRequest) {
		
		if (roleRightsRepository.existsByRolecode(roleRightsRequest.getRolecode())) {
            throw new GlobalException("Role already exists with this rolecode");
        }
		
		if (roleRightsRequest.getUserRightsDTO() == null || roleRightsRequest.getUserRightsDTO().isEmpty()) {
            throw new GlobalException("No modules or masters selected");
        }

        RoleRightsMST roleRightsMst = new RoleRightsMST();
        roleRightsMst.setRolecode(roleRightsRequest.getRolecode());
        roleRightsMst.setRolename(roleRightsRequest.getRolename());
        roleRightsMst.setStatus(roleRightsRequest.getStatus());
        roleRightsMst.setRemarks(roleRightsRequest.getRemarks());
       
        List<UserRightsMST> UserRightsMSTlist= new ArrayList<>();           
        
        List<UserRightsDTO> userRightsDto = roleRightsRequest.getUserRightsDTO();
        for(UserRightsDTO userrightsDto : userRightsDto) {
        	UserRightsMST userRightsMST = new UserRightsMST();
           userRightsMST.setModuleid(userrightsDto.getModuleid());
           userRightsMST.setMastername(userrightsDto.getMastername());
           userRightsMST.setPagetypeid(userrightsDto.getPagetypeid());
           userRightsMST.setAddopn(userrightsDto.getAddopn());
           userRightsMST.setEditopn(userrightsDto.getEditopn());
           userRightsMST.setViewopn(userrightsDto.getViewopn());
           userRightsMST.setDeleteopn(userrightsDto.getDeleteopn());
           
           userRightsMST.setRoleRights(roleRightsMst);
           UserRightsMSTlist.add(userRightsMST);        
        }        
        roleRightsMst.setUserRights(UserRightsMSTlist);
        roleRightsRepository.save(roleRightsMst);
		
	}

	
	@Override
	public List<RoleRightsResponse> getAllRoleRights() {
		
		List<RoleRightsResponse> RoleRightsResponseList = new ArrayList<>();
		List<RoleRightsMST> roleRightsList = roleRightsRepository.findAll();
		for( RoleRightsMST roleRightsMST: roleRightsList) {
			RoleRightsResponse response = new RoleRightsResponse();
			response.setRoleid(roleRightsMST.getRoleid());
			response.setRolecode(roleRightsMST.getRolecode());
			response.setRolename(roleRightsMST.getRolename());
			response.setRemarks(roleRightsMST.getRemarks());
			response.setStatus(roleRightsMST.getStatus());
			
			List<UserRightsResDTO> UserRightsResDTOList = new ArrayList<>();
			List<UserRightsMST> userRightsMSTList = roleRightsMST.getUserRights();
			for( UserRightsMST userRightsMST :userRightsMSTList) {
				UserRightsResDTO userRightsResDTO = new UserRightsResDTO();
				userRightsResDTO.setModuleid(userRightsMST.getModuleid());
				userRightsResDTO.setMastername(userRightsMST.getMastername());
				userRightsResDTO.setPagetypeid(userRightsMST.getPagetypeid());
				userRightsResDTO.setAddopn(userRightsMST.getAddopn());
				userRightsResDTO.setEditopn(userRightsMST.getEditopn());
				userRightsResDTO.setViewopn(userRightsMST.getViewopn());
				userRightsResDTO.setDeleteopn(userRightsMST.getDeleteopn());
				UserRightsResDTOList.add(userRightsResDTO);
			}
			response.setUserRightsResDTO(UserRightsResDTOList);			
			RoleRightsResponseList.add(response);
		}
		return RoleRightsResponseList;
	}

	
	@Override
	@Transactional
	public void updateRoleRights(Long roleid, RoleRightsRequest roleRightsRequest) {

	    RoleRightsMST roleRightsMst = roleRightsRepository.findById(roleid)
	            .orElseThrow(() -> new GlobalException("Role not found with roleid: " + roleid));

	    if (roleRightsRequest.getUserRightsDTO() == null
	            || roleRightsRequest.getUserRightsDTO().isEmpty()) {
	        throw new GlobalException("No modules or masters selected");
	    }

	    if (!roleRightsMst.getRolecode().equals(roleRightsRequest.getRolecode())
	            && roleRightsRepository.existsByRolecode(roleRightsRequest.getRolecode())) {
	        throw new GlobalException("Role already exists with this rolecode");
	    }

	    roleRightsMst.setRolecode(roleRightsRequest.getRolecode());
	    roleRightsMst.setRolename(roleRightsRequest.getRolename());
	    roleRightsMst.setStatus(roleRightsRequest.getStatus());
	    roleRightsMst.setRemarks(roleRightsRequest.getRemarks());

	    roleRightsMst.getUserRights().clear();

	    for (UserRightsDTO userRightsDTO : roleRightsRequest.getUserRightsDTO()) {

	        UserRightsMST userRightsMST = new UserRightsMST();

	        userRightsMST.setModuleid(userRightsDTO.getModuleid());
	        userRightsMST.setMastername(userRightsDTO.getMastername());
	        userRightsMST.setPagetypeid(userRightsDTO.getPagetypeid());
	        userRightsMST.setAddopn(userRightsDTO.getAddopn());
	        userRightsMST.setEditopn(userRightsDTO.getEditopn());
	        userRightsMST.setViewopn(userRightsDTO.getViewopn());
	        userRightsMST.setDeleteopn(userRightsDTO.getDeleteopn());

	        userRightsMST.setRoleRights(roleRightsMst);

	        roleRightsMst.getUserRights().add(userRightsMST);
	    }

	    roleRightsRepository.save(roleRightsMst);
	}



	@Override
	@Transactional
	public void deleteRoleRights(Long roleid) {

	    RoleRightsMST roleRightsMst = roleRightsRepository.findById(roleid)
	            .orElseThrow(() -> new GlobalException("Role not found with roleid: " + roleid));
	    
	    if ("Active".equalsIgnoreCase(roleRightsMst.getStatus())) {
            throw new GlobalException("Cannot delete an active Role. Deactivate the role first.");
        }

	    roleRightsRepository.delete(roleRightsMst);
	}
	
	@Override
	public List<RoleRightsResponse> getActiveRoles() {

	    List<RoleRightsMST> roleList = roleRightsRepository.findByStatus("Active");
	    List<RoleRightsResponse> responseList = new ArrayList<>();
	    for (RoleRightsMST role : roleList) {

	        RoleRightsResponse response = new RoleRightsResponse();
	        response.setRoleid(role.getRoleid());
	        response.setRolecode(role.getRolecode());
	        response.setRolename(role.getRolename());

	        responseList.add(response);
	    }

	    return responseList;
	}
	
	public List<ModuleMasterDTO> getModulesWithMasters() {

	    List<ModuleMST> modules = moduleRepository.findAll();
	    List<PageTypeMST> pageTypes = pageTypeRepository.findAll();

	    List<ModuleMasterDTO> response = new ArrayList<>();

	    for (ModuleMST module : modules) {

	        List<MasterItemDTO> masters = pageTypes.stream()
	                .filter(pageType ->pageType.getModuleid().equals(module.getModuleid()))
	                .map(pageType -> new MasterItemDTO(pageType.getPagetypeid(),pageType.getMastername()))
	                .collect(Collectors.toList());

	        ModuleMasterDTO moduleDTO = new ModuleMasterDTO(module.getModuleid(),module.getModulename(),masters);
	        response.add(moduleDTO);
	    }

	    return response;
	}


	@Override
	public RoleRightsResponse getRoleRightsById(Long roleid) {
		
		RoleRightsMST roleRightsMST = roleRightsRepository.findById(roleid).orElseThrow();
		
		RoleRightsResponse roleRightsResponse = new RoleRightsResponse();
		roleRightsResponse.setRoleid(roleRightsMST.getRoleid());
		roleRightsResponse.setRolecode(roleRightsMST.getRolecode());
		roleRightsResponse.setRolename(roleRightsMST.getRolename());
		roleRightsResponse.setStatus(roleRightsMST.getStatus());
		roleRightsResponse.setRemarks(roleRightsMST.getRemarks());
		
		List<UserRightsResDTO> UserRightsResDTOList = new ArrayList<>();
		List<UserRightsMST> UserRightsMSTList =roleRightsMST.getUserRights();
		for(UserRightsMST userRightsMST: UserRightsMSTList)
		{
			UserRightsResDTO UserRightsResDTO = new UserRightsResDTO();
			UserRightsResDTO.setAddopn(userRightsMST.getAddopn());
			UserRightsResDTO.setDeleteopn(userRightsMST.getDeleteopn());
			UserRightsResDTO.setEditopn(userRightsMST.getEditopn());
			UserRightsResDTO.setMastername(userRightsMST.getMastername());
			UserRightsResDTO.setModuleid(userRightsMST.getModuleid());
			UserRightsResDTO.setPagetypeid(userRightsMST.getPagetypeid());
			UserRightsResDTO.setViewopn(userRightsMST.getViewopn());
			UserRightsResDTOList.add(UserRightsResDTO);
		}
		roleRightsResponse.setUserRightsResDTO(UserRightsResDTOList);
				
		return roleRightsResponse;
	}


}
