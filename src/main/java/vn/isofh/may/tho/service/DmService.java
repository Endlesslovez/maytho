package vn.isofh.may.tho.service;

import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dto.DmBaseDTO;
import vn.isofh.may.tho.dto.DmDTO;

public interface DmService<DTO extends DmDTO> extends BaseService<DTO> {

  Long getIdByMa(String ma);

  Long getIdByTen(String ten);

  <X extends DmBaseDTO> X getBaseDTOById(Long id);
}
