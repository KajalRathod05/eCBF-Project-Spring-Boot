package com.DTOs.SA;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRightsRequest {

	private String rolecode;
    private String rolename;
    //private Integer moduleid;
    //private String mastername;
    private String status;
    private String remarks;
    private List<UserRightsDTO> UserRightsDTO;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRightsDTO {
        private Integer moduleid;
        private String mastername;
        private Integer addopn;
        private Integer editopn;
        private Integer viewopn;
        private Integer deleteopn;
    }
}
