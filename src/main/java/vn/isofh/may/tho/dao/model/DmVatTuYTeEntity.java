package vn.isofh.may.tho.dao.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "dm_vat_tu_y_te")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmVatTuYTeEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_vat_tu_y_te_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "dm_vat_tu_y_te_generator", sequenceName = "dm_vat_tu_y_te_sq")
  private Long id;

  @Column(name = "dm_loai_thiet_bi_id",nullable = false)
  private Long dmLoaiThietBiId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_loai_thiet_bi_id", insertable = false, updatable = false)
  private DmLoaiThietBiEntity dmLoaiThietBi;

}