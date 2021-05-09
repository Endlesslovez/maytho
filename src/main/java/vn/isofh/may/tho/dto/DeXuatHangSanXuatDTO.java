package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DeXuatHangSanXuatDTO extends BaseDTO {

  private Long id;

  private String tenHangSanXuat;

  private Long dmDonViId;

  private Object dmDonVi;

  private String lyDoHuyXacNhan;

  private String nguoiTao;

  private String nguoiSua;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String nhaCCUyQuyen;

  @Setter
  @Getter
  @NoArgsConstructor
  @ToString(callSuper = true)
  public static class XacNhanHangSanXuat implements Serializable {

    private Long id;

    private String lyDoHuyXacNhan;

    private String maHangSanXuat;

  }

}
