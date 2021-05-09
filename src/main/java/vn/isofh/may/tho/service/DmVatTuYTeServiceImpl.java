package vn.isofh.may.tho.service;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmVatTuYTeEntity;
import vn.isofh.may.tho.dao.repository.DmVatTuYTeRepository;
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.dto.DmVatTuYTeDTO;
import vn.isofh.may.tho.exception.DmTenVatTuYTeException;


@Service
public class DmVatTuYTeServiceImpl extends
    AbstractDmService<DmVatTuYTeEntity, DmVatTuYTeDTO, DmVatTuYTeRepository> implements DmVatTuYTeService {

  public final static String DM_VTYT_MA_PREFIX = "VTYT";

  @Autowired
  private DmVatTuYTeRepository repository;

  @Autowired
  private DmLoaiThietBiService dmLoaiThietBiService;

  @Autowired
  private DmThietBiService dmThietBiService;

  @Override
  protected DmVatTuYTeRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<DmVatTuYTeDTO, DmVatTuYTeEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmLoaiThietBi());
      }
    });
  }

  @Override
  protected void specificMapToDTO(DmVatTuYTeEntity entity, DmVatTuYTeDTO dto) {
    DmLoaiThietBiDTO dmLoaiThietBi = dmLoaiThietBiService.findById(entity.getDmLoaiThietBiId());
    dto.setDmLoaiThietBi(dmLoaiThietBi);
    dto.setDmThietBi(dmThietBiService.getBaseDTOById(dmLoaiThietBi.getDmThietBiId()));

    super.specificMapToDTO(entity, dto);
  }

  @Override
  public boolean existsByDmLoaiThietBiId(Long dmLoaiThietBiId) {
    return repository.existsByDmLoaiThietBiId(dmLoaiThietBiId,null);
  }

  @Override
  public DmVatTuYTeEntity beforeSave(DmVatTuYTeEntity entity, DmVatTuYTeDTO dto) {
    validateEntity(dto);

    if (entity.getMa() == null) {
      generateMa(entity);
    }

    return entity;
  }

  private void validateEntity(DmVatTuYTeDTO dto) {
    if (StringUtils.isBlank(dto.getTen())) {
      throw new DmTenVatTuYTeException.MissingName();
    }

    if( Objects.isNull(dto.getDmLoaiThietBiId())){
      throw new DmTenVatTuYTeException.MissingDmLoaiThietBiId();
    }


    if (repository.existsByTen(dto.getTen(), dto.getId())) {
      throw new DmTenVatTuYTeException.DuplicateName();
    }

    if (repository.existsByMaAAndDmLoaiThietBiId(dto.getMa(),dto.getDmLoaiThietBiId(), dto.getId())) {
      throw new DmTenVatTuYTeException.DuplicateCode();
    }

  }

  private void generateMa(DmVatTuYTeEntity entity) {
    int currentMaInt = 1;
    String currentMaStr = repository.getMaLonNhat(DM_VTYT_MA_PREFIX);
    if (currentMaStr != null) {
      currentMaInt = Integer.parseInt(currentMaStr.substring(DM_VTYT_MA_PREFIX.length() + 1))+1;
    }

    entity.setMa(DM_VTYT_MA_PREFIX + String.format("%05d", currentMaInt));
  }
}
