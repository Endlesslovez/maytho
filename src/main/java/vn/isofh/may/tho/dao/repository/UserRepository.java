package vn.isofh.may.tho.dao.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.UserEntity;
import vn.isofh.may.tho.dto.UserDTO;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, UserDTO, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findById(Long id);

  @Query("select case when count(e) > 0 then true else false end from UserEntity e where e.username = ?1 and (e.id <> ?2 or ?2 is null)")
  boolean existsByUsername(String username, Long id);

  @Override
  @Query("select e from UserEntity e " +
      " left join e.dmDonVi dv" +
      " where 1 = 1 " +
      " and (text_search(e.username) like text_search_like(:#{#dto.username}) or :#{#dto.username} is null)" +
      " and (text_search(e.fullName) like text_search_like(:#{#dto.fullName}) or :#{#dto.fullName} is null)" +
      " and (e.dmDonViId = :#{#dto.dmDonViId} or :#{#dto.dmDonViId} is null)" +
      " and (e.id = :#{#dto.id} or :#{#dto.id} is null)" +
      " and (text_search(dv.ten) like text_search_like(:#{#dto.dmDonViTen}) or :#{#dto.dmDonViTen} is null)" +
      " and (text_search(e.email) like text_search_like(:#{#dto.email}) or :#{#dto.email} is null)" +
      " and (e.trangThai = :#{#dto.trangThai} or :#{#dto.trangThai} is null)" +
      " and (trunc(e.createdAt) = :#{#dto.createdAt} or cast(:#{#dto.createdAt} as string) is null)")
  Page<UserEntity> search(UserDTO dto, Pageable pageable);
}