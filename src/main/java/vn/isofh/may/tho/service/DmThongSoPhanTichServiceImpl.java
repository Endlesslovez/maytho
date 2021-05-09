package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vn.isofh.may.tho.dao.model.DmThongSoPhanTichEntity;
import vn.isofh.may.tho.dao.repository.DmThongSoPhanTichRepository;
import vn.isofh.may.tho.dto.DmDonViDTO;
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.dto.DmPhuongPhapXetNgiemDTO;
import vn.isofh.may.tho.dto.DmThongSoPhanTichDTO;
import vn.isofh.may.tho.dto.DmTinhThanhPhoDTO;
import vn.isofh.may.tho.dto.ThietBiDTO;
import vn.isofh.may.tho.dto.ThietBiInVitroDTO;
import vn.isofh.may.tho.enums.LoaiThongSoPhanTichEnum;
import vn.isofh.may.tho.exception.ThongSoPhanTichException;

@Service
public class DmThongSoPhanTichServiceImpl extends
    AbstractDmService<DmThongSoPhanTichEntity, DmThongSoPhanTichDTO, DmThongSoPhanTichRepository> implements
    DmThongSoPhanTichService {

   private static String PREFIX_CODE = null;


   @Autowired
   private DmThongSoPhanTichRepository repository;

   @Autowired
   private UserService userService;

   @Autowired
   private ThietBiInVitroService thietBiInVitroService;

   @Autowired
   private DmDonViService dmDonViService;

   @Autowired
   private DmPhuongPhapXetNgiemService dmPhuongPhapXetNgiemService;

   @Override
   protected DmThongSoPhanTichRepository getRepository() {
      return repository;
   }

   @Override
   protected void specificMapToDTO(DmThongSoPhanTichEntity entity, DmThongSoPhanTichDTO dto) {
      super.specificMapToDTO(entity, dto);

      dto.setNguoiSuaDoi(userService.findById(entity.getUpdatedBy()).getFullName());
      dto.setNguoiTao(userService.findById(entity.getCreatedBy()).getFullName());
      dto.setDmPhuongPhap(dmPhuongPhapXetNgiemService.getBaseDTOById(entity.getDmPhuongPhapId()));
   }

   @Override
   protected DmThongSoPhanTichEntity beforeSave(DmThongSoPhanTichEntity entity,
       DmThongSoPhanTichDTO dto) {

      validateTen(entity);

      generateMa(dto, entity);

      setSoLuong(entity);

      return super.beforeSave(entity, dto);
   }

   private void generateMa(DmThongSoPhanTichDTO dto, DmThongSoPhanTichEntity entity) {

      if(dto.getMa() != null){
        return;
      }

      if (LoaiThongSoPhanTichEnum.THONG_SO_PHAN_TICH.equals(dto.getLoai())){
         PREFIX_CODE = "PP";
      } else if (LoaiThongSoPhanTichEnum.DICH_XET_NGHIEM.equals(dto.getLoai())) {
         PREFIX_CODE = "Đ";
      }

      String currentMa = getRepository().getCurrentMa(PREFIX_CODE);
      String ma;

      if (currentMa == null) {
         ma = PREFIX_CODE + "0001";
      } else {
         ma = PREFIX_CODE + String.format("%04d", Integer.parseInt(currentMa) + 1);
      }

      entity.setMa(ma);
   }

   private void setSoLuong(DmThongSoPhanTichEntity entity){
      if(entity.isNewRecord()){
         entity.setSoLuong(0);
      }
   }

   private void validateTen(DmThongSoPhanTichEntity model) {
      if (getRepository().existsByTenAndLoai(model.getTen(), model.getLoai(), model.getId())) {
         throw new ThongSoPhanTichException.DuplicateTen();
      }
   }

   @Override
   public void capNhapSoLuong(Long id, Integer soLuong) {
      if(id == null){
         return;
      }

      DmThongSoPhanTichEntity entity =  getById(id);
      entity.setSoLuong(soLuong);
      getRepository().save(entity);

   }

   @Override
   public Set<DmPhuongPhapXetNgiemDTO> getDsPhuongPhapXetNghiem(Long id) {
      List<ThietBiInVitroDTO> inVitroDTOs = thietBiInVitroService.getDmThongSoPhanTich(id);
      Set<DmPhuongPhapXetNgiemDTO> list = new HashSet<>();
      for(ThietBiInVitroDTO dto : inVitroDTOs){
         list.add(dmPhuongPhapXetNgiemService.findById(dto.getDmPhuongPhapId()));
      }
      return list;
   }

   @Override
   public Set<DmDonViDTO> getDsHangSanXuat(Long id) {
      List<ThietBiInVitroDTO> inVitroDTOs = thietBiInVitroService.getDmThongSoPhanTich(id);
      Set<DmDonViDTO> list = new HashSet<>();
      for(ThietBiInVitroDTO dto : inVitroDTOs){
         list.add(dmDonViService.findById(dto.getDmHangSanXuatId()));
      }
      return list;
   }

   @Override
   public Set<DmTinhThanhPhoDTO> getDsTinhThanhPho(Long id) {
      List<ThietBiInVitroDTO> inVitroDTOs = thietBiInVitroService.getDmThongSoPhanTich(id);
      Set<DmDonViDTO> list = new HashSet<>();
      for(ThietBiInVitroDTO dto : inVitroDTOs){
         list.add(dmDonViService.findById(dto.getDmHangSanXuatId()));
      }
      return null;
   }

   @Override
   public Long getIdByTen(String ten, LoaiThongSoPhanTichEnum loai) {
      return getRepository().getIdByTen(ten, loai);
   }
}
