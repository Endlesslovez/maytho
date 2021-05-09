package vn.isofh.may.tho.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmNguonVonEntity;
import vn.isofh.may.tho.dao.repository.DmNguonVonRepository;
import vn.isofh.may.tho.dto.DmNguonVonDTO;
import vn.isofh.may.tho.exception.DmNguonVonException;

@Service
public class DmNguonVonServiceImpl extends
    AbstractDmService<DmNguonVonEntity, DmNguonVonDTO, DmNguonVonRepository> implements
    DmNguonVonService {

  private final String PREFIX = "NV";
  @Autowired
  private DmNguonVonRepository repository;

  @Override
  protected DmNguonVonRepository getRepository() {
    return repository;
  }

  @Override
  protected DmNguonVonEntity beforeSave(DmNguonVonEntity model, DmNguonVonDTO dto) {
    validateTen(model);

    generateMa(model);

    return super.beforeSave(model, dto);
  }

  private void validateTen(DmNguonVonEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new DmNguonVonException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new DmNguonVonException.DuplicateName(new Object[]{model.getTen()});
    }
  }

  private void generateMa(DmNguonVonEntity model) {
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
}
