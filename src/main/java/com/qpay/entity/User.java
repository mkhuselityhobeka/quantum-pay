package com.qpay.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.qpay.dto.RolesDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String email;
    @NonNull
    private String status;
    @NonNull
    private String password;
    private String lastlogon;
    @JsonIgnore
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "id",orphanRemoval = true)
    @ToString.Exclude //excluding lazy attributes
    private List<Roles>roles;


    public void addRoles(Roles roles){
        this.roles.add(roles);
        roles.setUser(this);
    }

    public void removeRoles(Roles roles){
        roles.setUser(null);
        this.roles.remove(roles);
    }
    public void removeRoles(){
        Iterator<Roles>iterator = this.roles.listIterator();
        while (iterator.hasNext()){
              Roles role = iterator.next();
              role.setUser(null);
              iterator.remove();

        }
    }

}
