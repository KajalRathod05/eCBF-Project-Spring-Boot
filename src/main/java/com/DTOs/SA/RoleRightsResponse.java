package com.DTOs.SA;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleRightsResponse {

	private Long roleid;
	private String rolecode;
    private String rolename;
    private String status;
    private String remarks;
    
    private List<UserRightsResDTO> UserRightsResDTO;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRightsResDTO {
        private Integer moduleid;
        private String mastername;
        private Integer addopn;
        private Integer editopn;
        private Integer viewopn;
        private Integer deleteopn;
    }
}
