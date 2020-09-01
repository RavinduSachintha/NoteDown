package com.rs.notedown.security;

import com.rs.notedown.models.AppUser;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@SuppressWarnings({"UnusedDeclaration"})
public class UserPrincipal implements UserDetails {
  private long id;
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(AppUser appUser) {
    List<GrantedAuthority> authorities =
        new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority(
            appUser.getRole().getRoleName().name())));
    return new UserPrincipal(appUser.getId(), appUser.getUsername(),
                             appUser.getPassword(), authorities);
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    UserPrincipal that = (UserPrincipal)obj;
    return Objects.equals(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }
}
