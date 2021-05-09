package vn.isofh.may.tho.dao.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.isofh.may.tho.dao.model.DmThietBiInVitroEntity;
import vn.isofh.may.tho.dto.DmThietBiInVitroDTO;

public interface DmThietBiInVitroRepository extends
    DmRepository<DmThietBiInVitroEntity, DmThietBiInVitroDTO, Long> {

   @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmThietBiInVitroEntity e where e.ma like concat(?1, '%')")
   String getCurrentMa(String prefix);

   @Override
   @Query("select e from DmThietBiInVitroEntity e where 1 = 1"
       + " and ( text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null )"
       + " and ( text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null )"
       + " and ( trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)"
       + " and ( trunc(e.updatedAt) = :#{#dto.updatedAt} or cast(:#{#dto.updatedAt} as string) is null)"
       + " and ( :#{@f.isNull(#dto.active)} = true or e.active = :#{#dto.active})"
       + " and ( e.dmPhuongPhapXetNghiemId = :#{#dto.dmPhuongPhapXetNghiemId} or :#{#dto.dmPhuongPhapXetNghiemId} is null)"
       + " and ( e.dmThongSoPhanTichId = :#{#dto.dmThongSoPhanTichId} or :#{#dto.dmThongSoPhanTichId} is null)")
   Page<DmThietBiInVitroEntity> search(DmThietBiInVitroDTO dto, Pageable pageable);

   List<DmThietBiInVitroEntity> findByDmPhuongPhapXetNghiemId(Long id);

   @Override
   @Query("select case when count(e) > 0 then true else false end from DmThietBiInVitroEntity e where e.id = :id"
       + " and ( exists (select 1 from ThietBiInVitroEntity tt where tt.dmThietBiInVitroId = e.id))")
   boolean existsForeignKeyConstraint(Long id);
}
