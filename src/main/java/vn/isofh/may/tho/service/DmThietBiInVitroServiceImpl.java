package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.may.tho.dao.model.DmThietBiInVitroEntity;
import vn.isofh.may.tho.dao.repository.DmThietBiInVitroRepository;
import vn.isofh.may.tho.dto.DmThietBiInVitroDTO;

@Service
public class DmThietBiInVitroServiceImpl extends
    AbstractDmService<DmThietBiInVitroEntity, DmThietBiInVitroDTO, DmThietBiInVitroRepository> implements
    DmThietBiInVitroService {

   private static final String PREFIX_CODE = "TB";

   @Autowired
   private DmThietBiInVitroRepository repository;

   @Autowired
   private UserService userService;

   @Autowired
   private DmPhuongPhapXetNgiemService dmPhuongPhapXetNgiemService;

   @Autowired
   private DmThongSoPhanTichService dmThongSoPhanTichService;

   @Override
   protected DmThietBiInVitroRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmThietBiInVitroEntity entity, DmThietBiInVitroDTO dto) {
      super.specificMapToDTO(entity, dto);

      dto.setNguoiSuaDoi(userService.findById(entity.getUpdatedBy()).getFullName());
      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());

      dto.setDmPhuongPhapXetNghiem(dmPhuongPhapXetNgiemService.getBaseDTOById(entity.getDmPhuongPhapXetNghiemId()));
      dto.setDmThongSoPhanTich(dmThongSoPhanTichService.getBaseDTOById(entity.getDmThongSoPhanTichId()));
   }

   @Override
   protected DmThietBiInVitroEntity beforeSave(DmThietBiInVitroEntity entity, DmThietBiInVitroDTO dto) {

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
}
