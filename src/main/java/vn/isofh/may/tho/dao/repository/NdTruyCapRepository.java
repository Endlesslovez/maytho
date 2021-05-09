package vn.isofh.may.tho.dao.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import vn.isofh.may.tho.dao.model.NdTruyCapEntity;
import vn.isofh.may.tho.dto.NdTruyCapDTO;

@Repository
public interface NdTruyCapRepository extends DmRepository<NdTruyCapEntity, NdTruyCapDTO, Long> {

  Optional<NdTruyCapEntity> findTopByIpAndUserAccountIdIsNullOrderByNgayTruyCapDesc(String ip);
}