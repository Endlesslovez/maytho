package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import vn.isofh.common.service.AbstractBaseService;
import vn.isofh.may.tho.dao.model.DmHuongDanSuDungEntity;
import vn.isofh.may.tho.dao.repository.DmHuongDanSuDungRepository;
import vn.isofh.may.tho.dto.DmHuongDanSuDungDTO;

@Service
public class DmHuongDanSuDungServiceImpl extends
    AbstractBaseService<DmHuongDanSuDungEntity, DmHuongDanSuDungDTO, DmHuongDanSuDungRepository> implements
    DmHuongDanSuDungService {

   @Autowired
   private DmHuongDanSuDungRepository repository;

   @Autowired
   private UserService userService;

   @Autowired
   private RoleService roleService;

   @Override
   protected DmHuongDanSuDungRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmHuongDanSuDungEntity entity, DmHuongDanSuDungDTO dto) {
      super.specificMapToDTO(entity, dto);

      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
      dto.setNguoiSua(userService.findById(entity.getUpdatedBy()).getFullName());

      if (entity.getRoleIds() != null) {
         dto.setRoles(entity.getRoleIds()
             .stream()
             .map(e -> roleService.findById(e))
             .collect(Collectors.toList()));
      }
   }
}
