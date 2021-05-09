package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class NdTruyCapDTO extends DmDTO {

  private Long id;

  private String ip;

  private String maKhuVuc;

  private String tenKhuVuc;

  private String maQuocGia;

  private String tenQuocGia;

  private ZonedDateTime ngayTruyCap;

  private Long userAccountId;

  private ZonedDateTime thoiGianKetThuc;

  private UserDTO userDTO;

  @Setter
  @Getter
  @NoArgsConstructor
  @ToString(callSuper = true)
  public static class IpLocationResponse implements Serializable {

    private String status;

    private String countryCode;

    private String country;

    private String region;

    private String regionName;

  }
}
