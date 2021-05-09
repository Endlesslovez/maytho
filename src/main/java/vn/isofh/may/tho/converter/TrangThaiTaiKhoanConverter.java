package vn.isofh.may.tho.converter;

import javax.persistence.Converter;
import vn.isofh.common.enums.EnumConverter;
import vn.isofh.may.tho.enums.TrangThaiTaiKhoanEnum;

@Converter(autoApply = true)
public class TrangThaiTaiKhoanConverter extends EnumConverter<TrangThaiTaiKhoanEnum> {
}