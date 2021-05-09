package vn.isofh.may.tho.service;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmThietBiEntity;
import vn.isofh.may.tho.dao.repository.DmThietBiRepository;
import vn.isofh.may.tho.dto.DmThietBiDTO;
import vn.isofh.may.tho.enums.LoaiThietBiEnum;
import vn.isofh.may.tho.exception.DmThietBiException;

@Service
public class DmThietBiServiceImpl extends
    AbstractDmService<DmThietBiEntity, DmThietBiDTO, DmThietBiRepository> implements
    DmThietBiService {

  @Autowired
  private DmThietBiRepository repository;

  @Autowired
  private UserService userService;

  @Autowired
  private DmNhomThietBiService dmNhomThietBiService;

  @Override
  protected DmThietBiRepository getRepository() {
    return repository;
  }

  @Override
  protected void specificMapToDTO(DmThietBiEntity entity, DmThietBiDTO dto) {
    super.specificMapToDTO(entity, dto);

    dto.setNguoiSua(userService.findById(entity.getCreatedBy()).getFullName());
    dto.setNguoiTao(userService.findById(entity.getUpdatedBy()).getFullName());
    dto.setDmNhomTbyt(dmNhomThietBiService.getBaseDTOById(entity.getDmNhomTbytId()));

  }

  @Override
  protected DmThietBiEntity beforeSave(DmThietBiEntity model, DmThietBiDTO dto) {
    validateFields(model, dto);

    validateTen(model);

    generateMa(model, dto);

    setSoLuong(model);

    model.setTonTaiAnhDaiDien(dto.getAnhDaiDien() != null && !dto.getAnhDaiDien().isEmpty());

    return super.beforeSave(model, dto);
  }

  @Override
  public void capNhatSoLuongThietBi(Long id, Integer soLuong) {

    if(id == null){
      return;
    }

    DmThietBiEntity entity = getById(id);
    entity.setSoLuong(soLuong);
    getRepository().save(entity);
  }

  @Override
  public Optional<DmThietBiEntity> getByNhomVTYT(Long vtyt2Id) {
    return repository.getByNhomVTYT(vtyt2Id);
  }

  @Override
  public String getTenVietTat(Long id) {
    return getRepository().getTenVietTat(id);
  }

  private final String PREFIX = "TB";

  private void generateMa(DmThietBiEntity model, DmThietBiDTO dto) {

    if (dto.getMa() != null) {
      model.setMa(dto.getMa());
      return;
    }

    if (!StringUtils.isBlank(model.getMa()) || LoaiThietBiEnum.NHOM_VTYT.equals(model.getLoai())) {
      return;
    }

    String currentMa = repository.getCurrentMa(PREFIX);
    int current = 0;

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    model.setMa(PREFIX + (current < 9 ? "0" : "") + (current + 1));
  }

  private void validateFields(DmThietBiEntity model, DmThietBiDTO dto) {
    if (StringUtils.isBlank(model.getTenVietTat())
        && LoaiThietBiEnum.THIET_BI.equals(model.getLoai())) {
      throw new DmThietBiException.MissingTenVietTat();
    }

    if(LoaiThietBiEnum.NHOM_VTYT.equals(model.getLoai())
        && repository.existsByMa(dto.getMa(), dto.getId())){
      throw new DmThietBiException.DuplicateMaNhomVTYT();
    }
  }

  private void validateTen(DmThietBiEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new DmThietBiException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new DmThietBiException.DuplicateName(new Object[]{model.getTen()});
    }
  }

  private void setSoLuong(DmThietBiEntity entity) {
    if (entity.isNewRecord()) {
      entity.setSoLuong(0);
    }
  }
}
