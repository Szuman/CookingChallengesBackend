package com.cookingchallenges.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=30)
    private String name;

    @Column(nullable = false, length=50, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "enum ('BEGINNER', 'NOVICE', 'EXPERT', 'PRO', 'CHEF', 'MASTERCHEF', 'FAMOUS', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private Grade grade;

//    @Size(max = 255, message = "Too long about field (max 255 characters)")
    @Column
    private String about;

    User(String name, String email, String about) {
        this.name = name;
        this.email = email;
        this.grade = Grade.BEGINNER;
        this.about = about;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
