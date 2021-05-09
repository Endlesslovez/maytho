package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmNguonVonEntity;
import vn.isofh.may.tho.dto.DmNguonVonDTO;

@Repository
public interface DmNguonVonRepository extends
    DmRepository<DmNguonVonEntity, DmNguonVonDTO, Long> {

  @Query("select case when count(e) > 0 then true else false end from DmNguonVonEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmNguonVonEntity e where e.ma like concat(?1, '%')")
  String getCurrentMa(String prefix);

  @Override
  @Query("select e from DmNguonVonEntity e " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)")
  Page<DmNguonVonEntity> search(DmNguonVonDTO dto, Pageable pageable);

  @Override
  @Query("select case when count(e) > 0 then true else false end from DmNguonVonEntity e where e.id = :id"
      + " and (exists (select 1 from ThietBiEntity u where u.dmNguonVonId = e.id))")
  boolean existsForeignKeyConstraint(Long id);
}