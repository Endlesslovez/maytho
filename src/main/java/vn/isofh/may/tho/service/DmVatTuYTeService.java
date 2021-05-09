package vn.isofh.may.tho.service;

import vn.isofh.may.tho.dto.DmVatTuYTeDTO;

public interface DmVatTuYTeService extends DmService<DmVatTuYTeDTO> {

  boolean existsByDmLoaiThietBiId(Long dmLoaiThietBiId);
}

