package vn.isofh.may.tho.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmDonViTinhEntity;
import vn.isofh.may.tho.dao.repository.DmDonViTinhRepository;
import vn.isofh.may.tho.dto.DmDonViTinhDTO;
import vn.isofh.may.tho.exception.DmDonViTinhException;

@Service
public class DmDonViTinhServiceImpl extends
    AbstractDmService<DmDonViTinhEntity, DmDonViTinhDTO, DmDonViTinhRepository> implements
    DmDonViTinhService {

  @Autowired
  private DmDonViTinhRepository repository;

  @Override
  protected DmDonViTinhRepository getRepository() {
    return repository;
  }

  @Override
  protected DmDonViTinhEntity beforeSave(DmDonViTinhEntity model, DmDonViTinhDTO dto) {
    validateTen(model);

    generateMa(model);

    return super.beforeSave(model, dto);
  }

  private void validateTen(DmDonViTinhEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new DmDonViTinhException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new DmDonViTinhException.DuplicateName(new Object[]{model.getTen()});
    }
  }

  private final String PREFIX = "ĐVT";

  private void generateMa(DmDonViTinhEntity model) {
    if (!StringUtils.isBlank(model.getMa())) {
      return;
    }

    String currentMa = repository.getCurrentMa(PREFIX);
    Integer current = Integer.valueOf(0);

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    model.setMa(PREFIX + (current < 9 ? "0" : "") + (current + 1));
  }
}
