package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO.XacNhanHangSanXuat;
import vn.isofh.may.tho.service.DeXuatHangSanXuatService;

@RestController
@RequestMapping(path = "/de-xuat-hang-san-xuat")
public class DeXuatHangSanXuatController extends
    BaseController<DeXuatHangSanXuatDTO, DeXuatHangSanXuatService> {

  @Autowired
  private DeXuatHangSanXuatService service;

  @Override
  protected DeXuatHangSanXuatService getService() {
    return service;
  }


  @PostMapping("/xac-nhan")
  @Transactional
  public ResponseEntity<ResponseMsg> verify(@RequestBody XacNhanHangSanXuat xacNhanHangSanXuat) {
    service.xacNhan(xacNhanHangSanXuat);
    return response(0,"Xác nhận hãng sản xuất thành công");
  }

  @PostMapping("/huy-xac-nhan")
  @Transactional
  public ResponseEntity<ResponseMsg> unverify(@RequestBody XacNhanHangSanXuat xacNhanHangSanXuat) {
    service.huyXacNhan(xacNhanHangSanXuat);
    return response(0,"Hủy xác nhận hãng sản xuất thành công");
  }

}
