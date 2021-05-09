package vn.isofh.may.tho.dao.model;

import java.time.ZonedDateTime;
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
@Table(name = "nd_truy_cap")
@Where(clause = "deleted=0")
@Setter
@Getter
public class NdTruyCapEntity extends DmEntity {

  @Id
  @GeneratedValue(generator = "nd_truy_cap_generator")
  @SequenceGenerator(name = "nd_truy_cap_generator", sequenceName = "nd_truy_cap_sq")
  private Long id;

  @Column(name = "ip")
  private String ip;

  @Column(name = "ma_khu_vuc")
  private String maKhuVuc;

  @Column(name = "ten_khu_vuc")
  private String tenKhuVuc;

  @Column(name = "ma_quoc_gia")
  private String maQuocGia;

  @Column(name = "ten_quoc_gia")
  private String tenQuocGia;

  @Column(name = "ngay_truy_cap")
  private ZonedDateTime ngayTruyCap;

  @Column(name = "user_account_id")
  private Long userAccountId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_account_id", insertable = false, updatable = false)
  private UserEntity userEntity;

  @Column(name = "thoi_gian_ket_thuc")
  private ZonedDateTime thoiGianKetThuc;
}