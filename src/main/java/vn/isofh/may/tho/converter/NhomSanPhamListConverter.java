package vn.isofh.may.tho.converter;

import javax.persistence.Converter;
import vn.isofh.common.enums.EnumListConverter;
import vn.isofh.may.tho.enums.NhomSanPhamEnum;

@Converter
public class NhomSanPhamListConverter extends EnumListConverter<NhomSanPhamEnum> {

}
