drop function if exists thong_ke_thiet_bi_nguon_von(bigint, bigint, bigint, bigint, character varying);

create or replace function thong_ke_thiet_bi_nguon_von(p_co_so_y_te_id bigint,
                                                       p_tinh_thanh_pho_id bigint,
                                                       p_quan_huyen_id bigint,
                                                       p_xa_phuong_id bigint,
                                                       p_ten_thiet_bi character varying)
    returns table
            (
                id           bigint,
                so_luong     bigint,
                ma_thiet_bi  character varying,
                ten_thiet_bi character varying,
                nguon_von    character varying,
                ma_nguon_von character varying
            )
as
$body$
begin
    return query
        select row_number() over () id,
               count(*) so_luong,
               dtb.ma   ma_thiet_bi,
               dtb.ten  ten_thiet_bi,
               dnv.ten  nguon_von,
               dnv.ma   ma_nguon_von
        from thiet_bi tb
                 inner join dm_don_vi ddv on tb.dm_don_vi_id = ddv.id and ddv.deleted = 0
                 inner join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id and dtb.deleted = 0
                 inner join dm_nguon_von dnv on tb.dm_nguon_von_id = dnv.id and dnv.deleted = 0
        where tb.deleted = 0
          and (tb.dm_don_vi_id = p_co_so_y_te_id or p_co_so_y_te_id is null)
          and (ddv.dm_tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
          and (ddv.dm_quan_huyen_id = p_quan_huyen_id or p_quan_huyen_id is null)
          and (ddv.dm_xa_phuong_id = p_xa_phuong_id or p_xa_phuong_id is null)
          and (text_search(dtb.ten) like text_search_like(p_ten_thiet_bi) or p_ten_thiet_bi is null)
        group by dtb.ma,
                 dtb.ten,
                 dnv.ten,
                 dnv.ma;

end;
$body$
    language plpgsql stable
                     cost 100;
;
