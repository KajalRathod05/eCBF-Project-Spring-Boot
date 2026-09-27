package com.service.SA;

import java.util.List;

import com.DTOs.SA.RoleRightsRequest;
import com.DTOs.SA.RoleRightsResponse;

public interface RoleRightsService {

	void addRole(RoleRightsRequest roleRightsRequest);

	List<RoleRightsResponse> getAllRoleRights();

	void updateRoleRights(Long roleid, RoleRightsRequest roleRightsRequest);

	void deleteRoleRights(Long roleid);

}
