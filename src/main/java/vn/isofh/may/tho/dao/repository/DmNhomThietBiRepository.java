package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.isofh.may.tho.dao.model.DmNhomThietBiEntity;
import vn.isofh.may.tho.dto.DmNhomThietBiDTO;

public interface DmNhomThietBiRepository extends
    DmRepository<DmNhomThietBiEntity, DmNhomThietBiDTO, Long> {

   @Override
   @Query("select e from DmNhomThietBiEntity e where 1 = 1 "
       + " and (e.loai = :#{#dto.loai} or :#{#dto.loai} is null)"
       + " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null )"
       + " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)"
       + " and (e.active = :#{#dto.active} or :#{#dto.active} is null)")
   Page<DmNhomThietBiEntity> search(DmNhomThietBiDTO dto, Pageable pageable);
}
