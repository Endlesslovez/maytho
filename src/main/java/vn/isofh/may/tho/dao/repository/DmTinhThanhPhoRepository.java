package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmTinhThanhPhoEntity;
import vn.isofh.may.tho.dto.DmTinhThanhPhoDTO;

@Repository
public interface DmTinhThanhPhoRepository extends
    DmRepository<DmTinhThanhPhoEntity, DmTinhThanhPhoDTO, Long> {

  @Override
  @Query("select e from DmTinhThanhPhoEntity e " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (e.dmQuocGiaId = :#{#dto.dmQuocGiaId} or :#{#dto.dmQuocGiaId} is null)" +
      " and (:#{#dto.dmNhomVtytId} is null or " +
      "     e.id in (select distinct dv.dmTinhThanhPhoId from VatTuYTeEntity vtyt " +
      "     left join vtyt.dmLoaiVtyt tb2 " +
      "     left join tb2.dmLoaiVtyt1 tb1 " +
      "     left join  tb1.dmThietBi tb " +
      "     left join vtyt.dmDonVi dv " +
      "     where tb.id = :#{#dto.dmNhomVtytId}))" +
      " and (:#{#dto.dmLoaiVtytId} is null or " +
      "     e.id in (select distinct dv.dmTinhThanhPhoId from VatTuYTeEntity vtyt " +
      "     left join vtyt.dmDonVi dv " +
      "     where vtyt.dmLoaiVtytId = :#{#dto.dmLoaiVtytId}))" +
      " and (:#{#dto.dmLoaiThietBiId} is null or e.id in (select distinct d.dmTinhThanhPhoId from ThietBiEntity tb" +
      "     left join tb.dmLoaiThietBi ltb" +
      "     left join tb.dmDonVi d " +
      " where ltb.id = :#{#dto.dmLoaiThietBiId}))" +
      " and (:#{#dto.dmThietBiId} is null or e.id in (select distinct d.dmTinhThanhPhoId from ThietBiEntity tb" +
      "     left join tb.dmLoaiThietBi ltb" +
      "     left join ltb.dmThietBi dmtb" +
      "     left join tb.dmDonVi d " +
      " where dmtb.id = :#{#dto.dmThietBiId}))" +
      " and (:#{#dto.dmNhomTbytId} is null or e.id in (select distinct d.dmTinhThanhPhoId from ThietBiEntity tb" +
      "     left join tb.dmLoaiThietBi ltb" +
      "     left join ltb.dmThietBi dmtb" +
      "     left join dmtb.dmNhomTbyt dmntb " +
      "     left join tb.dmDonVi d " +
      " where dmntb.id = :#{#dto.dmNhomTbytId}) )" +
      " and (:#{#dto.dmThietBiInVitroId} is null or  e.id in  (select distinct dv.dmTinhThanhPhoId from ThietBiInVitroEntity tbivt" +
      "     left join tbivt.dmThietBiInVitro dmtbivt" +
      "     left join dmtbivt.dmThongSoPhanTich dxn" +
      "     left join dxn.dmPhuongPhap pp" +
      "     left join tbivt.dmDonVi dv" +
      " where dmtbivt.id = :#{#dto.dmThietBiInVitroId}))" +
      " and (:#{#dto.dmThongSoPhanTichId} is null or e.id in (select distinct dv.dmTinhThanhPhoId from ThietBiInVitroEntity tbivt" +
      "     left join tbivt.dmThietBiInVitro dmtbivt" +
      "     left join dmtbivt.dmThongSoPhanTich dxn" +
      "     left join tbivt.dmDonVi dv" +
      " where dxn.id = :#{#dto.dmThongSoPhanTichId} ))" +
      " and (:#{#dto.dmPhuongPhapId} is null or e.id in (select distinct dv.dmTinhThanhPhoId from ThietBiInVitroEntity tbivt" +
      "     left join tbivt.dmThietBiInVitro dmtbivt" +
      "     left join dmtbivt.dmThongSoPhanTich dxn" +
      "     left join dxn.dmPhuongPhap pp" +
      "     left join tbivt.dmDonVi dv" +
      " where pp.id = :#{#dto.dmPhuongPhapId}) )"
  )
  Page<DmTinhThanhPhoEntity> search(DmTinhThanhPhoDTO dto, Pageable pageable);
}