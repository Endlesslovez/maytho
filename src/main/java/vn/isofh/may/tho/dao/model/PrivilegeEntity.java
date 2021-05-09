package vn.isofh.may.tho.dao.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "privilege")
@Where(clause = "deleted=0")
@Setter
@Getter
public class PrivilegeEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "privilege_generator")
  @SequenceGenerator(name = "privilege_generator", sequenceName = "privilege_sq")
  private Long id;

  @ManyToMany(mappedBy = "privileges")
  List<RoleEntity> roles;
}