package vn.isofh.may.tho.dao.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "role")
@Where(clause = "deleted=0")
@Setter
@Getter
public class RoleEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "role_generator", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "role_generator", sequenceName = "role_sq")
  private Long id;

  @ManyToMany(mappedBy = "roles")
  List<UserEntity> users;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "roles_privileges",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
  private List<PrivilegeEntity> privileges;
}