package vn.isofh.may.tho.service;


import org.springframework.data.domain.Pageable;

import vn.isofh.common.report.ReportDTO;
import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO.XacNhanDangKy;

public interface DangKyTaiKhoanService extends BaseService<DangKyTaiKhoanDTO> {

  DangKyTaiKhoanDTO xacThucTaiKhoan(Long id);

  void xacNhanDangKy(XacNhanDangKy xacNhanDangKy);

  void guiMailYeuCauDangKy();

  void huyXacNhanDangKy(XacNhanDangKy xacNhanDangKy);

  ReportDTO exportUserDangKy(DangKyTaiKhoanDTO dto, Pageable pageable);

}

