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
        name = "thongKeTruyCapTraCuuGia",
        procedureName = "thong_ke_truy_cap_tra_cuu_gia",
        resultClasses = {ThongKeTruyCapTraCuuGiaEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "tu_ngay",
                type = ZonedDateTime.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "den_ngay",
                type = ZonedDateTime.class,
                mode = ParameterMode.IN)
        })
})
@Setter
@Getter
@NoArgsConstructor
public class ThongKeTruyCapTraCuuGiaEntity {

  @Id
  private Long id;

  private String ip;

  private String vi_tri;

  private Long so_luong;

  private String ngay_truy_cap;

}