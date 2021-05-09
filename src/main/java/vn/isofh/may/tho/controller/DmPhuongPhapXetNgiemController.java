package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.DmPhuongPhapXetNgiemDTO;
import vn.isofh.may.tho.service.DmPhuongPhapXetNgiemService;

@RestController
@RequestMapping("/dm-phuong-phap-xet-nghiem")
public class DmPhuongPhapXetNgiemController extends
    BaseController<DmPhuongPhapXetNgiemDTO, DmPhuongPhapXetNgiemService> {

   @Autowired
   private DmPhuongPhapXetNgiemService service;

   @Override
   protected DmPhuongPhapXetNgiemService getService() {
      return service;
   }
}
