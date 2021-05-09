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

@Entity
@Table(name = "dm_tinh_thanh_pho")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmTinhThanhPhoEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_tinh_thanh_pho_generator")
  @SequenceGenerator(name = "dm_tinh_thanh_pho_generator", sequenceName = "dm_tinh_thanh_pho_sq")
  private Long id;

  @Column(name = "dm_quoc_gia_id", nullable = false)
  private Long dmQuocGiaId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_quoc_gia_id", insertable = false, updatable = false)
  private DmQuocGiaEntity dmQuocGia;
}