package com.cms.portal.healthcare.repository;

import com.cms.portal.healthcare.entity.Role;
import com.cms.portal.healthcare.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);

}
