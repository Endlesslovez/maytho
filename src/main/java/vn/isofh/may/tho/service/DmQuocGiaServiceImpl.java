package vn.isofh.may.tho.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.isofh.common.util.ObjectMapperUtil;
import vn.isofh.may.tho.dao.model.DmQuocGiaEntity;
import vn.isofh.may.tho.dao.repository.DmQuocGiaRepository;
import vn.isofh.may.tho.dto.DmQuocGiaDTO;

@Service
public class DmQuocGiaServiceImpl extends
    AbstractDmService<DmQuocGiaEntity, DmQuocGiaDTO, DmQuocGiaRepository> implements
    DmQuocGiaService {

  @Autowired
  private DmQuocGiaRepository repository;

  @Override
  protected DmQuocGiaRepository getRepository() {
    return repository;
  }

  @Override
  protected Map<String, Object> getByKeys(Map<String, Object> newObj, Map<String, Object> keys) {
    for (String key : keys.keySet()) {
      if ("ten".equals(key)) {
        DmQuocGiaEntity entity = repository.findByTen((String) keys.get("ten")).orElse(null);
        if (entity != null) {
          return ObjectMapperUtil.convertValue(mapToDTO(entity));
        }
      }
    }

    return super.getByKeys(newObj, keys);
  }
}
