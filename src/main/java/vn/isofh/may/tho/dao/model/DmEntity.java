package vn.isofh.may.tho.dao.model;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.isofh.common.dao.model.BaseEntity;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class DmEntity extends BaseEntity {

  private String ma;

  private String ten;
}
