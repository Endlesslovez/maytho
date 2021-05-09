drop function if exists thong_ke_ivd_theo_hang_san_xuat(text, text, text);

create or replace function thong_ke_ivd_theo_hang_san_xuat(dm_phuong_phap_ids text, dm_don_vi_ids text,hang_san_xuat_ids text)
    returns TABLE(id bigint, ten character varying, so_luong bigint)
as
$$
begin
    return query
        select hsx.id, hsx.ten, count(ivd.id) as so_luong
        from thiet_bi_in_vitro ivd
             left join dm_thiet_bi_in_vitro divd on ivd.dm_thiet_bi_in_vitro_id = divd.id
             left join dm_phuong_phap_xet_nghiem dppxn on divd.dm_phuong_phap_xet_nghiem_id = dppxn.id
             left join dm_don_vi dv on ivd.dm_don_vi_id = dv.id
             left join dm_don_vi hsx on ivd.dm_hang_san_xuat_id = hsx.id
        where ivd.deleted=0
             and ivd.cong_khai_gia = true
          and (dppxn.id = any (string_to_array(dm_phuong_phap_ids, ',')::bigint[]) or
               dm_phuong_phap_ids is null)
          and (ivd.dm_don_vi_id  = any (string_to_array(dm_don_vi_ids, ',')::bigint[]) or
               dm_don_vi_ids is null)
          and (ivd.dm_hang_san_xuat_id= any (string_to_array(hang_san_xuat_ids, ',')::bigint[]) or
               hang_san_xuat_ids is null)
        group by hsx.id
order by so_luong desc;
end;
$$
    language plpgsql stable
                     cost 100;
;
