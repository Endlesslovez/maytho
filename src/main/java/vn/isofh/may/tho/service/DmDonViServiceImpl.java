package vn.isofh.may.tho.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.common.exception.StorageException;
import vn.isofh.common.storage.StorageService;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.Header;
import vn.isofh.common.util.ObjectMapperUtil;
import vn.isofh.common.util.StringUtil;
import vn.isofh.may.tho.dao.model.DmDonViEntity;
import vn.isofh.may.tho.dao.repository.DmDonViRepository;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.enums.LoaiCongTyEnum;
import vn.isofh.may.tho.enums.LoaiCsytEnum;
import vn.isofh.may.tho.enums.LoaiDonViEnum;
import vn.isofh.may.tho.exception.DmDonViException;

@Service
public class DmDonViServiceImpl extends
    AbstractDmService<DmDonViEntity, DmDonViDTO, DmDonViRepository> implements DmDonViService {

  @Autowired
  private DmDonViRepository repository;

  @Autowired
  private DmTinhThanhPhoService dmTinhThanhPhoService;

  @Autowired
  private DmQuanHuyenService dmQuanHuyenService;

  @Autowired
  private DmXaPhuongService dmXaPhuongService;

  @Autowired
  private StorageService storageService;

  @Override
  protected DmDonViRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<DmDonViDTO, DmDonViEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmTinhThanhPho());
        skip(destination.getDmQuanHuyen());
        skip(destination.getDmXaPhuong());
      }
    });
  }

  @Override
  protected void specificMapToEntity(DmDonViDTO dto, DmDonViEntity entity) {
    super.specificMapToEntity(dto, entity);

    if (dto.getNccUyQuyenIds() == null || dto.getNccUyQuyenIds().isEmpty()) {
      entity.setNccUyQuyen(new ArrayList<>());
    } else {
      entity.setNccUyQuyen(dto.getNccUyQuyenIds().stream()
          .map(id -> {
            DmDonViEntity q = new DmDonViEntity();
            q.setId(id);
            return q;
          })
          .collect(Collectors.toList()));
    }
  }

  @Override
  protected void specificMapToDTO(DmDonViEntity entity, DmDonViDTO dto) {
    super.specificMapToDTO(entity, dto);

    dto.setDmTinhThanhPho(dmTinhThanhPhoService.getBaseDTOById(entity.getDmTinhThanhPhoId()));

    if (entity.getNccUyQuyen() != null && entity.getNccUyQuyen().size() > 0) {
      dto.setNccUyQuyen(entity.getNccUyQuyen()
          .stream().map(e -> repository.findBaseDTOById(e.getId()))
          .collect(Collectors.toList()));
    }

    if (entity.getHangSanXuatUyQuyen() != null && entity.getHangSanXuatUyQuyen().size() > 0) {
      dto.setHangSanXuatUyQuyen(entity.getHangSanXuatUyQuyen()
          .stream().map(e -> repository.findBaseDTOById(e.getId()))
          .collect(Collectors.toList()));
    }
  }

  @Override
  protected DmDonViEntity beforeSave(DmDonViEntity model, DmDonViDTO dto) {
    validateFileds(model);

    validateTen(model);

    generateMa(model, dto);

    validateMa(model);

    return super.beforeSave(model, dto);
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmTinhThanhPhoId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmTinhThanhPhoService.getIdByMa(value);
    }

    if ("dmQuanHuyenId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmQuanHuyenService.getIdByMa(value);
    }

    if ("dmXaPhuongId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmXaPhuongService.getIdByMa(value);
    }

    if ("coQuanQuanLyIds".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return Arrays.stream(StringUtil.toArray(value)).map(ma -> getIdByMa(ma)).toArray();
    }
    return null;
  }

  @Override
  protected File export(DmDonViDTO dto, List<DmDonViDTO> dtos) {
    String template = LoaiDonViEnum.CO_SO_Y_TE.equals(dto.getLoaiDonVi()) ? "Template_DS_CSYT.xls"
        : "Template_DS_Cong_Ty.xls";

    FileInputStream inputStream = null;
    HSSFWorkbook workbook = null;
    FileOutputStream outputStream = null;
    try {
      inputStream = new FileInputStream(storageService.load(template, "template").getAbsolutePath().toFile());
      workbook = new HSSFWorkbook(inputStream);

      HSSFSheet sheet = workbook.getSheetAt(0);

      int rowNo = sheet.getPhysicalNumberOfRows();
      int columnNo = sheet.getRow(1).getPhysicalNumberOfCells();

      Map<String, Cell> map = new HashMap<>();
      int startRowNo = 0;

      for (int i = 0; i < rowNo; i++) {
        Row row = sheet.getRow(i);
        for (int j = 0; j < columnNo; j++) {
          Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
          CellType cellType = cell.getCellType();
          if (CellType.STRING.equals(cellType)) {
            String value = cell.getStringCellValue().trim();

            value = value.replace("${ngay}", DateUtil.format(LocalDate.now(), "dd/MM/yyyy"));
            value = value.replace("${tongSoCoSo}", dtos.size() + "");

            cell.setCellValue(value);

            if (value.startsWith("${")) {
              map.put(value, cell);
              startRowNo = cell.getAddress().getRow();
            }
          }
        }
      }

      int size = dtos.size();
      for (int i = 1; i < size; i++) {
        fillData(sheet, map, dtos.get(i), startRowNo, i);
      }

      fillData(sheet, map, dtos.get(0), startRowNo, 0);

      File output = storageService.load("DS_Don_Vi_" + DateUtil.format(LocalDateTime.now(), "ddMMyyyyHHmmss")
          + ".xlsx", "export", true).getAbsolutePath().toFile();

      outputStream = new FileOutputStream(output);
      workbook.write(outputStream);

      return output;

    } catch (IOException e) {
      throw new StorageException.FailedToStoreFile(null, e);
    } finally {
      try {
        if (workbook != null) {
          workbook.close();
        }

        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void fillData(HSSFSheet sheet, Map<String, Cell> map, DmDonViDTO dto, int startRowNo, int i) {
    Map<String, Object> m = ObjectMapperUtil.convertValue(dto);
    Row row = sheet.createRow(startRowNo + i);

    for (String key : map.keySet()) {
      Cell templateCell = map.get(key);

      Cell cell = row.getCell(templateCell.getAddress().getColumn(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
      cell.setCellStyle(templateCell.getCellStyle());

      Object value = m.get(key.substring(2, key.length() - 1));
      if (value instanceof String) {
        cell.setCellValue((String) value);
      } else if (value instanceof Map) {
        cell.setCellValue((String) ((Map<String, Object>) value).get("ten"));
      } else if ("${soStt}".equals(key)) {
        cell.setCellValue(String.valueOf(i + 1));
      } else if ("${loaiCsyt}".equals(key)) {
        cell.setCellValue(LoaiCsytEnum.BENH_VIEN.getValue().equals(value) ? "Bệnh viện" :
            (LoaiCsytEnum.PHONG_KHAM.getValue().equals(value) ? "Phòng khám" :
                (LoaiCsytEnum.TRUNG_TAM_Y_TE.getValue().equals(value) ? "Trung tâm Y tế" : "")));
      } else if ("${loaiCongTy}".equals(key)) {
        cell.setCellValue(LoaiCongTyEnum.NHAP_KHAU.getValue().equals(value) ? "Nhập khẩu" :
            (LoaiCongTyEnum.PHAN_PHOI.getValue().equals(value) ? "Phân phối" :
                (LoaiCongTyEnum.SAN_XUAT.getValue().equals(value) ? "Sản xuất" : "")));
      } else if ("${coQuanQuanLyIds}".equals(key)) {
        if (value != null) {
          cell.setCellValue(Arrays.stream(dto.getCoQuanQuanLyIds()).map(id -> getById(id).getTen())
              .reduce((a, b) -> a.concat(",").concat(b)).get());
        }
      }
    }
  }

  private void validateFileds(DmDonViEntity model) {

    if (model.getLoaiDonVi() == null) {
      throw new DmDonViException.MissingLoaiDonVi();
    }
  }

  private void validateTen(DmDonViEntity model) {
    if (StringUtils.isBlank(model.getTen())) {
      throw new DmDonViException.MissingName();
    }

    model.setTen(model.getTen().trim());

/*    if (repository.existsByTen(model.getTen(), model.getId())) {
      throw new DonViException.DuplicateName(new Object[]{model.getTen()});
    }*/
  }

  private final String PREFIX_CONG_TY_TTTB = "CTTTB";
  private final String PREFIX_CONG_TY_CQQL = "CQQL";
  private final String PREFIX_HANG_SAN_XUAT = "HSX";

  private void generateMa(DmDonViEntity model, DmDonViDTO dto) {

    if(dto.getMa() != null){
      model.setMa(dto.getMa());
      return;
    }

    if (!StringUtils.isBlank(model.getMa())) {
      return;
    }

    String prefix;
    if (LoaiDonViEnum.CONG_TY_TTB.equals(model.getLoaiDonVi())) {
      prefix = PREFIX_CONG_TY_TTTB;
    } else if (LoaiDonViEnum.CO_QUAN_QUAN_LY.equals(model.getLoaiDonVi())) {
      prefix = PREFIX_CONG_TY_CQQL;
    } else if (LoaiDonViEnum.HANG_SAN_XUAT.equals(model.getLoaiDonVi())) {
      prefix = PREFIX_HANG_SAN_XUAT;
    } else {
      return;
    }

    String currentMa = repository.getCurrentMa(prefix);
    Integer current = 0;

    if (currentMa != null) {
      current = Integer.parseInt(currentMa);
    }

    model.setMa(prefix + (current < 9 ? "0" : "") + (current + 1));
  }

  private void validateMa(DmDonViEntity model) {

    if (StringUtils.isBlank(model.getMa())) {
      throw new DmDonViException.MissingMa();
    }

    if (StringUtils.isBlank(model.getMaSoThue()) && model.getLoaiDonVi().equals( LoaiDonViEnum.CONG_TY_TTB)) {
      throw new DmDonViException.MissingMaSoThue();
    }

    model.setMa(model.getMa() == null ? null : model.getMa().trim());
    model.setMaSoThue(model.getMaSoThue() == null ? null : model.getMaSoThue().trim());

    if (repository.existsByMa(model.getMa(), model.getId())) {
      throw new DmDonViException.DuplicateMa(new Object[]{model.getMa()});
    }

    if (repository.existsByMaSoThue(model.getMaSoThue(), model.getId(), LoaiDonViEnum.CONG_TY_TTB)) {
      throw new DmDonViException.DuplicateMaSoThue(new Object[]{model.getMaSoThue()});
    }
  }
}
