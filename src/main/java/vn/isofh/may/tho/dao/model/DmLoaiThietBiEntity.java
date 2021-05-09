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
import vn.isofh.may.tho.enums.DmLoaiThietBiEnum;

@Entity
@Table(name = "dm_loai_thiet_bi")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmLoaiThietBiEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_loai_thiet_bi_generator")
  @SequenceGenerator(name = "dm_loai_thiet_bi_generator", sequenceName = "dm_loai_thiet_bi_sq")
  private Long id;

  private Boolean thietBiPhuTro;

  @Column(name = "dm_thiet_bi_id")
  private Long dmThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_thiet_bi_id", insertable = false, updatable = false)
  private DmThietBiEntity dmThietBi;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  private DmLoaiThietBiEnum loai;

  @Column(name = "dm_loai_vtyt1_id")
  private Long dmLoaiVtyt1Id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_loai_vtyt1_id", insertable = false, updatable = false)
  private DmLoaiThietBiEntity dmLoaiVtyt1;

  @Column(name = "dm_nhom_tbyt_id")
  private Long dmNhomTbytId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_nhom_tbyt_id", insertable = false, updatable = false)
  private DmNhomThietBiEntity dmNhomTbyt;

}