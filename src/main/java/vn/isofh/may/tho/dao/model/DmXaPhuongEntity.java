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
@Table(name = "dm_xa_phuong")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmXaPhuongEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_xa_phuong_generator")
  @SequenceGenerator(name = "dm_xa_phuong_generator", sequenceName = "dm_xa_phuong_sq")
  private Long id;

  @Column(name = "dm_quan_huyen_id", nullable = false)
  private Long dmQuanHuyenId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_quan_huyen_id", insertable = false, updatable = false)
  private DmQuanHuyenEntity dmQuanHuyen;
}