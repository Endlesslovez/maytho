package vn.isofh.may.tho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.may.tho.dto.RoleDTO;
import vn.isofh.may.tho.service.RoleService;

@RestController
@RequestMapping(path = "/roles")
public class RoleController extends BaseController<RoleDTO, RoleService> {

  @Autowired
  private RoleService service;

  @Override
  protected RoleService getService() {
    return service;
  }

}
