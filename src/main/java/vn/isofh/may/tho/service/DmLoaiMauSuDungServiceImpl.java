package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmLoaiMauSuDungEntity;
import vn.isofh.may.tho.dao.repository.DmLoaiMauSuDungRepository;
import vn.isofh.may.tho.dto.DmLoaiMauSuDungDTO;
import vn.isofh.may.tho.exception.LoaiMauSuDungException;

@Service
public class DmLoaiMauSuDungServiceImpl extends
    AbstractDmService<DmLoaiMauSuDungEntity, DmLoaiMauSuDungDTO, DmLoaiMauSuDungRepository> implements
    DmLoaiMauSuDungService {

   private static final String PREFIX_CODE = "LM";

   @Autowired
   private DmLoaiMauSuDungRepository repository;

   @Autowired
   private UserService userService;

   @Override
   protected DmLoaiMauSuDungRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmLoaiMauSuDungEntity entity, DmLoaiMauSuDungDTO dto) {
      super.specificMapToDTO(entity, dto);

      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
      dto.setNguoiSuaDoi(userService.findById(entity.getUpdatedBy()).getFullName());
   }

   @Override
   protected DmLoaiMauSuDungEntity beforeSave(DmLoaiMauSuDungEntity entity,
       DmLoaiMauSuDungDTO dto) {

      validateTen(entity);

      entity.setMa(generateMa());

      return super.beforeSave(entity, dto);
   }


   private String generateMa() {
      String currentMa = getRepository().getCurrentMa(PREFIX_CODE);
      String ma;

      if (currentMa == null) {
         ma = PREFIX_CODE + "001";
      } else {
         ma = PREFIX_CODE + String.format("%03d", Integer.parseInt(currentMa) + 1);
      }

      return ma;
   }

   private void validateTen(DmLoaiMauSuDungEntity model) {
      if (getRepository().existsByTen(model.getTen(), model.getId())) {
         throw new LoaiMauSuDungException.DuplicateTen();
      }
   }
}
