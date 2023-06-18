package com.qpay.services;

import com.qpay.dto.RolesDTO;
import com.qpay.dto.UserDTO;
import com.qpay.entity.Roles;
import com.qpay.entity.User;
import com.qpay.exceptions.ResourceNotFoundException;
import com.qpay.mapper.UserMapperImpl;
import com.qpay.repository.RolesRepo;
import com.qpay.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
//import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Qualifier("UserRepoImpl")
@Slf4j
public class UserService{

    private UserRepo userRepo;
    private  RolesRepo rolesRepo;
    //private DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
    private User user;
    private UserDTO userDTO;
    private List<Roles> roles = new ArrayList<>();
    private List<RolesDTO> rolesDTO;
    private RolesDTO roleDTO;
    private Roles role;
    private UserMapperImpl userMapper;

    public UserService(UserRepo userRepo, User user, List<Roles>  roles,Roles role,
                       UserMapperImpl userMapper,UserDTO userDTO,
                       List<RolesDTO> rolesDTO,RolesDTO roleDTO,RolesRepo rolesRepo) {
        this.userRepo = userRepo;
        this.user = user;
        this.roles = roles;
        this.userMapper = userMapper;
        this.userDTO = userDTO;
        this.role = role;
        this.userDTO = userDTO;
        this.roleDTO = roleDTO;
        this.rolesRepo = rolesRepo;
    }


    //create user
    public UserDTO createUser(UserDTO userDTO) {


        user = userMapper.UserDTOtoUser(userDTO);
        roles = userMapper.RolesDTOtoRoles(userDTO.getRolesDTO());
        String username = user.getUsername();
        roles.forEach(role -> role.setUser(user));
        roles.forEach(role -> user.setRoles(roles));
         user = userRepo.save(user);
        rolesDTO = userMapper.RolesToRolesDTO(roles);
        userDTO = userMapper.UserToUserDTO(user);
        userDTO.setRolesDTO(rolesDTO);
        return userDTO;
    }

    //find user by username before create
    public Boolean findByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    //find user by username
    public UserDTO findUserByUsername(String username){
        user = userRepo.findByUsername(username);
       // log.debug("user is" + user);
        if(user == null){
            throw new ResourceNotFoundException(User.class,"username", username);

        }
        roles = user.getRoles();
        rolesDTO = userMapper.RolesToRolesDTO(roles);
        userDTO = userMapper.UserToUserDTO(user);
        userDTO.setRolesDTO(rolesDTO);
        return userDTO;

    }

    public Page getAllUsers(Pageable pageable) {
        Page<User> users = userRepo.findAll(pageable);
        return users;
    }

    //Disable user
    public UserDTO disableUser(UserDTO userDTO){
         UserDTO dto = findUserByUsername(userDTO.getUsername());
            if (dto == null) {
                throw new ResourceNotFoundException(User.class,"username", dto.getUsername());
            }
            user = userMapper.UserDTOtoUser(dto);
            Optional<User> optionalUser = userRepo.findById(user.getId());
            if(optionalUser.isPresent()){
                rolesDTO = dto.getRolesDTO();
                roles = userMapper.RolesDTOtoRoles(rolesDTO);
                user.setStatus(userDTO.getStatus());
                roles.forEach(roles1 -> roles1.setUser(user));
                roles.forEach(roles1 -> user.setRoles(roles));
                user = userRepo.save(user);
            }
            dto = userMapper.UserToUserDTO(user);
            dto.setRolesDTO(rolesDTO);


        return dto;
    }

    //enable user
    public UserDTO enableUser(UserDTO userDTO) {
        UserDTO dto = findUserByUsername(userDTO.getUsername());
        if (dto != null) {
            throw new ResourceNotFoundException(User.class,"username", dto.getUsername());
        }
            user = userMapper.UserDTOtoUser(dto);
            Optional<User> optionalUser = userRepo.findById(user.getId());
            if(optionalUser.isPresent()){
                rolesDTO = dto.getRolesDTO();
                roles = userMapper.RolesDTOtoRoles(rolesDTO);
                user.setStatus(userDTO.getStatus());
                roles.forEach(roles1 -> roles1.setUser(user));
                roles.forEach(roles1 -> user.setRoles(roles));
                user = userRepo.save(user);
            }
            dto = userMapper.UserToUserDTO(user);
            dto.setRolesDTO(rolesDTO);


        return dto;
    }

    // remove role
    //update user details
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {

               // log.debug("user dto " + userDTO);
               // log.debug("roles dto  " + userDTO.getRolesDTO());
                roles = userMapper.RolesDTOtoRoles(userDTO.getRolesDTO());
               // log.debug("roles   " + roles.toString());
               User users  = userRepo.findByUsername(userDTO.getUsername());
              if(userRepo.findById(users.getId()).isPresent()){

                    roles.forEach(roles1 -> role.setUser(users));
                    roles.forEach(roles1 -> users.setRoles(roles));
                    userRepo.save(user);
              }

                return userDTO;
            }


}
