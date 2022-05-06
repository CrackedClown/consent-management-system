package com.cms.portal.patient.service.impl;

import com.cms.portal.patient.entity.Role;
import com.cms.portal.patient.enums.RoleEnum;
import com.cms.portal.patient.repository.RoleRepository;
import com.cms.portal.patient.service.RoleService;
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

        if(roleRepository.findById(1L).isPresent()){
            return;
        }

        Role role = Role.builder()
                .name(RoleEnum.PATIENT)
                .build();
        roleRepository.save(role);

        role = Role.builder()
                .name(RoleEnum.CM)
                .build();
        roleRepository.save(role);
    }
}
