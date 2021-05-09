package vn.isofh.may.tho.dao.repository;

import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.DmEntity;
import vn.isofh.may.tho.dto.DmBaseDTO;
import vn.isofh.may.tho.dto.DmDTO;

@NoRepositoryBean
public interface DmRepository<Entity extends DmEntity, DTO extends DmDTO, ID extends Long>
    extends BaseRepository<Entity, DTO, ID> {

  @Query("select e.id from #{#entityName} e where e.ma = :ma and e.active = true")
  Optional<Long> findIdByMa(String ma);

  @Query("select e.id from #{#entityName} e where e.ten = :ten and e.active = true")
  Optional<Long> findIdByTen(String ten);

  @Query("select e from #{#entityName} e where e.ten = :ten and e.active = true")
  Optional<Entity> findByTen(String ten);

  @Query("select e from #{#entityName} e where e.ma = :ma and e.active = true")
  Optional<Entity> findByMa(String ma);

  @Query("select case when count(e) > 0 then true else false end from #{#entityName} e"
      + " where e.ma = :ma and (e.id <> :id or :id is null)")
  boolean existsByMa(String ma, Long id);

  @Query("select case when count(e) > 0 then true else false end from #{#entityName} e"
      + " where e.ten = :ten and (e.id <> :id or :id is null)")
  boolean existsByTen(String ten, Long id);

  @Transactional(readOnly = true)
  @Cacheable
  @Query("select new vn.isofh.may.tho.dto.DmBaseDTO(e.id, e.ma, e.ten) from #{#entityName} e where e.id = ?1")
  <X extends DmBaseDTO> Optional<X> findBaseDTOById(Long id);
}