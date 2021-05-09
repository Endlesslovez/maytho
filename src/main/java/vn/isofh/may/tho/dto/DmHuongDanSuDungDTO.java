package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmHuongDanSuDungDTO extends BaseDTO {

   private String thongTin;

   private List<String> taiLieu;

   private List<Long> roleIds;

   private List<Object> roles;

   private String nguoiTao;

   private String nguoiSua;

   @JsonProperty(access = Access.WRITE_ONLY)
   private Long ps;
}
