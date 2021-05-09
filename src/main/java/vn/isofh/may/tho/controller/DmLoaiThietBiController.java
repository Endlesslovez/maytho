package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
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
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.service.DmLoaiThietBiService;

@RestController
@RequestMapping(path = "/dm-loai-thiet-bi")
public class DmLoaiThietBiController extends
    BaseController<DmLoaiThietBiDTO, DmLoaiThietBiService> {

  @Autowired
  private DmLoaiThietBiService service;

  @Override
  protected DmLoaiThietBiService getService() {
    return service;
  }

  @PostMapping("/nhap-du-lieu")
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }
  @GetMapping("/excel")
  @Transactional
  public ResponseEntity<ResponseMsg> export(DmLoaiThietBiDTO dto, Pageable pageable) {
    return response(service.exportLoaiThietBi(dto, pageable));
  }
}
