package vn.isofh.may.tho.service;

import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.Map;

import vn.isofh.common.report.ReportDTO;
import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO;
import vn.isofh.may.tho.dto.LoginDTO;
import vn.isofh.may.tho.dto.UserDTO;
import vn.isofh.may.tho.enums.TrangThaiTaiKhoanEnum;

public interface UserService extends BaseService<UserDTO> {

  Map<String, Object> validateLogin(LoginDTO loginDTO);

  UserDTO changePassword(Long id, UserDTO userDto);

  String taoMoiTaiKhoan(DangKyTaiKhoanDTO dto,Long dmDonViId);

  UserDTO resetPassword(Long id);

  UserDTO lock(Long id, TrangThaiTaiKhoanEnum trangThai);

  Boolean checkRoleUser(String roleString);

  UserDTO xacNhanDeXuatNhomTttbyt(Long id);

  ReportDTO exportUser(UserDTO dto, Pageable pageable);
}

