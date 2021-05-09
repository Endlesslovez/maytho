package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmThongSoPhanTichDTO;
import vn.isofh.may.tho.service.DmThongSoPhanTichService;

@RestController
@RequestMapping("/dm-thong-so-phan-tich")
public class DmThongSoPhanTichController extends
    BaseController<DmThongSoPhanTichDTO, DmThongSoPhanTichService> {

   @Autowired
   private DmThongSoPhanTichService service;

   @Override
   protected DmThongSoPhanTichService getService() {
      return service;
   }
}
