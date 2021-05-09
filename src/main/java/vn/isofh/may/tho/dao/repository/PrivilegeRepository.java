package vn.isofh.may.tho.dao.repository;

import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.PrivilegeEntity;
import vn.isofh.may.tho.dto.PrivilegeDTO;

@Repository
public interface PrivilegeRepository extends DmRepository<PrivilegeEntity, PrivilegeDTO, Long> {

}