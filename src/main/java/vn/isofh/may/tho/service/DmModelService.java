package vn.isofh.may.tho.service;

import org.springframework.data.domain.Pageable;
import vn.isofh.common.report.ReportDTO;
import vn.isofh.may.tho.dto.DmModelDTO;

public interface DmModelService extends DmService<DmModelDTO> {

  ReportDTO exportModel(DmModelDTO dto, Pageable pageable);
}

