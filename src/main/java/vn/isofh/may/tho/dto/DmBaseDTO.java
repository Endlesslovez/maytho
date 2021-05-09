package vn.isofh.may.tho.dto;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DmBaseDTO implements Serializable {

  private Long id;

  private String ma;

  private String ten;

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this == obj) {
      return true;
    }

    if (!(obj instanceof BaseDTO)) {
      return false;
    }

    BaseDTO dto = (BaseDTO) obj;

    if (dto.getId() == null) {
      return false;
    }

    return dto.getId().equals(getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
