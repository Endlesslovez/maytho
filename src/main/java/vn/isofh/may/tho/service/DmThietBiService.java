package vn.isofh.may.tho.service;

import java.util.Optional;
import vn.isofh.may.tho.dao.model.DmThietBiEntity;
import vn.isofh.may.tho.dto.DmThietBiDTO;

public interface DmThietBiService extends DmService<DmThietBiDTO> {

  void capNhatSoLuongThietBi(Long id, Integer soLuong);

  Optional<DmThietBiEntity> getByNhomVTYT(Long vtyt2Id);

  String getTenVietTat(Long id);
}

