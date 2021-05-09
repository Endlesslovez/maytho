package vn.isofh.may.tho.dao.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmThietBiEntity;
import vn.isofh.may.tho.dto.DmThietBiDTO;

@Repository
public interface DmThietBiRepository extends DmRepository<DmThietBiEntity, DmThietBiDTO, Long> {

  @Override
  @Query("select e from DmThietBiEntity e" +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (text_search(e.tenVietTat) like text_search_like(:#{#dto.tenVietTat}) or :#{#dto.tenVietTat} is null)" +
      " and (trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)" +
      " and (e.thietBiPhuTro = :#{#dto.thietBiPhuTro} or :#{#dto.thietBiPhuTro} is null)"+
      " and (e.soLuong > 0 or :#{@f.isTrue(#dto.tonTaiSoLuong)} = false)" +
      " and (e.loai = :#{#dto.loai} or :#{#dto.loai} is null)" +
      " and (e.dmNhomTbytId = :#{#dto.dmNhomTbytId} or :#{#dto.dmNhomTbytId} is null)"
  )
  Page<DmThietBiEntity> search(DmThietBiDTO dto, Pageable pageable);

  @Query("select case when count(e) > 0 then true else false end from DmThietBiEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmThietBiEntity e where e.ma like concat(?1, '%')")
  String getCurrentMa(String prefix);

  @Override
  @Query(
      "select case when count(e) > 0 then true else false end from DmThietBiEntity e where e.id = :id"
          + " and (exists (select 1 from DmLoaiThietBiEntity l where l.dmThietBiId = e.id))")
  boolean existsForeignKeyConstraint(Long id);

  @Query("select case when count(e) > 0 then true else false end from DmThietBiEntity e where e.ma = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByMa(String ma, Long id);

  @Query(" select e "
      + " from DmLoaiThietBiEntity ltb2 "
      + " left join ltb2.dmLoaiVtyt1 ltb1"
      + " left join ltb1.dmThietBi e"
      + " where ltb2.id = ?1")
  Optional<DmThietBiEntity> getByNhomVTYT(Long vtyt2Id);

  @Query("select e.tenVietTat from DmThietBiEntity e where e.id = ?1")
  String getTenVietTat(Long dmThietBiId);
}