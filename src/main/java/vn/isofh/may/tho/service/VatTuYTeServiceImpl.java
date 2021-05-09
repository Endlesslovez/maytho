package vn.isofh.may.tho.service;

import java.util.HashSet;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.Header;
import vn.isofh.common.util.NumberUtil;
import vn.isofh.common.util.StringUtil;
import vn.isofh.may.tho.dao.model.DmSuaDoiGiaEntity;
import vn.isofh.may.tho.dao.model.DmThietBiEntity;
import vn.isofh.may.tho.dao.model.VatTuYTeEntity;
import vn.isofh.may.tho.dao.repository.VatTuYTeRepository;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.dto.VatTuYTeDTO;
import vn.isofh.may.tho.enums.PhanLoaiTtbEnum;
import vn.isofh.may.tho.exception.VatTuYTeException;


@Service
public class VatTuYTeServiceImpl extends
    AbstractService<VatTuYTeEntity, VatTuYTeDTO, VatTuYTeRepository> implements
    VatTuYTeService {

  @Autowired
  private VatTuYTeRepository repository;

  @Autowired
  private DmQuocGiaService dmQuocGiaService;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private DmDonViTinhService dmDonViTinhService;

  @Autowired
  private DmNhomThietBiService nhomThietBiService;

  @Autowired
  private DmVatTuYTeService dmVatTuYTeService;

  @Autowired
  private DmThietBiService dmThietBiService;

  @Autowired
  private DmLoaiThietBiService dmLoaiThietBiService;

  @Autowired
  private UserService userService;

  @Override
  protected VatTuYTeRepository getRepository() {
    return repository;
  }


  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<VatTuYTeDTO, VatTuYTeEntity>() {
      @Override
      protected void configure() {
        skip(destination.getGiaNiemYet());
        skip(destination.getNgayHetHieuLuc());
      }
    });
  }


  @Override
  protected void specificMapToDTO(VatTuYTeEntity entity, VatTuYTeDTO dto) {
    super.specificMapToDTO(entity, dto);

    DmThietBiEntity dmThietBiEntity = dmThietBiService
        .getByNhomVTYT(entity.getDmLoaiVtytId()).orElse(null);

    dto.setHangSoHuu(dmDonViService.getBaseDTOById(entity.getHangSoHuuId()));
    dto.setNuocSoHuu(dmQuocGiaService.getBaseDTOById(entity.getNuocSoHuuId()));
    dto.setDmDonViTinh(dmDonViTinhService.getBaseDTOById(entity.getDmDonViTinhId()));
    dto.setNhomThietBi(nhomThietBiService.getBaseDTOById(entity.getNhomThietBiId()));
    dto.setDmVatTuYTe(dmVatTuYTeService.findById(entity.getDmVatTuYTeId()));
    dto.setDmHangSanXuat(dmDonViService.getBaseDTOById(entity.getDmHangSanXuatId()));
    dto.setDmLoaiVtyt(dmLoaiThietBiService.getBaseDTOById(entity.getDmLoaiVtytId()));
    dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
    dto.setNguoiSua(userService.findById(entity.getUpdatedBy()).getFullName());
    dto.setNuocSanXuat(dmQuocGiaService.getBaseDTOById(entity.getNuocSanXuatId()));
    dto.setDmDonVi(dmDonViService.getBaseDTOById(entity.getDmDonViId()));
    if (dmThietBiEntity != null) {
      dto.setDmNhomVtyt(dmThietBiService.getBaseDTOById(dmThietBiEntity.getId()));
      dto.setDmNhomVtytId(dmThietBiEntity.getId());
    }
  }

  @Override
  @PreAuthorize("hasRole('nhom_vtyt')")
  public VatTuYTeDTO save(VatTuYTeDTO dto) {
    return super.save(dto);
  }

  @Override
  @PreAuthorize("hasRole('nhom_vtyt')")
  public VatTuYTeDTO save(Long id, VatTuYTeDTO dto) {
    return super.save(id, dto);
  }

  @Override
  @PreAuthorize("hasRole('nhom_vtyt')")
  public VatTuYTeDTO save(Long id, Map<String, Object> map) {
    return super.save(id, map);
  }

  @Override
  @PreAuthorize("hasRole('nhom_vtyt')")
  public void delete(Long id) {
    super.delete(id);
  }

  @Override
  protected VatTuYTeEntity beforeSave(VatTuYTeEntity model, VatTuYTeDTO dto) {
    validateMaSanPham(dto);
    String prefix = getCurrentPrefix(model);
    generateSuaDoiGia(dto, model);
    if (model.getMa() == null || checkThayDoiHangSx(model, prefix)) {
      generateMa(prefix, model);
    }

    setDonVi(model);

    return super.beforeSave(model, dto);
  }

  public void generateSuaDoiGia(VatTuYTeDTO dto, VatTuYTeEntity model) {
    if (!model.isNewRecord() && dto.getGiaNiemYet().equals(model.getGiaNiemYet())
        && dto.getNgayHetHieuLuc().equals(model.getNgayHetHieuLuc())) {
      return;
    }

    DmSuaDoiGiaEntity suaDoiGia = new DmSuaDoiGiaEntity();

    suaDoiGia.setVatTuYTe(model);
    suaDoiGia.setGiaTruocThayDoi(model.getGiaNiemYet());
    suaDoiGia.setGiaSauThayDoi(dto.getGiaNiemYet());
    suaDoiGia.setDenNgay(DateUtil.getZonedDateTime(dto.getNgayHetHieuLuc()));
    suaDoiGia.setTuNgay(DateUtil.getZonedDateTime(dto.getNgayBatDauHieuLuc()));
    suaDoiGia.setVatTuYTeId(model.getId());

    if (model.getDmSuaDoiGia() == null) {
      model.setDmSuaDoiGia(new HashSet<>());
    }

    model.setGiaNiemYet(dto.getGiaNiemYet());
    model.setNgayHetHieuLuc(dto.getNgayHetHieuLuc());
    model.setNgayBatDauHieuLuc(dto.getNgayBatDauHieuLuc());
    model.getDmSuaDoiGia().add(suaDoiGia);
  }

  @Override
  protected VatTuYTeDTO afterSave(VatTuYTeEntity entity, VatTuYTeDTO dto) {
    DmThietBiEntity dmThietBiEntity = dmThietBiService.getByNhomVTYT(dto.getDmLoaiVtytId())
        .orElse(null);

    if (dmThietBiEntity != null) {
      dmThietBiService.capNhatSoLuongThietBi(dmThietBiEntity.getId(),
          getRepository().getSoLuongByDmThietBiId(dmThietBiEntity.getId()));
    }

    return super.afterSave(entity, dto);
  }

  @Override
  protected VatTuYTeEntity afterDelete(VatTuYTeEntity entity) {
    DmThietBiEntity dmThietBiEntity = dmThietBiService
        .getByNhomVTYT(entity.getDmLoaiVtytId()).orElse(null);
    if (dmThietBiEntity != null) {
      dmThietBiService.capNhatSoLuongThietBi(dmThietBiEntity.getId(),
          getRepository().getSoLuongByDmThietBiId(dmThietBiEntity.getId()));
    }
    return super.afterDelete(entity);
  }

  public void generateMa(String prefix, VatTuYTeEntity model) {
    int currentMaInt = 1;
    String currentMaStr = repository.getMaLonNhat(prefix);
    if (NumberUtil.isCreatable(currentMaStr)) {
      currentMaInt = Integer.parseInt(currentMaStr.substring(prefix.length() + 1)) + 1;
    }
    model.setMa(prefix + "." + String.format("%06d", currentMaInt));
  }

  public boolean checkThayDoiHangSx(VatTuYTeEntity model, String prefix) {
    return !(model.getMa().contains(prefix));
  }

  public String getCurrentPrefix(VatTuYTeEntity model) {
    DmLoaiThietBiDTO loai2 = dmLoaiThietBiService.findById(model.getDmLoaiVtytId());
    DmDonViDTO hangSanXuat = dmDonViService.findById(model.getDmHangSanXuatId());
    return loai2 == null ? "" : loai2.getMa() + "." + hangSanXuat.getMa();
  }

  private void setDonVi(VatTuYTeEntity entity) {

    if (entity.isNewRecord()) {
      entity.setDmDonViId(getDonViId());
    }

  }

  @Override
  protected VatTuYTeDTO beforeSearch(VatTuYTeDTO dto) {
    if (NumberUtil.isCreatable(dto.getTimKiem())) {
      dto.setGiaNiemYetLonNhat(NumberUtil.toLong(dto.getTimKiem()));
      dto.setNamSanXuatTimKiem(NumberUtil.toInteger(dto.getTimKiem()));
    }
    return super.beforeSearch(dto);
  }

  private void validateMaSanPham(VatTuYTeDTO model) {
    if (repository.validateMaSanPham(model.getDmLoaiVtytId(), model.getTen(), model.getMaSanPham(),
        model.getDmHangSanXuatId(), model.getId())) {
      throw new VatTuYTeException.DuplicateMSanPham("vat.tu.y.te.duplicate.ma.san.pham");
    }
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmLoaiVtytId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmLoaiThietBiService.getIdByTen(value);
    } else if (("dmHangSanXuatId".equals(header.getColumnName())
        || "hangSoHuuId".equals(header.getColumnName()))
        && "ten".equals(header.getLinkColumnName())) {
      return dmDonViService.getIdByTen(value);
    } else if (("nuocSanXuatId".equals(header.getColumnName())
        || "nuocSoHuuId".equals(header.getColumnName()))
        && "ten".equals(header.getLinkColumnName())) {
      return dmQuocGiaService.getIdByTen(value);
    } else if ("dmDonViTinhId".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return dmDonViTinhService.getIdByTen(value);
    } else if ("phanLoaiTtb".equals(header.getColumnName())
        && "ten".equals(header.getLinkColumnName())) {
      return PhanLoaiTtbEnum.valueOf(value);
    } else if ("namSanXuat".equals(header.getColumnName())
        && "value".equals(header.getLinkColumnName())) {
      return StringUtil.toStringList(value.replace(".0",""));
    }
    return super.getReference(header, value);
  }
}
