package com.DTOs.SA;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleMasterDTO {

	private Integer moduleid;

    private String modulename;

    private List<MasterItemDTO> masters;

}
