package vn.isofh.may.tho.dao.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.VatTuYTeEntity;
import vn.isofh.may.tho.dto.VatTuYTeDTO;

@Repository
public interface VatTuYTeRepository extends BaseRepository<VatTuYTeEntity, VatTuYTeDTO, Long> {

  @Query("select e.id from VatTuYTeEntity e where e.ma = ?1 and e.active = true")
  Optional<Long> findIdByMa(String ma);

  @Query("select count(e) "
      + "from VatTuYTeEntity e "
      + " left join e.dmLoaiVtyt ltb2  "
      + " left join ltb2.dmLoaiVtyt1 ltb1 "
      + " left join ltb1.dmThietBi dtb "
      + "where dtb.id = ?1 ")
  Integer getSoLuongByDmThietBiId(Long dmThietBiId);

  @Query("select max(e.ma) from VatTuYTeEntity e where 1=1"
      + " and text_search(e.ma) like text_search_like(:ma)")
  String getMaLonNhat(String ma);


  @Override
  @Query("select e from VatTuYTeEntity e "
      + " left join e.dmLoaiVtyt ltb2 "
      + " left join ltb2.dmLoaiVtyt1 ltb1 "
      + " left join ltb1.dmThietBi dtb "
      + " left join e.hangSoHuu hsh "
      + " left join e.nuocSoHuu nsh "
      + " left join e.dmHangSanXuat hsx"
      + " left join e.dmDonVi ncc "
      + " where 1 = 1 "
      + " and (dtb.id = :#{#dto.dmThietBiId} or :#{#dto.dmThietBiId} is null) "
      + " and (ltb1.id in :#{#dto.loaiVtyt1Ids} or :#{#dto.loaiVtyt1Ids} is null) "
      + " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null) "
      + " and (text_search(e.maSanPham) like text_search_like(:#{#dto.maSanPham}) or :#{#dto.maSanPham} is null) "
      + " and (text_search(e.soGiayPhepLuuHanh) like text_search_like(:#{#dto.soGiayPhepLuuHanh}) or :#{#dto.soGiayPhepLuuHanh} is null) "
      + " and (trunc(e.ngayBatDauHieuLuc) = :#{#dto.ngayBatDauHieuLuc} or cast(:#{#dto.ngayBatDauHieuLuc} as string) is null) "
      + " and (trunc(e.ngayHetHieuLuc) = :#{#dto.ngayHetHieuLuc} or cast(:#{#dto.ngayHetHieuLuc} as string) is null) "
      + " and (text_search(hsx.ten) like text_search_like(:#{#dto.hangSanXuatTen}) or :#{#dto.hangSanXuatTen} is null) "
      + " and (text_search(hsh.ten) like text_search_like(:#{#dto.hangSoHuuTen}) or :#{#dto.hangSoHuuTen} is null) "
      + " and (text_search(nsh.ten) like text_search_like(:#{#dto.nuocSoHuuTen}) or :#{#dto.nuocSoHuuTen} is null) "
      + " and (text_search(e.chungLoai) like text_search_like(:#{#dto.chungLoai}) or :#{#dto.chungLoai} is null) "
      + " and (text_search(e.quyCachDongGoi) like text_search_like(:#{#dto.quyCachDongGoi}) or :#{#dto.quyCachDongGoi} is null) "
      + " and (text_search(e.thongSoKtCoBan) like text_search_like(:#{#dto.thongSoKtCoBan}) or :#{#dto.thongSoKtCoBan} is null) "
      + " and (text_search(e.dieuKienThongTinKhac) like text_search_like(:#{#dto.dieuKienThongTinKhac}) or :#{#dto.dieuKienThongTinKhac} is null) "
      + " and (e.dmDonViTinhId = :#{#dto.dmDonViTinhId} or :#{#dto.dmDonViTinhId} is null) "
      + " and (e.giaNiemYet = :#{#dto.giaNiemYet} or :#{#dto.giaNiemYet} is null) "
      + " and (e.nhomThietBiId = :#{#dto.nhomThietBiId} or :#{#dto.nhomThietBiId} is null) "
      + " and (e.hangSoHuuId = :#{#dto.hangSoHuuId} or :#{#dto.hangSoHuuId} is null) "
      + " and (e.nuocSoHuuId = :#{#dto.nuocSoHuuId} or :#{#dto.nuocSoHuuId} is null) "
      + " and (e.namSanXuat = :#{#dto.namSanXuat} or :#{#dto.namSanXuat} is null) "
      + " and (e.phanLoaiTtb = :#{#dto.phanLoaiTtb} or :#{#dto.phanLoaiTtb} is null) "
      + " and (e.dmHangSanXuatId = :#{#dto.dmHangSanXuatId} or :#{#dto.dmHangSanXuatId} is null) "
      + " and (dtb.id = :#{#dto.dmNhomVtytId} or :#{#dto.dmNhomVtytId} is null) "
      + " and (e.dmLoaiVtytId = :#{#dto.dmLoaiVtytId} or :#{#dto.dmLoaiVtytId} is null) "
      + " and (e.dmHangSanXuatId in :#{#dto.dmHangSanXuatIds} or :#{#dto.dmHangSanXuatIds} is null)"
      + " and (e.dmDonViId = :#{#dto.dmDonViId} or :#{#dto.dmDonViId} is null)"
      + " and (text_search(e.ten) like text_search_like(:#{#dto.timKiem})"
      + " or text_search(hsx.ten) like text_search_like(:#{#dto.timKiem})"
      + " and (e.id <> :#{#dto.idLoaiBo} or :#{#dto.idLoaiBo} is null)"
      + " or e.giaNiemYet <= :#{#dto.giaNiemYetLonNhat}"
      + " or :#{#dto.timKiem} is null)"
      + " and (e.congKhaiGia = :#{#dto.congKhaiGia} or :#{#dto.congKhaiGia} is null)"
      + " and (ncc.dmTinhThanhPhoId in :#{#dto.dmTinhThanhPhoIds} or :#{#dto.dmTinhThanhPhoIds} is null)"
      + " and (e.dmLoaiVtytId in :#{#dto.dmLoaiVtytIds} or :#{#dto.dmLoaiVtytIds} is null) "
  )
  Page<VatTuYTeEntity> search(VatTuYTeDTO dto, Pageable pageable);

  @Query("select case when count(e) > 0 then true else false end from VatTuYTeEntity e"
      + " where e.dmLoaiVtytId = ?1 "
      + " and e.ten = ?2 "
      + " and e.maSanPham = ?3 "
      + " and e.dmHangSanXuatId = ?4 "
      + " and  (e.id <> ?5 or ?5 is null) "
  )
  boolean validateMaSanPham(Long dmLoaiVtytId,String tenThuongMai,String maSanPham,Long dmHangSanXuatId,Long id);

}