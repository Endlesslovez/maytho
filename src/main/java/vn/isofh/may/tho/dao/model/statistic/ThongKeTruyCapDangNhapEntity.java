package vn.isofh.may.tho.dao.model.statistic;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "thongKeTruyCapDangNhap",
        procedureName = "thong_ke_truy_cap_dang_nhap",
        resultClasses = {ThongKeTruyCapDangNhapEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "tu_ngay",
                type = ZonedDateTime.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "den_ngay",
                type = ZonedDateTime.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "t_dm_don_vi_id",
                type = Long.class,
                mode = ParameterMode.IN)

        })
})
@Setter
@Getter
@NoArgsConstructor
public class ThongKeTruyCapDangNhapEntity {

  @Id
  private Long id;

  private String taiKhoan;

  private String hoTen;

  private String donVi;

  private String thoiGianDangNhap;

  private String thoiGianDangXuat;

}