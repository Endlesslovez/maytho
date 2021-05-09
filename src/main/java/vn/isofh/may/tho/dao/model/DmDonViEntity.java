package vn.isofh.may.tho.dao.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.may.tho.enums.LoaiCongTyEnum;
import vn.isofh.may.tho.enums.LoaiCsytEnum;
import vn.isofh.may.tho.enums.LoaiDonViEnum;

@Entity
@Table(name = "dm_don_vi")
@Where(clause = "deleted=0")
@Setter
@Getter
@NoArgsConstructor
public class DmDonViEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_co_so_y_te_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "dm_co_so_y_te_generator", sequenceName = "dm_co_so_y_te_sq")
  private Long id;

  private String maSoThue;

  private LoaiCsytEnum loaiCsyt;

  private LoaiCongTyEnum loaiCongTy;

  @Column(nullable = false)
  private LoaiDonViEnum loaiDonVi;

  @Column(name = "dm_tinh_thanh_pho_id")
  private Long dmTinhThanhPhoId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_tinh_thanh_pho_id", insertable = false, updatable = false)
  private DmTinhThanhPhoEntity dmTinhThanhPho;

  @Column(name = "dm_quan_huyen_id")
  private Long dmQuanHuyenId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_quan_huyen_id", insertable = false, updatable = false)
  private DmQuanHuyenEntity dmQuanHuyen;

  @Column(name = "dm_xa_phuong_id")
  private Long dmXaPhuongId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_xa_phuong_id", insertable = false, updatable = false)
  private DmXaPhuongEntity dmXaPhuong;

  @Column(name = "co_quan_quan_ly_ids")
  @Convert(converter = StringListConverter.class)
  private List<String> coQuanQuanLyIds;

  private String diaChi;

  private String nguoiLienHe;

  private String soDienThoai;

  private String logo;

  private String email;

  private String ghiChu;

  private String nguoiDaiDienPhapLuat;

  private String fax;

  private String chungNhanDkkd;

  private LocalDate ngayCapChungNhanDkkd;

  private String coQuanCapPhep;

  private String nguoiDaiDienDoanhNghiep;

  private String chucVu;

  private String dienThoaiNguoiDaiDien;

  private String soCmndNguoiDaiDien;

  private LocalDate ngayCapCmnd;

  private String noiCapCmnd;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "hang_san_xuat_ncc_uy_quyen",
      joinColumns = @JoinColumn(name = "hang_sang_xuat_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "ncc_uy_quyen_id", referencedColumnName = "id"))
  private List<DmDonViEntity> nccUyQuyen;

  @ManyToMany(mappedBy = "nccUyQuyen")
  List<DmDonViEntity> hangSanXuatUyQuyen;

  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuUyQuyen;

  private String tenGiaoDichQt;

  private String chungNhanDangKyKinhDoanh;

}