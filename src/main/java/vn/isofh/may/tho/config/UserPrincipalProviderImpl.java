package vn.isofh.may.tho.config;

import org.springframework.stereotype.Component;
import vn.isofh.common.config.security.DefaultUserPrincipal;
import vn.isofh.common.config.security.UserPrincipalProvider;

@Component
public class UserPrincipalProviderImpl implements UserPrincipalProvider {

  @Override
  public DefaultUserPrincipal getUserPrincipal() {
    return new UserPrincipal();
  }
}
