package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import com.cms.portal.healthcare.entity.Role;
import com.cms.portal.healthcare.enums.RoleEnum;
import com.cms.portal.healthcare.repository.HealthcareProfessionalRepository;
import com.cms.portal.healthcare.repository.HospitalInformationRepository;
import com.cms.portal.healthcare.request.HealthcareProfessionalRegistrationRequest;
import com.cms.portal.healthcare.response.HealthcareProfessionalRegistrationResponse;
import com.cms.portal.healthcare.service.HealthcareProfessionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthcareProfessionalServiceImpl implements HealthcareProfessionalService {

    private final HealthcareProfessionalRepository healthcareProfessionalRepository;

    private final HospitalInformationRepository hospitalInformationRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public HealthcareProfessionalRegistrationResponse register(HealthcareProfessionalRegistrationRequest request) {

        HealthcareProfessional newHealthCareProfessional = HealthcareProfessional.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .email(request.getEmail())
                .username(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getGovernmentId()))
                .governmentId(request.getGovernmentId())
                .degree(request.getDegree())
                .department(request.getDepartment())
                .mobileNum(request.getMobileNum())
                .hospitalInformation(hospitalInformationRepository.findById(request.getHid()).get())
                .role(Set.of(Role.builder().name(RoleEnum.valueOf(request.getRole())).build()))
                .build();

        newHealthCareProfessional = healthcareProfessionalRepository.save(newHealthCareProfessional);

        return  HealthcareProfessionalRegistrationResponse.builder()
                .id(newHealthCareProfessional.getId())
                .name(newHealthCareProfessional.getName())
                .age(newHealthCareProfessional.getAge())
                .gender(newHealthCareProfessional.getGender())
                .email(newHealthCareProfessional.getEmail())
                .username(newHealthCareProfessional.getUsername())
                .governmentId(newHealthCareProfessional.getGovernmentId())
                .degree(newHealthCareProfessional.getDegree())
                .department(newHealthCareProfessional.getDepartment())
                .mobileNum(newHealthCareProfessional.getMobileNum())
                .hospitalInformation(newHealthCareProfessional.getHospitalInformation())
                .role(newHealthCareProfessional.getRole().stream().findFirst().get().getName().toString())
                .build();

    }

    @Override
    public HealthcareProfessional findById(Long id) {
        if (healthcareProfessionalRepository.findById(id).isPresent()) {
            return healthcareProfessionalRepository.findById(id).get();
        }
        return null;
    }

}
