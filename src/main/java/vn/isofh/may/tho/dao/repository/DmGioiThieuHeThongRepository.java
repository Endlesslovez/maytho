package vn.isofh.may.tho.dao.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.DmGioiThieuHeThongEntity;
import vn.isofh.may.tho.dto.DmGioiThieuHeThongDTO;

@Repository
public interface DmGioiThieuHeThongRepository extends
        DmRepository<DmGioiThieuHeThongEntity, DmGioiThieuHeThongDTO, Long> {

    List<DmGioiThieuHeThongEntity> findByActive(Boolean active);

    @Override
    @Query("select e from DmGioiThieuHeThongEntity e where 1 = 1"
            + " and (text_search(e.canCuPhapLy) like text_search_like(:#{#dto.canCuPhapLy}) or :#{#dto.canCuPhapLy} is null )"
            + " and (text_search(e.khaiNiemCongKhaiGia) like text_search_like(:#{#dto.khaiNiemCongKhaiGia}) or :#{#dto.khaiNiemCongKhaiGia} is null )"
            + " and (text_search(e.canCuPhapLy) like text_search_like(:#{#dto.canCuPhapLy}) or :#{#dto.canCuPhapLy} is null )"
            + " and (text_search(e.taiLieuThamKhao) like text_search_like(:#{#dto.taiLieuThamKhao}) or :#{#dto.taiLieuThamKhao} is null)"
            + " and (text_search(e.phuongThucThucHien) like text_search_like(:#{#dto.phuongThucThucHien}) or :#{#dto.phuongThucThucHien} is null)"
            + " and (:#{@f.isNull(#dto.active)} = true or e.active = :#{#dto.active})")
    Page<DmGioiThieuHeThongEntity> search(DmGioiThieuHeThongDTO dto, Pageable pageable);
}
