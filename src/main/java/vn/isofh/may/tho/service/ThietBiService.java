package vn.isofh.may.tho.service;

import java.io.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.service.BaseService;
import vn.isofh.may.tho.dao.model.statistic.DanhSachThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.DieuChuyenThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.NguonVonThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.ThongKeDonViEntity;
import vn.isofh.may.tho.dao.model.statistic.DashboardEntity;
import vn.isofh.may.tho.dao.model.statistic.TrangThaiThietBiEntity;
import vn.isofh.may.tho.dto.ThietBiDTO;

public interface ThietBiService extends BaseService<ThietBiDTO> {

  List<TrangThaiThietBiEntity> getTrangThaiThietBi(Long trangThaiId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, Long donViId, Integer tieuChi);

  File getDanhSachThietBiExcel(Long trangThaiId, Long tinhThanhPhoId, Long quanHuyenId,
      Long xaPhuongId, Long donViId);

  List<DanhSachThietBiEntity> getDanhSachThietBi(Long trangThaiId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, Long donViId);

  List<ThongKeDonViEntity> getCoSoYTeNhap(Long coSoYTeId, Long tinhThanhPhoId);

  List<DieuChuyenThietBiEntity> getDieuChuyenThietBi(Long coSoYTeId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId);

  List<NguonVonThietBiEntity> getNguonVonThietBi(Long coSoYTeId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, String tenThietBi);

  List<String> uploadDocument(MultipartFile[] file);

  List<String> uploadImage(MultipartFile[] file);

  List<DashboardEntity> thongKeHoThietBi(Long[] dmThietBiIds,Long[] dmLoaiThietBiIds,Long[] dmDonViIds,Long[] dmTinhThanhPhoIds);

  List<DashboardEntity> thongKeLoaiThietBi(Long[] dmThietBiIds,Long[] dmLoaiThietBiIds,Long[] dmDonViIds,Long giaTuKhoang,Long giaDenKhoang);

  List<DashboardEntity> thongKeHangSanXuat(Long[] dmNhomThietBiIds,Long[] dmLoaiThietBiIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds);

  List<DashboardEntity> thongKeNhomThietBi(Long[] dmNhomThietBiIds,Long[] dmLoaiThietBiIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds);

  List<DashboardEntity> thongKeThietBiTheoDoanhNghiep(Long[] dmNhomThietBiIds,Long[] dmLoaiThietBiIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds);
}

