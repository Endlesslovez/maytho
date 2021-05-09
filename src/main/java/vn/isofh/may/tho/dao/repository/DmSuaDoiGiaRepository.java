package vn.isofh.may.tho.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmSuaDoiGiaEntity;
import vn.isofh.may.tho.dto.DmSuaDoiGiaDTO;

@Repository
public interface DmSuaDoiGiaRepository extends
    DmRepository<DmSuaDoiGiaEntity, DmSuaDoiGiaDTO, Long> {

   @Override
   @Query("select e from DmSuaDoiGiaEntity e where 1 = 1"
       + " and (e.giaTruocThayDoi = :#{#dto.giaTruocThayDoi} or :#{#dto.giaTruocThayDoi} is null)"
       + " and (e.giaSauThayDoi = :#{#dto.giaSauThayDoi} or :#{#dto.giaSauThayDoi} is null)"
       + " and (trunc(e.tuNgay) = :#{#dto.tuNgay} or cast(:#{#dto.tuNgay} as string) is null)"
       + " and (trunc(e.denNgay) = :#{#dto.tuNgay} or cast(:#{#dto.tuNgay} as string) is null)"
       + " and (trunc(e.ngayBatDauHieuLuc) = :#{#dto.ngayBatDauHieuLuc} or cast(:#{#dto.ngayBatDauHieuLuc} as string) is null)"
       + " and (text_search(e.canCuCauThanhGia) like text_search_like(:#{#dto.canCuCauThanhGia}) or :#{#dto.canCuCauThanhGia} is null)"
       + " and (text_search(e.taiLieuCanCuCauThanhGia) like text_search_like(:#{#dto.taiLieuCanCuCauThanhGia}) or :#{#dto.taiLieuCanCuCauThanhGia} is null)"
       + " and (e.thietBiId = :#{#dto.thietBiId} or :#{#dto.thietBiId} is null) "
       + " and (e.vatTuYTeId = :#{#dto.vatTuYTeId} or :#{#dto.vatTuYTeId} is null) "
       + " and (e.thietBiInVitroId = :#{#dto.thietBiInVitroId} or :#{#dto.thietBiInVitroId} is null) "
       + " and (text_search(e.dienGiaiCnGia) like text_search_like(:#{#dto.dienGiaiCnGia}) or :#{#dto.dienGiaiCnGia} is null)")
   Page<DmSuaDoiGiaEntity> search(DmSuaDoiGiaDTO dto, Pageable pageable);

   @Query("select e from DmSuaDoiGiaEntity e where 1 = 1"
       + " and e.id = (select max(ee.id) from DmSuaDoiGiaEntity ee where ee.thietBiId = ?1)")
   DmSuaDoiGiaEntity getByThietBiId(Long thietBiId);

   @Query("select e from DmSuaDoiGiaEntity e where 1 = 1"
       + " and e.id = (select max(ee.id) from DmSuaDoiGiaEntity ee where ee.vatTuYTeId = ?1)")
   DmSuaDoiGiaEntity getByVatTuYTeId(Long vatTuYTeId);
}