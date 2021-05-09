package vn.isofh.may.tho.dao.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmDonViEntity;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.enums.LoaiDonViEnum;

@Repository
public interface DmDonViRepository extends DmRepository<DmDonViEntity, DmDonViDTO, Long> {

  @Query("select case when count(e) > 0 then true else false end from DmDonViEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Query("select case when count(e) > 0 then true else false end from DmDonViEntity e where e.ma = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByMa(String ma, Long id);

  @Query("select case when count(e) > 0 then true else false end from DmDonViEntity e where e.maSoThue = ?1 and (e.id <> ?2 or ?2 is null) and e.loaiDonVi = ?3")
  boolean existsByMaSoThue(String maSoThue, Long id, LoaiDonViEnum loaiDonVi);

  @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmDonViEntity e where e.ma like concat(?1, '%')")
  String getCurrentMa(String prefix);

  @Query("select e.id from DmDonViEntity e where concat(',', e.coQuanQuanLyIds, ',') like concat('%,', ?1, ',%')")
  List<Long> getIdByCoQuanQuanLyId(Long coQuanQuanLyId);

  @Override
  @Query("select e from DmDonViEntity e " +
      " left join e.dmTinhThanhPho tp" +
      " where 1 = 1 " +
      " and ((e.id = :#{#dto.id} or :#{#dto.id} is null)" +
      " or (e.id in :#{#dto.dmDonViIds} or :#{#dto.dmDonViIds} is null))" +
      " and (e.id = :#{#dto.ids} or :#{#dto.ids} is null)" +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (text_search(e.maSoThue) like text_search_like(:#{#dto.maSoThue}) or :#{#dto.maSoThue} is null)" +
      " and (text_search(e.soDienThoai) like text_search_like(:#{#dto.soDienThoai}) or :#{#dto.soDienThoai} is null)" +
      " and (text_search(e.nguoiLienHe) like text_search_like(:#{#dto.nguoiLienHe}) or :#{#dto.nguoiLienHe} is null)" +
      " and (text_search(e.nguoiDaiDienPhapLuat) like text_search_like(:#{#dto.nguoiDaiDienPhapLuat}) or :#{#dto.nguoiDaiDienPhapLuat} is null)" +
      " and (exists (select 1 from DmDonViEntity ee where text_search(ee.ten) like text_search_like(:#{#dto.coQuanQuanLyTen})" +
      " and concat(',', e.coQuanQuanLyIds, ',') like concat('%,', ee.id, ',%')) or :#{#dto.coQuanQuanLyTen} is null)" +
      " and (concat(',', e.coQuanQuanLyIds, ',') like concat('%,', cast(:#{#dto.coQuanQuanLyId} as string), ',%') or :#{#dto.coQuanQuanLyId} is null)" +
      " and (e.loaiCsyt = :#{#dto.loaiCsyt} or :#{#dto.loaiCsyt} is null)" +
      " and (e.loaiDonVi = :#{#dto.loaiDonVi} or :#{#dto.loaiDonVi} is null)" +
      " and (e.dmTinhThanhPhoId = :#{#dto.dmTinhThanhPhoId} or :#{#dto.dmTinhThanhPhoId} is null)" +
      " and (text_search(tp.ten) like text_search_like(:#{#dto.tenThanhPho}) or :#{#dto.tenThanhPho} is null)" +
      " and (e.dmQuanHuyenId = :#{#dto.dmQuanHuyenId} or :#{#dto.dmQuanHuyenId} is null)" +
      " and (e.dmXaPhuongId = :#{#dto.dmXaPhuongId} or :#{#dto.dmXaPhuongId} is null)" +
      " and (e.id in (select distinct dv.id from ThietBiEntity tb" +
      "     left join tb.dmLoaiThietBi ltb " +
      "     left join tb.dmModel md" +
      "     left join md.dmHangSanXuat dv " +
      " where ltb.id = :#{#dto.dmLoaiThietBiId} ) or :#{#dto.dmLoaiThietBiId} is null)" +
      " and (e.id in (select distinct dv.id from ThietBiEntity tb" +
      "     left join tb.dmLoaiThietBi ltb " +
      "     left join ltb.dmThietBi dmtb " +
      "     left join tb.dmModel md" +
      "     left join md.dmHangSanXuat dv " +
      " where dmtb.id = :#{#dto.dmThietBiId}) or :#{#dto.dmThietBiId} is null)" +
      " and (e.id in (select distinct dv.id from ThietBiEntity tb" +
      "     left join tb.dmLoaiThietBi ltb " +
      "     left join ltb.dmThietBi dmtb " +
      "     left join dmtb.dmNhomTbyt dmntb " +
      "     left join tb.dmModel md" +
      "     left join md.dmHangSanXuat dv " +
      " where dmntb.id = :#{#dto.dmNhomTbytId}) or :#{#dto.dmNhomTbytId} is null)" +
      " and (:#{#dto.dmNhomVtytId} is null or " +
      "     e.id in (select distinct dv.id from VatTuYTeEntity vtyt " +
      "     left join vtyt.dmLoaiVtyt tb2 " +
      "     left join tb2.dmLoaiVtyt1 tb1 " +
      "     left join  tb1.dmThietBi tb " +
      "     left join vtyt.dmHangSanXuat dv " +
      "     where tb.id = :#{#dto.dmNhomVtytId}))" +
      " and (:#{#dto.dmLoaiVtytId} is null or  " +
      "   e.id in (select distinct dv.id from VatTuYTeEntity vtyt " +
      "   left join vtyt.dmHangSanXuat dv  " +
      "   where vtyt.id = :#{#dto.dmLoaiVtytId}))" +
      " and (e.id in ( select distinct dv.id from ThietBiInVitroEntity tbivd" +
      "     left join tbivd.dmThietBiInVitro dmtbivt" +
      "     left join dmtbivt.dmThongSoPhanTich dxn " +
      "     left join dxn.dmPhuongPhap pp" +
      "     left join tbivd.dmDonVi dv" +
      " where pp.id = :#{#dto.dmPhuongPhapId}) or :#{#dto.dmPhuongPhapId} is null)" +
      " and (e.id in ( select distinct dv.id from ThietBiInVitroEntity tbivd" +
      "     left join tbivd.dmThietBiInVitro dmtbivt" +
      "     left join dmtbivt.dmThongSoPhanTich dxn " +
      "     left join tbivd.dmDonVi dv" +
      " where dxn.id = :#{#dto.dmThongSoPhanTichId}) or :#{#dto.dmThongSoPhanTichId} is null)" +
      " and (e.id in ( select distinct dv.id from ThietBiInVitroEntity tbivd" +
      "     left join tbivd.dmThietBiInVitro dmtbivt" +
      "     left join tbivd.dmDonVi dv " +
      " where dmtbivt.id = :#{#dto.dmThietBiInVitroId}) or :#{#dto.dmThietBiInVitroId} is null)"
  )
  Page<DmDonViEntity> search(DmDonViDTO dto, Pageable pageable);

  @Override
  @Query("select case when count(e) > 0 then true else false end from DmDonViEntity e where e.id = :id"
      + " and (exists (select 1 from UserEntity u where u.dmDonViId = e.id)"
      + " or exists (select 1 from DmDonViEntity ee where ee.id = any_string_to_number_array(e.coQuanQuanLyIds))"
      + " or exists (select 1 from DmModelEntity ncc where ncc.nhaCungCapId = e.id)"
      + " or exists (select 1 from DmModelEntity hsh where hsh.hangSoHuuId = e.id)"
      + " or exists (select 1 from DmModelEntity hsx where hsx.dmHangSanXuatId = e.id))")
  boolean existsForeignKeyConstraint(Long id);
}