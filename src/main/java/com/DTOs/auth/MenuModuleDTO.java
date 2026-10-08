package com.DTOs.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModuleDTO {

	private Integer moduleid;
    private String modulename;
    private String icon;
    private List<MenuMasterDTO> masters;
}
