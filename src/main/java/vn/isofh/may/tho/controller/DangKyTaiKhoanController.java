package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.common.email.EmailService;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO.XacNhanDangKy;
import vn.isofh.may.tho.dto.UserDTO;
import vn.isofh.may.tho.service.DangKyTaiKhoanService;
import vn.isofh.may.tho.service.UserService;

@RestController
@RequestMapping(path = "/dang-ky-tai-khoan")
public class DangKyTaiKhoanController extends
    BaseController<DangKyTaiKhoanDTO, DangKyTaiKhoanService> {

  @Autowired
  private DangKyTaiKhoanService service;

  @Override
  protected DangKyTaiKhoanService getService() {
    return service;
  }

  @GetMapping("/export-data")
  @Transactional
  public ResponseEntity<?> exportData(DangKyTaiKhoanDTO dto, Pageable pageable) {
    return response(service.exportUserDangKy(dto, pageable));
  }

  @GetMapping("/send")
  @Transactional
  public ResponseEntity<?> send() {
    service.guiMailYeuCauDangKy();
    return response(null);
  }
}
