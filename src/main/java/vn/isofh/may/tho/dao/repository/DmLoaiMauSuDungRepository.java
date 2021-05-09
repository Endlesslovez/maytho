package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmLoaiMauSuDungEntity;
import vn.isofh.may.tho.dto.DmLoaiMauSuDungDTO;

@Repository
public interface DmLoaiMauSuDungRepository extends
    DmRepository<DmLoaiMauSuDungEntity, DmLoaiMauSuDungDTO, Long> {

   @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmLoaiMauSuDungEntity e where e.ma like concat(?1, '%')")
   String getCurrentMa(String prefix);

   @Override
   @Query("select e from DmLoaiMauSuDungEntity e where 1 = 1"
       + " and ( text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null )"
       + " and ( text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null )"
       + " and ( trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)"
       + " and ( trunc(e.updatedAt) = :#{#dto.updatedAt} or cast(:#{#dto.updatedAt} as string) is null)"
       + " and ( :#{@f.isNull(#dto.active)} = true or e.active = :#{#dto.active})")
   Page<DmLoaiMauSuDungEntity> search(DmLoaiMauSuDungDTO dto, Pageable pageable);

   @Override
   @Query("select case when count(e) > 0 then true else false end from DmLoaiMauSuDungEntity e where e.ten = :ten and (e.id <> :id or :id is null)")
   boolean existsByTen(String ten, Long id);

   @Override
   @Query("select case when count(e) > 0 then true else false end from DmLoaiMauSuDungEntity e where e.id = :id "
       + " and ( exists (select 1 from ThietBiInVitroEntity t where cast(t.dmLoaiMauSuDungIds as string) is not null))")
   boolean existsForeignKeyConstraint(Long id);
}
