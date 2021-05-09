package vn.isofh.may.tho.dao.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.common.converter.persistence.LongListConverter;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.common.dao.model.BaseEntity;
import vn.isofh.may.tho.enums.DinhTinhDinhLuongEnum;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Entity
@Table(name = "thiet_bi_in_vitro")
@Where(clause = "deleted=0")
@Setter
@Getter
public class ThietBiInVitroEntity extends BaseEntity {

  @Id
  @GeneratedValue(generator = "thiet_bi_in_vitro_generator",strategy= GenerationType.SEQUENCE)
  @SequenceGenerator(name = "thiet_bi_in_vitro_generator", sequenceName = "thiet_bi_in_vitro_sq")
  private Long id;

  private String ma;

  @Column(name = "dm_thiet_bi_in_vitro_id")
  private Long dmThietBiInVitroId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_thiet_bi_in_vitro_id", insertable = false, updatable = false)
  private DmThietBiInVitroEntity dmThietBiInVitro;

  @Column(name = "dm_nhom_thiet_bi_id")
  private Long dmNhomThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nhom_thiet_bi_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity dmNhomThietBi;

  private PhanLoaiTtbEnum phanLoai;

  @Convert(converter = LongListConverter.class)
  private List<Long> nuocSanXuatIds;

  @Column(name = "xuat_xu_id")
  private Long xuatXuId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "xuat_xu_id", insertable = false, updatable = false)
  private DmQuocGiaEntity xuatXu;

  @Column(name = "dm_hang_san_xuat_id")
  private Long dmHangSanXuatId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_hang_san_xuat_id", insertable = false, updatable = false)
  private DmDonViEntity dmHangSanXuat;

  @Column(name = "hang_so_huu_id")
  private Long hangSoHuuId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hang_so_huu_id", insertable = false, updatable = false)
  private DmDonViEntity hangSoHuu;

  @Column(name = "nuoc_so_huu_id")
  private Long nuocSoHuuId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nuoc_so_huu_id", insertable = false, updatable = false)
  private DmQuocGiaEntity nuocSoHuu;

  private String giayPhepLuuHanh;

  private String soGiayPhepLuuHanh;

  private DinhTinhDinhLuongEnum dinhTinhDinhLuong;

  @Convert(converter = LongListConverter.class)
  private List<Long> dmThongSoPhanTichIds;

  private String thongSoPhanTich;

  @Convert(converter = LongListConverter.class)
  private List<Long> dmLoaiMauSuDungIds;
  
  @Column(length = 2500)
  private String tenChungLoaiTbSuDungCung;

  private String dieuKienThongTinKhac;

  private String taiLieuHdsd;

  private String taiLieuIso;

  private String taiLieuCfs;

  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuKhac;

  private String anhDaiDien;

  private String thongSoHieuNang;

  private String chungLoai;

  @Convert(converter = StringListConverter.class)
  private List<String> anhChiTiet;

  private String quyCachDongGoi;

  @Column(name = "dm_don_vi_tinh_id")
  private Long dmDonViTinhId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_tinh_id", insertable = false, updatable = false)
  private DmDonViTinhEntity dmDonViTinh;

  private Long giaNiemYet;

  private LocalDate ngayBatDauHieuLuc;

  private LocalDate ngayHetHieuLuc;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "thietBiInVitro")
  private Set<DmSuaDoiGiaEntity> dmSuaDoiGia;

  @Convert(converter = StringListConverter.class)
  private List<String> namSanXuat;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  private Boolean congKhaiGia;

  private Boolean vat;
}