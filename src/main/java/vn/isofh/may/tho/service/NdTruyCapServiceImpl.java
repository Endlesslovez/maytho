package vn.isofh.may.tho.service;


import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.isofh.common.client.RestClientServiceImpl;
import vn.isofh.common.util.DateUtil;
import vn.isofh.may.tho.dao.model.NdTruyCapEntity;
import vn.isofh.may.tho.dao.model.statistic.ThongKeTruyCapDangNhapEntity;
import vn.isofh.may.tho.dao.model.statistic.ThongKeTruyCapTraCuuGiaEntity;
import vn.isofh.may.tho.dao.repository.NdTruyCapRepository;
import vn.isofh.may.tho.dao.repository.statistic.ThongKeTruyCapRepository;
import vn.isofh.may.tho.dto.NdTruyCapDTO;
import vn.isofh.may.tho.dto.NdTruyCapDTO.IpLocationResponse;
import vn.isofh.may.tho.exception.NdTruyCapException;

@Service
public class NdTruyCapServiceImpl extends
    AbstractDmService<NdTruyCapEntity, NdTruyCapDTO, NdTruyCapRepository> implements
    NdTruyCapService {

  @Autowired
  private NdTruyCapRepository repository;

  @Autowired
  private RestClientServiceImpl restClientService;

  @Autowired
  private UserService userService;

  @Autowired
  private ThongKeTruyCapRepository thongKeTruyCapRepository;

  @Override
  protected NdTruyCapRepository getRepository() {
    return repository;
  }

  @Override
  protected void specificMapToDTO(NdTruyCapEntity entity, NdTruyCapDTO dto) {
    super.specificMapToDTO(entity, dto);
    if (userService.existsById(entity.getUserAccountId())) {
      dto.setUserDTO(userService.findById(entity.getUserAccountId()));
    }
  }

  @Override
  protected NdTruyCapEntity beforeSave(NdTruyCapEntity entity, NdTruyCapDTO dto) {
    ZonedDateTime ngayTruyCap = DateUtil.getNow();

    NdTruyCapEntity old = repository.findTopByIpAndUserAccountIdIsNullOrderByNgayTruyCapDesc(dto.getIp()).orElse(null);
    if (dto.getUserAccountId() != null) {
      old = null;
    }
    //Lấy bản ghi mới nhất của "ip" và kiểm tra thời gian
    //lưu bản ghi mỗi tối đa 30 phút
    if (dto.getId() != null) {
      entity.setThoiGianKetThuc(ngayTruyCap);
    } else {
      if ((old != null && ngayTruyCap.compareTo(old.getNgayTruyCap().plusMinutes(30)) < 0)) {
        entity = old;
      } else {
        IpLocationResponse r = restClientService
            .init("http://ip-api.com/json/" + dto.getIp(), IpLocationResponse.class)
            .setLoadBalancer(false)
            .doGet();

        if (!"success".equals(r.getStatus())) {
          throw new NdTruyCapException.IpInvalid();
        }
        entity.setMaKhuVuc(r.getRegion());
        entity.setTenKhuVuc(r.getRegionName());
        entity.setMaQuocGia(r.getCountryCode());
        entity.setTenQuocGia(r.getCountry());
        entity.setNgayTruyCap(ngayTruyCap);
        entity.setIp(dto.getIp());
        if (dto.getUserAccountId() != null) {
          entity.setUserAccountId(dto.getUserAccountId());
        }
      }
    }
    return super.beforeSave(entity, dto);
  }

  @Override
  public List<ThongKeTruyCapTraCuuGiaEntity> thongKeTruyCapTraCuuGia(ZonedDateTime tuNgay,
      ZonedDateTime denNgay) {

    return null;
  }

  @Override
  public Page<ThongKeTruyCapDangNhapEntity> thongKeTruyCapDangNhap(ZonedDateTime tuNgay,
      ZonedDateTime denNgay, Long dmDonViId, Pageable pageable) {
    return thongKeTruyCapRepository.thongKeTruyCapTraCuuGia(tuNgay,denNgay,dmDonViId,pageable);
  }
}
