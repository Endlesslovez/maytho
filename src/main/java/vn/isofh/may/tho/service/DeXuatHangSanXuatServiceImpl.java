package vn.isofh.may.tho.service;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import vn.isofh.common.email.EmailService;
import vn.isofh.may.tho.dao.model.DeXuatHangSanXuatEntity;
import vn.isofh.may.tho.dao.repository.DeXuatHangSanXuatRepository;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO;
import vn.isofh.may.tho.dto.DeXuatHangSanXuatDTO.XacNhanHangSanXuat;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.dto.UserDTO;
import vn.isofh.may.tho.enums.LoaiDonViEnum;
import vn.isofh.may.tho.exception.DeXuatHangSanXuatException;


@Service
public class DeXuatHangSanXuatServiceImpl extends
    AbstractService<DeXuatHangSanXuatEntity, DeXuatHangSanXuatDTO, DeXuatHangSanXuatRepository> implements
    DeXuatHangSanXuatService {

  @Autowired
  private DeXuatHangSanXuatRepository repository;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private UserService userService;

  @Autowired
  private EmailService emailService;

  private static final String roleBYT = "bo_y_te";

  @Override
  protected DeXuatHangSanXuatRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<DeXuatHangSanXuatDTO, DeXuatHangSanXuatEntity>() {
      @Override
      protected void configure() {

      }
    });
  }

  @Override
  protected void specificMapToDTO(DeXuatHangSanXuatEntity entity, DeXuatHangSanXuatDTO dto) {
    super.specificMapToDTO(entity, dto);
    dto.setDmDonVi(dmDonViService.getBaseDTOById(entity.getDmDonViId()));
    dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
    dto.setNguoiSua(userService.findById(entity.getUpdatedBy()).getFullName());
  }

  @Override
  protected DeXuatHangSanXuatEntity beforeSave(DeXuatHangSanXuatEntity entity,
      DeXuatHangSanXuatDTO dto) {
    validateTen(entity);
    if (entity.isNewRecord()) {
      entity.setActive(false);
      entity.setDmDonViId(getDonViId());

    }

    return super.beforeSave(entity, dto);
  }

  @Value("${email.xac.nhan.hang.sx.title}")
  private String emailXacNhanTitle;

  @Value("${email.xac.nhan.hang.sx.content}")
  private String emailXacNhanContent;

  @Value("${email.huy.xac.nhan.hang.sx.title}")
  private String emailHuyXacNhanTitle;

  @Value("${email.huy.xac.nhan.hang.sx.content}")
  private String emailHuyXacNhanContent;

  @Value("${email.send.to}")
  private String cc;

  @Override
  @PreAuthorize("hasRole('bo_y_te')")
  public void xacNhan(XacNhanHangSanXuat xacNhanHangSanXuat) {
    DeXuatHangSanXuatDTO dto = findById(xacNhanHangSanXuat.getId());
    validateDto(dto);
    dto.setActive(true);
    save(dto);

    UserDTO userDTO = userService.findById(dto.getCreatedBy());
    DmDonViDTO donViDTO = new DmDonViDTO();
    donViDTO.setLoaiDonVi(LoaiDonViEnum.HANG_SAN_XUAT);
    donViDTO.setTen(dto.getTenHangSanXuat());
    donViDTO.setMa(xacNhanHangSanXuat.getMaHangSanXuat());
    donViDTO.setNccUyQuyenIds(new ArrayList<>(Arrays.asList(userDTO.getDmDonViId())));
    dmDonViService.save(donViDTO);

    emailService.sendMessage(userDTO.getEmail(), emailXacNhanTitle,
        emailXacNhanContent.replace("$hangSanXuatTen$", dto.getTenHangSanXuat()),false);

  }

  @Override
  @PreAuthorize("hasRole('bo_y_te')")
  public void huyXacNhan(XacNhanHangSanXuat xacNhanHangSanXuat) {
    validateLyDo(xacNhanHangSanXuat);
    DeXuatHangSanXuatDTO dto = findById(xacNhanHangSanXuat.getId());
    dto.setLyDoHuyXacNhan(xacNhanHangSanXuat.getLyDoHuyXacNhan());
    save(dto);
    String content = emailHuyXacNhanContent.replace("$hangSanXuatTen$", dto.getTenHangSanXuat())
        + xacNhanHangSanXuat.getLyDoHuyXacNhan();
    UserDTO userDTO = userService.findById(dto.getCreatedBy());
    emailService.sendMessage(userDTO.getEmail(), emailHuyXacNhanTitle,
        content,false);
  }

  @Override
  protected DeXuatHangSanXuatDTO beforeSearch(DeXuatHangSanXuatDTO dto) {
    if (!userService.checkRoleUser(roleBYT)) {
      dto.setDmDonViId(getDonViId());
    }
    return super.beforeSearch(dto);
  }

  public void validateTen(DeXuatHangSanXuatEntity entity) {
    if (StringUtils.isBlank(entity.getTenHangSanXuat())) {
      throw new DeXuatHangSanXuatException.MissingName();
    }
  }

  public void validateLyDo(XacNhanHangSanXuat xacNhanHangSanXuat) {
    if (StringUtils.isBlank(xacNhanHangSanXuat.getLyDoHuyXacNhan())) {
      throw new DeXuatHangSanXuatException.MissingReason();
    }
  }

  public void validateDto(DeXuatHangSanXuatDTO dto) {
    if (dto.getActive()) {
      throw new DeXuatHangSanXuatException.InvalidDto();
    }
  }
}
