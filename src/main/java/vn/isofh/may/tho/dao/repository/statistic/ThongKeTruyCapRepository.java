package vn.isofh.may.tho.dao.repository.statistic;


import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.statistic.ThongKeTruyCapDangNhapEntity;

@Repository
public class ThongKeTruyCapRepository {

  @Autowired
  @Qualifier("entityManagerFactory")
  private EntityManager firstDataManager;

  public Page<ThongKeTruyCapDangNhapEntity> thongKeTruyCapTraCuuGia(ZonedDateTime tuNgay,
      ZonedDateTime denNgay
      , Long dmDonViId, Pageable pageable) {
    List<ThongKeTruyCapDangNhapEntity> list = firstDataManager
        .createNamedStoredProcedureQuery("thongKeTruyCapDangNhap")
        .setParameter("tu_ngay", tuNgay)
        .setParameter("den_ngay", denNgay)
        .setParameter("t_dm_don_vi_id", dmDonViId)
        .getResultList();
    int page = pageable.getPageNumber();
    int count = pageable.getPageSize();
    int max = (count*(page+1)>list.size())? list.size(): count*(page+1);
    return new PageImpl<ThongKeTruyCapDangNhapEntity>(list.subList(page*count, max), pageable, list.size());
  }
}