package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.PrivilegeDTO;
import vn.isofh.may.tho.service.PrivilegeService;

@RestController
@RequestMapping(path = "/privileges")
public class PrivilegeController extends BaseController<PrivilegeDTO, PrivilegeService> {

  @Autowired
  private PrivilegeService service;

  @Override
  protected PrivilegeService getService() {
    return service;
  }
}
