package com.serviceImpl.SA;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.DTOs.SA.RoleRightsRequest;
import com.DTOs.SA.RoleRightsRequest.UserRightsDTO;
import com.DTOs.SA.RoleRightsResponse;
import com.DTOs.SA.RoleRightsResponse.UserRightsResDTO;
import com.exception.GlobalException;
import com.model.SA.UserRightsMST;
import com.model.SA.RoleRightsMST;
import com.repository.SA.RoleRightsRepository;
import com.service.SA.RoleRightsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleRightsServiceImpl implements RoleRightsService{
	
	private final RoleRightsRepository roleRightsRepository;

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


}
