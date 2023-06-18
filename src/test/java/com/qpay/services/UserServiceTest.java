package com.qpay.services;

import com.qpay.dto.RolesDTO;
import com.qpay.dto.UserDTO;
import com.qpay.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(basePackages = {"com.qpay"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest {

   @Autowired @Qualifier("UserRepoImpl")
     UserService userService;
    RolesDTO dtoRoles;
    List<RolesDTO> rolesDTOList;
    UserDTO dtoUser;

    @BeforeEach
    void setUp() {
        //given - setup
         dtoRoles  = RolesDTO.builder().name("tester").description("Testing").build();
         rolesDTOList  = List.of(dtoRoles);

        dtoUser = UserDTO.builder().firstname("Mkhuseli").lastname("Tyhobeka").username("mkhuselityhobeka@standardbank.co.za")
                .password("mkhuselityhobeka@88").lastlogon("20230810").idNumber("8454564654564").email("mkhuselityhobeka@standardbank.co.za")
                .status("active").rolesDTO(rolesDTOList).build();
    }

    @Test
    void createUser() {

        //When - action taken
        userService.createUser(dtoUser);
        //the - very result
        assertEquals(dtoUser.getFirstname(),"Mkhuseli");
    }

    @Test
    void findByUsername() {
        //when - action taken
     userService.createUser(dtoUser);
      //then - verify
      assertEquals(userService.findByUsername("mkhuselityhobeka@standardbank.co.za"),true);

    }


    @Test
    void getAllUsers() {

    }

    @Test
    void disableUser() {
        userService.createUser(dtoUser);

        //given - setup
        dtoRoles  = RolesDTO.builder().name("tester").description("Testing").build();
        rolesDTOList  = List.of(dtoRoles);

        dtoUser = UserDTO.builder().firstname("Mkhuseli").lastname("Tyhobeka").username("mkhuselityhobeka@standardbank.co.za")
                .password("mkhuselityhobeka@88").lastlogon("20230810").idNumber("8454564654564").email("mkhuselityhobeka@standardbank.co.za")
                .status("disabled").rolesDTO(rolesDTOList).build();
        //when
        userService.disableUser(dtoUser);
        assertEquals(dtoUser.getStatus(),"disabled");

    }

    @Test
    void enableUser() {


        //given - setup
        dtoRoles  = RolesDTO.builder().name("tester").description("Testing").build();
        rolesDTOList  = List.of(dtoRoles);

        dtoUser = UserDTO.builder().firstname("Mkhuseli").lastname("Tyhobeka").username("mkhuselityhobeka@standardbank.co.za")
                .password("mkhuselityhobeka@88").lastlogon("20230810").idNumber("8454564654564").email("mkhuselityhobeka@standardbank.co.za")
                .status("disabled").rolesDTO(rolesDTOList).build();

        userService.createUser(dtoUser);
        dtoUser = UserDTO.builder().firstname("Mkhuseli").lastname("Tyhobeka").username("mkhuselityhobeka@standardbank.co.za")
                .password("mkhuselityhobeka@88").lastlogon("20230810").idNumber("8454564654564").email("mkhuselityhobeka@standardbank.co.za")
                .status("active").rolesDTO(rolesDTOList).build();
        //when
        userService.disableUser(dtoUser);
        assertEquals(dtoUser.getStatus(),"active");
    }

    @Test
    void updateUser() {
    }


}