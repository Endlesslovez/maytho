package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmSuaDoiGiaDTO;
import vn.isofh.may.tho.service.DmSuaDoiGiaService;

@RestController
@RequestMapping("/dm-sua-doi-gia")
public class DmSuaDoiGiaController extends BaseController<DmSuaDoiGiaDTO, DmSuaDoiGiaService> {

   @Autowired
   private DmSuaDoiGiaService service;

   @Override
   protected DmSuaDoiGiaService getService() {
      return service;
   }
}
