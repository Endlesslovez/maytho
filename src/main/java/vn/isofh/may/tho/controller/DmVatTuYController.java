package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmVatTuYTeDTO;
import vn.isofh.may.tho.service.DmVatTuYTeService;

@RestController
@RequestMapping(path = "/dm-vat-tu-y-te")
public class DmVatTuYController extends BaseController<DmVatTuYTeDTO, DmVatTuYTeService> {

  @Autowired
  private DmVatTuYTeService service;

  @Override
  protected DmVatTuYTeService getService() {
    return service;
  }
}
