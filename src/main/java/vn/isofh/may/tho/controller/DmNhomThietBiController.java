package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmNhomThietBiDTO;
import vn.isofh.may.tho.service.DmNhomThietBiService;


@RestController
@RequestMapping("/dm-nhom-thiet-bi")
public class DmNhomThietBiController extends BaseController<DmNhomThietBiDTO, DmNhomThietBiService> {

   @Autowired
   private DmNhomThietBiService service;

   @Override
   protected DmNhomThietBiService getService() {
      return service;
   }
}
