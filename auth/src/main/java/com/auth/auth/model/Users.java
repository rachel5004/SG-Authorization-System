package com.auth.auth.model;

import com.auth.auth.common.RoleCodeConverter;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table
public class Users extends BaseTime{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false, length = 254)
    private String password;

    @Column
    private boolean emailVerified;

//    @Enumerated(EnumType.STRING)
    @Convert(converter = RoleCodeConverter.class)
    @Column(columnDefinition = "int2")
    private Role role;

    @Builder
    public Users(final UUID id, final String name, final String email, final String password,Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}