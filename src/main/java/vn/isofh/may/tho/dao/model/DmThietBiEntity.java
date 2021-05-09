package vn.isofh.may.tho.dao.model;

import javax.persistence.Column;
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
import vn.isofh.may.tho.enums.LoaiThietBiEnum;

@Entity
@Table(name = "dm_thiet_bi")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmThietBiEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_thiet_bi_generator")
  @SequenceGenerator(name = "dm_thiet_bi_generator", sequenceName = "dm_thiet_bi_sq")
  private Long id;

  private String tenThuongMai;

  private String tenVietTat;

  private Boolean thietBiPhuTro;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  private String anhDaiDien;

  private Integer soLuong;

  private Integer namSanXuat;

  private Boolean tonTaiAnhDaiDien;

  private LoaiThietBiEnum loai;

  @Column(name = "dm_nhom_tbyt_id")
  private Long dmNhomTbytId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nhom_tbyt_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity dmNhomTbyt;
}