package vn.isofh.may.tho.dao.model.statistic;

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
@Subselect("select * from thong_ke_co_so_y_te_da_nhap(null, null)")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "thongKeCoSoYTeDaNhap",
        procedureName = "thong_ke_co_so_y_te_da_nhap",
        resultClasses = {ThongKeDonViEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "p_tinh_thanh_pho_id",
                type = Long.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_co_so_y_te_id",
                type = Long.class,
                mode = ParameterMode.IN)})
})
@Setter
@Getter
@NoArgsConstructor
public class ThongKeDonViEntity {

  @Id
  private Long id;

  private Long daNhap;

  private Long chuaNhap;

  private Long tinhThanhPhoId;

  private String maTinh;

  private String tenTinh;
}