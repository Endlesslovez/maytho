package vn.isofh.may.tho.dao.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.common.converter.persistence.StringListConverter;

@Entity
@Table(name = "gioi_thieu_he_thong")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmGioiThieuHeThongEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "gioi_thieu_he_thong_generator")
   @SequenceGenerator(name = "gioi_thieu_he_thong_generator", sequenceName = "gioi_thieu_he_thong_sq")
   private Long id;

   @Column(length = 5000)
   @Convert(converter = StringListConverter.class)
   private List<String> canCuPhapLy;

   @Column(length = 5000)
   private String khaiNiemCongKhaiGia;

   @Column(length = 5000)
   private String nguonCungCapThongTin;

   @Column(length = 5000)
   @Convert(converter = StringListConverter.class)
   private List<String> taiLieuThamKhao;

   private String phuongThucThucHien;
}
