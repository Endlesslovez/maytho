package vn.isofh.may.tho.dao.model;

import java.util.List;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import vn.isofh.common.converter.persistence.LongListConverter;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.common.dao.model.BaseEntity;

@Entity
@Table(name = "dm_huong_dan_su_dung")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmHuongDanSuDungEntity extends BaseEntity {

   @Id
   @GeneratedValue(generator = "dm_huong_dan_su_dung_generator")
   @SequenceGenerator(name = "dm_huong_dan_su_dung_generator", sequenceName = "dm_huong_dan_su_dung_sq")
   private Long id;

   @Column(length = 500)
   private String thongTin;

   @Column(length = 2500)
   @Convert(converter = StringListConverter.class)
   private List<String> taiLieu;

   @Convert(converter = LongListConverter.class)
   private List<Long> roleIds;
}
