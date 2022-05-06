package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.Role;
import com.cms.portal.healthcare.enums.RoleEnum;
import com.cms.portal.healthcare.repository.RoleRepository;
import com.cms.portal.healthcare.service.RoleService;
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
                .name(RoleEnum.ADMIN)
                .build();
        roleRepository.save(role);

        role = Role.builder()
                .name(RoleEnum.DOCTOR)
                .build();
        roleRepository.save(role);

        role = Role.builder()
                .name(RoleEnum.NURSE)
                .build();
        roleRepository.save(role);

        role = Role.builder()
                .name(RoleEnum.CM)
                .build();
        roleRepository.save(role);
    }
}
