drop function if exists thong_ke_hang_san_xuat;

create or replace function thong_ke_hang_san_xuat(dm_nhom_thiet_bi_ids text, dm_loai_thiet_bi_ids text, dm_don_vi_ids text,hang_san_xuat_ids text, dm_tinh_thanh_pho_ids text)
    returns TABLE(id bigint, ten character varying, so_luong bigint)
as
$$
begin
    return query
        select hsx.id, hsx.ten, count(tb.id) as so_luong
        from thiet_bi tb
                 LEFT JOIN dm_model dm on tb.dm_model_id = dm.id
                 left join dm_don_vi dv on tb.dm_don_vi_id = dv.id
                 LEFT JOIN dm_loai_thiet_bi ltb on tb.dm_loai_thiet_bi_id=ltb.id
                 LEFT JOIN dm_thiet_bi dtb on ltb.dm_thiet_bi_id = dtb.id
                 left join dm_nhom_thiet_bi ntb on dtb.dm_nhom_tbyt_id = ntb.id
                 left join dm_don_vi hsx on dm.dm_hang_san_xuat_id = hsx.id
        where tb.deleted = 0
           and ntb.deleted = 0
          and tb.gia_niem_yet is not null
          and tb.device = 2
          and (dtb.dm_nhom_tbyt_id = any (string_to_array(dm_nhom_thiet_bi_ids, ',')::bigint[]) or
               dm_nhom_thiet_bi_ids is null)
          and (tb.dm_loai_thiet_bi_id = any (string_to_array(dm_loai_thiet_bi_ids, ',')::bigint[]) or
               dm_loai_thiet_bi_ids is null)
          and (tb.dm_don_vi_cung_cap_id  = any (string_to_array(dm_don_vi_ids, ',')::bigint[]) or
               dm_don_vi_ids is null)
          and (dv.dm_tinh_thanh_pho_id = any (string_to_array(dm_tinh_thanh_pho_ids, ',')::bigint[]) or
               dm_tinh_thanh_pho_ids is null)
          and (dm.dm_hang_san_xuat_id= any (string_to_array(hang_san_xuat_ids, ',')::bigint[]) or
               hang_san_xuat_ids is null)
        group by hsx.id
        order by so_luong desc;
end;
$$
    language plpgsql stable
                     cost 100;
;
