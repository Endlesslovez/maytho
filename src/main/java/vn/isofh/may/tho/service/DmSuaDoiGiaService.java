package vn.isofh.may.tho.service;

import vn.isofh.may.tho.dto.DmSuaDoiGiaDTO;

public interface DmSuaDoiGiaService extends DmService<DmSuaDoiGiaDTO> {

   DmSuaDoiGiaDTO getByThietBiId(Long thietBiId);

   DmSuaDoiGiaDTO getByVatTuYTeId(Long dmVTYTId);
}
