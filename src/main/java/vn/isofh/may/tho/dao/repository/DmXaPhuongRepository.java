package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmXaPhuongEntity;
import vn.isofh.may.tho.dto.DmXaPhuongDTO;

@Repository
public interface DmXaPhuongRepository extends
    DmRepository<DmXaPhuongEntity, DmXaPhuongDTO, Long> {

  @Override
  @Query("select e from DmXaPhuongEntity e " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (e.dmQuanHuyenId = :#{#dto.dmQuanHuyenId} or :#{#dto.dmQuanHuyenId} is null)")
  Page<DmXaPhuongEntity> search(DmXaPhuongDTO dto, Pageable pageable);
}