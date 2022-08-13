package com.qpay.web;

import com.qpay.dto.UserDTO;
import com.qpay.entity.Roles;
import com.qpay.entity.User;
import com.qpay.exceptions.ResourceNotFoundException;
import com.qpay.services.RolesServices;
import com.qpay.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("qpay/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("create/user")
    public ResponseEntity<UserDTO>createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
    @PutMapping("disable/user")
    public ResponseEntity<UserDTO>disableAccount(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.disableUser(userDTO),HttpStatus.OK);
    }
    @PutMapping("enable/user")
    public ResponseEntity<UserDTO>enableAccount(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.enableUser(userDTO),HttpStatus.OK);
    }
    @PutMapping("update/user")
    public  ResponseEntity<UserDTO>updateAccount(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUser(userDTO),HttpStatus.OK);
    }
    @GetMapping("get/user/{username}")
    public ResponseEntity<UserDTO>getUser(@PathVariable String username) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.findUserByUsername(username),HttpStatus.OK);
    }
    @GetMapping("get/users")
    public ResponseEntity<Page<User>> getAllUserAccounts(Pageable pageable){
        return  new ResponseEntity<>(userService.getAllUsers(pageable),HttpStatus.OK);
    }

}
