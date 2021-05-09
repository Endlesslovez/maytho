package vn.isofh.may.tho.dao.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.common.converter.persistence.LongListConverter;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.common.dao.model.BaseEntity;
import vn.isofh.may.tho.converter.NhomSanPhamListConverter;
import vn.isofh.may.tho.enums.NhomSanPhamEnum;

@Entity
@Table(name = "dang_ky_tai_khoan")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DangKyTaiKhoanEntity extends BaseEntity {

  @Id
  @GeneratedValue(generator = "dang_ky_tai_khoan_generator")
  @SequenceGenerator(name = "dang_ky_tai_khoan_generator", sequenceName = "dang_ky_tai_khoan_sq")
  private Long id;

  private String maSoThue;

  private String email;

  private String nguoiDaiDienDoanhNghiep;

  private String chucVu;

  private String soCmndNguoiDaiDien;

  private LocalDate ngayCapCmnd;

  private String noiCapCmnd;

  private String tenDoanhNghiep;

  private String tenGiaoDichQuocTe;

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

  private String diaChiChiTiet;

  private String soDienThoai;

  private String fax;

  private String giayChungNhanDkkd;

  private LocalDate ngayCapGiayDkkd;

  private String coQuanCapGiayDkkd;

  private String soDienThoaiDoanhNghiep;

  @Convert(converter = NhomSanPhamListConverter.class)
  private List<NhomSanPhamEnum> nhomSanPham;

  @Convert(converter = LongListConverter.class)
  private List<Long> hangSoHuuTtbIds;

  @Convert(converter = LongListConverter.class)
  private List<Long> loaiSanPhamTtbIds;

  @Convert(converter = LongListConverter.class)
  private List<Long> hangSoHuuVtthIds;

  @Convert(converter = LongListConverter.class)
  private List<Long> loaiSanPhamVtthIds;

  @Convert(converter = LongListConverter.class)
  private List<Long> hangSoHuuIVDIds;

  @Convert(converter = LongListConverter.class)
  private List<Long> loaiSanPhamIVDIds;

  @Convert(converter = StringListConverter.class)
  @Column(length = 2500)
  private List<String> taiLieuGiayUyQuyen;

}