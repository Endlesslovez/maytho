package vn.isofh.may.tho.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dao.model.statistic.DashboardEntity;
import vn.isofh.may.tho.dto.DmThongSoPhanTichDTO;
import vn.isofh.may.tho.dto.ThietBiInVitroDTO;

public interface ThietBiInVitroService extends BaseService<ThietBiInVitroDTO> {

   List<ThietBiInVitroDTO> getDmThongSoPhanTich(Long id);

   List<DashboardEntity> thongKeIvdTheoPhuongPhap(Long[] dmPhuongPhapIds, Long[] dmDonViIds,
       Long[] hang_san_xuat_ids);

   List<DashboardEntity> thongKeIvdTheoDoanhNghiep(Long[] dmPhuongPhapIds, Long[] dmDonViIds,
       Long[] hang_san_xuat_ids);

   List<DashboardEntity> thongKeIvdTheoHangSanXuat(Long[] dmPhuongPhapIds, Long[] dmDonViIds,
       Long[] hang_san_xuat_ids);

}

