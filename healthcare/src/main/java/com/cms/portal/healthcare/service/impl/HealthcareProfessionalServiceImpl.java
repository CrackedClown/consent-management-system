package com.cms.portal.healthcare.service.impl;

import com.cms.portal.healthcare.entity.HealthcareProfessional;
import com.cms.portal.healthcare.entity.HospitalInformation;
import com.cms.portal.healthcare.repository.HealthcareProfessionalRepository;
import com.cms.portal.healthcare.repository.HospitalInformationRepository;
import com.cms.portal.healthcare.request.HealthcareProfessionalRegistrationRequest;
import com.cms.portal.healthcare.response.HealthcareProfessionalRegistrationResponse;
import com.cms.portal.healthcare.service.HealthcareProfessionalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthcareProfessionalServiceImpl implements HealthcareProfessionalService {

    private final HealthcareProfessionalRepository healthcareProfessionalRepository;

    private final HospitalInformationRepository hospitalInformationRepository;

    @Override
    public HealthcareProfessionalRegistrationResponse register(HealthcareProfessionalRegistrationRequest request) {

        HealthcareProfessional newHealthCareProfessional = HealthcareProfessional.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .email(request.getEmail())
                .password(request.getPassword())
                .governmentId(request.getGovernmentId())
                .degree(request.getDegree())
                .department(request.getDepartment())
                .mobileNum(request.getMobileNum())
                .hospitalInformation(hospitalInformationRepository.getById(request.getHid()))
                .build();

        newHealthCareProfessional = healthcareProfessionalRepository.save(newHealthCareProfessional);

        HealthcareProfessionalRegistrationResponse response = HealthcareProfessionalRegistrationResponse.builder()
                .id(newHealthCareProfessional.getId())
                .name(newHealthCareProfessional.getName())
                .age(newHealthCareProfessional.getAge())
                .gender(newHealthCareProfessional.getGender())
                .email(newHealthCareProfessional.getEmail())
                .governmentId(newHealthCareProfessional.getGovernmentId())
                .degree(newHealthCareProfessional.getDegree())
                .department(newHealthCareProfessional.getDepartment())
                .mobileNum(newHealthCareProfessional.getMobileNum())
                .hospitalInformation(newHealthCareProfessional.getHospitalInformation())
                .build();

        return response;
    }



    @Override
    public HealthcareProfessional findById(Long id) {
        if(healthcareProfessionalRepository.findById(id).isPresent()) {
            return healthcareProfessionalRepository.findById(id).get();
        }
        return null;
    }
}
