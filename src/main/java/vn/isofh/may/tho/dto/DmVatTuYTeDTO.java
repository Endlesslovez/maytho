package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.may.tho.dao.model.DmLoaiThietBiEntity;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmVatTuYTeDTO extends DmDTO {

  private Long dmLoaiThietBiId;//loại 2 VTYT

  private Object dmLoaiThietBi;//loại 2 VTYT

  private Long dmThietBiId; //nhóm VTYT

  private Object dmThietBi; //nhóm VTYT

  @JsonProperty(access = Access.WRITE_ONLY)
  private String dmThietBiTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String dmLoaiThietBiTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String dmDonViTinhTen;

  private Long dmLoaiVtyt1Id;

  private Object dmLoaiVtyt1;


}