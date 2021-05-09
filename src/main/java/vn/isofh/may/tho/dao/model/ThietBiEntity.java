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
import vn.isofh.common.converter.persistence.StringBinaryConverter;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.common.dao.model.BaseEntity;
import vn.isofh.may.tho.enums.DeviceEnum;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Entity
@Table(name = "thiet_bi")
@Where(clause = "deleted=0")
@Setter
@Getter
public class ThietBiEntity extends BaseEntity {

  @Id
  @GeneratedValue(generator = "thiet_bi_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "thiet_bi_generator", sequenceName = "thiet_bi_sq")
  private Long id;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  @Column(name = "dm_loai_thiet_bi_id")
  private Long dmLoaiThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_loai_thiet_bi_id", insertable = false, updatable = false)
  private DmLoaiThietBiEntity dmLoaiThietBi;

  @Column(name = "dm_nhom_thiet_bi_id")
  private Long dmNhomThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nhom_thiet_bi_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity dmNhomThietBi;

  @Column(name = "dm_nhom_tbyt_id")
  private Long dmNhomTbytId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nhom_tbyt_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity dmNhomTbyt;

  @Column(name = "dm_model_id")
  private Long dmModelId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_model_id", insertable = false, updatable = false)
  private DmModelEntity dmModel;

  @Column(name = "dm_nguon_von_id")
  private Long dmNguonVonId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nguon_von_id", insertable = false, updatable = false)
  private DmNguonVonEntity dmNguonVon;

  @Column(name = "dm_don_vi_tinh_id")
  private Long dmDonViTinhId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_tinh_id", insertable = false, updatable = false)
  private DmDonViTinhEntity dmDonViTinh;

  @Column(name = "dm_trang_thai_id")
  private Long dmTrangThaiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_trang_thai_id", insertable = false, updatable = false)
  private DmTrangThaiEntity dmTrangThai;

  @Column(name = "dm_thong_so_phan_tich_id")
  private Long dmThongSoPhanTichId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_thong_so_phan_tich_id", insertable = false, updatable = false)
  private DmThongSoPhanTichEntity dmThongSoPhanTich;

  @Column(name = "xuat_xu_id")
  private Long xuatXuId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "xuat_xu_id", insertable = false, updatable = false)
  private DmQuocGiaEntity xuatXu;

  private PhanLoaiTtbEnum phanLoai;

  private String ma;

  private String ten;

  private String anhDaiDien;

  @Convert(converter = StringListConverter.class)
  private List<String> anh;

  private String serial;

  private String soGiayPhepLuuHanh;

  private String donViSuDung;

  @Convert(converter = StringListConverter.class)
  private List<String> namSanXuat;

  private Integer namMua;

  private Integer namSuDung;

  private Integer giaNhap;

  private Long giaNiemYet;

  private LocalDate ngayBatDauHieuLuc;

  private LocalDate ngayHetHieuLuc;

  private String chungLoai;

  private DeviceEnum device;

  @Convert(converter = StringListConverter.class)
  @Column(length = 5000)
  private List<String> taiLieuCanCuCauThanhGia;

  @Convert(converter = StringBinaryConverter.class)
  private String canCuCauThanhGia;

  @Convert(converter = StringListConverter.class)
  @Column(length = 5000)
  private List<String> taiLieuCauHinhCoBan;

  @Column(length = 5000)
  @Convert(converter = StringBinaryConverter.class)
  private String cauHinhCoBan;

  @Convert(converter = StringListConverter.class)
  @Column(length = 5000)
  private List<String> taiLieuCauHinhNangCao;

  @Convert(converter = StringBinaryConverter.class)
  private String cauHinhNangCao;

  @Convert(converter = StringListConverter.class)
  @Column(length = 5000)
  private List<String> taiLieuChiTieuKyThuat;

  @Column(length = 5000)
  @Convert(converter = StringBinaryConverter.class)
  private String chiTieuKyThuat;

  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuDichVuDinhKem;

  @Convert(converter = StringBinaryConverter.class)
  private String dichVuDinhKem;

  private String  taiLieuHuongDan;

  private String taiLieuHuongDanSuaChua;

  @Convert(converter = LongListConverter.class)
  private List<Long> nuocSanXuatIds;

  private String taiLieuCatalogue;

  private String taiLieuIso;

  private String taiLieuCfs;

  private  String linhKien;

  private String taiLieuLinhKien;

  private String vatTuTieuHao;

  private String taiLieuVatTuTieuHao;

  private String dichVuBaoDuong;

  private String taiLieuDichVuBaoDuong;

  private String dichVuKyThuat;

  private String taiLieuDichVuKyThuat;

  private String thongTinSauBanHanh;

  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuSauBanHang;

  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuKhac;

  @Column(name = "thiet_bi_chinh_id", insertable = false, updatable = false)
  private Long thietBiChinhId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "thiet_bi_chinh_id")
  private ThietBiEntity thietBiChinh;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "thietBiChinh")
  private Set<ThietBiEntity> thietBiPhuTro;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "thietBi")
  private Set<DmSuaDoiGiaEntity> dmSuaDoiGia;

  private Boolean congKhaiGia;

  private Boolean vat;
}