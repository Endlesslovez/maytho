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
import vn.isofh.may.tho.dao.model.DmThietBiEntity;
import vn.isofh.may.tho.enums.DmLoaiThietBiEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmLoaiThietBiDTO extends DmDTO {

  private Long dmThietBiId;

  private Object dmThietBi;

  private Boolean thietBiPhuTro;

  private DmLoaiThietBiEnum loai;

  private Long dmLoaiVtyt1Id;

  private Object dmLoaiVtyt1;

  private Long dmNhomVtytId;

  private Object dmNhomVtyt;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> dmThietBiIds;

  private Long dmNhomTbytId;

  private Object dmNhomTbyt;

  private Long dmHoThietBiId;

  private Object dmHoThietBi;

  private String nguoiTao;

  private String nguoiSua;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Boolean tonTaiThietBi;

  @JsonProperty(access = Access.READ_WRITE)
  private Long dmNhomThietBiId;
}
