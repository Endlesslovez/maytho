package vn.isofh.may.tho.dao.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "dm_model")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmModelEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_model_generator")
  @SequenceGenerator(name = "dm_model_generator", sequenceName = "dm_model_sq")
  private Long id;

  @Column(name = "dm_thiet_bi_id")
  private Long dmThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_thiet_bi_id", insertable = false, updatable = false)
  private DmThietBiEntity dmThietBi;

  @Column(name = "dm_loai_thiet_bi_id")
  private Long dmLoaiThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_loai_thiet_bi_id", insertable = false, updatable = false)
  private DmLoaiThietBiEntity dmLoaiThietBi;

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

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "model_nuoc_san_xuat",
      joinColumns = @JoinColumn(name = "model_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "nuoc_san_xuat_id", referencedColumnName = "id"))
  private List<DmQuocGiaEntity> nuocSanXuat;

  @Column(name = "nuoc_so_huu_id")
  private Long nuocSoHuuId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nuoc_so_huu_id", insertable = false, updatable = false)
  private DmQuocGiaEntity nuocSoHuu;

  @Column(name = "nha_cung_cap_id")
  private Long nhaCungCapId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nha_cung_cap_id", insertable = false, updatable = false)
  private DmDonViEntity nhaCungCap;

  private String ghiChu;

  @Column(name = "nhom_thiet_bi_id")
  private Long dmNhomTbytId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nhom_thiet_bi_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity dmNhomTbyt;

}