package com.inventariosips.user.model;

import com.inventariosips.userType.model.UserTypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(length = 60, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_user_type", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_USERTYPE"))
    private UserTypeEntity userType;

    @Column(nullable = false, length = 20)
    private String status;
}
