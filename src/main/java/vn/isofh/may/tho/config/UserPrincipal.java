package vn.isofh.may.tho.config;

import vn.isofh.common.config.security.DefaultUserPrincipal;
import vn.isofh.common.util.NumberUtil;
import vn.isofh.may.tho.service.UserServiceImpl;

public class UserPrincipal extends DefaultUserPrincipal {

  public Long getDonViId() {
    return NumberUtil.toLong(getAdditionalInformation().get(UserServiceImpl.DON_VI));
  }
}
