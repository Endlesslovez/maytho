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
@Subselect("select * from thong_ke_thiet_bi_nguon_von(null, null, null, null, null, null, null)")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "thongKeThietBiNguonVon",
        procedureName = "thong_ke_thiet_bi_nguon_von",
        resultClasses = {NguonVonThietBiEntity.class},
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
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "p_ten_thiet_bi",
                type = String.class,
                mode = ParameterMode.IN)})
})
@Setter
@Getter
@NoArgsConstructor
public class NguonVonThietBiEntity {

  @Id
  private Long id;

  private String soLuong;

  private String maThietBi;

  private String tenThietBi;

  private String nguonVon;

  private String maNguonVon;
}