package vn.isofh.may.tho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.common.util.Header;
import vn.isofh.may.tho.dao.model.DmQuanHuyenEntity;
import vn.isofh.may.tho.dao.repository.DmQuanHuyenRepository;
import vn.isofh.may.tho.dto.DmQuanHuyenDTO;

@Service
public class DmQuanHuyenServiceImpl extends
    AbstractDmService<DmQuanHuyenEntity, DmQuanHuyenDTO, DmQuanHuyenRepository> implements
    DmQuanHuyenService {

  @Autowired
  private DmQuanHuyenRepository repository;

  @Autowired
  private DmTinhThanhPhoService dmTinhThanhPhoService;

  @Override
  protected DmQuanHuyenRepository getRepository() {
    return repository;
  }

  @Override
  protected Object getReference(Header header, String value) {
    if ("dmTinhThanhPhoId".equals(header.getColumnName()) && "ma"
        .equals(header.getLinkColumnName())) {
      return dmTinhThanhPhoService.getIdByMa(value);
    }

    return null;
  }
}
