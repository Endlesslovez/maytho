package vn.isofh.may.tho.dao.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmLoaiThietBiEntity;
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.enums.DmLoaiThietBiEnum;

@Repository
public interface DmLoaiThietBiRepository extends
    DmRepository<DmLoaiThietBiEntity, DmLoaiThietBiDTO, Long> {

  @Query("select case when count(e) > 0 then true else false end from DmLoaiThietBiEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmLoaiThietBiEntity e where e.ma like concat(?1, '%')")
  String getCurrentMa(String prefix);

  @Override
  @Query("select e from DmLoaiThietBiEntity e "
      + " left join e.dmLoaiVtyt1 loaiVtyt1"
      + " left join loaiVtyt1.dmThietBi nhomVtyt "
      + " where 1 = 1 "
      + " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)"
      + " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)"
      + " and (trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)"
      + " and (e.thietBiPhuTro = :#{#dto.thietBiPhuTro} or :#{#dto.thietBiPhuTro} is null)"
      + " and (e.dmThietBiId = :#{#dto.dmThietBiId} or :#{#dto.dmThietBiId} is null)"
      + " and (e.dmLoaiVtyt1Id = :#{#dto.dmLoaiVtyt1Id} or :#{#dto.dmLoaiVtyt1Id} is null)"
      + " and (e.loai = :#{#dto.loai} or :#{#dto.loai} is null)"
      + " and (e.dmThietBiId in :#{#dto.dmThietBiIds} or :#{#dto.dmThietBiIds} is null)"
      + " and (e.dmNhomTbytId = :#{#dto.dmNhomTbytId} or :#{#dto.dmNhomTbytId} is null)"
      + " and (:#{@f.isTrue(#dto.tonTaiThietBi)} = false or exists (select 1 from ThietBiEntity ee where ee.dmLoaiThietBiId = e.id))"
      + " and (nhomVtyt.id = :#{#dto.dmNhomVtytId} or :#{#dto.dmNhomVtytId} is null)"
  )
  Page<DmLoaiThietBiEntity> search(DmLoaiThietBiDTO dto, Pageable pageable);

  List<DmLoaiThietBiEntity> findByDmThietBiId(Long id);

  @Query("select case when count(e) > 0 then true else false end "
      + " from DmLoaiThietBiEntity e where e.ma = ?1 "
      + " and (e.id <> ?2 or ?2 is null) "
      + " and ( e.loai = ?3)")
  boolean existsByMa(String ma, Long id, DmLoaiThietBiEnum type);

  @Override
  @Query("select case when count(e) > 0 then true else false end from DmLoaiThietBiEntity e where e.id = :id"
      + " and (exists (select 1 from ThietBiEntity ttb where ttb.dmLoaiThietBiId = e.id)"
      + " or exists (select 1 from DmVatTuYTeEntity vt where vt.dmLoaiThietBiId = e.id)"
      + " or exists (select 1 from DmLoaiThietBiEntity lt where lt.dmLoaiVtyt1Id = e.id))")
  boolean existsForeignKeyConstraint(Long id);

  @Query("select e "
      + " from DmVatTuYTeEntity vtyt "
      + " left join vtyt.dmLoaiThietBi e "
      + " where vtyt.id = ?1 "
     )
  Optional<DmLoaiThietBiEntity> findByDmVatTuYTeId(Long dmVatTuYTeId);

  @Query("select e.id from DmLoaiThietBiEntity e where 1 = 1" +
          " and (e.ten = ?1)" +
          " and (e.loai = ?2)")
  Long getIdByTenAndLoai(String ten, DmLoaiThietBiEnum loai);
}