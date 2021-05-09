package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmTinhThanhPhoDTO extends DmDTO {

  private Long dmQuocGiaId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmThietBiId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmPhuongPhapXetNghiemId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmNhomVtytId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmLoaiThietBiId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmNhomTbytId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmHoThietBiId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmThietBiInVitroId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmThongSoPhanTichId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmPhuongPhapId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmLoaiVtytId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmNhomThietBiId;
}
