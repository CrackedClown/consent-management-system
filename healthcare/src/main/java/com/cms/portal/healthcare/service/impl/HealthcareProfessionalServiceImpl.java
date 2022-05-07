package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import com.cms.portal.healthcare.enums.RoleEnum;
import com.cms.portal.healthcare.repository.HealthcareProfessionalRepository;
import com.cms.portal.healthcare.repository.HospitalInformationRepository;
import com.cms.portal.healthcare.repository.RoleRepository;
import com.cms.portal.healthcare.request.HealthcareProfessionalRegistrationRequest;
import com.cms.portal.healthcare.response.HealthcareProfessionalRegistrationResponse;
import com.cms.portal.healthcare.service.HealthcareProfessionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthcareProfessionalServiceImpl implements HealthcareProfessionalService {

    private final HealthcareProfessionalRepository healthcareProfessionalRepository;

    private final HospitalInformationRepository hospitalInformationRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public HealthcareProfessionalRegistrationResponse register(HealthcareProfessionalRegistrationRequest request) {

        HealthcareProfessional newHealthcareProfessional = HealthcareProfessional.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .email(request.getEmail())
                .username(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getMobileNum()))
                .governmentId(request.getGovernmentId())
                .degree(request.getDegree())
                .department(request.getDepartment())
                .mobileNum(request.getMobileNum())
                .hospitalInformation(hospitalInformationRepository.findById(request.getHid()).get())
                .role(Set.of(roleRepository.findByName(RoleEnum.valueOf(request.getRole())).get()))
                .build();

        newHealthcareProfessional = healthcareProfessionalRepository.save(newHealthcareProfessional);

        return buildHealthcareProfessionalRegistrationResponse(newHealthcareProfessional);

    }

    private HealthcareProfessionalRegistrationResponse buildHealthcareProfessionalRegistrationResponse(HealthcareProfessional healthcareProfessional){
        return HealthcareProfessionalRegistrationResponse.builder()
                .id(healthcareProfessional.getId())
                .name(healthcareProfessional.getName())
                .age(healthcareProfessional.getAge())
                .gender(healthcareProfessional.getGender())
                .email(healthcareProfessional.getEmail())
                .username(healthcareProfessional.getUsername())
                .governmentId(healthcareProfessional.getGovernmentId())
                .degree(healthcareProfessional.getDegree())
                .department(healthcareProfessional.getDepartment())
                .mobileNum(healthcareProfessional.getMobileNum())
                .hospitalInformation(healthcareProfessional.getHospitalInformation())
                .role(healthcareProfessional.getRole().stream().findFirst().get().getName().toString())
                .build();
    }


    @Override
    public HealthcareProfessional findById(Long id) {
        if (healthcareProfessionalRepository.findById(id).isPresent()) {
            return healthcareProfessionalRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public HealthcareProfessionalRegistrationResponse removeHealthcareProfessional(Long healthProfessionalId) {
        HealthcareProfessional healthcareProfessional = healthcareProfessionalRepository.findById(healthProfessionalId).get();
        HealthcareProfessionalRegistrationResponse response = buildHealthcareProfessionalRegistrationResponse(healthcareProfessional);
        healthcareProfessional.setRole(null);
        healthcareProfessionalRepository.save(healthcareProfessional);
        healthcareProfessionalRepository.deleteById(healthProfessionalId);
        return response;
    }

    @Override
    public List<HealthcareProfessionalRegistrationResponse> getHealthcareProfessionalList() {
        List<HealthcareProfessional> healthcareProfessionalList =  healthcareProfessionalRepository.findAll();
        List<HealthcareProfessionalRegistrationResponse> healthcareProfessionalRegistrationResponseList = new ArrayList<>();
        for(HealthcareProfessional healthcareProfessional: healthcareProfessionalList){
            healthcareProfessionalRegistrationResponseList.add(buildHealthcareProfessionalRegistrationResponse(healthcareProfessional));
        }
        return  healthcareProfessionalRegistrationResponseList;
    }

}
