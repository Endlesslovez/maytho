package vn.isofh.may.tho.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseResponseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.may.tho.dto.LoginDTO;
import vn.isofh.may.tho.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseResponseController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @Transactional(readOnly = true)
  public ResponseEntity<ResponseMsg> login(@Valid @RequestBody LoginDTO loginDto) {
    return response(userService.validateLogin(loginDto));
  }
}
