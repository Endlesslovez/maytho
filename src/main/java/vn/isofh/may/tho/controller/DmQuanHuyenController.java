package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmQuanHuyenDTO;
import vn.isofh.may.tho.service.DmQuanHuyenService;

@RestController
@RequestMapping("/dm-quan-huyen")
public class DmQuanHuyenController extends BaseController<DmQuanHuyenDTO, DmQuanHuyenService> {

  @Autowired
  private DmQuanHuyenService service;

  @Override
  protected DmQuanHuyenService getService() {
    return service;
  }

  @PostMapping("/nhap-du-lieu")
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }
}
