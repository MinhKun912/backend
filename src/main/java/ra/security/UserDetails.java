package ra.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ra.model.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private Long userID;
    private String userName;
    @JsonIgnore
    private String password;
    private boolean userStatus;
    private int age;
    private String name;
    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    public static UserDetails mapUserToUserDetails(User user){
        List<GrantedAuthority> list=user.getListRole().stream().map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name())).collect(Collectors.toList());
        return new UserDetails(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getStatus(),
                user.getAge(),
                user.getName(),
                user.getEmail(),
                list
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
