package vn.isofh.may.tho.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.isofh.common.report.ReportDTO;
import vn.isofh.common.report.ReportService;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.Header;
import vn.isofh.may.tho.dao.model.DmLoaiThietBiEntity;
import vn.isofh.may.tho.dao.repository.DmLoaiThietBiRepository;
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.enums.DmLoaiThietBiEnum;
import vn.isofh.may.tho.exception.DmLoaiThietBiException;

@Service
public class DmLoaiThietBiServiceImpl extends
    AbstractDmService<DmLoaiThietBiEntity, DmLoaiThietBiDTO, DmLoaiThietBiRepository> implements
    DmLoaiThietBiService {

  private final String PREFIX = "LTB";

  @Autowired
  private DmLoaiThietBiRepository repository;

  @Autowired
  private DmThietBiService dmThietBiService;

  @Autowired
  private ReportService reportService;

  @Autowired
  private DmLoaiThietBiService dmLoaiThietBiService;

  @Autowired
  private UserService userService;

  @Autowired
  private DmNhomThietBiService dmNhomThietBiService;

  @Override
  protected DmLoaiThietBiRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<DmLoaiThietBiDTO, DmLoaiThietBiEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmDonVi());
        skip(destination.getDmThietBi());
      }
    });
  }

  @Override
  protected void specificMapToDTO(DmLoaiThietBiEntity entity, DmLoaiThietBiDTO dto) {
    super.specificMapToDTO(entity, dto);
    if (entity.getDmThietBiId() == null && DmLoaiThietBiEnum.LOAI_VTTH_II.equals(entity.getLoai())) {
      DmLoaiThietBiDTO dmLoaiThietBi = dmLoaiThietBiService.findById(entity.getDmLoaiVtyt1Id());
      dto.setDmThietBi(dmThietBiService.getBaseDTOById(dmLoaiThietBi.getDmThietBiId()));
    } else {
      dto.setDmThietBi(dmThietBiService.getBaseDTOById(entity.getDmThietBiId()));
    }

    dto.setDmLoaiVtyt1(dmLoaiThietBiService.getBaseDTOById(entity.getDmLoaiVtyt1Id()));
    dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
    dto.setNguoiSua(userService.findById(entity.getUpdatedBy()).getFullName());
    dto.setDmNhomVtyt(dmNhomThietBiService.findById(entity.getDmNhomTbytId()));
    dto.setDmNhomTbyt(dmNhomThietBiService.findById(entity.getDmNhomTbytId()));
  }

  @Override
  protected DmLoaiThietBiEntity beforeSave(DmLoaiThietBiEntity model, DmLoaiThietBiDTO dto) {
    validateTen(model);

    validateFields(model);

    generateMa(model);

    return super.beforeSave(model, dto);
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmThietBiId".equals(header.getColumnName())) {
      if ("ma".equals(header.getLinkColumnName())) {
        return dmThietBiService.getIdByMa(value);
      } else if ("ten".equals(header.getLinkColumnName())) {
        return dmThietBiService.getIdByTen(value);
      }
    }

    return null;
  }

  @Override
  public ReportDTO exportLoaiThietBi(DmLoaiThietBiDTO dto, Pageable pageable) {
    List<DmLoaiThietBiDTO> lst = search(dto, pageable).getContent();

    Map<String, Object> report = new HashMap<>();
    List<Map<String, Object>> rows = new ArrayList<>();
    report.put("ngay", DateUtil.format(LocalDate.now(), "dd/MM/yyyy"));
    report.put("tongSoLoai", lst.size());
    report.put("rows", rows);

    for (int i = 0; i < lst.size(); i++) {
      Map<String, Object> row = new HashMap<>();
      row.put("stt", i + 1);
      row.put("maLoai", lst.get(i).getMa());
      row.put("loaiThietBi", lst.get(i).getTen());
      row.put("tenThietBi", dmThietBiService.getBaseDTOById(lst.get(i).getDmThietBiId()).getTen());
      row.put("thietBiPhuTro", (lst.get(i).getThietBiPhuTro() == null || !lst.get(i).getThietBiPhuTro()) ? "no" : "yes");
      row.put("ngayTao", DateUtil.format(lst.get(i).getCreatedAt(), "dd/MM/yyyy"));
      rows.add(row);
    }

    return reportService
        .getReport(report, "reports/templates/Template_DS_Loai_thiet_bi.xlsx", "reports/output");
  }

  @Override
  public List<DmLoaiThietBiDTO> getListLoaiThietBi(Long id) {
    List<DmLoaiThietBiEntity> getAll = getRepository().findByDmThietBiId(id);
    List<DmLoaiThietBiDTO> list = new ArrayList<>();
    getAll.forEach(e->list.add(mapToDTO(e)));
    return list;
  }

  @Override
  public Optional<DmLoaiThietBiEntity> findByDmVatTuYTeId(Long dmVatTuYTeId) {
    return repository.findByDmVatTuYTeId(dmVatTuYTeId);
  }

  @Override
  public Long getIdByTenAndLoai(String ten, DmLoaiThietBiEnum loai) {
    return getRepository().getIdByTenAndLoai(ten, loai);
  }

  private void validateTen(DmLoaiThietBiEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new DmLoaiThietBiException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if(repository.existsByMa(model.getMa(), model.getId(), model.getLoai())){
      throw new DmLoaiThietBiException.DuplicateCode();
    }
  }

  private void validateFields(DmLoaiThietBiEntity model) {
    if (model.getDmThietBiId() == null) {
      throw new DmLoaiThietBiException.MissingDmThietBi();
    }
  }

  private void generateMa(DmLoaiThietBiEntity model) {
    if (!StringUtils.isBlank(model.getMa()) || DmLoaiThietBiEnum.LOAI_VTYT_I.equals(model.getLoai())) {
      return;
    }

    String currentMa = repository.getCurrentMa(PREFIX);
    int current = 0;

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    model.setMa(PREFIX + (current < 9? "0" : "") + (current + 1));
  }
}
