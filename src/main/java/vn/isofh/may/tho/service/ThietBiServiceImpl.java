package vn.isofh.may.tho.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.Header;
import vn.isofh.common.util.NumberUtil;
import vn.isofh.common.util.StringUtil;
import vn.isofh.may.tho.dao.model.DmSuaDoiGiaEntity;
import vn.isofh.may.tho.dao.model.ThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.DanhSachThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.DashboardEntity;
import vn.isofh.may.tho.dao.model.statistic.DieuChuyenThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.NguonVonThietBiEntity;
import vn.isofh.may.tho.dao.model.statistic.ThongKeDonViEntity;
import vn.isofh.may.tho.dao.model.statistic.TrangThaiThietBiEntity;
import vn.isofh.may.tho.dao.repository.ThietBiRepository;
import vn.isofh.may.tho.dao.repository.statistic.DashboardThietBiRepository;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.dto.DmModelDTO;
import vn.isofh.may.tho.dto.DmThietBiDTO;
import vn.isofh.may.tho.dto.ThietBiDTO;
import vn.isofh.may.tho.enums.DeviceEnum;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Service
public class ThietBiServiceImpl extends
    AbstractService<ThietBiEntity, ThietBiDTO, ThietBiRepository> implements ThietBiService {

  private final String THIET_BI_IMAGES = "thiet-bi/images";
  private final String THIET_BI_DOCUMENTS = "thiet-bi/documents";
  private final String SERIAL_PREFIX = "SN*";

  @Autowired
  private ThietBiRepository repository;

  @Autowired
  private DashboardThietBiRepository dashboardRepository;

  @Autowired
  private DmThietBiService dmThietBiService;

  @Autowired
  private DmLoaiThietBiService dmLoaiThietBiService;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private DmQuocGiaService dmQuocGiaService;

  @Autowired
  private DmNguonVonService dmNguonVonService;

  @Autowired
  private DmModelService dmModelService;

  @Autowired
  private DmDonViTinhService dmDonViTinhService;

  @Autowired
  private DmTrangThaiService dmTrangThaiService;

  @Autowired
  private DmThongSoPhanTichService dmThongSoPhanTichService;

  @Autowired
  private DmNhomThietBiService dmNhomThietBiService;

  @Autowired
  private UserService userService;

  @Override
  protected ThietBiRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<ThietBiDTO, ThietBiEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmDonVi());
        skip(destination.getDmTrangThai());
        skip(destination.getThietBiPhuTro());
        skip(destination.getDmDonViTinh());
        skip(destination.getDmModel());
        skip(destination.getDmNguonVon());
        skip(destination.getDmDonViId());

        skip(destination.getGiaNiemYet());
        skip(destination.getNgayHetHieuLuc());
      }
    });
  }

  @Override
  protected void specificMapToDTO(ThietBiEntity entity, ThietBiDTO dto) {
    super.specificMapToDTO(entity, dto);

    if (entity.isMapAllProperties()) {

      dto.setThietBiPhuTro(
          getRepository().findByThietBiChinhId(entity.getId())
              .stream()
              .map(this::mapToDTO)
              .collect(Collectors.toSet()));
    }

    DmModelDTO model = dmModelService.findById(entity.getDmModelId());
    dto.setDmModel(model);
    if (model != null) {
      dto.setHangSoHuu(dmDonViService.getBaseDTOById(model.getHangSoHuuId()));
      dto.setNuocSoHuu(dmQuocGiaService.getBaseDTOById(model.getNuocSoHuuId()));
      dto.setDmHangSanXuat(dmDonViService.getBaseDTOById(model.getDmHangSanXuatId()));
    }
    dto.setDmLoaiThietBi(dmLoaiThietBiService.findById(entity.getDmLoaiThietBiId()));
    dto.setDmDonVi(dmDonViService.getBaseDTOById(entity.getDmDonViId()));
    dto.setDmNguonVon(dmNguonVonService.getBaseDTOById(entity.getDmNguonVonId()));
    dto.setDmDonViTinh(dmDonViTinhService.getBaseDTOById(entity.getDmDonViTinhId()));
    dto.setDmTrangThai(dmTrangThaiService.getBaseDTOById(entity.getDmTrangThaiId()));
    dto.setDmThongSoPhanTich(dmThongSoPhanTichService.getBaseDTOById(entity.getDmThongSoPhanTichId()));
    dto.setXuatXu(dmQuocGiaService.getBaseDTOById(entity.getXuatXuId()));
    dto.setDmNhomThietBi(dmNhomThietBiService.getBaseDTOById(entity.getDmNhomThietBiId()));
    dto.setDmNhomTbyt(dmNhomThietBiService.getBaseDTOById(entity.getDmNhomTbytId()));

    if (entity.getNuocSanXuatIds() != null) {
      dto.setNuocSanXuat(entity.getNuocSanXuatIds()
          .stream()
          .map(id -> dmQuocGiaService.getBaseDTOById(id))
          .collect(Collectors.toList())
      );
    }

    dto.setNguoiTao(entity.getCreatedBy() == null ? null : userService.findById(entity.getCreatedBy()).getFullName());
    dto.setNguoiSua(entity.getUpdatedBy() == null ? null : userService.findById(entity.getUpdatedBy()).getFullName());
  }

  @Override
  @PreAuthorize("hasRole('nhom_ttbyt')")
  public ThietBiDTO save(ThietBiDTO dto) {
    return super.save(dto);
  }

  @Override
  @PreAuthorize("hasRole('nhom_ttbyt')")
  public ThietBiDTO save(Long id, ThietBiDTO dto) {
    return super.save(id, dto);
  }

  @Override
  @PreAuthorize("hasRole('nhom_ttbyt')")
  public ThietBiDTO save(Long id, Map<String, Object> map) {
    return super.save(id, map);
  }

  @Override
  @PreAuthorize("hasRole('nhom_ttbyt')")
  public void delete(Long id) {
    super.delete(id);
  }

  @Override
  protected ThietBiEntity beforeSave(ThietBiEntity model, ThietBiDTO dto) {

    setCSYT(model);

    Map<String, Integer> map = new HashMap<>();

    generateSerial(model);

    setToLines(model, dto.getThietBiPhuTro(), map);

    generateSuaDoiGia(dto, model);

    addNuocSanXuat(model);

    return super.beforeSave(model, dto);
  }

  @Override
  protected ThietBiDTO afterSave(ThietBiEntity entity, ThietBiDTO dto) {

    dmNhomThietBiService.capNhapSoLuong(entity.getDmNhomTbytId(), getRepository().getSoLuong(entity.getDmNhomTbytId()));

    return super.afterSave(entity, dto);
  }

  @Override
  public List<TrangThaiThietBiEntity> getTrangThaiThietBi(Long trangThaiId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, Long donViId, Integer tieuChi) {
    return dashboardRepository.getTrangThaiThietBi(trangThaiId, tinhThanhPhoId, quanHuyenId, xaPhuongId,
            donViId, tieuChi);
  }

  @Override
  protected ThietBiDTO beforeSearch(ThietBiDTO dto) {

    if (NumberUtil.isCreatable(dto.getTimKiem())) {
      dto.setGiaNiemYetLonNhat(NumberUtil.toInteger(dto.getTimKiem()));
      dto.setNamSanXuatTimKiem(NumberUtil.toInteger(dto.getTimKiem()));
    }

    return super.beforeSearch(dto);
  }

  @Override
  public List<DanhSachThietBiEntity> getDanhSachThietBi(Long trangThaiId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, Long donViId) {
    return dashboardRepository.getDanhSachThietBi(trangThaiId, tinhThanhPhoId,
        quanHuyenId, xaPhuongId, donViId);
  }

  @Override
  public List<ThongKeDonViEntity> getCoSoYTeNhap(Long coSoYTeId, Long tinhThanhPhoId) {
    return dashboardRepository.getCoSoYTeNhap(coSoYTeId, tinhThanhPhoId);
  }

  @Override
  public List<DieuChuyenThietBiEntity> getDieuChuyenThietBi(Long coSoYTeId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId) {
    return dashboardRepository.getDieuChuyenThietBi(coSoYTeId, tinhThanhPhoId, quanHuyenId, xaPhuongId);
  }

  @Override
  public List<NguonVonThietBiEntity> getNguonVonThietBi(Long coSoYTeId, Long tinhThanhPhoId,
      Long quanHuyenId, Long xaPhuongId, String tenThietBi) {
    return dashboardRepository.getNguonVonThietBi(coSoYTeId, tinhThanhPhoId, quanHuyenId, xaPhuongId, tenThietBi);
  }


  @Override
  public List<String> uploadDocument(MultipartFile[] file) {
    List<String> list = new ArrayList<>();
    for (MultipartFile f : file) {
      list.add(getStorageService().store(f, THIET_BI_DOCUMENTS).getRelativePath().toString());
    }

    return list;
  }

  @Override
  public List<String> uploadImage(MultipartFile[] file) {
    List<String> list = new ArrayList<>();
    for (MultipartFile f : file) {
      list.add(getStorageService().store(f, THIET_BI_IMAGES).getRelativePath().toString());
    }

    return list;
  }

  @Override
  public File getDanhSachThietBiExcel(Long trangThaiId, Long tinhThanhPhoId, Long quanHuyenId,
      Long xaPhuongId, Long donViId) {
    return null;
  }

  @Override
  public List<DashboardEntity> thongKeHoThietBi(Long[] dmThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds, Long[] dmTinhThanhPhoIds) {
    return dashboardRepository.thongKeHoThietBi(dmThietBiIds, dmLoaiThietBiIds, dmDonViIds, dmTinhThanhPhoIds);
  }

  @Override
  public List<DashboardEntity> thongKeLoaiThietBi(Long[] dmThietBiIds, Long[] dmLoaiThietBiIds,
      Long[] dmDonViIds, Long giaTuKhoang, Long giaDenKhoang) {
    return dashboardRepository.thongKeLoaiThietBi(dmThietBiIds, dmLoaiThietBiIds, dmDonViIds, giaTuKhoang, giaDenKhoang);
  }

  @Override
  public List<DashboardEntity> thongKeHangSanXuat(Long[] dmNhomThietBiIds,Long[] dmLoaiThietBiIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds) {
    return dashboardRepository.thongKeHangSanXuat(dmNhomThietBiIds,dmLoaiThietBiIds,dmDonViIds,
        hang_san_xuat_ids,dmTinhThanhPhoIds);
  }

  @Override
  public List<DashboardEntity> thongKeNhomThietBi(Long[] dmNhomThietBiIds,Long[] dmLoaiThietBiIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds) {
    return dashboardRepository.thongKeNhomThietBi(dmNhomThietBiIds,dmLoaiThietBiIds,dmDonViIds,
        hang_san_xuat_ids,dmTinhThanhPhoIds);
  }

  @Override
  public List<DashboardEntity> thongKeThietBiTheoDoanhNghiep(Long[] dmNhomThietBiIds,Long[] dmLoaiThietBiIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids, Long[] dmTinhThanhPhoIds) {
    return dashboardRepository.thongKeThietBiTheoDoanhNghiep(dmNhomThietBiIds,dmLoaiThietBiIds,dmDonViIds,
        hang_san_xuat_ids,dmTinhThanhPhoIds);
  }

  private void generateMaThietBi(ThietBiEntity thietBi, Map<String, Integer> map) {

    if (thietBi.getMa() != null) {
      return;
    }

    DmThietBiDTO dmThietBi = null;
    String prefix = null;

    if (thietBi.getDmNhomThietBiId() != null && thietBi.getDmNhomThietBiId() > 0) {
      prefix = dmThietBiService.getTenVietTat(thietBi.getDmNhomThietBiId());
    }

    if (prefix != null) {
      prefix = prefix + "_";
    } else {
      prefix = "";
    }

    if (thietBi.getNamMua() != null) {
      prefix = prefix + thietBi.getNamMua() + "_";
    }

    DmDonViDTO dmDonVi = dmDonViService.findById(getDonViId());

    prefix = prefix + dmDonVi.getMa() + "_";

    Integer currentInt = map.get(prefix);

    if (currentInt == null) {
      String current = repository.getCurrentMa(prefix);
      if (current != null) {
        currentInt = Integer.parseInt(current);
      } else {
        currentInt = 0;
      }
    }

    map.put(prefix, currentInt + 1);

    thietBi.setMa(prefix + String.format("%04d", currentInt + 1));
  }

  private void generateSerial(ThietBiEntity thietBi) {
    if (!StringUtils.isBlank(thietBi.getSerial())) {
      thietBi.setSerial(thietBi.getSerial().trim());
      return;
    }

    String currentMa = repository.getCurrentSerial(SERIAL_PREFIX);
    int current = 0;

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    thietBi.setSerial(SERIAL_PREFIX + (current + 1));
  }

  private void setCSYT(ThietBiEntity thietBi) {

    if(thietBi.isNewRecord()){
      thietBi.setDmDonViId(getDonViId());
    }

  }

  private void setToLines(ThietBiEntity entity, Set<ThietBiDTO> dtos, Map<String, Integer> map) {
    Set<ThietBiEntity> lines = mapToEntities(dtos, entity.getThietBiPhuTro());

    lines.forEach(line -> {
      line.setThietBiChinh(entity);
      line.setDmDonViId(entity.getDmDonViId());
      generateMaThietBi(line, map);
      generateSerial(line);
    });
    getRepository().saveAll(lines);
    entity.setThietBiPhuTro(lines);
  }

  private void generateSuaDoiGia(ThietBiDTO dto, ThietBiEntity model) {
    if (!model.isNewRecord() && dto.getGiaNiemYet().equals(model.getGiaNiemYet())
        && dto.getNgayHetHieuLuc().equals(model.getNgayHetHieuLuc())) {
      return;
    }

    DmSuaDoiGiaEntity suaDoiGia = new DmSuaDoiGiaEntity();

    suaDoiGia.setThietBi(model);
    suaDoiGia.setGiaTruocThayDoi(model.getGiaNiemYet());
    suaDoiGia.setGiaSauThayDoi(dto.getGiaNiemYet());
    suaDoiGia.setDenNgay(DateUtil.getZonedDateTime(dto.getNgayHetHieuLuc()));
    suaDoiGia.setTuNgay(DateUtil.getZonedDateTime(dto.getNgayBatDauHieuLuc()));

    if (model.getDmSuaDoiGia() == null) {
      model.setDmSuaDoiGia(new HashSet<>());
    }

    model.setGiaNiemYet(dto.getGiaNiemYet());
    model.setNgayHetHieuLuc(dto.getNgayHetHieuLuc());
    model.setCanCuCauThanhGia(dto.getCanCuCauThanhGia());
    model.setNgayBatDauHieuLuc(dto.getNgayBatDauHieuLuc());
    model.getDmSuaDoiGia().add(suaDoiGia);
  }

  @Override
  protected Object getReference(Header header, String value) {
  if (("dmLoaiThietBiId".equals(header.getColumnName()))
        && "ten".equals(header.getLinkColumnName())) {
      return dmLoaiThietBiService.getIdByTen(value);
    } else if (("dmModelId".equals(header.getColumnName()))
        && "ten".equals(header.getLinkColumnName())) {
      return dmModelService.getIdByTen(value);
    } else if ("dmDonViTinhId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmDonViTinhService.getIdByTen(value);
    }else if ("dmNhomTbytId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmNhomThietBiService.getIdByTen(value);
    }else if ("dmThongSoPhanTichId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmThongSoPhanTichService.getIdByTen(value, LoaiThongSoPhanTichEnum.THONG_SO_PHAN_TICH);
    }else if ("xuatXuId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmQuocGiaService.getIdByTen(value);
    }else if ("phanLoai".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return PhanLoaiTtbEnum.valueOf(value);
    } else if ("namSanXuat".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return StringUtil.toStringList(value.replace(".0",""));
  } else if ("nuocSanXuatIds".equals(header.getColumnName())
      && "ten".equals(header.getLinkColumnName())) {
    return Collections.singletonList(dmQuocGiaService.getIdByTen(value));
  } else if ("device".equals(header.getColumnName()) && "ten".equals(header.getLinkColumnName())) {
    return DeviceEnum.valueOf(value);
  }

    return super.getReference(header, value);
  }

  private void addNuocSanXuat(ThietBiEntity entity) {

    if (entity.getNuocSanXuatIds() == null && entity.isNewRecord()) {

      entity.setDevice(DeviceEnum.TTB_CT);
      dmModelService.findById(entity.getDmModelId())
          .getNuocSanXuatIds()
          .forEach(e-> entity.setNuocSanXuatIds(Collections.singletonList(e)));
    }
  }
}
