package vn.isofh.may.tho.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.isofh.common.dto.BaseDTO;
import vn.isofh.may.tho.dao.model.DmLoaiThietBiEntity;
import vn.isofh.may.tho.dao.model.DmQuocGiaEntity;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class VatTuYTeDTO extends BaseDTO {

  private String tenThuongMai;

  private Long dmVatTuYTeId;

  private Object dmVatTuYTe;

  private String chungLoai;

  private String maSanPham;

  private String soGiayPhepLuuHanh;

  private String quyCachDongGoi;

  private Long dmDonViTinhId;

  private Object dmDonViTinh;

  private Long giaNiemYet;

  private Long nhomThietBiId;

  private Object nhomThietBi;

  private Long dmHangSanXuatId;

  private Object dmHangSanXuat;

  private Long hangSoHuuId;

  private Object hangSoHuu;

  private Long nuocSoHuuId;

  private Object nuocSoHuu;

  private List<String> namSanXuat;

  private PhanLoaiTtbEnum phanLoaiTtb;

  private LocalDate ngayBatDauHieuLuc;

  private LocalDate ngayHetHieuLuc;

  private String thongSoKtCoBan;

  private List<String> taiLieuThongSoKtCoBan;

  private String dieuKienThongTinKhac;

  private  List<String> taiLieuDkThongTinKhac;

  private String anhDaiDien;

  private List<String> anh;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String tenVtyt;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String maVtyt;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String hangSanXuatTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String nuocSanXuatTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String hangSoHuuTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String nuocSoHuuTen;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long dmThietBiId;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> loaiVtyt1Ids;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> dmHangSanXuatIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> truSoChinhIds;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> dmLoaiVtytIds;

  private Long dmNhomVtytId;

  private Object dmNhomVtyt;

  private Long dmLoaiVtytId;

  private Object dmLoaiVtyt;

  private Long nuocSanXuatId;

  private Object nuocSanXuat;

  private Long dmDonViId;

  private Object dmDonVi;

  private String ma;

  private String ten;

  private String nguoiTao;

  private String nguoiSua;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String timKiem;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long giaNiemYetLonNhat;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Integer namSanXuatTimKiem;

  @JsonProperty(access = Access.WRITE_ONLY)
  private Long idLoaiBo;

  private Boolean congKhaiGia;

  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Long> dmTinhThanhPhoIds;

  private Boolean vat;
}
