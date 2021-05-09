package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.converter.persistence.StringListConverter;
import vn.isofh.common.dto.BaseDTO;
import vn.isofh.may.tho.enums.NhomSanPhamEnum;

import javax.persistence.Convert;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DangKyTaiKhoanDTO extends BaseDTO {

  private Long id;

  private String maSoThue;

  private String email;

  private String nguoiDaiDienDoanhNghiep;

  private String chucVu;

  private String soCmndNguoiDaiDien;

  private LocalDate ngayCapCmnd;

  private String noiCapCmnd;

  private String tenDoanhNghiep;

  private String tenGiaoDichQuocTe;

  private Long dmTinhThanhPhoId;

  private Object dmTinhThanhPho;

  private Long dmQuanHuyenId;

  private Object dmQuanHuyen;

  private Long dmXaPhuongId;

  private Object dmXaPhuong;

  private String diaChiChiTiet;

  private String soDienThoai;

  private String fax;

  private String giayChungNhanDkkd;

  private LocalDate ngayCapGiayDkkd;

  private String coQuanCapGiayDkkd;

  private String soDienThoaiDoanhNghiep;

  private List<NhomSanPhamEnum> nhomSanPham;

  private List<Long> hangSoHuuTtbIds;

  private List<Object> hangSoHuuTtb;

  private List<Long> loaiSanPhamTtbIds;

  private List<Object> loaiSanPhamTtb;

  private List<Long> hangSoHuuVtthIds;

  private List<Object> hangSoHuuVtth;

  private List<Long> loaiSanPhamVtthIds;

  private List<Object> loaiSanPhamVtth;

  private List<Long> hangSoHuuIVDIds;

  private List<Object> hangSoHuuIVD;

  private List<Long> loaiSanPhamIVDIds;

  private List<Object> loaiSanPhamIVD;

  private List<String> taiLieuGiayUyQuyen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String tenTinhThanhPho;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String tenQuanHuyen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String tenXaPhuong;

  @Setter
  @Getter
  @NoArgsConstructor
  @ToString(callSuper = true)
  public static class XacNhanDangKy implements Serializable {

    private Long[] ids;

    private String lyDoHuyXacNhan;

    private String emailTitle;

    private String emailContent;

  }
}
