package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmGioiThieuHeThongEntity;
import vn.isofh.may.tho.dao.repository.DmGioiThieuHeThongRepository;
import vn.isofh.may.tho.dto.DmGioiThieuHeThongDTO;

@Service
public class DmGioiThieuHeThongServiceImpl extends
    AbstractDmService<DmGioiThieuHeThongEntity, DmGioiThieuHeThongDTO, DmGioiThieuHeThongRepository> implements
    DmGioiThieuHeThongService {

   @Autowired
   private DmGioiThieuHeThongRepository repository;

   @Autowired
   private UserService userService;

   @Override
   protected DmGioiThieuHeThongRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmGioiThieuHeThongEntity entity, DmGioiThieuHeThongDTO dto) {
      super.specificMapToDTO(entity, dto);
      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
      dto.setNguoiSuaDoi(userService.findById(entity.getUpdatedBy()).getFullName());
   }

   @Override
   protected DmGioiThieuHeThongEntity beforeSave(DmGioiThieuHeThongEntity model, DmGioiThieuHeThongDTO dto) {

      if (model.getActive() != null && model.getActive()) {
        getRepository().findByActive(true).forEach(e -> {
           if(!e.getId().equals(dto.getId())){
              e.setActive(false);
              getRepository().save(e);
           }
        });
      }

      return super.beforeSave(model, dto);
   }
}
