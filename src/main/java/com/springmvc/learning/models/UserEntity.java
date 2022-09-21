package com.springmvc.learning.models;

import com.springmvc.learning.validation.PasswordValid;
import com.springmvc.learning.validation.UniqueEmail;
import com.springmvc.learning.validation.UniqueUsername;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.springmvc.learning.constants.ValidationConstants.*;


@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@PasswordValid
public class UserEntity {

    public UserEntity(String firstName, String lastName, String userName, String email, String password,
                      LocalDate birthday, RoleEntity role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Pattern(regexp = FIRST_LAST_NAME_PATTERN, message = "Invalid first name")
    private String firstName;

    @NotNull
    @Pattern(regexp = FIRST_LAST_NAME_PATTERN, message = "Invalid last name")
    private String lastName;

    @Column(unique = true)
    @Pattern(regexp = USERNAME_PATTERN, message = "Only letters and digits are allowed")
    @Size(min = 3, max = 15, message = "Enter value between 3 and 10")
    @UniqueUsername
    private String username;

    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email is mandatory")
    @Column(unique = true)
    @UniqueEmail
    private String email;

    private String password;

    @Transient
    private String passwordConfirm;

    @Past(message = "Input your date of birthday")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

}
