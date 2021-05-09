package vn.isofh.may.tho.converter;

import javax.persistence.Converter;
import vn.isofh.common.enums.EnumConverter;
import vn.isofh.may.tho.enums.LoaiThietBiEnum;

@Converter(autoApply = true)
public class LoaiThietBiConverter extends EnumConverter<LoaiThietBiEnum> {
}