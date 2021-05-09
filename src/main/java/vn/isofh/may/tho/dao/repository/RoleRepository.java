package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.RoleEntity;
import vn.isofh.may.tho.dto.RoleDTO;

@Repository
public interface RoleRepository extends DmRepository<RoleEntity, RoleDTO, Long> {

  @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from RoleEntity e where e.ma like concat(?1, '%')")
  String getCurrentMa(String prefix);

  @Query("select case when count(e) > 0 then true else false end from RoleEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Override
  @Query("select e from RoleEntity e " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)")
  Page<RoleEntity> search(RoleDTO dto, Pageable pageable);
}