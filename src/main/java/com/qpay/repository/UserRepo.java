package com.qpay.repository;

import com.qpay.dto.UserDTO;
import com.qpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends PagingAndSortingRepository<User,Long> {
    User findByUsername (String username);
    ;
}
