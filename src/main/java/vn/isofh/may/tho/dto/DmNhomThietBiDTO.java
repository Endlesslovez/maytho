package vn.isofh.may.tho.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.may.tho.enums.LoaiNhomEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmNhomThietBiDTO extends DmDTO{

   private LoaiNhomEnum loai;

   private String anhDaiDien;

   private String nguoiTao;

   private String nguoiSua;

   private Integer soLuong;
}
