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
@Table(name = "dm_trang_thai")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmTrangThaiEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_trang_thai_generator")
  @SequenceGenerator(name = "dm_trang_thai_generator", sequenceName = "dm_trang_thai_sq")
  private Long id;
}