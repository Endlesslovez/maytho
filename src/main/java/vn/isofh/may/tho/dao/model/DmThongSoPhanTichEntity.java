package vn.isofh.may.tho.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;

@Entity
@Table(name = "dm_thong_so_phan_tich")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmThongSoPhanTichEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "dm_thong_so_phan_tich_generator", strategy= GenerationType.SEQUENCE)
   @SequenceGenerator(name = "dm_thong_so_phan_tich_generator", sequenceName = "dm_thong_so_phan_tich_sq")
   private Long id;

   private LoaiThongSoPhanTichEnum loai;

   private String anhDaiDien;

   private Integer soLuong;

   @Column(name = "dm_phuong_phap_id")
   private Long dmPhuongPhapId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "dm_phuong_phap_id", insertable = false, updatable = false)
   private DmPhuongPhapXetNgiemEntity dmPhuongPhap;
}