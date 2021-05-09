package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;
import vn.isofh.may.tho.enums.TrangThaiTaiKhoanEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class UserDTO extends BaseDTO {

  private String fullName;

  private String username;

  private Long dmDonViId;

  private DmDonViDTO dmDonVi;

  private String dmDonViTen;

  private RoleDTO role;

  private Long roleId;

  private List<Long> roleIds;

  private List<Object> roles;

  private String email;

  private String diaChi;

  private String ghiChu;

  private String anhDaiDien;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String newPassword;

  private TrangThaiTaiKhoanEnum trangThai;

  private String nguoiTao;

  private String nguoiSua;

  private List<Long> deXuatNhomTtbyt;

}
