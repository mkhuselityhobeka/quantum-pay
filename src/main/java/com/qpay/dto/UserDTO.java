package com.qpay.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String status;
    private String password;
    private String lastlogon;
    private List<RolesDTO>rolesDTO;
}
