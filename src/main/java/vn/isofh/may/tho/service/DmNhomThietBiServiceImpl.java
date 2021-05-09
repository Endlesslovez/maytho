package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmNhomThietBiEntity;
import vn.isofh.may.tho.dao.repository.DmNhomThietBiRepository;
import vn.isofh.may.tho.dto.DmNhomThietBiDTO;
import vn.isofh.may.tho.enums.LoaiNhomEnum;
import vn.isofh.may.tho.exception.DmNhomThietBiException;

@Service
public class DmNhomThietBiServiceImpl extends
    AbstractDmService<DmNhomThietBiEntity, DmNhomThietBiDTO, DmNhomThietBiRepository> implements
    DmNhomThietBiService {

   @Autowired
   private DmNhomThietBiRepository repository;

   @Autowired
   private UserService userService;

   @Override
   protected DmNhomThietBiRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmNhomThietBiEntity entity, DmNhomThietBiDTO dto) {
      super.specificMapToDTO(entity, dto);

      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
      dto.setNguoiTao(userService.findById(entity.getUpdatedBy()).getFullName());

   }

   @Override
   protected DmNhomThietBiEntity beforeSave(DmNhomThietBiEntity entity, DmNhomThietBiDTO dto) {

      validateMa(entity);

      validateTen(entity);

      return super.beforeSave(entity, dto);
   }

   private void validateMa(DmNhomThietBiEntity entity) {

      if (entity.getLoai().equals(LoaiNhomEnum.NHOM_THIET_BI)) {
         return;
      }

      if (getRepository().existsByMa(entity.getMa(), entity.getId())) {
         throw new DmNhomThietBiException.DuplicateMa();
      }

   }

   private void validateTen(DmNhomThietBiEntity entity) {

      if (entity.getLoai().equals(LoaiNhomEnum.NHOM_THIET_BI)) {
         return;
      }

      if (getRepository().existsByTen(entity.getTen(), entity.getId())) {
         throw new DmNhomThietBiException.DuplicateTen();
      }
   }

   @Override
   public void capNhapSoLuong(Long id, Integer soLuong) {
      DmNhomThietBiEntity dmNhomThietBi = getById(id);
      dmNhomThietBi.setSoLuong(soLuong);

      repository.save(dmNhomThietBi);
   }
}
