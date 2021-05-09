package vn.isofh.may.tho.service;

import vn.isofh.may.tho.dto.DmPhuongPhapXetNgiemDTO;

public interface DmPhuongPhapXetNgiemService extends DmService<DmPhuongPhapXetNgiemDTO> {

   void capNhapSoLuong(Long id, Integer soLuong);
}
