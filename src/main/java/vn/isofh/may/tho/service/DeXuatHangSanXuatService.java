package vn.isofh.may.tho.service;

import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO.XacNhanDangKy;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO.XacNhanHangSanXuat;


public interface DeXuatHangSanXuatService extends BaseService<DeXuatHangSanXuatDTO> {

  void xacNhan(XacNhanHangSanXuat xacNhanHangSanXuat);
  void huyXacNhan(XacNhanHangSanXuat xacNhanHangSanXuat);

}

