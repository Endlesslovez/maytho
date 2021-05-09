package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmGioiThieuHeThongDTO;
import vn.isofh.may.tho.service.DmGioiThieuHeThongService;

@RestController
@RequestMapping("/dm-gioi-thieu-he-thong")
public class DmGioiThieuHeThongController extends
    BaseController<DmGioiThieuHeThongDTO, DmGioiThieuHeThongService> {

   @Autowired
   private DmGioiThieuHeThongService service;

   @Override
   protected DmGioiThieuHeThongService getService() {
      return service;
   }
}
