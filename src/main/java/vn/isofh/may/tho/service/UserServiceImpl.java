package vn.isofh.may.tho.service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.isofh.common.config.security.JwtTokenProvider;
import vn.isofh.common.config.security.JwtTokenProvider.JwtTokenProperties;
import vn.isofh.common.msg.Msg;
import vn.isofh.common.report.ReportDTO;
import vn.isofh.common.report.ReportService;
import vn.isofh.common.util.DateUtil;
import vn.isofh.common.util.Header;
import vn.isofh.may.tho.config.UserPrincipal;
import vn.isofh.may.tho.dao.model.RoleEntity;
import vn.isofh.may.tho.dao.model.UserEntity;
import vn.isofh.may.tho.dao.repository.UserRepository;
import vn.isofh.may.tho.dto.DangKyTaiKhoanDTO;
import vn.isofh.may.tho.dto.LoginDTO;
import vn.isofh.may.tho.dto.UserDTO;
import vn.isofh.may.tho.enums.NhomSanPhamEnum;
import vn.isofh.may.tho.enums.TrangThaiTaiKhoanEnum;
import vn.isofh.may.tho.exception.UserException;
import vn.isofh.may.tho.exception.UserException.UserHasLocked;

@Service
public class UserServiceImpl extends
    AbstractService<UserEntity, UserDTO, UserRepository> implements UserService {

  public final static String ROLE_TTBYT = "nhom_ttbyt";
  public final static String ROLE_VTYT = "nhom_vtyt";
  public final static String ROLE_INVITRO = "nhom_invitro";

  public final static String DON_VI = "donViId";
  public final static String ID = "id";

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private DmDonViService dmDonViService;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private ReportService reportService;

  @Override
  protected UserRepository getRepository() {
    return repository;
  }

  @Override
  protected void configModelMapper(ModelMapper modelMapper) {
    super.configModelMapper(modelMapper);

    modelMapper.addMappings(new PropertyMap<UserDTO, UserEntity>() {
      @Override
      protected void configure() {
        skip(destination.getDmDonVi());
        skip(destination.getPassword());
      }
    });
  }

  @Override
  protected void specificMapToDTO(UserEntity entity, UserDTO dto) {
    super.specificMapToDTO(entity, dto);

    if (entity.getRoles() != null && entity.getRoles().size() > 0) {
      dto.setRoleId(entity.getRoles().get(0).getId());
      dto.setRole(roleService.findById(dto.getRoleId()));
    }

    if (entity.getRoles() != null) {
      dto.setRoles(entity.getRoles().stream().map(e -> roleService.getBaseDTOById(e.getId()))
          .collect(Collectors.toList()));

      dto.setRoleIds(entity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toList()));
    }

    dto.setNguoiSua(repository.findById(entity.getUpdatedBy()).orElse(new UserEntity()).getFullName());
    dto.setNguoiTao(repository.findById(entity.getCreatedBy()).orElse(new UserEntity()).getFullName());

  }

  @Override
  public UserEntity beforeSave(UserEntity user, UserDTO dto) {
    validateUsername(dto);

    if (user.isNewRecord() && dto.getPassword() != null) {
      user.setPassword(dto.getPassword());
    }
    setDefaultPassword(user);

    roleService.addRole(user, dto.getRoleId());
    roleService.addRoles(user, dto.getRoleIds());

    return user;
  }

  @Override
  public Map<String, Object> validateLogin(LoginDTO loginDTO) {
    String username = loginDTO.getUsername();
    String password = loginDTO.getPassword();

    if (StringUtils.isBlank(username)) {
      throw new UserException.InvalidUsername(Msg.getMessage("user.invalid.username"));
    }

    if (StringUtils.isBlank(password)) {
      throw new UserException.InvalidPassword(Msg.getMessage("user.invalid.password"));
    }

    UserEntity user = repository.findByUsername(username).orElseThrow(
        () -> new UserException.NotExistsByUsername(
            Msg.getMessage("user.not.exists.username", new Object[]{username})));

    if (!user.getPassword().equals(getHashPassword(loginDTO.getPassword(), user.getSalt()))) {
      throw new UserException.InvalidPassword(Msg.getMessage("user.wrong.password"));
    }

    if(TrangThaiTaiKhoanEnum.DANG_KHOA.equals(user.getTrangThai()))
    {
      throw new UserHasLocked(Msg.getMessage("user.has.locked"));
    }

    List<String> roles = new ArrayList<>();

    if (user.getRoles() != null) {
      for (RoleEntity role : user.getRoles()) {
        roles.add("ROLE_" + role.getMa());
      }
    }

    Map<String, Object> jwtAdditionalInformation = new HashMap<>();

    if (user.getDmDonVi() != null) {
      jwtAdditionalInformation.put("donViId", user.getDmDonViId());
      jwtAdditionalInformation.put("donViTen", user.getDmDonVi().getTen());
    }

    JwtTokenProperties jwtTokenProperties = JwtTokenProperties.builder()
        .id(user.getId())
        .username(user.getUsername())
        .fullName(user.getUsername())
        .email(user.getEmail())
        .privileges(roles)
        .jwtAdditionalInformation(jwtAdditionalInformation)
        .build();

    return tokenProvider.generateToken(jwtTokenProperties);
  }

  @Override
  public UserDTO changePassword(Long id, UserDTO userDto) {
    UserEntity entity = getById(id);

    if (StringUtils.isBlank(userDto.getPassword())) {
      throw new UserException.InvalidPassword(Msg.getMessage("user.invalid.password"));
    }

    if (StringUtils.isBlank(userDto.getNewPassword())) {
      throw new UserException.InvalidPassword(Msg.getMessage("user.invalid.new.password"));
    }

    if (!entity.getPassword().equals(getHashPassword(userDto.getPassword(), entity.getSalt()))) {
      throw new UserException.InvalidPassword(Msg.getMessage("user.wrong.password"));
    }

    String newPassword = userDto.getNewPassword();

    entity.setPassword(getHashPassword(newPassword, entity.getSalt()));
    repository.save(entity);

    return mapToDTO(entity);
  }

  @Override
  public String taoMoiTaiKhoan(DangKyTaiKhoanDTO dangKyTaiKhoanDTO,Long dmDonViId) {
    UserDTO dto = new UserDTO();
    String pass = generateRandomPassword(8);
    dto.setUsername(dangKyTaiKhoanDTO.getMaSoThue());
    dto.setDiaChi(dangKyTaiKhoanDTO.getDiaChiChiTiet());
    dto.setEmail(dangKyTaiKhoanDTO.getEmail());
    dto.setPassword(pass);
    dto.setDmDonViId(dmDonViId);
    dto.setFullName(dangKyTaiKhoanDTO.getTenDoanhNghiep());
    dto.setTrangThai(TrangThaiTaiKhoanEnum.DANG_HOAT_DONG);

    List<Long> longId = new ArrayList<>();
    for(NhomSanPhamEnum nhomSanPhamEnum : dangKyTaiKhoanDTO.getNhomSanPham()){
      if(NhomSanPhamEnum.TRANG_THIET_BI_YT.equals(nhomSanPhamEnum)) {
        longId.add(roleService.getIdByMa(ROLE_TTBYT));
      } else if(NhomSanPhamEnum.VAT_TU_TIEU_HAO.equals(nhomSanPhamEnum)) {
        longId.add(roleService.getIdByMa(ROLE_VTYT));
      } else if(NhomSanPhamEnum.IVD.equals(nhomSanPhamEnum)) {
        longId.add(roleService.getIdByMa(ROLE_INVITRO));
      }
    }
    dto.setRoleIds(longId);
    save(dto);
    return pass;
  }


  public UserDTO resetPassword(Long id) {
    UserEntity entity = getById(id);

    entity.setPassword(getHashPassword("123456", entity.getSalt()));

    repository.save(entity);

    return mapToDTO(entity);
  }

  @Override
  public UserDTO lock(Long id, TrangThaiTaiKhoanEnum trangThai) {
    UserEntity entity = getById(id);

    if (trangThai.equals(entity.getTrangThai())) {
      if (TrangThaiTaiKhoanEnum.DANG_HOAT_DONG.equals(trangThai)) {
        throw new UserHasLocked(Msg.getMessage("user.has.unlocked"));
      } else if (TrangThaiTaiKhoanEnum.DANG_KHOA.equals(trangThai)) {
        throw new UserHasLocked(Msg.getMessage("user.has.locked"));
      }
    }

    entity.setTrangThai(trangThai);

    repository.save(entity);

    return mapToDTO(entity);
  }

  @Override
  public Boolean checkRoleUser(String roleString) {
    UserPrincipal user = getUserPrincipal();
    if(user!=null)
    {
      for(String role:user.getPrivileges()){
        if (("ROLE_"+roleString).equals(role)){
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public UserDTO xacNhanDeXuatNhomTttbyt(Long id) {
    UserDTO dto = findById(id);
    List<Long> roles = dto.getRoleIds();
    roles.addAll(dto.getDeXuatNhomTtbyt());
    dto.setRoleIds(roles);
    return save(dto);
  }

  @Override
  public ReportDTO exportUser(UserDTO dto, Pageable pageable) {
    List<UserDTO> lst = search(dto, pageable).getContent();
    Map<String, Object> report = new HashMap<>();
    List<Map<String, Object>> rows = new ArrayList<>();

    report.put("ngay", DateUtil.format(LocalDate.now(), "dd/MM/yyyy"));
    report.put("tongSo", lst.size());

    for (int i = 0; i < lst.size(); i++) {
      Map<String, Object> row = new HashMap<>();

      String trangThai = lst.get(i).getTrangThai() == null
          ? null : lst.get(i).getTrangThai().getValue() == 0
          ? "Đang hoạt động" : lst.get(i).getTrangThai().getValue() == 10
          ? "Đang khóa" : "Mới đăng ký";

      StringBuilder stringBuilder = new StringBuilder(20);
      lst.get(i).getRoleIds().forEach(e -> {
        stringBuilder.append(roleService.findById(e).getTen()).append("; ");
      });

      String createDate = DateUtil.format(lst.get(i).getCreatedAt(), "yyyy-MM-dd HH:mm:ss");
      String updateDate  = DateUtil.format(lst.get(i).getUpdatedAt(), "yyyy-MM-dd HH:mm:ss");

    row.put("stt", i + 1);
      row.put("donVi",dmDonViService.findById(lst.get(i).getDmDonViId()).getTen());
      row.put("tenDangNhap", lst.get(i).getUsername());
      row.put("loaiTaiKhoan", stringBuilder);
      row.put("email",  lst.get(i).getEmail());
      row.put("trangThai", trangThai);
      row.put("nguoiTao",  findById(lst.get(i).getCreatedBy()).getFullName());
      row.put("ngayTao",  createDate);
      row.put("nguoiSua", findById(lst.get(i).getUpdatedBy()).getFullName());
      row.put("ngaySua", updateDate);
      rows.add(row);

    }

    report.put("rows", rows);
    return reportService
        .getReport(report, "templates/DanhSachTaiKhoan.xlsx", "bao-cao");
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmDonViId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmDonViService.getIdByMa(value);
    }

    return null;
  }

  private void validateUsername(UserDTO dto) {
    if (StringUtils.isBlank(dto.getUsername())) {
      throw new UserException.MissingUsername();
    }

    if (repository.existsByUsername(dto.getUsername(), dto.getId())) {
      throw new UserException.DuplicateUsername(Msg.getMessage("user.exist.user.name"));
    }
  }

  private void setDefaultPassword(UserEntity user) {
    if (!user.isNewRecord()) {
      return;
    }

    user.setSalt(UUID.randomUUID().toString());

    if (StringUtils.isBlank(user.getPassword())) {
      user.setPassword(getHashPassword("123456", user.getSalt()));
    } else {
      user.setPassword(getHashPassword(user.getPassword(), user.getSalt()));
    }
  }

  private String getHashPassword(String password, String salt) {
    if (salt == null) {
      salt = "";
    }

    return DigestUtils.sha256Hex(password + salt);
  }

  public static String generateRandomPassword(int len)
  {
    // ASCII range - alphanumeric (0-9, a-z, A-Z)
    final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder();

    // each iteration of loop choose a character randomly from the given ASCII range
    // and append it to StringBuilder instance

    for (int i = 0; i < len; i++) {
      int randomIndex = random.nextInt(chars.length());
      sb.append(chars.charAt(randomIndex));
    }

    return sb.toString();
  }



}
