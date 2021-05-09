package vn.isofh.may.tho.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.Header;
import vn.isofh.may.tho.dao.model.DmSuaDoiGiaEntity;
import vn.isofh.may.tho.dao.model.ThietBiEntity;
import vn.isofh.may.tho.dao.model.ThietBiInVitroEntity;
import vn.isofh.may.tho.dao.model.statistic.DashboardEntity;
import vn.isofh.may.tho.dao.repository.ThietBiInVitroRepository;
import vn.isofh.may.tho.dao.repository.statistic.DashboardThietBiRepository;
import vn.isofh.may.tho.dto.DmThietBiInVitroDTO;
import vn.isofh.may.tho.dto.ThietBiDTO;
import vn.isofh.may.tho.dto.ThietBiInVitroDTO;
import vn.isofh.may.tho.enums.DinhTinhDinhLuongEnum;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;

@Service
public class ThietBiInVitroServiceImpl extends
    AbstractService<ThietBiInVitroEntity, ThietBiInVitroDTO, ThietBiInVitroRepository> implements ThietBiInVitroService {

  @Autowired
  private ThietBiInVitroRepository repository;

  @Autowired
  private DmQuocGiaService dmQuocGiaService;

  @Autowired
  private DmDonViTinhService dmDonViTinhService;

  @Autowired
  private DmPhuongPhapXetNgiemService dmPhuongPhapXetNgiemService;

  @Autowired
  private DmThongSoPhanTichService dmThongSoPhanTichService;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private DmThietBiInVitroService dmThietBiInVitroService;

  @Autowired
  private DmNhomThietBiService dmNhomThietBiService;

  @Autowired
  private DmLoaiMauSuDungService dmLoaiMauSuDungService;

  @Autowired
  private UserService userService;

  @Autowired
  private DashboardThietBiRepository dashboardRepository;

  @Override
  protected ThietBiInVitroRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<ThietBiDTO, ThietBiEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmModel());

        skip(destination.getGiaNiemYet());
        skip(destination.getNgayHetHieuLuc());
      }
    });
  }

  @Override
  protected void specificMapToDTO(ThietBiInVitroEntity entity, ThietBiInVitroDTO dto) {
    super.specificMapToDTO(entity, dto);

    dto.setDmDonViTinh(dmDonViTinhService.getBaseDTOById(entity.getDmDonViTinhId()));
    dto.setXuatXu(dmQuocGiaService.getBaseDTOById(entity.getXuatXuId()));
    dto.setHangSoHuu(dmDonViService.getBaseDTOById(entity.getHangSoHuuId()));
    dto.setDmThietBiInVitro(dmThietBiInVitroService.findById(entity.getDmThietBiInVitroId()));
    dto.setDmNhomThietBi(dmNhomThietBiService.getBaseDTOById(entity.getDmNhomThietBiId()));
    dto.setDmNhomThietBi(dmNhomThietBiService.getBaseDTOById(entity.getDmNhomThietBiId()));
    dto.setNuocSoHuu(dmQuocGiaService.getBaseDTOById(entity.getNuocSoHuuId()));
    dto.setDmHangSanXuat(dmDonViService.getBaseDTOById(entity.getDmHangSanXuatId()));
    dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
    dto.setNguoiSua(userService.findById(entity.getUpdatedBy()).getFullName());
    dto.setDmDonVi(dmDonViService.getBaseDTOById(entity.getDmDonViId()));

    if (entity.isMapAllProperties()) {
      if (entity.getNuocSanXuatIds() != null) {
        dto.setNuocSanXuat(entity.getNuocSanXuatIds()
            .stream()
            .map(id -> dmQuocGiaService.getBaseDTOById(id))
            .collect(Collectors.toList())
        );
      }

      if (entity.getDmThongSoPhanTichIds() != null) {
        dto.setDmThongSoPhanTich(entity.getDmThongSoPhanTichIds()
            .stream()
            .map(id -> dmThongSoPhanTichService.getBaseDTOById(id))
            .collect(Collectors.toList())
        );
      }

      if (entity.getDmLoaiMauSuDungIds() != null) {
        dto.setDmLoaiMauSuDung(entity.getDmLoaiMauSuDungIds()
            .stream()
            .map(id -> dmLoaiMauSuDungService.getBaseDTOById(id))
            .collect(Collectors.toList())
        );
      }
    }
  }

  @Override
  @PreAuthorize("hasRole('nhom_invitro')")
  public ThietBiInVitroDTO save(ThietBiInVitroDTO dto) {
    return super.save(dto);
  }

  @Override
  @PreAuthorize("hasRole('nhom_invitro')")
  public ThietBiInVitroDTO save(Long id, ThietBiInVitroDTO dto) {
    return super.save(id, dto);
  }

  @Override
  @PreAuthorize("hasRole('nhom_invitro')")
  public ThietBiInVitroDTO save(Long id, Map<String, Object> map) {
    return super.save(id, map);
  }

  @Override
  @PreAuthorize("hasRole('nhom_invitro')")
  public void delete(Long id) {
    super.delete(id);
  }

  @Override
  protected ThietBiInVitroEntity beforeSave(ThietBiInVitroEntity entity, ThietBiInVitroDTO dto) {

    generateSuaDoiGia(dto, entity);

    setDonVi(entity);

    return super.beforeSave(entity, dto);
  }

  @Override
  protected ThietBiInVitroDTO afterSave(ThietBiInVitroEntity entity, ThietBiInVitroDTO dto) {

    capNhapSoLuong(entity);

    return super.afterSave(entity, dto);
  }

  public void generateSuaDoiGia(ThietBiInVitroDTO dto, ThietBiInVitroEntity model) {
    if (!model.isNewRecord() && dto.getGiaNiemYet().equals(model.getGiaNiemYet())
        && dto.getNgayHetHieuLuc().equals(model.getNgayHetHieuLuc())) {
      return;
    }

    DmSuaDoiGiaEntity suaDoiGia = new DmSuaDoiGiaEntity();

    suaDoiGia.setThietBiInVitro(model);
    suaDoiGia.setGiaTruocThayDoi(model.getGiaNiemYet());
    suaDoiGia.setGiaSauThayDoi(dto.getGiaNiemYet());
    suaDoiGia.setDenNgay(DateUtil.getZonedDateTime(dto.getNgayHetHieuLuc()));
    suaDoiGia.setTuNgay(DateUtil.getZonedDateTime(dto.getNgayBatDauHieuLuc()));

    if (model.getDmSuaDoiGia() == null) {
      model.setDmSuaDoiGia(new HashSet<>());
    }

    model.setGiaNiemYet(dto.getGiaNiemYet());
    model.setNgayHetHieuLuc(dto.getNgayHetHieuLuc());
    model.setNgayBatDauHieuLuc(dto.getNgayBatDauHieuLuc());
    model.getDmSuaDoiGia().add(suaDoiGia);
  }

  private void capNhapSoLuong(ThietBiInVitroEntity entity){
    DmThietBiInVitroDTO thietBiInVitro = dmThietBiInVitroService.findById(entity.getDmThietBiInVitroId());

    dmPhuongPhapXetNgiemService.capNhapSoLuong(thietBiInVitro.getDmPhuongPhapXetNghiemId()
        , getRepository().getSoLuong(thietBiInVitro.getDmPhuongPhapXetNghiemId()));
  }

  @Override
  public List<ThietBiInVitroDTO> getDmThongSoPhanTich(Long id) {
    return null;
  }

  @Override
  public List<DashboardEntity> thongKeIvdTheoPhuongPhap(Long[] dmPhuongPhapIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids) {
    return dashboardRepository.thongKeIvdTheoPhuongPhap(dmPhuongPhapIds,dmDonViIds,hang_san_xuat_ids);
  }

  @Override
  public List<DashboardEntity> thongKeIvdTheoDoanhNghiep(Long[] dmPhuongPhapIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids) {
    return dashboardRepository.thongKeIvdTheoDoanhNghiep(dmPhuongPhapIds,dmDonViIds,hang_san_xuat_ids);
  }

  @Override
  public List<DashboardEntity> thongKeIvdTheoHangSanXuat(Long[] dmPhuongPhapIds, Long[] dmDonViIds,
      Long[] hang_san_xuat_ids) {
    return dashboardRepository.thongKeIvdTheoHangSanXuat(dmPhuongPhapIds,dmDonViIds,hang_san_xuat_ids);
  }

  private void setDonVi(ThietBiInVitroEntity entity){

    if(entity.isNewRecord()){
      entity.setDmDonViId(getDonViId());
    }

  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmThietBiInVitroId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmThietBiInVitroService.getIdByTen(value);
    }else if ("xuatXuId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmQuocGiaService.getIdByTen(value);
    }else if ("dmHangSanXuatId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmDonViService.getIdByTen(value);
    }else if ("nuocSanXuatIds".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return Collections.singleton(dmQuocGiaService.getIdByTen(value));
    }else if ("hangSoHuuId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmDonViService.getIdByTen(value);
    }else if ("nuocSoHuuId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmQuocGiaService.getIdByTen(value);
    }else if ("dinhTinhDinhLuong".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dinhTinhDinhLuong(value);
    }else if ("dmThongSoPhanTichIds".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return Collections.singletonList(dmThongSoPhanTichService.getIdByTen(value, LoaiThongSoPhanTichEnum.THONG_SO_PHAN_TICH));
    }else if ("dmDonViTinhId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmDonViTinhService.getIdByTen(value);
    }else if ("dmLoaiMauSuDungIds".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return Collections.singletonList(dmLoaiMauSuDungService.getIdByTen(value));
    }else if ("namSanXuat".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return Collections.singletonList(value.replace(".0",""));
    } else if ("phanLoai".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return PhanLoaiTtbEnum.valueOf(value);
    }

    return super.getReference(header, value);
  }

  private DinhTinhDinhLuongEnum dinhTinhDinhLuong(String value) {

    if (value.equals("định tính/dịnh danh")) {
      return DinhTinhDinhLuongEnum.DINH_TINH;
    }
    if (value.equals("định lượng")) {
      return DinhTinhDinhLuongEnum.DINH_LUONG;
    }
    if (value.equals("bán định lượng")) {
      return DinhTinhDinhLuongEnum.BAN_DINH_LUONG;
    }
    return DinhTinhDinhLuongEnum.DINH_TINH;
  }

}
