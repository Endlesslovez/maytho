package vn.isofh.may.tho.dao.model;

import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.isofh.may.tho.enums.LoaiNhomEnum;

@Entity
@Table(name = "dm_nhom_thiet_bi")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmNhomThietBiEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "dm_nhom_thiet_bi_generator")
   @SequenceGenerator(name = "dm_nhom_thiet_bi_generator", sequenceName = "dm_nhom_thiet_bi_sq")
   private Long id;

   private LoaiNhomEnum loai;

   private String anhDaiDien;

   private Integer soLuong;
}
