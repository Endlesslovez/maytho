package vn.isofh.may.tho.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.may.tho.enums.LoaiThietBiEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmThietBiDTO extends DmDTO {

  private String tenThuongMai;

  private String tenVietTat;

  private Boolean thietBiPhuTro;

  private Long dmDonViId;

  private String anhDaiDien;

  private Integer soLuong;

  private Boolean tonTaiAnhDaiDien;

  private Boolean tonTaiSoLuong;

  private LoaiThietBiEnum loai;

  private Long dmNhomTbytId;

  private Object dmNhomTbyt;

  private String nguoiTao;

  private String nguoiSua;
}
