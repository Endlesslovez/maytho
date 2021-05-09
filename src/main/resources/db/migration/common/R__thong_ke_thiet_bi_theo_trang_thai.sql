drop function if exists thong_ke_thiet_bi_theo_trang_thai(bigint, bigint, bigint, bigint, bigint, int);

create or replace function thong_ke_thiet_bi_theo_trang_thai(p_trang_thai_id bigint,
                                                             p_tinh_thanh_pho_id bigint,
                                                             p_quan_huyen_id bigint,
                                                             p_xa_phuong_id bigint,
                                                             p_don_vi_id bigint,
                                                             p_tieu_chi int)
    returns table
            (
                id                 bigint,
                ma_thiet_bi        character varying,
                ten_thiet_bi       character varying,
                ma_loai_thiet_bi   character varying,
                ten_loai_thiet_bi  character varying,
                trang_thai         character varying,
                trang_thai_id      bigint,
                ma_tinh_thanh_pho  character varying,
                ten_tinh_thanh_pho character varying,
                so_luong           bigint
            )
as
$body$
begin

    if p_tieu_chi = 2 then
        return query
            select row_number() over ()    id,
                   dtb.ma                  ma_thiet_bi,
                   dtb.ten                 ten_thiet_bi,
                   dltb.ma                 ma_loai_thiet_bi,
                   dltb.ten                ten_loai_thiet_bi,
                   dtt.ten                 trang_thai,
                   tb.dm_trang_thai_id     trang_thai_id,
                   null::character varying ma_tinh_thanh_pho,
                   null::character varying ten_tinh_thanh_pho,
                   sum(tb.so_luong)        so_luong
            from thiet_bi tb
                     left join dm_trang_thai dtt on tb.dm_trang_thai_id = dtt.id
                     left join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id
                     left join dm_loai_thiet_bi dltb on tb.dm_loai_thiet_bi_id = dltb.id
                     left join dm_don_vi dcsyt on tb.dm_don_vi_id = dcsyt.id
            where tb.deleted = 0 and (tb.dm_trang_thai_id = p_trang_thai_id or p_trang_thai_id is null)
              and (dcsyt.dm_tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
              and (dcsyt.dm_quan_huyen_id = p_quan_huyen_id or p_quan_huyen_id is null)
              and (dcsyt.dm_xa_phuong_id = p_xa_phuong_id or p_xa_phuong_id is null)
              and (tb.dm_don_vi_id = p_don_vi_id or p_don_vi_id is null)
            group by dltb.ma, dltb.ten, dtt.ten, dtb.ma, dtb.ten, tb.dm_trang_thai_id;
    elsif p_tieu_chi = 1 then
        return query
            select row_number() over ()    id,
                   dtb.ma                  ma_thiet_bi,
                   dtb.ten                 ten_thiet_bi,
                   null::character varying ma_loai_thiet_bi,
                   null::character varying ten_loai_thiet_bi,
                   dtt.ten                 trang_thai,
                   tb.dm_trang_thai_id     trang_thai_id,
                   null::character varying ma_tinh_thanh_pho,
                   null::character varying ten_tinh_thanh_pho,
                   sum(tb.so_luong)        so_luong
            from thiet_bi tb
                     left join dm_trang_thai dtt on tb.dm_trang_thai_id = dtt.id
                     left join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id
                     left join dm_don_vi dcsyt on tb.dm_don_vi_id = dcsyt.id
            where tb.deleted = 0 and (tb.dm_trang_thai_id = p_trang_thai_id or p_trang_thai_id is null)
              and (dcsyt.dm_tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
              and (dcsyt.dm_quan_huyen_id = p_quan_huyen_id or p_quan_huyen_id is null)
              and (dcsyt.dm_xa_phuong_id = p_xa_phuong_id or p_xa_phuong_id is null)
              and (tb.dm_don_vi_id = p_don_vi_id or p_don_vi_id is null)
            group by dtt.ten, dtb.ma, dtb.ten, tb.dm_trang_thai_id;
    elsif p_tieu_chi = 3 then
        return query
            select row_number() over ()    id,
                   dtb.ma                  ma_thiet_bi,
                   dtb.ten                 ten_thiet_bi,
                   null::character varying ma_loai_thiet_bi,
                   null::character varying ten_loai_thiet_bi,
                   dtt.ten                 trang_thai,
                   tb.dm_trang_thai_id     trang_thai_id,
                   dttp.ma                 ma_tinh_thanh_pho,
                   dttp.ten                ten_tinh_thanh_pho,
                   sum(tb.so_luong)        so_luong
            from thiet_bi tb
                     left join dm_trang_thai dtt on tb.dm_trang_thai_id = dtt.id
                     left join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id
                     left join dm_don_vi dcsyt on tb.dm_don_vi_id = dcsyt.id
                     left join dm_tinh_thanh_pho dttp on dcsyt.dm_tinh_thanh_pho_id = dttp.id
            where tb.deleted = 0 and (tb.dm_trang_thai_id = p_trang_thai_id or p_trang_thai_id is null)
              and (dcsyt.dm_tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
              and (dcsyt.dm_quan_huyen_id = p_quan_huyen_id or p_quan_huyen_id is null)
              and (dcsyt.dm_xa_phuong_id = p_xa_phuong_id or p_xa_phuong_id is null)
              and (tb.dm_don_vi_id = p_don_vi_id or p_don_vi_id is null)
            group by dtt.ten, dtb.ma, dtb.ten, tb.dm_trang_thai_id, dttp.ma, dttp.ten;
    else
        return next;
    end if;
end;
$body$
    language plpgsql stable
                     cost 100;
;
