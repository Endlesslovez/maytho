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
@Subselect("select * from thong_ke_thiet_bi_dieu_chuyen(null, null, null, null, null, null)")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "thongKeThietBiDieuChuyen",
        procedureName = "thong_ke_thiet_bi_dieu_chuyen",
        resultClasses = {DieuChuyenThietBiEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "p_co_so_y_te_id",
                type = Long.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_tinh_thanh_pho_id",
                type = Long.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_quan_huyen_id",
                type = Long.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_xa_phuong_id",
                type = Long.class,
                mode = ParameterMode.IN)})
})
@Setter
@Getter
@NoArgsConstructor
public class DieuChuyenThietBiEntity {

  @Id
  private Long id;

  private String soLuong;

  private String maThietBi;

  private String tenThietBi;

  private String trangThai;

  private String maTrangThai;
}