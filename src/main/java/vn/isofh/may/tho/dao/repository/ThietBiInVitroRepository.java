package vn.isofh.may.tho.dao.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.isofh.common.dao.repository.BaseRepository;
import vn.isofh.may.tho.dao.model.ThietBiInVitroEntity;
import vn.isofh.may.tho.dto.ThietBiInVitroDTO;

@Repository
public interface ThietBiInVitroRepository extends BaseRepository<ThietBiInVitroEntity, ThietBiInVitroDTO, Long> {

   @Override
   @Query("select e from ThietBiInVitroEntity e"
       + " left join e.dmThietBiInVitro tv"
       + " left join tv.dmPhuongPhapXetNghiem pp"
       + " left join e.dmHangSanXuat hsx"
       + " left join e.dmThietBiInVitro ivt"
       + " left join ivt.dmThongSoPhanTich tspt"
       + " where 1 = 1"
       + " and (text_search(e.ma) like text_search_like(:#{#dto.ma}) or :#{#dto.ma} is null)"
       + " and (e.dmNhomThietBiId = :#{#dto.dmNhomThietBiId} or :#{#dto.dmNhomThietBiId} is null)"
       + " and (e.phanLoai = :#{#dto.phanLoai} or :#{#dto.phanLoai} is null)"
       + " and (e.xuatXuId = :#{#dto.xuatXuId} or :#{#dto.xuatXuId} is null)"
       + " and (e.dmHangSanXuatId = :#{#dto.dmHangSanXuatId} or :#{#dto.dmHangSanXuatId} is null)"
       + " and (e.hangSoHuuId = :#{#dto.hangSoHuuId} or :#{#dto.hangSoHuuId} is null)"
       + " and (e.nuocSoHuuId = :#{#dto.nuocSoHuuId} or :#{#dto.nuocSoHuuId} is null)"
       + " and (text_search(e.giayPhepLuuHanh) like text_search_like(:#{#dto.giayPhepLuuHanh}) or :#{#dto.giayPhepLuuHanh} is null)"
       + " and (text_search(e.soGiayPhepLuuHanh) like text_search_like(:#{#dto.soGiayPhepLuuHanh}) or :#{#dto.soGiayPhepLuuHanh} is null)"
       + " and (text_search(e.thongSoPhanTich) like text_search_like(:#{#dto.thongSoPhanTich}) or :#{#dto.thongSoPhanTich} is null)"
       + " and (text_search(e.tenChungLoaiTbSuDungCung) like text_search_like(:#{#dto.tenChungLoaiTbSuDungCung}) or :#{#dto.tenChungLoaiTbSuDungCung} is null)"
       + " and (text_search(e.dieuKienThongTinKhac) like text_search_like(:#{#dto.dieuKienThongTinKhac}) or :#{#dto.dieuKienThongTinKhac} is null)"
       + " and (text_search(e.taiLieuHdsd) like text_search_like(:#{#dto.taiLieuHdsd}) or :#{#dto.taiLieuHdsd} is null)"
       + " and (text_search(e.taiLieuIso) like text_search_like(:#{#dto.taiLieuIso}) or :#{#dto.taiLieuIso} is null)"
       + " and (text_search(e.taiLieuCfs) like text_search_like(:#{#dto.taiLieuCfs}) or :#{#dto.taiLieuCfs} is null)"
       + " and (text_search(e.taiLieuKhac) like text_search_like(:#{#dto.taiLieuKhac}) or :#{#dto.taiLieuKhac} is null)"
       + " and (text_search(e.thongSoHieuNang) like text_search_like(:#{#dto.thongSoHieuNang}) or :#{#dto.thongSoHieuNang} is null)"
       + " and (text_search(e.chungLoai) like text_search_like(:#{#dto.chungLoai}) or :#{#dto.chungLoai} is null)"
       + " and (e.dmDonViTinhId = :#{#dto.dmDonViTinhId} or :#{#dto.dmDonViTinhId} is null)"
       + " and (e.giaNiemYet = :#{#dto.giaNiemYet} or :#{#dto.giaNiemYet} is null)"
       + " and (trunc(e.ngayBatDauHieuLuc) = :#{#dto.ngayBatDauHieuLuc} or cast(:#{#dto.ngayBatDauHieuLuc} as string) is null)"
       + " and (trunc(e.ngayHetHieuLuc) = :#{#dto.ngayHetHieuLuc} or cast(:#{#dto.ngayHetHieuLuc} as string) is null)"
       + " and (e.namSanXuat = :#{#dto.namSanXuat} or :#{#dto.namSanXuat} is null)"
       + " and (tv.dmPhuongPhapXetNghiemId in :#{#dto.dmPpXetNghiemIds} or :#{#dto.dmPpXetNghiemIds} is null)"
       + " and (hsx.dmTinhThanhPhoId in :#{#dto.truSoChinhIds} or :#{#dto.truSoChinhIds} is null)"
       + " and (e.dmHangSanXuatId in :#{#dto.dmHangSanXuatIds} or :#{#dto.dmHangSanXuatIds} is null)"
       + " and (e.dmDonViId = :#{#dto.dmDonViId} or :#{#dto.dmDonViId} is null)"
       + " and (e.id <> :#{#dto.idLoaiBo} or :#{#dto.idLoaiBo} is null)"
       + " and (tv.dmThongSoPhanTichId in :#{#dto.dmThongSoPhanTichIds} or :#{#dto.dmThongSoPhanTichIds} is null)"
       + " and (e.congKhaiGia = :#{#dto.congKhaiGia} or :#{#dto.congKhaiGia} is null)"
       + " and (e.dmThietBiInVitroId = :#{#dto.dmThietBiInVitroId} or :#{#dto.dmThietBiInVitroId} is null)"
       + " and (e.dmThietBiInVitroId in :#{#dto.dmThietBiInVitroIds} or :#{#dto.dmThietBiInVitroIds} is null)"
       + " and (ivt.dmThongSoPhanTichId = :#{#dto.dmThongSoPhanTichId} or :#{#dto.dmThongSoPhanTichId} is null)"
       + " and (tspt.dmPhuongPhapId = :#{#dto.dmPhuongPhapId} or :#{#dto.dmPhuongPhapId} is null)"
   )
   Page<ThietBiInVitroEntity> search(ThietBiInVitroDTO dto, Pageable pageable);

   @Query("select count(e) from ThietBiInVitroEntity e"
       + " left join e.dmThietBiInVitro tt"
       + " where tt.dmPhuongPhapXetNghiemId  = :dmPhuongPhapXetNghiemId"
       + " and (e.giaNiemYet is not null)")
   Integer getSoLuong(Long dmPhuongPhapXetNghiemId);

   @Query(" select e from ThietBiInVitroEntity e where 1 = 1 "
       + " and (:id in e.dmThongSoPhanTichIds or :dmThietBiIid is null )"
       + " and ( e.giaNiemYet is not null) order by e.id desc")
   List<ThietBiInVitroEntity> getByDmThongSoPhanTichId(Long id);


}