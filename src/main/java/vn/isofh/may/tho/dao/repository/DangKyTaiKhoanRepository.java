package vn.isofh.may.tho.dao.repository;

import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.DangKyTaiKhoanEntity;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO;

@Repository
public interface DangKyTaiKhoanRepository extends
    BaseRepository<DangKyTaiKhoanEntity, DangKyTaiKhoanDTO, Long> {

  @Override
  @Query("select e from DangKyTaiKhoanEntity e "
      + " left join e.dmQuanHuyen qh "
      + " left join e.dmTinhThanhPho tp "
      + " left join e.dmXaPhuong xp "
      + " where 1 = 1 "
      + " and e.active = false "
      + " and (text_search(e.maSoThue) like text_search_like(:#{#dto.maSoThue}) or :#{#dto.maSoThue} is null)"
      + " and (text_search(e.email) like text_search_like(:#{#dto.email}) or :#{#dto.email} is null)"
      + " and (text_search(e.tenDoanhNghiep) like text_search_like(:#{#dto.tenDoanhNghiep}) or :#{#dto.tenDoanhNghiep} is null)"
      + " and (text_search(e.diaChiChiTiet) like text_search_like(:#{#dto.diaChiChiTiet}) or :#{#dto.diaChiChiTiet} is null)"
      + " and (text_search(qh.ten) like text_search_like(:#{#dto.tenQuanHuyen}) or :#{#dto.tenQuanHuyen} is null)"
      + " and (text_search(tp.ten) like text_search_like(:#{#dto.tenTinhThanhPho}) or :#{#dto.tenTinhThanhPho} is null)"
      + " and (text_search(xp.ten) like text_search_like(:#{#dto.tenXaPhuong}) or :#{#dto.tenXaPhuong} is null)"
      + " and (text_search(e.nguoiDaiDienDoanhNghiep) like text_search_like(:#{#dto.nguoiDaiDienDoanhNghiep}) or :#{#dto.nguoiDaiDienDoanhNghiep} is null)"
      + " and (text_search(e.soDienThoai) like text_search_like(:#{#dto.soDienThoai}) or :#{#dto.soDienThoai} is null)"
  )
  Page<DangKyTaiKhoanEntity> search(DangKyTaiKhoanDTO dto, Pageable pageable);

  @Query("select case when count(e) > 0 then true else false end "
      + " from DangKyTaiKhoanEntity e where e.maSoThue = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByMaSoThue(String maSoThue, Long id);

  @Query("select case when count(e) > 0 then true else false end "
      + " from DangKyTaiKhoanEntity e where e.email = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByEmail(String email, Long id);

  @Query("select e "
      + " from DangKyTaiKhoanEntity e where "
      + " e.active = ?1 "
      + " and e.createdAt >= ?2 "
  )
  List<DangKyTaiKhoanEntity> findAllByActive(Boolean active, ZonedDateTime timeFrom);
}