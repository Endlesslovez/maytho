package vn.isofh.may.tho.dao.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Entity
@Table(name = "vat_tu_y_te")
@Where(clause = "deleted=0")
@Setter
@Getter
public class VatTuYTeEntity extends BaseEntity {

  @Id
  @GeneratedValue(generator = "vat_tu_y_te_generator")
  @SequenceGenerator(name = "vat_tu_y_te_generator", sequenceName = "vat_tu_y_te_sq")
  private Long id;

  private String ten;

  @Column(name = "dm_vat_tu_y_te_id")
  private Long dmVatTuYTeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_vat_tu_y_te_id", insertable = false, updatable = false)
  private DmVatTuYTeEntity dmVatTuYTe;

  private String chungLoai;

  private String maSanPham;

  private String soGiayPhepLuuHanh;

  private String quyCachDongGoi;

  @Column(name = "dm_don_vi_tinh_id")
  private Long dmDonViTinhId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_tinh_id", insertable = false, updatable = false)
  private DmDonViTinhEntity dmDonViTinh;

  private Long giaNiemYet;

  @Column(name = "nhom_thiet_bi_id")
  private Long nhomThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nhom_thiet_bi_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity nhomThietBi;

  @Column(name = "nuoc_san_xuat_id")
  private Long nuocSanXuatId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nuoc_san_xuat_id", insertable = false, updatable = false)
  private DmQuocGiaEntity nuocSanXuat;

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

  @Convert(converter = StringListConverter.class)
  private List<String> namSanXuat;

  private PhanLoaiTtbEnum phanLoaiTtb;

  private LocalDate ngayBatDauHieuLuc;

  private LocalDate ngayHetHieuLuc;

  @Column(length = 2500)
  private String thongSoKtCoBan;
  
  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuThongSoKtCoBan;

  @Column(length = 2500)
  private String dieuKienThongTinKhac;

  @Column(length = 2500)
  @Convert(converter = StringListConverter.class)
  private List<String> taiLieuDkThongTinKhac;

  private String anhDaiDien;

  @Convert(converter = StringListConverter.class)
  @Column(length = 5000)
  private List<String> anh;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vatTuYTe")
  private Set<DmSuaDoiGiaEntity> dmSuaDoiGia;

  @Column(name = "hang_san_xuat_id")
  private Long dmHangSanXuatId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hang_san_xuat_id", insertable = false, updatable = false)
  private DmDonViEntity dmHangSanXuat;

  @Column(name = "dm_nhom_vtyt_id")
  private Long dmNhomVtytId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nhom_vtyt_id", insertable = false, updatable = false)
  private DmThietBiEntity dmNhomVtyt;

  @Column(name = "dm_loai_vtyt_id")
  private Long dmLoaiVtytId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_loai_vtyt_id", insertable = false, updatable = false)
  private DmLoaiThietBiEntity dmLoaiVtyt;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  private String ma;

  private Boolean congKhaiGia;

  private Boolean vat;
}