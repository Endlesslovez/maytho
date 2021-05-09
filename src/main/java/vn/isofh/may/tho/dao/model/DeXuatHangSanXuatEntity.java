package vn.isofh.may.tho.dao.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.common.converter.persistence.LongListConverter;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.common.dao.model.BaseEntity;

@Entity
@Table(name = "de_xuat_hang_san_xuat")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DeXuatHangSanXuatEntity extends BaseEntity {

  @Id
  @GeneratedValue(generator = "de_xuat_hang_san_xuat_generator",strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "de_xuat_hang_san_xuat_generator", sequenceName = "de_xuat_hang_san_xuat_sq")
  private Long id;

  @Column(length = 1000)
  private String tenHangSanXuat;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  @Column(length = 1000)
  private String lyDoHuyXacNhan;
}