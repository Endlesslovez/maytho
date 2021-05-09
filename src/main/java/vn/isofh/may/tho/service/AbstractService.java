package vn.isofh.may.tho.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.isofh.common.dao.model.BaseEntity;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.common.dto.BaseDTO;
import vn.isofh.common.service.AbstractBaseService;
import vn.isofh.may.tho.config.UserPrincipal;

public abstract class AbstractService<X extends BaseEntity, Y extends BaseDTO, Z extends BaseRepository<X, Y, Long>> extends
    AbstractBaseService<X, Y, Z> {

  @Override
  protected UserPrincipal getUserPrincipal() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return null;
    }

    return (UserPrincipal) authentication.getPrincipal();
  }

  public Long getDonViId() {
    UserPrincipal user = getUserPrincipal();

    if (user == null) {
      return null;
    }

    return user.getDonViId();
  }
}