package com.cms.cm.response;

import com.cms.cm.entity.Role;
import com.cms.cm.enums.RoleEnum;
import com.cms.cm.repository.RoleRepository;
import com.cms.cm.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addRolesOnStartup() {

        if (roleRepository.findById(1L).isPresent()) {
            return;
        }

        Role role = Role.builder()
                .name(RoleEnum.PATIENT_PORTAL)
                .build();
        roleRepository.save(role);

        role = Role.builder()
                .name(RoleEnum.HEALTHCARE_PORTAL)
                .build();
        roleRepository.save(role);
    }
}
