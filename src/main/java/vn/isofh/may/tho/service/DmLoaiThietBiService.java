package vn.isofh.may.tho.service;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import vn.isofh.common.report.ReportDTO;
import vn.isofh.may.tho.dao.model.DmLoaiThietBiEntity;
import vn.isofh.may.tho.dto.DmLoaiThietBiDTO;
import vn.isofh.may.tho.enums.DmLoaiThietBiEnum;

public interface DmLoaiThietBiService extends DmService<DmLoaiThietBiDTO> {

  ReportDTO exportLoaiThietBi(DmLoaiThietBiDTO dto, Pageable pageable);

  List<DmLoaiThietBiDTO> getListLoaiThietBi(Long id);

  Optional<DmLoaiThietBiEntity> findByDmVatTuYTeId(Long dmVatTuYTeId);

  Long getIdByTenAndLoai(String ten, DmLoaiThietBiEnum loai);

}

