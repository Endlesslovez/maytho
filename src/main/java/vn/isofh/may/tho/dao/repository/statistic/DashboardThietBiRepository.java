package vn.isofh.may.tho.dao.repository.statistic;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import vn.isofh.common.util.StringUtil;
import vn.isofh.may.tho.dao.model.statistic.DanhSachThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.DashboardEntity;
import vn.isofh.may.tho.dao.model.statistic.DieuChuyenThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.NguonVonThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.ThongKeDonViEntity;
import vn.isofh.may.tho.dao.model.statistic.TrangThaiThietBiEntity;

@Repository
public class DashboardThietBiRepository {

  @Autowired
  @Qualifier("entityManagerFactory")
  private EntityManager firstDataManager;

  public List<TrangThaiThietBiEntity> getTrangThaiThietBi(Long trangThaiId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, Long donViId, Integer tieuChi) {

    List<TrangThaiThietBiEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeThietBiTheoTrangThai")
        .setParameter("p_trang_thai_id", trangThaiId)
        .setParameter("p_tinh_thanh_pho_id", tinhThanhPhoId)
        .setParameter("p_quan_huyen_id", quanHuyenId)
        .setParameter("p_xa_phuong_id", xaPhuongId)
        .setParameter("p_don_vi_id", donViId)
        .setParameter("p_tieu_chi", tieuChi)
        .getResultList();

    return dtos;
  }

  public List<DanhSachThietBiEntity> getDanhSachThietBi(Long trangThaiId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, Long donViId) {

    List<DanhSachThietBiEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("danhSachThietBi")
        .setParameter("p_trang_thai_id", trangThaiId)
        .setParameter("p_tinh_thanh_pho_id", tinhThanhPhoId)
        .setParameter("p_quan_huyen_id", quanHuyenId)
        .setParameter("p_xa_phuong_id", xaPhuongId)
        .setParameter("p_don_vi_id", donViId)
        .getResultList();

    return dtos;
  }

  public List<ThongKeDonViEntity> getCoSoYTeNhap(Long coSoYTeId, Long tinhThanhPhoId) {

    List<ThongKeDonViEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeCoSoYTeDaNhap")
        .setParameter("p_tinh_thanh_pho_id", tinhThanhPhoId)
        .setParameter("p_co_so_y_te_id", coSoYTeId)
        .getResultList();

    return dtos;
  }

  public List<DieuChuyenThietBiEntity> getDieuChuyenThietBi(Long coSoYTeId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId) {

    List<DieuChuyenThietBiEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeThietBiDieuChuyen")
        .setParameter("p_co_so_y_te_id", coSoYTeId)
        .setParameter("p_tinh_thanh_pho_id", tinhThanhPhoId)
        .setParameter("p_quan_huyen_id", quanHuyenId)
        .setParameter("p_xa_phuong_id", xaPhuongId)
        .getResultList();

    return dtos;
  }

  public List<NguonVonThietBiEntity> getNguonVonThietBi(Long coSoYTeId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, String tenThietBi) {

    List<NguonVonThietBiEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeThietBiNguonVon")
        .setParameter("p_co_so_y_te_id", coSoYTeId)
        .setParameter("p_tinh_thanh_pho_id", tinhThanhPhoId)
        .setParameter("p_quan_huyen_id", quanHuyenId)
        .setParameter("p_xa_phuong_id", xaPhuongId)
        .setParameter("p_ten_thiet_bi", tenThietBi)
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeHoThietBi(Long[] dmThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds,
      Long[] dmTinhThanhPhoIds) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeHoThietBi")
        .setParameter("dm_thiet_bi_ids", StringUtil.toString(dmThietBiIds))
        .setParameter("dm_loai_thiet_bi_ids", StringUtil.toString(dmLoaiThietBiIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("dm_tinh_thanh_pho_ids", StringUtil.toString(dmTinhThanhPhoIds))
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeLoaiThietBi(Long[] dmThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds,
      Long giaTuKhoang, Long giaDenKhoang) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeLoaiThietBi")
        .setParameter("dm_thiet_bi_ids", StringUtil.toString(dmThietBiIds))
        .setParameter("dm_loai_thiet_bi_ids", StringUtil.toString(dmLoaiThietBiIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("gia_tu_khoang", giaTuKhoang)
        .setParameter("gia_den_khoang", giaDenKhoang)
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeHangSanXuat(Long[] dmNhomThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds, Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeHangSanXuat")
        .setParameter("dm_nhom_thiet_bi_ids", StringUtil.toString(dmNhomThietBiIds))
        .setParameter("dm_loai_thiet_bi_ids", StringUtil.toString(dmLoaiThietBiIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("hang_san_xuat_ids", StringUtil.toString(hang_san_xuat_ids))
        .setParameter("dm_tinh_thanh_pho_ids", StringUtil.toString(dmTinhThanhPhoIds))
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeNhomThietBi(Long[] dmNhomThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds, Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeNhomThietBi")
        .setParameter("dm_nhom_thiet_bi_ids", StringUtil.toString(dmNhomThietBiIds))
        .setParameter("dm_loai_thiet_bi_ids", StringUtil.toString(dmLoaiThietBiIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("hang_san_xuat_ids", StringUtil.toString(hang_san_xuat_ids))
        .setParameter("dm_tinh_thanh_pho_ids", StringUtil.toString(dmTinhThanhPhoIds))
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeThietBiTheoDoanhNghiep(Long[] dmNhomThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds, Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeThietBiTheoDN")
        .setParameter("dm_nhom_thiet_bi_ids", StringUtil.toString(dmNhomThietBiIds))
        .setParameter("dm_loai_thiet_bi_ids", StringUtil.toString(dmLoaiThietBiIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("hang_san_xuat_ids", StringUtil.toString(hang_san_xuat_ids))
        .setParameter("dm_tinh_thanh_pho_ids", StringUtil.toString(dmTinhThanhPhoIds))
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeIvdTheoPhuongPhap(Long[] dmPhuongPhapIds,
      Long[] dmDonViIds, Long[] hang_san_xuat_ids) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeIVDTheoPhuongPhap")
        .setParameter("dm_phuong_phap_ids", StringUtil.toString(dmPhuongPhapIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("hang_san_xuat_ids", StringUtil.toString(hang_san_xuat_ids))
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeIvdTheoDoanhNghiep(Long[] dmPhuongPhapIds,
      Long[] dmDonViIds, Long[] hang_san_xuat_ids) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeIVDTheoDoanhNghiep")
        .setParameter("dm_phuong_phap_ids", StringUtil.toString(dmPhuongPhapIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("hang_san_xuat_ids", StringUtil.toString(hang_san_xuat_ids))
        .getResultList();

    return dtos;
  }

  public List<DashboardEntity> thongKeIvdTheoHangSanXuat(Long[] dmPhuongPhapIds,
      Long[] dmDonViIds, Long[] hang_san_xuat_ids) {
    List<DashboardEntity> dtos = firstDataManager
        .createNamedStoredProcedureQuery("thongKeIVDTheoHangSx")
        .setParameter("dm_phuong_phap_ids", StringUtil.toString(dmPhuongPhapIds))
        .setParameter("dm_don_vi_ids", StringUtil.toString(dmDonViIds))
        .setParameter("hang_san_xuat_ids", StringUtil.toString(hang_san_xuat_ids))
        .getResultList();

    return dtos;
  }
}