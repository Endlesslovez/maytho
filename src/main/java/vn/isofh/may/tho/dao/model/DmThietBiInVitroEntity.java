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

@Entity
@Table(name = "dm_thiet_bi_in_vitro")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmThietBiInVitroEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "dm_thiet_bi_in_vitro_generator",strategy= GenerationType.SEQUENCE)
   @SequenceGenerator(name = "dm_thiet_bi_in_vitro_generator", sequenceName = "dm_thiet_bi_in_vitro_sq")
   private Long id;

   @Column(name = "dm_phuong_phap_xet_nghiem_id", nullable = false)
   private Long dmPhuongPhapXetNghiemId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "dm_phuong_phap_xet_nghiem_id", insertable = false, updatable = false)
   private DmPhuongPhapXetNgiemEntity dmPhuongPhapXetNghiem;

   @Column(name = "dm_thong_so_phan_tich_id", nullable = false)
   private Long dmThongSoPhanTichId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "dm_thong_so_phan_tich_id", insertable = false, updatable = false)
   private DmThongSoPhanTichEntity dmThongSoPhanTich;
}
