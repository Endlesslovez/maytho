package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.common.util.Header;
import vn.isofh.may.tho.dao.model.DmXaPhuongEntity;
import vn.isofh.may.tho.dao.repository.DmXaPhuongRepository;
import vn.isofh.may.tho.dto.DmXaPhuongDTO;

@Service
public class DmXaPhuongServiceImpl extends
    AbstractDmService<DmXaPhuongEntity, DmXaPhuongDTO, DmXaPhuongRepository> implements
    DmXaPhuongService {

  @Autowired
  private DmXaPhuongRepository repository;

  @Autowired
  private DmQuanHuyenService dmQuanHuyenService;

  @Override
  protected DmXaPhuongRepository getRepository() {
    return repository;
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmQuanHuyenId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmQuanHuyenService.getIdByMa(value);
    }

    return null;
  }
}
