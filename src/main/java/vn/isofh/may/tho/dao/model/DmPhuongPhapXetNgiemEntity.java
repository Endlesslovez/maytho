package vn.isofh.may.tho.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "dm_phuong_phap_xet_nghiem")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmPhuongPhapXetNgiemEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "dm_phuong_phap_xet_nghiem_generator",strategy= GenerationType.SEQUENCE)
   @SequenceGenerator(name = "dm_phuong_phap_xet_nghiem_generator", sequenceName = "dm_phuong_phap_xet_nghiem_sq")
   private Long id;

   private String anhDaiDien;

   private Integer soLuong;

}

