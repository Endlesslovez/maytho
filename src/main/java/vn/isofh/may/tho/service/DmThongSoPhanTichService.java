package vn.isofh.may.tho.service;

import java.util.Set;

import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.dto.DmPhuongPhapXetNgiemDTO;
import vn.isofh.may.tho.dto.DmThongSoPhanTichDTO;
import vn.isofh.may.tho.dto.DmTinhThanhPhoDTO;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;

public interface DmThongSoPhanTichService extends DmService<DmThongSoPhanTichDTO> {

   void capNhapSoLuong(Long id, Integer soLuong);

   Set<DmPhuongPhapXetNgiemDTO> getDsPhuongPhapXetNghiem(Long id);

   Set<DmDonViDTO> getDsHangSanXuat(Long id);

   Set<DmTinhThanhPhoDTO> getDsTinhThanhPho(Long id);

   Long getIdByTen(String ten, LoaiThongSoPhanTichEnum loai);
}
