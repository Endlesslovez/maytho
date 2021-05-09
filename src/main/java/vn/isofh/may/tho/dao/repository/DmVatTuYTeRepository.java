package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmVatTuYTeEntity;
import vn.isofh.may.tho.dto.DmVatTuYTeDTO;

@Repository
public interface DmVatTuYTeRepository extends DmRepository<DmVatTuYTeEntity, DmVatTuYTeDTO, Long> {

  @Override
  @Query("select e from DmVatTuYTeEntity e"
      + " left join e.dmLoaiThietBi ltb2 "
      + " left join ltb2.dmLoaiVtyt1 ltb1 "
      + " left join ltb1.dmThietBi dtb "
      + " where 1 = 1"
      + " and ( text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null )"
      + " and ( text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null )"
      + " and ( e.dmLoaiThietBiId = :#{#dto.dmLoaiThietBiId} or :#{#dto.dmLoaiThietBiId} is null)"
      + " and ( text_search(ltb2.ten) like text_search_like(:#{#dto.dmLoaiThietBiTen}) or :#{#dto.dmLoaiThietBiTen} is null )"
      + " and ( text_search(dtb.ten) like text_search_like(:#{#dto.dmThietBiTen}) or :#{#dto.dmThietBiTen} is null )"
      + " and ( ltb1.id = :#{#dto.dmLoaiVtyt1Id} or :#{#dto.dmLoaiVtyt1Id} is null)"
      + " and ( dtb.id = :#{#dto.dmThietBiId} or :#{#dto.dmThietBiId} is null)"
  )
  Page<DmVatTuYTeEntity> search(DmVatTuYTeDTO dto, Pageable pageable);

  @Query("select max(e.ma) from DmVatTuYTeEntity e where 1=1"
      + " and text_search(e.ma) like text_search_like(:ma)")
  String getMaLonNhat(String ma);

  @Query("select case when count(e) > 0 then true else false end "
      + "from DmVatTuYTeEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Query("select case when count(e) > 0 then true else false end "
      + "from DmVatTuYTeEntity e where e.ma = ?1 and e.dmLoaiThietBiId= ?2 and (e.id <> ?3 or ?3 is null)")
  boolean existsByMaAAndDmLoaiThietBiId(String ma,Long dmLoaiThietBiID, Long id);

  @Query("select case when count(e) > 0 then true else false end "
      + "from DmVatTuYTeEntity e where e.dmLoaiThietBiId= ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByDmLoaiThietBiId(Long dmLoaiThietBiID, Long id);

  @Override
  @Query("select case when count(e) > 0 then true else false end from DmVatTuYTeEntity e where e.id = :id"
      + " and (exists (select 1 from VatTuYTeEntity vt where 1 = 1 and vt.dmVatTuYTeId = e.id))")
  boolean existsForeignKeyConstraint(Long id);
}