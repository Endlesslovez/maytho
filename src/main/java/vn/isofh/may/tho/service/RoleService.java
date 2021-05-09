package vn.isofh.may.tho.service;

import java.util.List;
import vn.isofh.may.tho.dao.model.UserEntity;
import vn.isofh.may.tho.dto.RoleDTO;

public interface RoleService extends DmService<RoleDTO> {

  void addRole(UserEntity user, Long roleId);

  void addRoles(UserEntity user, List<Long> roleId);
}

