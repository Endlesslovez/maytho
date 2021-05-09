drop function if exists thong_ke_ho_thiet_bi(text, text, text, text);

create or replace function thong_ke_ho_thiet_bi(dm_thiet_bi_ids text, dm_loai_thiet_bi_ids text, dm_don_vi_ids text, dm_tinh_thanh_pho_ids text)
    returns TABLE(id bigint, ten character varying, so_luong bigint)
as
$$
begin
    return query
        select dtb.id, dtb.ten, count(tb.id) as so_luong
        from thiet_bi tb
                 LEFT JOIN dm_model dm on tb.dm_model_id = dm.id
                 left join dm_don_vi dv on tb.dm_don_vi_cung_cap_id = dv.id
                 LEFT JOIN dm_loai_thiet_bi ltb on tb.dm_loai_thiet_bi_id=ltb.id
                 LEFT JOIN dm_thiet_bi dtb on ltb.dm_thiet_bi_id = dtb.id
        where tb.deleted = 0
          and tb.gia_niem_yet is not null
          and tb.device = 2
          and (ltb.dm_thiet_bi_id = any (string_to_array(dm_thiet_bi_ids, ',')::bigint[]) or
               dm_thiet_bi_ids is null)
          and (tb.dm_loai_thiet_bi_id = any (string_to_array(dm_loai_thiet_bi_ids, ',')::bigint[]) or
               dm_loai_thiet_bi_ids is null)
        and (tb.dm_don_vi_cung_cap_id  = any (string_to_array(dm_don_vi_ids, ',')::bigint[]) or
               dm_don_vi_ids is null)
        and (dv.dm_tinh_thanh_pho_id = any (string_to_array(dm_tinh_thanh_pho_ids, ',')::bigint[]) or
               dm_tinh_thanh_pho_ids is null)
        group by dtb.id
        order by so_luong desc;
end;
$$
    language plpgsql stable
                     cost 100;
;
