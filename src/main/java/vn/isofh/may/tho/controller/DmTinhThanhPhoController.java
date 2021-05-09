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
import vn.isofh.may.tho.dto.DmTinhThanhPhoDTO;
import vn.isofh.may.tho.service.DmTinhThanhPhoService;

@RestController
@RequestMapping(path = "/dm-tinh-thanh-pho")
public class DmTinhThanhPhoController extends
    BaseController<DmTinhThanhPhoDTO, DmTinhThanhPhoService> {

  @Autowired
  private DmTinhThanhPhoService service;

  @Override
  protected DmTinhThanhPhoService getService() {
    return service;
  }

  @PostMapping("/nhap-du-lieu")
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }
}
