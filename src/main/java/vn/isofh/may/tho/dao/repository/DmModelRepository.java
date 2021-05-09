package vn.isofh.may.tho.dao.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmModelEntity;
import vn.isofh.may.tho.dto.DmModelDTO;

@Repository
public interface DmModelRepository extends DmRepository<DmModelEntity, DmModelDTO, Long> {

  @Query("select case when count(e) > 0 then true else false end from DmModelEntity e where e.ten = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByTen(String ten, Long id);

  @Override
  @Query("select e from DmModelEntity e " +
      " left join e.dmThietBi dmtb " +
      " where 1 = 1 " +
      " and (text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null)" +
      " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)" +
      " and (e.dmThietBiId = :#{#dto.dmThietBiId} or :#{#dto.dmThietBiId} is null)" +
      " and (e.dmNhomTbytId = :#{#dto.dmNhomTbytId} or :#{#dto.dmNhomTbytId} is null)" +
      " and (e.dmHangSanXuatId = :#{#dto.dmHangSanXuatId} or :#{#dto.dmHangSanXuatId} is null)" +
      " and (e.hangSoHuuId = :#{#dto.hangSoHuuId} or :#{#dto.hangSoHuuId} is null)" +
      " and (exists(select 1 from e.nuocSanXuat nsx where nsx.id = :#{#dto.nuocSanXuatId}) or :#{#dto.nuocSanXuatId} is null)" +
      " and (e.nuocSoHuuId = :#{#dto.nuocSoHuuId} or :#{#dto.nuocSoHuuId} is null)" +
      " and (e.nhaCungCapId = :#{#dto.nhaCungCapId} or :#{#dto.nhaCungCapId} is null)" +
      " and (e.dmLoaiThietBiId = :#{#dto.dmLoaiThietBiId} or :#{#dto.dmLoaiThietBiId} is null)")
  Page<DmModelEntity> search(DmModelDTO dto, Pageable pageable);

  @Query("select max(to_number(e.ma, '9999999')) from DmModelEntity e")
  String getCurrentMa();

  @Override
  @Query("select case when count(e) > 0 then true else false end from DmModelEntity e"
      + " where e.id = ?1 and (exists(select 1 from ThietBiEntity t where t.dmModelId = e.id))")
  boolean existsForeignKeyConstraint(Long id);
}