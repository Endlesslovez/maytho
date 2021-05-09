drop function if exists danh_sach_thiet_bi(bigint, bigint, bigint, bigint, bigint);

create or replace function danh_sach_thiet_bi(p_trang_thai_id bigint,
                                              p_tinh_thanh_pho_id bigint,
                                              p_quan_huyen_id bigint,
                                              p_xa_phuong_id bigint,
                                              p_don_vi_id bigint)
    returns table
            (
                id                bigint,
                ma_thiet_bi       character varying,
                ten_thiet_bi      character varying,
                ten_thuong_mai    character varying,
                loai_thiet_bi_id  bigint,
                loai_thiet_bi_ten character varying,
                serial            character varying,
                model             character varying,
                hang_san_xuat     character varying,
                nam_san_xuat      int,
                nam_su_dung       int,
                don_vi            character varying,
                don_vi_id         bigint,
                don_vi_su_dung    character varying,
                trang_thai_id     bigint,
                trang_thai        character varying,
                tinh_thanh_pho_id bigint,
                tinh_thanh_pho    character varying,
                quan_huyen_id     bigint,
                quan_huyen        character varying,
                xa_phuong_id      bigint,
                xa_phuong         character varying,
                ghi_chu           character varying
            )
as
$body$
begin

    return query
        select tb.id,
               tb.ma     ma_thiet_bi,
               dtb.ten   ten_thiet_bi,
               dtb.ten_thuong_mai,
               dltb.id   loai_thiet_bi_id,
               dltb.ten  loai_thiet_bi_ten,
               tb.serial,
               tb.model,
               dhsx.ten  hang_san_xuat,
               tb.nam_san_xuat,
               tb.nam_su_dung,
               dcsyt.ten don_vi,
               dcsyt.id  don_vi_id,
               tb.don_vi_su_dung,
               dtt.id    trang_thai_id,
               dtt.ten   trang_thai,
               dttp.id   tinh_thanh_pho_id,
               dttp.ten  tinh_thanh_pho,
               dqh.id    quan_huyen_id,
               dqh.ten   quan_huyen,
               dxp.id    xa_phuong_id,
               dxp.ten   xa_phuong,
               tb.ghi_chu
        from thiet_bi tb
                 left join dm_don_vi dcsyt on tb.dm_don_vi_id = dcsyt.id
                 left join dm_thiet_bi dtb on tb.dm_thiet_bi_id = dtb.id
                 left join dm_loai_thiet_bi dltb on tb.dm_loai_thiet_bi_id = dltb.id
                 left join dm_hang_san_xuat dhsx on tb.dm_hang_san_xuat_id = dhsx.id
                 left join dm_trang_thai dtt on tb.dm_trang_thai_id = dtt.id
                 left join dm_tinh_thanh_pho dttp on dcsyt.dm_tinh_thanh_pho_id = dttp.id
                 left join dm_quan_huyen dqh on dcsyt.dm_quan_huyen_id = dqh.id
                 left join dm_xa_phuong dxp on dcsyt.dm_xa_phuong_id = dxp.id
        where tb.deleted = 0
          and (tb.dm_trang_thai_id = p_trang_thai_id or p_trang_thai_id is null)
          and (dcsyt.dm_tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
          and (dcsyt.dm_quan_huyen_id = p_quan_huyen_id or p_quan_huyen_id is null)
          and (dcsyt.dm_xa_phuong_id = p_xa_phuong_id or p_xa_phuong_id is null)
          and (tb.dm_don_vi_id = p_don_vi_id or p_don_vi_id is null);
end;
$body$
    language plpgsql stable
                     cost 100
;
