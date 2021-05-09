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
@Subselect("select * from thong_ke_thiet_bi_theo_trang_thai(null, null, null, null, null, null)")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "thongKeThietBiTheoTrangThai",
        procedureName = "thong_ke_thiet_bi_theo_trang_thai",
        resultClasses = {TrangThaiThietBiEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "p_trang_thai_id",
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
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_don_vi_id",
                type = Long.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_tieu_chi",
                type = Integer.class,
                mode = ParameterMode.IN)})
})
@Setter
@Getter
@NoArgsConstructor
public class TrangThaiThietBiEntity {

  @Id
  private Long id;

  private String maThietBi;

  private String tenThietBi;

  private String maLoaiThietBi;

  private String tenLoaiThietBi;

  private String trangThai;

  private Long trangThaiId;

  private String maTinhThanhPho;

  private String tenTinhThanhPho;

  private Integer soLuong;
}