package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.controller.BaseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.common.msg.Msg;
import vn.isofh.may.tho.dto.VatTuYTeDTO;
import vn.isofh.may.tho.service.VatTuYTeService;

@RestController
@RequestMapping("/vat-tu-y-te")
public class VatTuYTeController extends BaseController<VatTuYTeDTO, VatTuYTeService> {

  @Autowired
  private VatTuYTeService service;

  @Override
  protected VatTuYTeService getService() {
    return service;
  }

  @PostMapping("/nhap-du-lieu")
  @Transactional
  public ResponseEntity<InputStreamResource> importData(@RequestParam("file") MultipartFile file,
      @RequestParam(value = "sheet", defaultValue = "1") Integer sheet,
      @RequestParam(value = "dong", defaultValue = "1") Integer dong) {
    return responseAttachment(service.importData(file, sheet, dong));
  }
}

