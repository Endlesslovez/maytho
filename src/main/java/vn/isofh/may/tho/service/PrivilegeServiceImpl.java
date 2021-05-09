package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.common.service.AbstractBaseService;
import vn.isofh.may.tho.dao.model.PrivilegeEntity;
import vn.isofh.may.tho.dao.repository.PrivilegeRepository;
import vn.isofh.may.tho.dto.PrivilegeDTO;

@Service
public class PrivilegeServiceImpl extends
    AbstractDmService<PrivilegeEntity, PrivilegeDTO, PrivilegeRepository> implements
    PrivilegeService {

  @Autowired
  private PrivilegeRepository repository;

  @Override
  protected PrivilegeRepository getRepository() {
    return repository;
  }
}
