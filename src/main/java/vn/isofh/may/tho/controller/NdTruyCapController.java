package vn.isofh.may.tho.controller;


import java.time.ZonedDateTime;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.isofh.common.controller.BaseController;
import vn.isofh.common.dto.ResponseMsg;
import vn.isofh.may.tho.dto.NdTruyCapDTO;
import vn.isofh.may.tho.service.NdTruyCapService;

@RestController
@RequestMapping(path = "/nd-truy-cap")
public class NdTruyCapController extends BaseController<NdTruyCapDTO, NdTruyCapService> {

  @Autowired
  private NdTruyCapService service;

  @Override
  protected NdTruyCapService getService() {
    return service;
  }

  @PostMapping("/login")
  @Transactional
  public ResponseEntity<ResponseMsg> login(@RequestBody NdTruyCapDTO dto) {
    return response(service.save(dto));
  }

  @PostMapping("/logout")
  @Transactional
  public ResponseEntity<ResponseMsg> logout(@RequestBody NdTruyCapDTO dto) {
    NdTruyCapDTO dtoOld = service.findById(dto.getId());
    return response(service.save(dtoOld));
  }

  @GetMapping("/thong-ke-truy-cap")
  @Transactional
  public ResponseEntity<ResponseMsg> thongKeTruyCap(
      @RequestParam ZonedDateTime tuNgay,
      @RequestParam ZonedDateTime denNgay,
      @RequestParam(required = false, defaultValue = "false") boolean nguoiDungDangNhap,
      @RequestParam(required = false) Long dmDonViId,
      @PageableDefault(value = 200, size = 200, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
  ) {
    if (nguoiDungDangNhap) {
      return response(service.thongKeTruyCapDangNhap(tuNgay, denNgay, dmDonViId,pageable));
    } else {
      return response(service.thongKeTruyCapTraCuuGia(tuNgay, denNgay));
    }
  }
}
