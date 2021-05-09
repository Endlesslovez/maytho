package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.isofh.may.tho.dao.model.DmThongSoPhanTichEntity;
import vn.isofh.may.tho.dto.DmThongSoPhanTichDTO;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;

public interface DmThongSoPhanTichRepository extends
    DmRepository<DmThongSoPhanTichEntity, DmThongSoPhanTichDTO, Long> {

   @Override
   @Query("select e from DmThongSoPhanTichEntity e where 1 = 1"
       + " and ( text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null )"
       + " and ( text_search(e.ten) like text_search_like(:#{#dto.ten}) or :#{#dto.ten} is null )"
       + " and ( trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)"
       + " and ( trunc(e.updatedAt) = :#{#dto.updatedAt} or cast(:#{#dto.updatedAt} as string) is null)"
       + " and ( :#{@f.isNull(#dto.active)} = true or e.active = :#{#dto.active})"
       + " and ( e.loai = :#{#dto.loai} or :#{#dto.loai} is null)"
       + " and ( e.dmPhuongPhapId = :#{#dto.dmPhuongPhapId} or :#{#dto.dmPhuongPhapId} is null)"
       + " and (exists(select 1 from ThietBiInVitroEntity ee "
       + "left join ee.dmThietBiInVitro dmtb "
       + "left join dmtb.dmThongSoPhanTich ttpt where ttpt.dmPhuongPhapId = :#{#dto.dmPhuongPhapXnId}) or :#{#dto.dmPhuongPhapXnId} is null)"
   )
   Page<DmThongSoPhanTichEntity> search(DmThongSoPhanTichDTO dto, Pageable pageable);

   @Query("select max(to_number(replace(e.ma, ?1, ''), '9999999')) from DmThongSoPhanTichEntity e where e.ma like concat(?1, '%')")
   String getCurrentMa(String prefix);

   @Query("select case when count(e) > 0 then true else false end from DmThongSoPhanTichEntity e where 1 = 1"
       + " and ( e.ten = :ten )"
       + " and ( e.loai = :loai )"
       + " and ( e.id <> :id or :id is null )")
   boolean existsByTenAndLoai(String ten, LoaiThongSoPhanTichEnum loai, Long id);

   @Override
   @Query("select case when count(e) > 0 then true else false end from DmThongSoPhanTichEntity e where e.id = :id"
       + " and ( exists (select 1 from DmThietBiInVitroEntity tt where tt.dmThongSoPhanTichId = e.id)"
       + " or  (exists  (select 1 from ThietBiEntity tb where tb.dmThongSoPhanTichId = e.id)))")
   boolean existsForeignKeyConstraint(Long id);

@Query("select e.id from DmThongSoPhanTichEntity e where 1 = 1" +
        " and (e.ten = ?1)" +
        " and (e.loai = ?2)")
   Long getIdByTen(String ten, LoaiThongSoPhanTichEnum loai);
}
