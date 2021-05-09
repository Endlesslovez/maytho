package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmHuongDanSuDungDTO;
import vn.isofh.may.tho.service.DmHuongDanSuDungService;

@RestController
@RequestMapping(path = "/dm-huong-dan-su-dung")
public class DmHuongDanSuDungConTroller extends
    BaseController<DmHuongDanSuDungDTO, DmHuongDanSuDungService> {

   @Autowired
   private DmHuongDanSuDungService service;

   @Override
   protected DmHuongDanSuDungService getService() {
      return service;
   }
}
