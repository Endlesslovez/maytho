package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmQuocGiaEntity;
import vn.isofh.may.tho.dto.DmQuocGiaDTO;

@Repository
public interface DmQuocGiaRepository extends DmRepository<DmQuocGiaEntity, DmQuocGiaDTO, Long> {

  @Override
  @Query("select e from DmQuocGiaEntity e " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)")
  Page<DmQuocGiaEntity> search(DmQuocGiaDTO dto, Pageable pageable);
}