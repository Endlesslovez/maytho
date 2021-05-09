package vn.isofh.may.tho.dao.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.common.converter.persistence.LongListConverter;
import vn.isofh.common.dao.model.BaseEntity;
import vn.isofh.may.tho.enums.TrangThaiTaiKhoanEnum;

@Entity
@Table(name = "user_account")
@Where(clause = "deleted=0")
@Setter
@Getter
public class UserEntity extends BaseEntity {

  @Id
  @GeneratedValue(generator = "user_account_generator")
  @SequenceGenerator(name = "user_account_generator", sequenceName = "user_account_sq")
  private Long id;

  private String fullName;

  private String username;

  private String email;

  private String diaChi;

  private String ghiChu;

  private String anhDaiDien;

  private String password;

  private String salt;

  private TrangThaiTaiKhoanEnum trangThai;

  @Column(name = "dm_don_vi_id")
  private Long dmDonViId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dm_don_vi_id", insertable = false, updatable = false)
  private DmDonViEntity dmDonVi;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<RoleEntity> roles;

  @Convert(converter = LongListConverter.class)
  private List<Long> deXuatNhomTtbyt;
}