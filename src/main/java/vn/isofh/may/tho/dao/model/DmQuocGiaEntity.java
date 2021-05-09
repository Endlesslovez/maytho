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
@Table(name = "dm_quoc_gia")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmQuocGiaEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "dm_quoc_gia_generator")
  @SequenceGenerator(name = "dm_quoc_gia_generator", sequenceName = "dm_quoc_gia_sq")
  private Long id;
}