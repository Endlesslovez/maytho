package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.controller.BaseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO.XacNhanDangKy;
import vn.isofh.may.tho.dto.UserDTO;
import vn.isofh.may.tho.enums.TrangThaiTaiKhoanEnum;
import vn.isofh.may.tho.service.DangKyTaiKhoanService;
import vn.isofh.may.tho.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController extends BaseController<UserDTO, UserService> {

  @Autowired
  private UserService service;

  @Autowired
  private DangKyTaiKhoanService dangKyTaiKhoanService;

  @Override
  protected UserService getService() {
    return service;
  }

  @PutMapping("/change-password/{id}")
  @Transactional
  public ResponseEntity<?> changePassWord(@PathVariable Long id, @RequestBody UserDTO dto) {
    return response(service.changePassword(id, dto));
  }

  @PostMapping("/nhap-du-lieu")
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }

  @PostMapping("/huy-xac-nhan")
  @Transactional
  public ResponseEntity<ResponseMsg> huyXacNhan(
      @RequestBody XacNhanDangKy xacNhanDangKy
  ) {
    dangKyTaiKhoanService.huyXacNhanDangKy(xacNhanDangKy);
    return response(null);
  }

  @PostMapping("/xac-nhan")
  @Transactional
  public ResponseEntity<ResponseMsg> verifyAccount(@RequestBody XacNhanDangKy xacNhanDangKy) {
    dangKyTaiKhoanService.xacNhanDangKy(xacNhanDangKy);
    return response(null);
  }

  @GetMapping("/export-data")
  @Transactional
  public ResponseEntity<?> exportData(UserDTO dto, Pageable pageable) {
    return response(service.exportUser(dto, pageable));
  }

  @PutMapping("/reset-password/{id}")
  @Transactional
  public ResponseEntity<?> resetPassword(@PathVariable Long id) {
    return response(service.resetPassword(id));
  }

  @PutMapping("/lock/{id}")
  @Transactional
  public ResponseEntity<?> lock(@PathVariable Long id) {
    return response(service.lock(id, TrangThaiTaiKhoanEnum.DANG_KHOA));
  }

  @PutMapping("/unlock/{id}")
  @Transactional
  public ResponseEntity<?> unlock(@PathVariable Long id) {
    return response(service.lock(id, TrangThaiTaiKhoanEnum.DANG_HOAT_DONG));
  }



}
