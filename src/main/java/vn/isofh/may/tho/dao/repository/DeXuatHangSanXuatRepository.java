package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.DeXuatHangSanXuatEntity;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO;

@Repository
public interface DeXuatHangSanXuatRepository extends
    BaseRepository<DeXuatHangSanXuatEntity, DeXuatHangSanXuatDTO, Long> {

  @Override
  @Query("select e from DeXuatHangSanXuatEntity e "
      + " left join e.dmDonVi dv "
      + " where 1 = 1 "
      + " and (e.active =  :#{#dto.active} or :#{#dto.active} is null) "
      + " and (text_search(e.tenHangSanXuat) like text_search_like(:#{#dto.tenHangSanXuat}) or :#{#dto.tenHangSanXuat} is null)"
      + " and (text_search(dv.ten) like text_search_like(:#{#dto.nhaCCUyQuyen}) or :#{#dto.nhaCCUyQuyen} is null)"
      + " and (e.dmDonViId = :#{#dto.dmDonViId} or :#{#dto.dmDonViId} is null) "
  )
  Page<DeXuatHangSanXuatEntity> search(DeXuatHangSanXuatDTO dto, Pageable pageable);
}