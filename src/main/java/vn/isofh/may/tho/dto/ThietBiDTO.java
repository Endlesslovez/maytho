package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;
import vn.isofh.may.tho.dao.model.DmNhomThietBiEntity;
import vn.isofh.may.tho.enums.DeviceEnum;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class ThietBiDTO extends BaseDTO {

  private Long dmDonViId;

  private Object dmDonVi;

  private Long dmThietBiId;

  private Long dmLoaiThietBiId;

  private Object dmLoaiThietBi;

  private Long dmModelId;

  private Object dmModel;

  private Long dmNguonVonId;

  private Object dmNguonVon;

  private Long dmDonViTinhId;

  private Object dmDonViTinh;

  private Long dmTrangThaiId;

  private Object dmTrangThai;

  private Long dmNhomThietBiId;

  private Object dmNhomThietBi;

  private String ma;

  private String ten;

  private String serial;

  private String donViSuDung;

  private List<String> namSanXuat;

  private Integer namMua;

  private Integer namSuDung;

  private Integer giaNhap;

  private Long giaNiemYet;

  private LocalDate ngayBatDauHieuLuc;

  private LocalDate ngayHetHieuLuc;

  private String chungLoai;

  private DeviceEnum device;

  private List<String> taiLieuCanCuCauThanhGia;

  private String canCuCauThanhGia;

  private List<String> taiLieuCauHinhCoBan;

  private String cauHinhCoBan;

  private List<String> taiLieuCauHinhNangCao;

  private String cauHinhNangCao;

  private List<Long> nuocSanXuatIds;

  private List<Object> nuocSanXuat;

  private Long thietBiChinhId;

  private Object thietBiChinh;

  private Set<ThietBiDTO> thietBiPhuTro;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Object hangSoHuu;

  private Object nuocSoHuu;

  private Object dmHangSanXuat;

  private Long dmThongSoPhanTichId;

  private Object dmThongSoPhanTich;

  private Long xuatXuId;

  private Object xuatXu;

  private PhanLoaiTtbEnum phanLoai;

  private List<String> taiLieuChiTieuKyThuat;

  private String chiTieuKyThuat;

  private List<String> taiLieuDichVuDinhKem;

  private String dichVuDinhKem;

  private String anhDaiDien;

  private List<String> anh;

  private String soGiayPhepLuuHanh;

  private String taiLieuHuongDan;

  private String taiLieuHuongDanSuaChua;

  private String taiLieuCatalogue;

  private String taiLieuIso;

  private String taiLieuCfs;

  private  String linhKien;

  private String taiLieuLinhKien;

  private String vatTuTieuHao;

  private String taiLieuVatTuTieuHao;

  private String dichVuBaoDuong;

  private String taiLieuDichVuBaoDuong;

  private String dichVuKyThuat;

  private String taiLieuDichVuKyThuat;

  private String thongTinSauBanHanh;

  private List<String> taiLieuSauBanHang;

  private List<String> taiLieuKhac;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String timKiem;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Integer namSanXuatTimKiem;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Double giaTu;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Double giaDen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Boolean tonTaiGiaNiemYet;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> dmDonViIds;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> dmLoaiThietBiIds;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String dmTinhThanhPhoTen;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String dmQuanHuyenTen;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String dmXaPhuongTen;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmTinhThanhPhoId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> dmTinhThanhPhoIds;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmQuanHuyenId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmXaPhuongId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String dmNuocSanXuatTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Integer giaNiemYetLonNhat;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long idLoaiBo;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String dmDonViTinhTen;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String dmTrangThaiTen;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String loaiThietBiTen;

  private String nguoiTao;

  private String nguoiSua;

  private Boolean congKhaiGia;

  private Long dmNhomTbytId;

  private Object dmNhomTbyt;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> dmThietBiIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> dmHangSanXuatIds;

  private Boolean vat;
}
