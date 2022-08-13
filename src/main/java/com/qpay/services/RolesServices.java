package com.qpay.services;

import com.qpay.dto.RolesDTO;
import com.qpay.dto.UserDTO;
import com.qpay.entity.Roles;
import com.qpay.entity.User;
import com.qpay.mapper.UserMapperImpl;
import com.qpay.repository.RolesRepo;
import com.qpay.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class RolesServices {


    private RolesRepo rolesRepo;
    private User user;
    private UserRepo userRepo;
    private Roles role;
    private List<RolesDTO> rolesDTO;
    private List<Roles> rolesList;
    private UserMapperImpl userMapper;
    public RolesServices(Roles role, RolesRepo rolesRepo,User user,UserRepo userRepo,List<RolesDTO> rolesDTO,UserMapperImpl userMapper,List<Roles> rolesList){
        this.role = role;
        this.rolesRepo = rolesRepo;
        this.user = user;
        this.userRepo = userRepo;
        this.rolesDTO = rolesDTO;
        this.userMapper = userMapper;
        this.rolesList = rolesList;
    }

    //returns all roles
    public Page  getAllroles(Pageable pageable){
        Page<Roles> roles = rolesRepo.findAll(pageable);
        return roles;
    }


    @Transactional
    public UserDTO removeRole(UserDTO userDTO){
        user = userRepo.findByUsername(userDTO.getUsername());
        if(user != null){
            rolesDTO = userDTO.getRolesDTO();
            Iterator<RolesDTO> rolesIterator = rolesDTO.listIterator();
            while(rolesIterator.hasNext()){
                String roleName = rolesIterator.next().getName();
                role = rolesRepo.findByName(roleName);
                if(role != null){
                    rolesRepo.deleteById(role.getId());
                }
            }
        }
        userDTO = userMapper.UserToUserDTO(user);
        return userDTO;
    }

    public UserDTO addUserRole(UserDTO userDTO){
         User users = userRepo.findByUsername(userDTO.getUsername());
         rolesDTO = userDTO.getRolesDTO();
         if(users != null){
             rolesList = userMapper.RolesDTOtoRoles(rolesDTO);
             for(Roles roles : rolesList){
                 roles.setName(roles.getName());
                 roles.setName(roles.getDescription());
                 roles.setUser(users);
                 rolesRepo.save(roles);
             }
         }
         return userDTO;
    }

}
