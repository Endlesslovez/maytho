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
@Table(name = "dm_loai_mau_su_dung")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmLoaiMauSuDungEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "dm_loai_mau_su_dung_generator")
   @SequenceGenerator(name = "dm_loai_mau_su_dung_generator", sequenceName = "dm_loai_mau_su_dung_sq")
   private Long id;
}