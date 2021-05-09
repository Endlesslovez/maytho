package vn.isofh.may.tho.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;
import vn.isofh.may.tho.enums.DinhTinhDinhLuongEnum;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class ThietBiInVitroDTO extends BaseDTO {

  private String ma;

  private Long dmThietBiInVitroId;

  private Object dmThietBiInVitro;

  private Long dmNhomThietBiId;

  private Object dmNhomThietBi;

  private PhanLoaiTtbEnum phanLoai;

  private Long xuatXuId;

  private Object xuatXu;

  private List<Long> nuocSanXuatIds;

  private List<Object> nuocSanXuat;

  private Long dmHangSanXuatId;

  private Object dmHangSanXuat;

  private Long hangSoHuuId;

  private Object hangSoHuu;

  private Long nuocSoHuuId;

  private Object nuocSoHuu;

  private String giayPhepLuuHanh;

  private String soGiayPhepLuuHanh;

  private DinhTinhDinhLuongEnum dinhTinhDinhLuong;

  private List<Long> dmThongSoPhanTichIds;

  private List<Object> dmThongSoPhanTich;

  private String thongSoPhanTich;

  private List<Long> dmLoaiMauSuDungIds;

  private List<Object> dmLoaiMauSuDung;

  private String tenChungLoaiTbSuDungCung;

  private String dieuKienThongTinKhac;

  private String taiLieuHdsd;

  private String taiLieuIso;

  private String taiLieuCfs;

  private List<String> taiLieuKhac;

  private String anhDaiDien;

  private String thongSoHieuNang;

  private String chungLoai;

  private List<String> anhChiTiet;

  private String quyCachDongGoi;

  private Long dmDonViTinhId;

  private Object dmDonViTinh;

  private Long giaNiemYet;

  private LocalDate ngayBatDauHieuLuc;

  private LocalDate ngayHetHieuLuc;

  private List<String> namSanXuat;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmPhuongPhapId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long timKiem;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long dmDichXetNghiemId;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> dmHangSanXuatIds;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> truSoChinhIds;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Long> dmPpXetNghiemIds;

  private Long dmDonViId;

  private Object dmDonVi;

  private String nguoiTao;

  private String nguoiSua;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long idLoaiBo;

  private Boolean congKhaiGia;

  @JsonProperty(access = Access.READ_WRITE)
  private Long dmThongSoPhanTichId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> dmThietBiInVitroIds;

  private Boolean vat;

}
