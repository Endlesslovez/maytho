package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmThietBiInVitroDTO;
import vn.isofh.may.tho.service.DmThietBiInVitroService;

@RestController
@RequestMapping("/dm-thiet-bi-invitro")
public class DmThietBiInVitroController extends
    BaseController<DmThietBiInVitroDTO, DmThietBiInVitroService> {

   @Autowired
   private DmThietBiInVitroService service;

   @Override
   protected DmThietBiInVitroService getService() {
      return service;
   }

}
