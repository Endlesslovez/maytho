package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.may.tho.enums.LoaiCsytEnum;
import vn.isofh.may.tho.enums.LoaiCongTyEnum;
import vn.isofh.may.tho.enums.LoaiDonViEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class DmDonViDTO extends DmDTO {

  private String maSoThue;

  private LoaiDonViEnum loaiDonVi;

  private LoaiCsytEnum loaiCsyt;

  private LoaiCongTyEnum loaiCongTy;

  private Long[] coQuanQuanLyIds;

  private Long dmTinhThanhPhoId;

  private Object dmTinhThanhPho;

  private Long dmQuanHuyenId;

  private Object dmQuanHuyen;

  private Long dmXaPhuongId;

  private Object dmXaPhuong;

  private String diaChi;

  private String nguoiLienHe;

  private String soDienThoai;

  private String logo;

  private String email;

  private String ghiChu;

  private String nguoiDaiDienPhapLuat;

  private String fax;

  private String chungNhanDkkd;

  private LocalDate ngayCapChungNhanDkkd;

  private String coQuanCapPhep;

  private String nguoiDaiDienDoanhNghiep;

  private String chucVu;

  private String dienThoaiNguoiDaiDien;

  private String soCmndNguoiDaiDien;

  private LocalDate ngayCapCmnd;

  private String noiCapCmnd;

  private List<Long> nccUyQuyenIds;

  private List<Object> nccUyQuyen;

  private List<Object> hangSanXuatUyQuyen;

  private Long nccUyQuyenId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long coQuanQuanLyId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String coQuanQuanLyTen;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long[] dmDonViIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmThietBiId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Boolean tonTaiThietBi;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long ids;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String tenThanhPho;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmPhuongPhapXetNghiemId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmNhomVtytId;

  private List<String> taiLieuUyQuyen;

  private String tenGiaoDichQt;

  private String chungNhanDangKyKinhDoanh;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmNhomTbytId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmLoaiThietBiId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmHoThietBiId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmThietBiInVitroId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmPhuongPhapId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmThongSoPhanTichId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmLoaiVtytId;
}