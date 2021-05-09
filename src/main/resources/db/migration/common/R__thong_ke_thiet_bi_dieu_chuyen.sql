drop function if exists thong_ke_thiet_bi_dieu_chuyen(bigint, bigint, bigint, bigint);

create or replace function thong_ke_thiet_bi_dieu_chuyen(p_co_so_y_te_id bigint,
                                                        p_tinh_thanh_pho_id bigint,
                                                        p_quan_huyen_id bigint,
                                                        p_xa_phuong_id bigint)
    returns table
            (
                id            bigint,
                so_luong      bigint,
                ma_thiet_bi   character varying,
                ten_thiet_bi  character varying,
                trang_thai    character varying,
                ma_trang_thai character varying
            )
as
$body$
begin
    return query
        select row_number() over () id,
               sum(tb.so_luong) so_luong,
               tb.ma_thiet_bi,
               tb.ten_thiet_bi,
               tb.trang_thai,
               tb.ma_trang_thai
        from (select coalesce(tb.so_luong, 1) so_luong,
                     dtb.ma                   ma_thiet_bi,
                     dtb.ten                  ten_thiet_bi,
                     'Khả năng điều chuyển'   trang_thai,
                     'DIEU_CHUYEN'            ma_trang_thai,
                     ddv.id                   co_so_y_te_id,
                     ddv.dm_tinh_thanh_pho_id tinh_thanh_pho_id,
                     ddv.dm_quan_huyen_id     quan_huyen_id,
                     ddv.dm_xa_phuong_id      xa_phuong_id
              from thiet_bi tb
                       inner join dm_don_vi ddv on tb.dm_don_vi_id = ddv.id and ddv.deleted = 0
                       inner join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id and dtb.deleted = 0
              where tb.deleted = 0
                and ddv.loai_don_vi = 10
                and tb.san_sang_dieu_chuyen = true
              union all
              select coalesce(tb.so_luong, 1) so_luong,
                     dtb.ma                   ma_thiet_bi,
                     dtb.ten                  ten_thiet_bi,
                     dtt.ten                  trang_thai,
                     dtt.ma                   ma_trang_thai,
                     ddv.id                   co_so_y_te_id,
                     ddv.dm_tinh_thanh_pho_id tinh_thanh_pho_id,
                     ddv.dm_quan_huyen_id     quan_huyen_id,
                     ddv.dm_xa_phuong_id      xa_phuong_id
              from thiet_bi tb
                       inner join dm_don_vi ddv on tb.dm_don_vi_id = ddv.id and ddv.deleted = 0
                       inner join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id and dtb.deleted = 0
                       inner join dm_trang_thai dtt on tb.dm_trang_thai_id = dtt.id and dtt.deleted = 0
              where tb.deleted = 0
                and ddv.loai_don_vi = 10) tb
        where (tb.co_so_y_te_id = p_co_so_y_te_id or p_co_so_y_te_id is null)
        and (tb.tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
        and (tb.quan_huyen_id = p_quan_huyen_id or p_quan_huyen_id is null)
        and (tb.xa_phuong_id = p_xa_phuong_id or p_xa_phuong_id is null)
        group by tb.ma_thiet_bi, tb.ten_thiet_bi, tb.trang_thai, tb.ma_trang_thai;

end;
$body$
    language plpgsql stable
                     cost 100;
;
