package vn.isofh.may.tho.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmPhuongPhapXetNgiemDTO extends DmDTO{

   private String nguoiTao;

   private String nguoiSuaDoi;

   private String anhDaiDien;

   private Integer soLuong;
}
