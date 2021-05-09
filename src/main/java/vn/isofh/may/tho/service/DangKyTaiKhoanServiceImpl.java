package vn.isofh.may.tho.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.isofh.common.email.EmailService;
import vn.isofh.common.report.ReportDTO;
import vn.isofh.common.report.ReportService;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.StringUtil;
import vn.isofh.may.tho.dao.model.DangKyTaiKhoanEntity;
import vn.isofh.may.tho.dao.repository.DangKyTaiKhoanRepository;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO.XacNhanDangKy;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.dto.DmQuanHuyenDTO;
import vn.isofh.may.tho.dto.DmTinhThanhPhoDTO;
import vn.isofh.may.tho.dto.DmXaPhuongDTO;
import vn.isofh.may.tho.enums.LoaiCongTyEnum;
import vn.isofh.may.tho.enums.LoaiCsytEnum;
import vn.isofh.may.tho.enums.LoaiDonViEnum;
import vn.isofh.may.tho.exception.DangKyTaiKhoanException;


@Service
public class DangKyTaiKhoanServiceImpl extends
    AbstractService<DangKyTaiKhoanEntity, DangKyTaiKhoanDTO, DangKyTaiKhoanRepository> implements
    DangKyTaiKhoanService {

  @Autowired
  private DangKyTaiKhoanRepository repository;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private DmThietBiService dmThietBiService;

  @Autowired
  private DmLoaiThietBiService dmLoaiThietBiService;

  @Autowired
  private EmailService emailService;

  @Autowired
  private UserService userService;

  @Autowired
  private DmQuanHuyenService dmQuanHuyenService;

  @Autowired
  private DmThongSoPhanTichService dmThongSoPhanTichService;

  @Autowired
  private DmXaPhuongService dmXaPhuongService;

  @Autowired
  private DmTinhThanhPhoService dmTinhThanhPhoService;

  @Autowired
  private ReportService reportService;

  private ExecutorService sendEmailPool = Executors.newFixedThreadPool(5);

  @Override
  protected DangKyTaiKhoanRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<DangKyTaiKhoanDTO, DangKyTaiKhoanEntity>() {
      @Override
      protected void configure() {

      }
    });
  }

  @Override
  protected void specificMapToEntity(DangKyTaiKhoanDTO dto, DangKyTaiKhoanEntity entity) {
    super.specificMapToEntity(dto, entity);
  }

  @Override
  protected void specificMapToDTO(DangKyTaiKhoanEntity entity, DangKyTaiKhoanDTO dto) {
    super.specificMapToDTO(entity, dto);

    if (entity.getHangSoHuuTtbIds() != null) {
      dto.setHangSoHuuTtb(entity.getHangSoHuuTtbIds().
          stream()
          .filter(id -> dmDonViService.existsById(id))
          .map(id -> dmDonViService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }
    if (entity.getLoaiSanPhamTtbIds() != null) {
      dto.setLoaiSanPhamTtb(entity.getLoaiSanPhamTtbIds().
          stream()
          .filter(id -> dmThietBiService.existsById(id))
          .map(id -> dmThietBiService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }
    if (entity.getHangSoHuuVtthIds() != null) {
      dto.setHangSoHuuVtth(entity.getHangSoHuuVtthIds()
          .stream()
          .filter(id -> dmDonViService.existsById(id))
          .map(id -> dmDonViService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }
    if (entity.getLoaiSanPhamVtthIds() != null) {
      dto.setLoaiSanPhamVtth(entity.getLoaiSanPhamVtthIds().
          stream()
          .filter(id -> dmLoaiThietBiService.existsById(id))
          .map(id -> dmLoaiThietBiService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }
    if (entity.getHangSoHuuIVDIds() != null) {
      dto.setHangSoHuuIVD(entity.getHangSoHuuIVDIds()
          .stream()
          .filter(id -> dmDonViService.existsById(id))
          .map(id -> dmDonViService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }
    if (entity.getLoaiSanPhamIVDIds() != null) {
      dto.setLoaiSanPhamIVD(entity.getLoaiSanPhamIVDIds().
          stream()
          .filter(id -> dmThongSoPhanTichService.existsById(id))
          .map(id -> dmThongSoPhanTichService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }
    dto.setDmXaPhuong(dmXaPhuongService.getBaseDTOById(entity.getDmXaPhuongId()));
    dto.setDmQuanHuyen(dmQuanHuyenService.getBaseDTOById(entity.getDmQuanHuyenId()));
    dto.setDmTinhThanhPho(dmTinhThanhPhoService.getBaseDTOById(entity.getDmTinhThanhPhoId()));

  }

  @Override
  protected DangKyTaiKhoanEntity beforeSave(DangKyTaiKhoanEntity entity, DangKyTaiKhoanDTO dto) {
    validate(entity);
    if (entity.isNewRecord()) {
      entity.setActive(false);
    }
    return super.beforeSave(entity, dto);
  }

  @Override
  public DangKyTaiKhoanDTO xacThucTaiKhoan(Long id) {

    DangKyTaiKhoanEntity dangKyTaiKhoan = getById(id);

    if (dangKyTaiKhoan.getActive()) {
      throw new DangKyTaiKhoanException.IsActived();
    }

    dangKyTaiKhoan.setActive(true);

    return mapToDTO(repository.save(dangKyTaiKhoan));
  }

  @Value("${email.confirm.title}")
  private String emailConfirmTitle;

  @Override
  public void xacNhanDangKy(XacNhanDangKy xacNhanDangKy) {
    Long[] ids = xacNhanDangKy.getIds();

    String contentTemplate = getFileContent("confirm.html");

    for (Long id : ids) {
      DangKyTaiKhoanDTO dto = xacThucTaiKhoan(id);

      DmDonViDTO dmDonVi = mapToDmDonViDTO(dto);
      dmDonViService.save(dmDonVi);

      String pass = userService.taoMoiTaiKhoan(dto, dmDonVi.getId());

      String content = contentTemplate.replace("$USERNAME$", dto.getMaSoThue())
          .replace("$PASSWORD$", pass);

      String title = emailConfirmTitle.replace("$tenCongTy$", dto.getTenDoanhNghiep());

      sendEmailPool.execute(() -> emailService.sendMessage(dto.getEmail(), title, content, true));
    }
  }

  @Value("${email.send.to}")
  private String cc;

  @Override
  public void guiMailYeuCauDangKy() {
    List<DangKyTaiKhoanEntity> list;
    LocalDateTime now = LocalDateTime.now();
    list = repository.findAllByActive(false, DateUtil.getZonedDateTime(now.minusHours(12L)));
    String title = "Xin chào bạn, có " + list.size() + " doanh nghiệp đã yêu cầu xác nhận đăng kí tài khoản";

    StringBuilder content = new StringBuilder("Đã có ")
        .append(list.size())
        .append(" doanh nghiệp yêu cầu xác nhận đăng kí tài khoản\n");
    for (DangKyTaiKhoanEntity e : list) {

      content.append("\n================================")
          .append("\nTên doanh nghiệp: ").append(StringUtil.emptyIfNull(e.getTenDoanhNghiep()))
          .append("\nMã số thuế: ").append(StringUtil.emptyIfNull(e.getMaSoThue()))
          .append("\nNgười đại diện: ").append(StringUtil.emptyIfNull(e.getNguoiDaiDienDoanhNghiep()))
          .append("\nĐịa chỉ: ").append(StringUtil.emptyIfNull(e.getDiaChiChiTiet())).append(" - ")
          .append(StringUtil.emptyIfNull(e.getDmXaPhuong().getTen())).append(" - ")
          .append(StringUtil.emptyIfNull(e.getDmQuanHuyen().getTen())).append(" - ")
          .append(StringUtil.emptyIfNull(e.getDmTinhThanhPho().getTen()));
    }

    sendEmailPool.execute(() -> emailService.sendMessage(cc, title, content.toString()));
  }

  @Value("${email.reject.title}")
  String emailRejectTitle;

  @Override
  public void huyXacNhanDangKy(XacNhanDangKy xacNhanDangKy) {

    String content = getFileContent("reject.html")
        .replace("$REASON$", xacNhanDangKy.getLyDoHuyXacNhan());

    DangKyTaiKhoanDTO dto;
    for (Long id : xacNhanDangKy.getIds()) {
      dto = findById(id);

      delete(id);

      String email = dto.getEmail();
      sendEmailPool.execute(() -> emailService.sendMessage(email, emailRejectTitle, content, true));
    }
  }

  @Override
  public ReportDTO exportUserDangKy(DangKyTaiKhoanDTO dto, Pageable pageable) {
    List<DangKyTaiKhoanDTO> lst = search(dto, pageable).getContent();
    Map<String, Object> report = new HashMap<>();
    List<Map<String, Object>> rows = new ArrayList<>();

    report.put("ngay", DateUtil.format(LocalDate.now(), "dd/MM/yyyy"));
    report.put("tongSo", lst.size());

    for (int i = 0; i < lst.size(); i++) {
      Map<String, Object> row = new HashMap<>();

      DangKyTaiKhoanDTO user = lst.get(i);
      DmTinhThanhPhoDTO dmTinhThanhPhoDTO = dmTinhThanhPhoService
          .findById(user.getDmTinhThanhPhoId());
      DmQuanHuyenDTO dmQuanHuyenDTO = dmQuanHuyenService.findById(user.getDmQuanHuyenId());
      DmXaPhuongDTO dmXaPhuongDTO = dmXaPhuongService.findById(user.getDmXaPhuongId());

      row.put("stt", i + 1);
      row.put("maSoThue",user.getMaSoThue());
      row.put("email", user.getEmail());
      row.put("tenDoanhNghiep", user.getTenDoanhNghiep());
      row.put("diaChi",  user.getDiaChiChiTiet());
      row.put("xaPhuong", dmXaPhuongDTO == null ? null : dmXaPhuongDTO.getTen());
      row.put("quanHuyen",  dmQuanHuyenDTO == null ? null : dmQuanHuyenDTO.getTen());
      row.put("tinh",  dmTinhThanhPhoDTO == null ? null : dmTinhThanhPhoDTO.getTen());
      row.put("nguoiDaiDien", user.getNguoiDaiDienDoanhNghiep());
      row.put("soDienThoai", user.getSoDienThoai());
      row.put("thoiGian", DateUtil.format(user.getCreatedAt(), "yyyy-MM-dd HH:mm:ss"));
      rows.add(row);

    }

    report.put("rows", rows);
    return reportService
        .getReport(report, "templates/Danh_sach_tai_khoan_dang_ky.xlsx", "bao-cao");
  }

  private void validate(DangKyTaiKhoanEntity model) {
    if (model.getMaSoThue() == null) {
      throw new DangKyTaiKhoanException.MissingMaSoThue();
    }
    if (model.getEmail() == null) {
      throw new DangKyTaiKhoanException.MissingEmail();
    }
    if (model.getSoDienThoai() == null) {
      throw new DangKyTaiKhoanException.MissingSoDienThoai();
    }
    if (model.getSoCmndNguoiDaiDien() == null) {
      throw new DangKyTaiKhoanException.MissingSoCMND();
    }
    if (model.getTenDoanhNghiep() == null) {
      throw new DangKyTaiKhoanException.MissingTenDoanhNghiep();
    }
    if (model.getDmXaPhuongId() == null
        || model.getDmQuanHuyenId() == null || model.getDmTinhThanhPhoId() == null) {
      throw new DangKyTaiKhoanException.MissingDiaChi();
    }

    if (model.getNhomSanPham() == null || model.getNhomSanPham().size() == 0) {
      throw new DangKyTaiKhoanException.MissingNhomSanPham();
    }

    if (repository.existsByMaSoThue(model.getMaSoThue(), model.getId())) {
      throw new DangKyTaiKhoanException.DuplicateMaSoThue(new Object[]{model.getMaSoThue()});
    }

    if (repository.existsByEmail(model.getEmail(), model.getId())) {
      throw new DangKyTaiKhoanException.DuplicateEmail(new Object[]{model.getEmail()});
    }
  }

  private DmDonViDTO mapToDmDonViDTO(DangKyTaiKhoanDTO dto) {
    DmDonViDTO dmDonViDTO = new DmDonViDTO();
    dmDonViDTO.setMaSoThue(dto.getMaSoThue());
    dmDonViDTO.setTen(dto.getTenDoanhNghiep());
    dmDonViDTO.setDmXaPhuongId(dto.getDmXaPhuongId());
    dmDonViDTO.setDmQuanHuyenId(dto.getDmQuanHuyenId());
    dmDonViDTO.setDmTinhThanhPhoId(dto.getDmTinhThanhPhoId());
    dmDonViDTO.setLoaiCongTy(LoaiCongTyEnum.PHAN_PHOI);
    dmDonViDTO.setLoaiDonVi(LoaiDonViEnum.CONG_TY_TTB);
    dmDonViDTO.setLoaiCsyt(LoaiCsytEnum.KHAC);
    dmDonViDTO.setMa(dto.getMaSoThue());
    dmDonViDTO.setNguoiDaiDienDoanhNghiep(dto.getNguoiDaiDienDoanhNghiep());
    dmDonViDTO.setNguoiLienHe(dto.getNguoiDaiDienDoanhNghiep());
    dmDonViDTO.setDiaChi(dto.getDiaChiChiTiet());
    dmDonViDTO.setSoDienThoai(dto.getSoDienThoaiDoanhNghiep());
    dmDonViDTO.setEmail(dto.getEmail());
    dmDonViDTO.setTaiLieuUyQuyen(dto.getTaiLieuGiayUyQuyen());
    dmDonViDTO.setFax(dto.getFax());
    dmDonViDTO.setChungNhanDangKyKinhDoanh(dto.getGiayChungNhanDkkd());
    dmDonViDTO.setNgayCapChungNhanDkkd(dto.getNgayCapGiayDkkd());
    dmDonViDTO.setCoQuanCapPhep(dto.getCoQuanCapGiayDkkd());
    dmDonViDTO.setNguoiDaiDienDoanhNghiep(dto.getNguoiDaiDienDoanhNghiep());
    dmDonViDTO.setChucVu(dto.getChucVu());
    dmDonViDTO.setDienThoaiNguoiDaiDien(dto.getSoDienThoai());
    dmDonViDTO.setSoCmndNguoiDaiDien(dto.getSoCmndNguoiDaiDien());
    dmDonViDTO.setNgayCapCmnd(dto.getNgayCapCmnd());
    dmDonViDTO.setNoiCapCmnd(dto.getNoiCapCmnd());
    dmDonViDTO.setTenGiaoDichQt(dto.getTenGiaoDichQuocTe());
    return dmDonViDTO;

  }


  private static String getFileContent(String fileName) {
    String result = "";
    ClassLoader classLoader = FileUtils.class.getClassLoader();
    try {
      result = IOUtils.toString(classLoader.getResourceAsStream(fileName), "UTF-8");
    } catch (IOException e) {
      throw new DangKyTaiKhoanException.MissingFileTemplateEmail();
    }

    return result;
  }
}
