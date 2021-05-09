package vn.isofh.may.tho.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmSuaDoiGiaDTO extends DmDTO {

   private Long giaTruocThayDoi;

   private Long giaSauThayDoi;

   private ZonedDateTime tuNgay;

   private ZonedDateTime denNgay;

   private LocalDate ngayBatDauHieuLuc;

   private String canCuCauThanhGia;

   private List<String> taiLieuCanCuCauThanhGia;

   private Long thietBiId;

   private Long vatTuYTeId;

   private Long thietBiInVitroId;

   private String dienGiaiCnGia;

   private String nguoiTao;

   private String nguoiSua;
}
