package com.DTOs.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMasterDTO {
	
	    private Integer pagetypeid;
	    private String mastername;
	    private String icon;
	    private String filename;

	    private Integer addopn;
	    private Integer editopn;
	    private Integer viewopn;
	    private Integer deleteopn;
}
