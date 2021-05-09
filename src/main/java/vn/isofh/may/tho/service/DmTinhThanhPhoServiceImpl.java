package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.common.util.Header;
import vn.isofh.may.tho.dao.model.DmTinhThanhPhoEntity;
import vn.isofh.may.tho.dao.repository.DmTinhThanhPhoRepository;
import vn.isofh.may.tho.dto.DmTinhThanhPhoDTO;

@Service
public class DmTinhThanhPhoServiceImpl extends
    AbstractDmService<DmTinhThanhPhoEntity, DmTinhThanhPhoDTO, DmTinhThanhPhoRepository> implements
    DmTinhThanhPhoService {

  @Autowired
  private DmTinhThanhPhoRepository repository;

  @Autowired
  private DmQuocGiaService dmQuocGiaService;

  @Override
  protected DmTinhThanhPhoRepository getRepository() {
    return repository;
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmQuocGiaId".equals(header.getColumnName()) && "ma".equals(header.getLinkColumnName())) {
      return dmQuocGiaService.getIdByMa(value);
    }

    return null;
  }
}
