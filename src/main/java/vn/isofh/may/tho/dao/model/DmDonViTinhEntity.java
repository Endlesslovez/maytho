package vn.isofh.may.tho.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "dm_don_vi_tinh")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmDonViTinhEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_don_vi_tinh_generator")
  @SequenceGenerator(name = "dm_don_vi_tinh_generator", sequenceName = "dm_don_vi_tinh_sq")
  private Long id;
}