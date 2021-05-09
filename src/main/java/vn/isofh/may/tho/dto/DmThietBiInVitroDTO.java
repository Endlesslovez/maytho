package vn.isofh.may.tho.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmThietBiInVitroDTO extends DmDTO {

   private Object dmPhuongPhapXetNghiem;

   private Long dmPhuongPhapXetNghiemId;

   private Long dmThongSoPhanTichId;

   private Object dmThongSoPhanTich;

   private String nguoiTao;

   private String nguoiSuaDoi;
}
