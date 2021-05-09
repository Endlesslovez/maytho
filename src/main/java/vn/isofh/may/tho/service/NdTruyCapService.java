package vn.isofh.may.tho.service;


import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dao.model.statistic.ThongKeTruyCapDangNhapEntity;
import vn.isofh.may.tho.dao.model.statistic.ThongKeTruyCapTraCuuGiaEntity;
import vn.isofh.may.tho.dto.NdTruyCapDTO;

public interface NdTruyCapService extends BaseService<NdTruyCapDTO> {

  List<ThongKeTruyCapTraCuuGiaEntity> thongKeTruyCapTraCuuGia(ZonedDateTime tuNgay,ZonedDateTime denNgay);

  Page<ThongKeTruyCapDangNhapEntity> thongKeTruyCapDangNhap(ZonedDateTime tuNgay,ZonedDateTime denNgay,Long dmDonViId,
      Pageable pageable);
  }

