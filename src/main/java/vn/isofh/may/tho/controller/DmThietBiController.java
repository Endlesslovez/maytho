package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmThietBiDTO;
import vn.isofh.may.tho.service.DmThietBiService;

@RestController
@RequestMapping("/dm-thiet-bi")
public class DmThietBiController extends BaseController<DmThietBiDTO, DmThietBiService> {

  @Autowired
  private DmThietBiService service;

  @Override
  protected DmThietBiService getService() {
    return service;
  }

  @PostMapping("/nhap-du-lieu")
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }

}
