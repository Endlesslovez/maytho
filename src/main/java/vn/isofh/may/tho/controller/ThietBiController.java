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
import vn.isofh.may.tho.dto.ThietBiDTO;
import vn.isofh.may.tho.service.ThietBiService;

@RestController
@RequestMapping(path = "/thiet-bi")
public class ThietBiController extends BaseController<ThietBiDTO, ThietBiService> {

  @Autowired
  private ThietBiService service;

  @Override
  protected ThietBiService getService() {
    return service;
  }

  @GetMapping("/thong-ke/trang-thai")
  @Transactional
  public ResponseEntity<ResponseMsg> getTrangThaiThietBi(
      @RequestParam(required = false) Long trangThaiId,
      @RequestParam(required = false) Long tinhThanhPhoId,
      @RequestParam(required = false) Long quanHuyenId,
      @RequestParam(required = false) Long xaPhuongId,
      @RequestParam(required = false) Long donViId,
      @RequestParam(defaultValue = "1") Integer tieuChi) {
    return response(service
        .getTrangThaiThietBi(trangThaiId, tinhThanhPhoId, quanHuyenId, xaPhuongId, donViId,
            tieuChi));
  }

  @GetMapping("/thong-ke/excel/danh-sach")
  @Transactional
  public ResponseEntity<InputStreamResource> getDanhSachThietBiExcel(
      @RequestParam(required = false) Long trangThaiId,
      @RequestParam(required = false) Long tinhThanhPhoId,
      @RequestParam(required = false) Long quanHuyenId,
      @RequestParam(required = false) Long xaPhuongId,
      @RequestParam(required = false) Long donViId) {
    return responseAttachment(service
        .getDanhSachThietBiExcel(trangThaiId, tinhThanhPhoId, quanHuyenId, xaPhuongId, donViId));
  }

  @GetMapping("/thong-ke/danh-sach")
  @Transactional
  public ResponseEntity<ResponseMsg> getDanhSachThietBi(
      @RequestParam(required = false) Long trangThaiId,
      @RequestParam(required = false) Long tinhThanhPhoId,
      @RequestParam(required = false) Long quanHuyenId,
      @RequestParam(required = false) Long xaPhuongId,
      @RequestParam(required = false) Long donViId) {
    return response(
        service.getDanhSachThietBi(trangThaiId, tinhThanhPhoId, quanHuyenId, xaPhuongId, donViId));
  }

  @GetMapping("/thong-ke/so-so-y-te-nhap")
  @Transactional
  public ResponseEntity<ResponseMsg> getDanhSachThietBi(
      @RequestParam(required = false) Long coSoYTeId,
      @RequestParam(required = false) Long tinhThanhPhoId) {
    return response(service.getCoSoYTeNhap(coSoYTeId, tinhThanhPhoId));
  }

  @GetMapping("/thong-ke/thiet-bi-dieu-chuyen")
  @Transactional
  public ResponseEntity<ResponseMsg> getDanhSachThietBi(
      @RequestParam(required = false) Long coSoYTeId,
      @RequestParam(required = false) Long tinhThanhPhoId,
      @RequestParam(required = false) Long quanHuyenId,
      @RequestParam(required = false) Long xaPhuongId) {
    return response(service.getDieuChuyenThietBi(coSoYTeId, tinhThanhPhoId, quanHuyenId, xaPhuongId));
  }

  @GetMapping("/thong-ke/thiet-bi-nguon-von")
  @Transactional
  public ResponseEntity<ResponseMsg> getNguonVonThietBi(
      @RequestParam(required = false) Long coSoYTeId,
      @RequestParam(required = false) Long tinhThanhPhoId,
      @RequestParam(required = false) Long quanHuyenId,
      @RequestParam(required = false) Long xaPhuongId,
      @RequestParam(required = false) String tenThietBi) {
    return response(service.getNguonVonThietBi(coSoYTeId, tinhThanhPhoId, quanHuyenId, xaPhuongId, tenThietBi));
  }

  @PostMapping("/tai-lieu")
  public ResponseEntity<ResponseMsg> uploadDocument(@RequestParam("file") MultipartFile[] file) {
    return response(getService().uploadDocument(file));
  }

  @PostMapping("/anh")
  public ResponseEntity<ResponseMsg> uploadImage(@RequestParam("file") MultipartFile[] file) {
    return response(getService().uploadImage(file));
  }

  @GetMapping("/thong-ke/ho-thiet-bi")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeHoThietBi(
      @RequestParam(required = false) Long[] dmThietBiIds,
      @RequestParam(required = false) Long[] dmLoaiThietBiIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] dmTinhThanhPhoIds
  ) {
    return response(
        service.thongKeHoThietBi(dmThietBiIds, dmLoaiThietBiIds, dmDonViIds, dmTinhThanhPhoIds));
  }

  @GetMapping("/thong-ke/loai-thiet-bi")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeLoaiThietBi(
      @RequestParam(required = false) Long[] dmThietBiIds,
      @RequestParam(required = false) Long[] dmLoaiThietBiIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long giaTuKhoang,
      @RequestParam(required = false) Long giaDenKhoang
  ) {
   return response(service
        .thongKeLoaiThietBi(dmThietBiIds, dmLoaiThietBiIds, dmDonViIds, giaTuKhoang, giaDenKhoang));
  }

  @GetMapping("/thong-ke/hang-san-xuat")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeHangSanXuat(
      @RequestParam(required = false) Long[] dmNhomThietBiIds,
      @RequestParam(required = false) Long[] dmLoaiThietBiIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] hang_san_xuat_ids,
      @RequestParam(required = false) Long[] dmTinhThanhPhoIds
  ) {
    return response(service.thongKeHangSanXuat(dmNhomThietBiIds, dmLoaiThietBiIds, dmDonViIds, hang_san_xuat_ids, dmTinhThanhPhoIds));
  }

  @PostMapping("/nhap-du-lieu")
  @Transactional
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }

  @GetMapping("/thong-ke/nhom-thiet-bi")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeNhomThietBi(
      @RequestParam(required = false) Long[] dmNhomThietBiIds,
      @RequestParam(required = false) Long[] dmLoaiThietBiIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] hang_san_xuat_ids,
      @RequestParam(required = false) Long[] dmTinhThanhPhoIds
  ) {
    return response(service
        .thongKeNhomThietBi(dmNhomThietBiIds, dmLoaiThietBiIds, dmDonViIds, hang_san_xuat_ids, dmTinhThanhPhoIds));
  }

  @GetMapping("/thong-ke/doanh-nghiep")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeThietBiTheoDN(
      @RequestParam(required = false) Long[] dmNhomThietBiIds,
      @RequestParam(required = false) Long[] dmLoaiThietBiIds,
      @RequestParam(required = false) Long[] dmDonViIds,
      @RequestParam(required = false) Long[] hang_san_xuat_ids,
      @RequestParam(required = false) Long[] dmTinhThanhPhoIds
  ) {
    return response(service
        .thongKeThietBiTheoDoanhNghiep(dmNhomThietBiIds, dmLoaiThietBiIds, dmDonViIds, hang_san_xuat_ids, dmTinhThanhPhoIds));
  }

}
