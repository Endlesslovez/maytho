package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.controller.BaseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.may.tho.dto.ThietBiInVitroDTO;
import vn.isofh.may.tho.service.ThietBiInVitroService;

@RestController
@RequestMapping(path = "/thiet-bi-invitro")
public class ThietBiInVitroController extends BaseController<ThietBiInVitroDTO, ThietBiInVitroService> {

  @Autowired
  private ThietBiInVitroService service;

  @Override
  protected ThietBiInVitroService getService() {
    return service;
  }

  @PostMapping("/nhap-du-lieu")
  @Transactional
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }

  @GetMapping("/thong-ke/phuong-phap")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeTheoPhuongPhap(
      @RequestParam(required = false) Long[] dmPhuongPhapIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] hang_san_xuat_ids
  ) {
    return response(service
        .thongKeIvdTheoPhuongPhap(dmPhuongPhapIds, dmDonViIds, hang_san_xuat_ids));
  }

  @GetMapping("/thong-ke/doanh-nghiep")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeTheoDoanhNghiep(
      @RequestParam(required = false) Long[] dmPhuongPhapIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] hang_san_xuat_ids
  ) {
    return response(service
        .thongKeIvdTheoDoanhNghiep(dmPhuongPhapIds, dmDonViIds, hang_san_xuat_ids));
  }

  @GetMapping("/thong-ke/hang-san-xuat")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeTheoHangSx(
      @RequestParam(required = false) Long[] dmPhuongPhapIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] hang_san_xuat_ids
  ) {
    return response(service
        .thongKeIvdTheoHangSanXuat(dmPhuongPhapIds, dmDonViIds, hang_san_xuat_ids));
  }
}
