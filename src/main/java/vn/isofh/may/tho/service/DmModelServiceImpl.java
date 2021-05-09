package vn.isofh.may.tho.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import vn.isofh.may.tho.dao.model.DmModelEntity;
import vn.isofh.may.tho.dao.model.DmQuocGiaEntity;
import vn.isofh.may.tho.dao.repository.DmModelRepository;
import vn.isofh.may.tho.dto.DmModelDTO;
import vn.isofh.may.tho.exception.DmModelException;

@Service
public class DmModelServiceImpl extends
    AbstractDmService<DmModelEntity, DmModelDTO, DmModelRepository> implements DmModelService {

  @Autowired
  private DmModelRepository repository;

  @Autowired
  private DmThietBiService dmThietBiService;

  @Autowired
  private DmLoaiThietBiService dmLoaiThietBiService;

  @Autowired
  private DmQuocGiaService dmQuocGiaService;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private ReportService reportService;

  @Autowired
  private DmNhomThietBiService dmNhomThietBiService;

  @Override
  protected DmModelRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<DmModelDTO, DmModelEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmHangSanXuat());
        skip(destination.getHangSoHuu());
        skip(destination.getDmLoaiThietBi());
        skip(destination.getDmThietBi());
        skip(destination.getNhaCungCap());
        skip(destination.getNuocSoHuu());
      }
    });
  }

  @Override
  protected void specificMapToEntity(DmModelDTO dto, DmModelEntity entity) {
    super.specificMapToEntity(dto, entity);

    if (dto.getNuocSanXuatIds() == null || dto.getNuocSanXuatIds().isEmpty()) {
      entity.setNuocSanXuat(new ArrayList<>());
    } else {
      entity.setNuocSanXuat(dto.getNuocSanXuatIds().stream()
          .map(id -> {
            DmQuocGiaEntity q = new DmQuocGiaEntity();
            q.setId(id);
            return q;
          })
          .collect(Collectors.toList()));
    }
  }

  @Override
  protected void specificMapToDTO(DmModelEntity entity, DmModelDTO dto) {
    super.specificMapToDTO(entity, dto);
    dto.setDmThietBi(dmThietBiService.getBaseDTOById(entity.getDmThietBiId()));
    dto.setDmLoaiThietBi(dmLoaiThietBiService.getBaseDTOById(entity.getDmLoaiThietBiId()));
    dto.setDmHangSanXuat(dmDonViService.getBaseDTOById(entity.getDmHangSanXuatId()));
    dto.setHangSoHuu(dmDonViService.getBaseDTOById(entity.getHangSoHuuId()));
    dto.setNuocSoHuu(dmQuocGiaService.getBaseDTOById(entity.getNuocSoHuuId()));
    dto.setNhaCungCap(dmDonViService.getBaseDTOById(entity.getNhaCungCapId()));
    dto.setDmNhomTbyt(dmNhomThietBiService.getBaseDTOById(entity.getDmNhomTbytId()));

    dto.setNuocSanXuat(entity.getNuocSanXuat()
        .stream()
        .map(e -> dmQuocGiaService.getBaseDTOById(e.getId())).collect(Collectors.toList()));
    dto.setNuocSanXuatIds(entity.getNuocSanXuat()
        .stream()
        .map(DmQuocGiaEntity::getId).collect(Collectors.toList()));
  }

  @Override
  protected DmModelEntity beforeSave(DmModelEntity model, DmModelDTO dto) {

    validateTen(model);

    generateMa(model);

    return super.beforeSave(model, dto);
  }

  @Override
  public ReportDTO exportModel(DmModelDTO dto, Pageable pageable) {
    List<DmModelDTO> lst = search(dto, pageable).getContent();
    Map<String, Object> report = new HashMap<>();
    List<Map<String, Object>> rows = new ArrayList<>();

    report.put("ngay", DateUtil.format(LocalDate.now(), "dd/MM/yyyy"));
    report.put("tongSo", lst.size());
    report.put("rows", rows);

    for (int i = 0; i < lst.size(); i++) {
      Map<String, Object> row = new HashMap<>();
      List<String> tens = new ArrayList<>();
      List<Long> nuocSanXuatId = lst.get(i).getNuocSanXuatIds();

      row.put("stt", i + 1);
      row.put("maModel", lst.get(i).getMa());
      row.put("tenThietBi",
          lst.get(i).getDmThietBiId() == null ? "" : dmThietBiService.getBaseDTOById(lst.get(i).getDmThietBiId()).getTen());
      row.put("loaiThietBi",
          lst.get(i).getDmLoaiThietBi() == null ? "" : dmLoaiThietBiService.getBaseDTOById(lst.get(i).getDmLoaiThietBiId()).getTen());
      row.put("hangSanXuat",
          lst.get(i).getDmHangSanXuat() == null ? "" : dmDonViService.getBaseDTOById(lst.get(i).getDmHangSanXuatId()).getTen());
      row.put("hangChuSoHuu",
          lst.get(i).getHangSoHuu() == null ? "" : dmDonViService.getBaseDTOById(lst.get(i).getHangSoHuuId()).getTen());

      nuocSanXuatId.forEach(e-> tens.add(dmQuocGiaService.getBaseDTOById(e).getTen()));
      row.put("nuocSanXuat", tens);
      row.put("nhaThau",
          lst.get(i).getNhaCungCap() == null ? "" : dmQuocGiaService.getBaseDTOById(lst.get(i).getNhaCungCapId()).getTen());
      rows.add(row);
    }

    return reportService
        .getReport(report, "reports/templates/Template_DS_Model.xlsx", "reports/output");
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmThietBiId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmThietBiService.getIdByMa(value);
    } else if ("dmLoaiThietBiId".equals(header.getColumnName())
        && "ma".equals(header.getLinkColumnName())) {
      return dmLoaiThietBiService.getIdByMa(value);
    } else if ("dmHangSanXuatId".equals(header.getColumnName())
        && "ma".equals(header.getLinkColumnName())) {
      return dmDonViService.getIdByMa(value);
    } else if ("nuocSanXuatId".equals(header.getColumnName())
        && "ma".equals(header.getLinkColumnName())) {
      return dmQuocGiaService.getIdByMa(value);
    } else if ("nhaCungCapId".equals(header.getColumnName())
        && "ma".equals(header.getLinkColumnName())) {
      return dmDonViService.getIdByMa(value);
    }

    return null;
  }

  private void validateTen(DmModelEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new DmModelException.MissingName();
    }

    model.setTen(model.getTen().trim());

    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new DmModelException.DuplicateName(new Object[]{model.getTen()});
    }
  }

  private void generateMa(DmModelEntity model) {
    if (!StringUtils.isBlank(model.getMa())) {
      return;
    }

    String currentMa = repository.getCurrentMa();
    int current = 0;

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    model.setMa((current < 9 ? "0" : "") + (current + 1));
  }
}
