package xyz.obeyblessingportfolio.data;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.obeyblessingportfolio.data.user.UserProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {

    private final UserProfile userProfile;

    public CustomUserDetails(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority dev=new SimpleGrantedAuthority("ROLE_DEVELOPER");
        SimpleGrantedAuthority owner = new SimpleGrantedAuthority("ROLE_OWNER");

        if(Objects.equals("obey", userProfile.getUsername().toLowerCase())){
            authorities.add(dev);
            authorities.add(owner);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userProfile.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return userProfile.getUsername();
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
        return true;    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
