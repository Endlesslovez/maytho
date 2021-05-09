package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmPhuongPhapXetNgiemEntity;
import vn.isofh.may.tho.dao.repository.DmPhuongPhapXetNgiemRepository;
import vn.isofh.may.tho.dto.DmPhuongPhapXetNgiemDTO;

@Service
public class DmPhuongPhapXetNgiemServiceImpl extends
    AbstractDmService<DmPhuongPhapXetNgiemEntity, DmPhuongPhapXetNgiemDTO, DmPhuongPhapXetNgiemRepository> implements
    DmPhuongPhapXetNgiemService {

   private static final String PREFIX_CODE = "PP";

   @Autowired
   private DmPhuongPhapXetNgiemRepository repository;

   @Autowired
   private UserService userService;

   @Override
   protected DmPhuongPhapXetNgiemRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmPhuongPhapXetNgiemEntity entity, DmPhuongPhapXetNgiemDTO dto) {
      super.specificMapToDTO(entity, dto);

      dto.setNguoiSuaDoi(userService.findById(entity.getUpdatedBy()).getFullName());
      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
   }

   @Override
   protected DmPhuongPhapXetNgiemEntity beforeSave(DmPhuongPhapXetNgiemEntity entity,
       DmPhuongPhapXetNgiemDTO dto) {

      entity.setMa(generateMa());

      setSoLuong(entity);

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

   private void setSoLuong(DmPhuongPhapXetNgiemEntity entity){
      if(entity.isNewRecord()){
         entity.setSoLuong(0);
      }
   }


   @Override
   public void capNhapSoLuong(Long id, Integer soLuong) {
      if(id == null){
         return;
      }

      DmPhuongPhapXetNgiemEntity entity =  getById(id);
      entity.setSoLuong(soLuong);
      getRepository().save(entity);

   }
}
