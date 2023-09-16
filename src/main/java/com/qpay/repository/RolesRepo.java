package com.qpay.repository;

import com.qpay.entity.Roles;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("RolesRepo")
public interface RolesRepo extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
