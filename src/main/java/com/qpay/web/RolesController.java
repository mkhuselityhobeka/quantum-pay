package com.qpay.web;

import com.qpay.dto.UserDTO;
import com.qpay.services.RolesServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("qpay/v1")
public class RolesController {

    private RolesServices rolesServices;

    public RolesController(RolesServices rolesServices){
        this.rolesServices = rolesServices;
    }

    @GetMapping("get/roles")
    public ResponseEntity<Page> returnAllRoles(Pageable pageable){
        return new ResponseEntity<>(rolesServices.getAllroles(pageable), HttpStatus.OK);
    }

    @DeleteMapping("remove/role")
    public  ResponseEntity<UserDTO> removeRole(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(rolesServices.removeRole(userDTO),HttpStatus.OK);
    }
    @PutMapping("add/role")
    public  ResponseEntity<UserDTO>addRolesToUser(@RequestBody UserDTO userDTO){
        return  new ResponseEntity<>(rolesServices.addUserRole(userDTO),HttpStatus.OK);
    }
}
