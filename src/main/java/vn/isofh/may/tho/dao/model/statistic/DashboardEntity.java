package vn.isofh.may.tho.dao.model.statistic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("")
@Immutable
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "thongKeHoThietBi",
        procedureName = "thong_ke_ho_thiet_bi",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_loai_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_tinh_thanh_pho_ids",
                type = String.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeLoaiThietBi",
        procedureName = "thong_ke_loai_thiet_bi",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_loai_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "gia_tu_khoang",
                type = Long.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "gia_den_khoang",
                type = Long.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeHangSanXuat",
        procedureName = "thong_ke_hang_san_xuat",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_nhom_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_loai_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "hang_san_xuat_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_tinh_thanh_pho_ids",
                type = String.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeNhomThietBi",
        procedureName = "thong_ke_nhom_thiet_bi",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_nhom_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_loai_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "hang_san_xuat_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_tinh_thanh_pho_ids",
                type = String.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeThietBiTheoDN",
        procedureName = "thong_ke_thiet_bi_theo_doanh_nghiep",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_nhom_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_loai_thiet_bi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "hang_san_xuat_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_tinh_thanh_pho_ids",
                type = String.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeIVDTheoPhuongPhap",
        procedureName = "thong_ke_ivd_theo_phuong_phap",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_phuong_phap_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "hang_san_xuat_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeIVDTheoDoanhNghiep",
        procedureName = "thong_ke_ivd_theo_doanh_nghiep",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_phuong_phap_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "hang_san_xuat_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN)}),
    @NamedStoredProcedureQuery(
        name = "thongKeIVDTheoHangSx",
        procedureName = "thong_ke_ivd_theo_hang_san_xuat",
        resultClasses = {DashboardEntity.class},
        parameters = {
            @StoredProcedureParameter(
                name = "dm_phuong_phap_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "hang_san_xuat_ids",
                type = String.class,
                mode = ParameterMode.IN),
            @StoredProcedureParameter(
                name = "dm_don_vi_ids",
                type = String.class,
                mode = ParameterMode.IN)})
})
@Setter
@Getter
@NoArgsConstructor
public class DashboardEntity {

  @Id
  private Long id;

  private String ten;

  private Long so_luong;

}