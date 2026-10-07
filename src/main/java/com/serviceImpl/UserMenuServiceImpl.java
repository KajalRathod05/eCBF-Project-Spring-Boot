package com.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.DTOs.auth.MenuMasterDTO;
import com.DTOs.auth.MenuModuleDTO;
import com.repository.UserRightsRepository;
import com.service.UserMenuService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMenuServiceImpl implements UserMenuService{

	private final UserRightsRepository userRightsRepository;

    
	public List<MenuModuleDTO> getUserMenu(Integer userid) {

        List<Object[]> rows = userRightsRepository.findUserMenu(userid);

        Map<Integer, MenuModuleDTO> moduleMap = new LinkedHashMap<>();

        for (Object[] row : rows) {

            Integer moduleid = ((Number) row[0]).intValue();
            String modulename = (String) row[1];
            String moduleicon = (String) row[2];

            Integer pagetypeid = ((Number) row[3]).intValue();
            String mastername = (String) row[4];
            String mastericon = (String) row[5];
            String filename = (String) row[6];

            Integer addopn = ((Number) row[7]).intValue();
            Integer editopn = ((Number) row[8]).intValue();
            Integer viewopn = ((Number) row[9]).intValue();
            Integer deleteopn = ((Number) row[10]).intValue();

            MenuMasterDTO master = new MenuMasterDTO( pagetypeid, mastername,mastericon,filename,
            		addopn,editopn,viewopn,deleteopn);

            MenuModuleDTO module = moduleMap.get(moduleid);

            if (module == null) {

                List<MenuMasterDTO> masters = new ArrayList<>();
                masters.add(master);
                module = new MenuModuleDTO(moduleid,modulename,moduleicon,masters);

                moduleMap.put(moduleid, module);

            } else {

                module.getMasters().add(master);
            }
        }

        return new ArrayList<>(moduleMap.values());
    }
}
