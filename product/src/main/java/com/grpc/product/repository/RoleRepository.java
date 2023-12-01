package com.grpc.product.repository;


import com.grpc.product.entity.ERole;
import com.grpc.product.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole eRole);
}
