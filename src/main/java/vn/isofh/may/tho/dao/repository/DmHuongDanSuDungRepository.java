package vn.isofh.may.tho.dao.repository;

import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.DmHuongDanSuDungEntity;
import vn.isofh.may.tho.dto.DmHuongDanSuDungDTO;

@Repository
public interface DmHuongDanSuDungRepository extends
    BaseRepository<DmHuongDanSuDungEntity, DmHuongDanSuDungDTO, Long> {

}
