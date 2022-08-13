package com.qpay.mapper;


import com.qpay.dto.RolesDTO;
import com.qpay.dto.UserDTO;
import com.qpay.entity.Roles;
import com.qpay.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User UserDTOtoUser(UserDTO userDTO);
    List<Roles> RolesDTOtoRoles(List<RolesDTO> rolesDTO);
    UserDTO UserToUserDTO(User user);
    List<RolesDTO> RolesToRolesDTO(List<Roles> roles);
}
