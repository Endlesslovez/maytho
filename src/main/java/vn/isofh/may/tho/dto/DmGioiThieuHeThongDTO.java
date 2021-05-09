package vn.isofh.may.tho.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.converter.persistence.StringListConverter;

import javax.persistence.Column;
import javax.persistence.Convert;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmGioiThieuHeThongDTO extends DmDTO {

   private List<String> canCuPhapLy;

   private String khaiNiemCongKhaiGia;

   private String nguonCungCapThongTin;

   private List<String> taiLieuThamKhao;

   private String nguoiTao;

   private String nguoiSuaDoi;

   private String phuongThucThucHien;
}
