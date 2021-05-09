package vn.isofh.may.tho.converter;

import javax.persistence.Converter;
import vn.isofh.common.enums.EnumConverter;
import vn.isofh.may.tho.enums.DeviceEnum;
import vn.isofh.may.tho.enums.NhomSanPhamEnum;

@Converter(autoApply = true)
public class NhomSanPhamConverter extends EnumConverter<NhomSanPhamEnum> {

  @Override
  public NhomSanPhamEnum convertToEntityAttribute(Short value) {
    return super.convertToEntityAttribute(value);
  }


}