package vn.isofh.may.tho.service;

import vn.isofh.may.tho.dto.DmNhomThietBiDTO;

public interface DmNhomThietBiService extends DmService<DmNhomThietBiDTO> {

	void capNhapSoLuong(Long id, Integer soLuong);
}
