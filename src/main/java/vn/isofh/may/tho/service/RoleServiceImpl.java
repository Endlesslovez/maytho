package vn.isofh.may.tho.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.RoleEntity;
import vn.isofh.may.tho.dao.model.UserEntity;
import vn.isofh.may.tho.dao.repository.RoleRepository;
import vn.isofh.may.tho.dto.RoleDTO;
import vn.isofh.may.tho.exception.RoleException;

@Service
public class RoleServiceImpl extends
    AbstractDmService<RoleEntity, RoleDTO, RoleRepository> implements RoleService {

  private final String PREFIX = "LTK";

  public final static String ROLE_BO_Y_TE = "bo_y_te";
  public final static String ROLE_CO_SO_Y_TE = "co_so_y_te";
  public final static String ROLE_CONG_TY = "cong_ty";
  public final static String ROLE_VU_TTB = "vu_ttb";
  public final static String ROLE_SO_Y_TE = "so_y_te";

  @Autowired
  private RoleRepository repository;

  @Override
  protected RoleRepository getRepository() {
    return repository;
  }

  @Override
  protected RoleEntity beforeSave(RoleEntity model, RoleDTO dto) {
    validateTen(model);

    generateMa(model);

    return super.beforeSave(model, dto);
  }

  private void validateTen(RoleEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new RoleException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new RoleException.DuplicateName(new Object[]{model.getTen()});
    }
  }

  private void generateMa(RoleEntity model) {
    if (!StringUtils.isBlank(model.getMa())) {
      return;
    }

    String currentMa = repository.getCurrentMa(PREFIX);
    int current = 0;

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    model.setMa(PREFIX + (current < 9 ? "0" : "") + (current + 1));
  }

  @Override
  public void addRole(UserEntity user, Long roleId) {
    if (roleId == null) {
      user.setRoles(null);
      return;
    }

    List<RoleEntity> roles = user.getRoles();
    if (roles == null || roles.size() != 1 || !roleId.equals(roles.get(0).getId())) {
      roles = new ArrayList<>();
    }

    if (roles.size() <= 0 ) {
      roles.add(getById(roleId));
    }

    user.setRoles(roles);
  }

  @Override
  public void addRoles(UserEntity user, List<Long> roleIds) {
    if(roleIds!=null && roleIds.size()>0)
    {
      List<RoleEntity> roles = user.getRoles();
      if(roles == null ) roles = new ArrayList<>();
      for (Long roleId:roleIds){
        roles.add(getById(roleId));
      }
      user.setRoles(roles);
    }
  }
}
