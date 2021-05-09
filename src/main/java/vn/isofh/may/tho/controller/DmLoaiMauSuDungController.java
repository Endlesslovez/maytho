package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmLoaiMauSuDungDTO;
import vn.isofh.may.tho.service.DmLoaiMauSuDungService;

@RestController
@RequestMapping("/dm-loai-mau")
public class DmLoaiMauSuDungController extends
    BaseController<DmLoaiMauSuDungDTO, DmLoaiMauSuDungService> {

   @Autowired
   private DmLoaiMauSuDungService service;

   @Override
   protected DmLoaiMauSuDungService getService() {
      return service;
   }
}
