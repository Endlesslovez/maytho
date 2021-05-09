package vn.isofh.may.tho.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.CastUtils;
import vn.isofh.may.tho.dao.model.DmEntity;
import vn.isofh.may.tho.dao.repository.DmRepository;
import vn.isofh.may.tho.dto.DmBaseDTO;
import vn.isofh.may.tho.dto.DmDTO;
import vn.isofh.may.tho.exception.DataException;

public abstract class AbstractDmService<Entity extends DmEntity, DTO extends DmDTO, Repository extends DmRepository<Entity, DTO, Long>> extends
    AbstractService<Entity, DTO, Repository> implements DmService<DTO> {

  @Override
  @Cacheable
  public <X extends DmBaseDTO> X getBaseDTOById(Long id) {
    if (id == null || id.compareTo(0L) <= 0) {
      return null;
    }

    return CastUtils.cast(getRepository().findBaseDTOById(id).orElseThrow(
        () -> new DataException.NotFoundEntityById(id, getName())));
  }

  @Override
  @Cacheable
  public Long getIdByMa(String ma) {
    return getRepository().findIdByMa(ma)
        .orElseThrow(() -> new DataException.NotFoundEntityByMa(ma, getName()));
  }

  @Override
  @Cacheable
  public Long getIdByTen(String ten) {
    return getRepository().findIdByTen(ten)
        .orElseThrow(() -> new DataException.NotFoundEntityByTen(ten, getName()));
  }
}