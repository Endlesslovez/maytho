package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmSuaDoiGiaEntity;
import vn.isofh.may.tho.dao.repository.DmSuaDoiGiaRepository;
import vn.isofh.may.tho.dto.DmSuaDoiGiaDTO;

@Service
public class DmSuaDoiGiaServiceImpl extends
    AbstractDmService<DmSuaDoiGiaEntity, DmSuaDoiGiaDTO, DmSuaDoiGiaRepository> implements
    DmSuaDoiGiaService {

   @Autowired
   private DmSuaDoiGiaRepository repository;

   @Autowired
   private UserService userService;

   @Override
   protected DmSuaDoiGiaRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmSuaDoiGiaEntity entity, DmSuaDoiGiaDTO dto) {
      super.specificMapToDTO(entity, dto);

      Long nguoiTaoId = entity.getCreatedBy();
      Long nguoiSuaDoiId = entity.getUpdatedBy();

      dto.setNguoiSua(nguoiSuaDoiId == null ? "" : userService.findById(nguoiSuaDoiId).getFullName());
      dto.setNguoiTao(nguoiTaoId == null ? "" : userService.findById(nguoiTaoId).getFullName());
   }

   @Override
   @Cacheable
   public DmSuaDoiGiaDTO getByThietBiId(Long thietBiId) {
      return mapToDTO(getRepository().getByThietBiId(thietBiId));
   }

   @Override
   @Cacheable
   public DmSuaDoiGiaDTO getByVatTuYTeId(Long dmVTYTId) {
      return mapToDTO(getRepository().getByVatTuYTeId(dmVTYTId));
   }
}
