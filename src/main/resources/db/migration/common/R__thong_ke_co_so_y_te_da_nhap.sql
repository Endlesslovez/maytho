drop function if exists thong_ke_co_so_y_te_da_nhap(bigint, bigint);

create or replace function thong_ke_co_so_y_te_da_nhap(p_tinh_thanh_pho_id bigint,
                                                             p_co_so_y_te_id bigint)
    returns table
            (
                id                bigint,
                da_nhap           bigint,
                chua_nhap         bigint,
                tinh_thanh_pho_id bigint,
                ma_tinh           character varying,
                ten_tinh          character varying
            )
as
$body$
begin
    return query
        select csyt.tinh_thanh_pho_id                                   id,
               sum(case csyt.da_nhap when true then 1 else 0 end)  da_nhap,
               sum(case csyt.da_nhap when false then 1 else 0 end) chua_nhap,
               csyt.tinh_thanh_pho_id,
               csyt.ma_tinh,
               csyt.ten_tinh
        from (select ddv.id               co_so_y_te_id,
                     ddv.ma               ma_co_so_y_te,
                     ddv.ten              ten_co_so_y_te,
                     dttp.id              tinh_thanh_pho_id,
                     dttp.ma              ma_tinh,
                     dttp.ten             ten_tinh,
                     (case
                          when exists(select 1 from thiet_bi where dm_don_vi_id = ddv.id and deleted = 0) then true
                          else false end) da_nhap
              from dm_don_vi ddv
                       inner join dm_tinh_thanh_pho dttp on ddv.dm_tinh_thanh_pho_id = dttp.id and dttp.deleted = 0
              where ddv.deleted = 0
                and ddv.loai_don_vi = 10
             ) csyt
        where (csyt.tinh_thanh_pho_id = p_tinh_thanh_pho_id or p_tinh_thanh_pho_id is null)
          and (co_so_y_te_id = p_co_so_y_te_id or p_co_so_y_te_id is null)
        group by csyt.tinh_thanh_pho_id, csyt.ma_tinh, csyt.ten_tinh;

end;
$body$
    language plpgsql stable
                     cost 100;
;
