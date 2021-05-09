package vn.isofh.may.tho.dao.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.ThietBiEntity;
import vn.isofh.may.tho.dto.ThietBiDTO;

@Repository
public interface ThietBiRepository extends BaseRepository<ThietBiEntity, ThietBiDTO, Long> {

  @Override
  @Query("select e from ThietBiEntity e " +
      " left join e.dmTrangThai dtt" +
      " left join e.dmDonViTinh ddvt" +
      " left join e.dmDonVi csyt" +
      " left join csyt.dmTinhThanhPho ttp" +
      " left join csyt.dmQuanHuyen qh" +
      " left join csyt.dmXaPhuong xp" +
      " left join e.dmLoaiThietBi ltb" +
      " left join ltb.dmThietBi dmtb" +
      " left join dmtb.dmNhomTbyt ntbyt" +
      " left join e.dmModel md" +
      " left join md.dmHangSanXuat dhsx "+
      " where 1 = 1 and e.thietBiChinhId is null" +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (text_search(e.serial) like text_search_like(:#{#dto.serial}) or :#{#dto.serial} is null)" +
      " and (e.giaNiemYet >= 0 or :#{@f.isTrue(#dto.tonTaiGiaNiemYet)} = false)" +
      " and (e.giaNiemYet >= :#{#dto.giaTu} or :#{#dto.giaTu} is null)" +
      " and (e.giaNiemYet <= :#{#dto.giaDen} or :#{#dto.giaDen} is null)" +
      " and (e.namSuDung = :#{#dto.namSuDung} or :#{#dto.namSuDung} is null)" +
      " and (e.dmDonViId = :#{#dto.dmDonViId} or :#{#dto.dmDonViId} is null)" +
      " and (e.dmDonViId in :#{#dto.dmDonViIds} or :#{#dto.dmDonViIds} is null)" +
      " and (e.dmLoaiThietBiId in :#{#dto.dmLoaiThietBiIds} or :#{#dto.dmLoaiThietBiIds} is null)" +
      " and (e.dmLoaiThietBiId = :#{#dto.dmLoaiThietBiId} or :#{#dto.dmLoaiThietBiId} is null)" +
      " and (ltb.dmThietBiId = :#{#dto.dmThietBiId} or :#{#dto.dmThietBiId} is null)" +
      " and (ltb.dmThietBiId in :#{#dto.dmThietBiIds} or :#{#dto.dmThietBiIds} is null)" +
      " and (dmtb.dmNhomTbytId = :#{#dto.dmNhomThietBiId} or :#{#dto.dmNhomThietBiId} is null)" +
      " and (dmtb.dmNhomTbytId = :#{#dto.dmNhomTbytId} or :#{#dto.dmNhomTbytId} is null)" +
      " and (e.dmTrangThaiId = :#{#dto.dmTrangThaiId} or :#{#dto.dmTrangThaiId} is null)" +
      " and (csyt.dmTinhThanhPhoId in :#{#dto.dmTinhThanhPhoIds} or :#{#dto.dmTinhThanhPhoIds} is null)" +
      " and (csyt.dmQuanHuyenId = :#{#dto.dmQuanHuyenId} or :#{#dto.dmQuanHuyenId} is null)" +
      " and (csyt.dmXaPhuongId = :#{#dto.dmXaPhuongId} or :#{#dto.dmXaPhuongId} is null)" +
      " and (text_search(ttp.ten) like text_search_like(:#{#dto.dmTinhThanhPhoTen}) or :#{#dto.dmTinhThanhPhoTen} is null)" +
      " and (text_search(qh.ten) like text_search_like(:#{#dto.dmQuanHuyenTen}) or :#{#dto.dmQuanHuyenTen} is null)" +
      " and (text_search(xp.ten) like text_search_like(:#{#dto.dmXaPhuongTen}) or :#{#dto.dmXaPhuongTen} is null)" +
      " and (text_search(dtt.ten) like text_search_like(:#{#dto.dmDonViTinhTen}) or :#{#dto.dmDonViTinhTen} is null)" +
      " and (text_search(ltb.ten) like text_search_like(:#{#dto.loaiThietBiTen}) or :#{#dto.loaiThietBiTen} is null)" +
      " and (text_search(ddvt.ten) like text_search_like(:#{#dto.dmTrangThaiTen}) or :#{#dto.dmTrangThaiTen} is null)" +
      " and (e.giaNiemYet = :#{#dto.giaNiemYet} or :#{#dto.giaNiemYet} is null)" +
      " and (text_search(e.linhKien) like text_search_like(:#{#dto.linhKien}) or :#{#dto.linhKien} is null)" +
      " and (text_search(e.vatTuTieuHao) like text_search_like(:#{#dto.vatTuTieuHao}) or :#{#dto.vatTuTieuHao} is null)" +
      " and (text_search(e.dichVuBaoDuong) like text_search_like(:#{#dto.dichVuBaoDuong}) or :#{#dto.dichVuBaoDuong} is null)" +
      " and (text_search(e.dichVuKyThuat) like text_search_like(:#{#dto.dichVuKyThuat}) or :#{#dto.dichVuKyThuat} is null)" +
      " and (e.namSanXuat = :#{#dto.namSanXuat} or :#{#dto.namSanXuat} is null)" +
      " and (exists(select 1 from DmQuocGiaEntity nsx where nsx.id = any_string_to_number_array(e.nuocSanXuatIds)) or :#{#dto.dmNuocSanXuatTen} is null)" +
      " and (text_search(e.ten) like text_search_like(:#{#dto.timKiem})" +
      " or text_search(md.ten) like text_search_like(:#{#dto.timKiem})" +
      " or text_search(dhsx.ten) like text_search_like(:#{#dto.timKiem})" +
      " or e.giaNiemYet <= :#{#dto.giaNiemYetLonNhat}" +
      " or :#{#dto.timKiem} is null)" +
      " and (trunc(e.ngayHetHieuLuc) = :#{#dto.ngayHetHieuLuc} or cast(:#{#dto.ngayHetHieuLuc} as string) is null)"+
      " and (e.id <> :#{#dto.idLoaiBo} or :#{#dto.idLoaiBo} is null)" +
      " and (e.device = :#{#dto.device} or :#{#dto.device} is null)" +
      " and (e.active = :#{#dto.active} or :#{#dto.active} is null)" +
      " and (e.congKhaiGia = :#{#dto.congKhaiGia} or :#{#dto.congKhaiGia} is null)"+
      " and (dhsx.id in :#{#dto.dmHangSanXuatIds} or :#{#dto.dmHangSanXuatIds} is null)"
  )
  Page<ThietBiEntity> search(ThietBiDTO dto, Pageable pageable);

  @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from ThietBiEntity e where e.ma like concat(?1, '%')")
  String getCurrentMa(String prefix);

  @Query("select max(to_number(replace(e.serial, ?1, ''), '9999999')) from ThietBiEntity e where e.serial like concat(?1, '%')")
  String getCurrentSerial(String prefix);

  Set<ThietBiEntity> findByThietBiChinhId(Long id);

  List<ThietBiEntity> findByDmLoaiThietBiId(Long dmLoaiThietBiId);

  @Query("select e from ThietBiEntity e where 1 = 1 "
      + " and (trunc(e.ngayHetHieuLuc) = ?1)")
  Set<ThietBiEntity> getByNgayHieuLuc(LocalDate ngayHetHieuLuc);

  @Query("select count(e) from ThietBiEntity e where 1 = 1"
      + " and (e.dmNhomTbytId = ?1)")
  Integer getSoLuong(Long dmNhomTbytId);


}