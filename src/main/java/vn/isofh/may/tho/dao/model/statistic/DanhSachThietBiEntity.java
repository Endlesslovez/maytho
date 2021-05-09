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
@Subselect("select * from danh_sach_thiet_bi(null, null, null, null, null)")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "danhSachThietBi",
        procedureName = "danh_sach_thiet_bi",
        resultClasses = {DanhSachThietBiEntity.class},
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
                mode = ParameterMode.IN)})
})
@Setter
@Getter
@NoArgsConstructor
public class DanhSachThietBiEntity {

  @Id
  private Long id;

  private String maThietBi;

  private String tenThietBi;

  private String tenThuongMai;

  private Long loaiThietBiId;

  private String loaiThietBiTen;

  private String serial;

  private String model;

  private String hangSanXuat;

  private Integer namSanXuat;

  private Integer namSuDung;

  private String donVi;

  private Long donViId;

  private String donViSuDung;

  private Long trangThaiId;

  private String trangThai;

  private Long tinhThanhPhoId;

  private String tinhThanhPho;

  private Long quanHuyenId;

  private String quanHuyen;

  private Long xaPhuongId;

  private String xaPhuong;

  private String ghiChu;
}