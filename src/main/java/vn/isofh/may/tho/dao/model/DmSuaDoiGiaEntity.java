package vn.isofh.may.tho.dao.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import vn.isofh.common.converter.persistence.StringListConverter;


@Entity
@Table(name = "dm_sua_doi_gia")
@Where(clause = "deleted=0")
@Setter
@Getter
public class DmSuaDoiGiaEntity extends DmEntity {

   @Id
   @GeneratedValue(generator = "dm_sua_doi_gia_generator")
   @SequenceGenerator(name = "dm_sua_doi_gia_generator", sequenceName = "dm_sua_doi_gia_sq")
   private Long id;

   private Long giaTruocThayDoi;

   private Long giaSauThayDoi;

   private ZonedDateTime tuNgay;

   private ZonedDateTime denNgay;

   private LocalDate ngayBatDauHieuLuc;

   @Column(length = 5000)
   @Convert(converter = StringListConverter.class)
   private List<String> taiLieuCanCuCauThanhGia;

   @Column(length = 5000)
   private String canCuCauThanhGia;

   @Column(name = "thiet_bi_id", insertable = false, updatable = false)
   private Long thietBiId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "thiet_bi_id")
   private ThietBiEntity thietBi;

   @Column(length = 5000)
   private String dienGiaiCnGia;

   @Column(name = "vat_tu_y_te_id", insertable = false, updatable = false)
   private Long vatTuYTeId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vat_tu_y_te_id")
   private VatTuYTeEntity vatTuYTe;

   @Column(name = "thiet_bi_in_vitro_id", insertable = false, updatable = false)
   private Long thietBiInVitroId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "thiet_bi_in_vitro_id")
   private ThietBiInVitroEntity thietBiInVitro;
}
