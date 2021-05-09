package vn.isofh.may.tho.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmTrangThaiEntity;
import vn.isofh.may.tho.dao.repository.DmTrangThaiRepository;
import vn.isofh.may.tho.dto.DmTrangThaiDTO;
import vn.isofh.may.tho.exception.TrangThaiException;

@Service
public class DmTrangThaiServiceImpl extends
    AbstractDmService<DmTrangThaiEntity, DmTrangThaiDTO, DmTrangThaiRepository> implements
    DmTrangThaiService {

  private final String PREFIX = "TT";

  @Autowired
  private DmTrangThaiRepository repository;

  @Override
  protected DmTrangThaiRepository getRepository() {
    return repository;
  }

  @Override
  protected void specificMapToDTO(DmTrangThaiEntity entity, DmTrangThaiDTO dto) {
    super.specificMapToDTO(entity, dto);
  }

  @Override
  protected void specificMapToEntity(DmTrangThaiDTO dto, DmTrangThaiEntity entity) {
    super.specificMapToEntity(dto, entity);
  }

  @Override
  protected DmTrangThaiEntity beforeSave(DmTrangThaiEntity model, DmTrangThaiDTO dto) {
    validateTen(model);

    generateMa(model);

    return super.beforeSave(model, dto);
  }

  private void generateMa(DmTrangThaiEntity model) {
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

  private void validateTen(DmTrangThaiEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new TrangThaiException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new TrangThaiException.DuplicateName(new Object[]{model.getTen()});
    }
  }
}
