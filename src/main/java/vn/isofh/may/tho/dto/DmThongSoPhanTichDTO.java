package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.may.tho.dao.model.DmPhuongPhapXetNgiemEntity;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmThongSoPhanTichDTO extends DmDTO {

   private String nguoiTao;

   private String nguoiSuaDoi;

   private LoaiThongSoPhanTichEnum loai;

   private String anhDaiDien;

   private Integer soLuong;

   private Long dmPhuongPhapId;

   private Object dmPhuongPhap;

   @JsonProperty(access = Access.WRITE_ONLY)
   private Long dmPhuongPhapXnId;
}
