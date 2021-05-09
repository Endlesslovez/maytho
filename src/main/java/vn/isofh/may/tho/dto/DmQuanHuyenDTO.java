package vn.isofh.may.tho.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmQuanHuyenDTO extends DmDTO {

  private Long dmTinhThanhPhoId;
}
