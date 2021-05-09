package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.may.tho.dao.model.DmNhomThietBiEntity;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmModelDTO extends DmDTO {

  private Long dmThietBiId;

  private Object dmThietBi;

  private Long dmLoaiThietBiId;

  private Object dmLoaiThietBi;

  private Long dmHangSanXuatId;

  private Object dmHangSanXuat;

  private Long hangSoHuuId;

  private Object hangSoHuu;

  private List<Long> nuocSanXuatIds;

  private List<Object> nuocSanXuat;

  private Long nuocSoHuuId;

  private Object nuocSoHuu;

  private Long nhaCungCapId;

  private Object nhaCungCap;

  private String ghiChu;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long nuocSanXuatId;

  private Long dmNhomTbytId;

  private Object dmNhomTbyt;
}
