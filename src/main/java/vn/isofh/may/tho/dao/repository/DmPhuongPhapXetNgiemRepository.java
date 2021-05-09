package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.isofh.may.tho.dao.model.DmPhuongPhapXetNgiemEntity;
import vn.isofh.may.tho.dto.DmPhuongPhapXetNgiemDTO;

public interface DmPhuongPhapXetNgiemRepository extends
    DmRepository<DmPhuongPhapXetNgiemEntity, DmPhuongPhapXetNgiemDTO, Long> {

   @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmPhuongPhapXetNgiemEntity e where e.ma like concat(?1, '%')")
   String getCurrentMa(String prefix);

   @Override
   @Query("select e from DmPhuongPhapXetNgiemEntity e where 1 = 1"
       + " and ( text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null )"
       + " and ( text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null )"
       + " and (trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)"
       + " and (trunc(e.updatedAt) = :#{#dto.updatedAt} or cast(:#{#dto.updatedAt} as string) is null)"
       + " and (:#{@f.isNull(#dto.active)} = true or e.active = :#{#dto.active})")
   Page<DmPhuongPhapXetNgiemEntity> search(DmPhuongPhapXetNgiemDTO dto, Pageable pageable);

   @Override
   @Query("select case when count(e) > 0 then true else false end from DmPhuongPhapXetNgiemEntity e where e.id = :id"
       + " and ( exists (select 1 from DmThietBiInVitroEntity tt where tt.dmPhuongPhapXetNghiemId = e.id))")
   boolean existsForeignKeyConstraint(Long id);
}
