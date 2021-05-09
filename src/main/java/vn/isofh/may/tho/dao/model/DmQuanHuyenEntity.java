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
@Table(name = "dm_quan_huyen")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmQuanHuyenEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_quan_huyen_generator")
  @SequenceGenerator(name = "dm_quan_huyen_generator", sequenceName = "dm_quan_huyen_sq")
  private Long id;

  @Column(name = "dm_tinh_thanh_pho_id", nullable = false)
  private Long dmTinhThanhPhoId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_tinh_thanh_pho_id", insertable = false, updatable = false)
  private DmTinhThanhPhoEntity dmTinhThanhPho;
}