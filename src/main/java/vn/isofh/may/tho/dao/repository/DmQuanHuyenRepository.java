package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmQuanHuyenEntity;
import vn.isofh.may.tho.dto.DmQuanHuyenDTO;

@Repository
public interface DmQuanHuyenRepository extends
    DmRepository<DmQuanHuyenEntity, DmQuanHuyenDTO, Long> {

  @Override
  @Query("select e from DmQuanHuyenEntity e " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (e.dmTinhThanhPhoId = :#{#dto.dmTinhThanhPhoId} or :#{#dto.dmTinhThanhPhoId} is null)")
  Page<DmQuanHuyenEntity> search(DmQuanHuyenDTO dto, Pageable pageable);
}