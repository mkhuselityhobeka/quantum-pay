package com.qpay.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO {

    private Long id;
    private String name;
    private String description;
    private UserDTO userDTO;
}
