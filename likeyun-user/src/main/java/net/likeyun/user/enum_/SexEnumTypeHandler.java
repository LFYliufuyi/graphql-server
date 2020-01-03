package net.likeyun.user.enum_;

import net.likeyun.base.enum_base.BaseEnum;
import net.likeyun.base.enum_base.BaseEnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2019/12/6 11:30
 */
@MappedTypes({SexEnum.class})
public class SexEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E> {
    public SexEnumTypeHandler(Class<E> type) {
        super(type);
    }
}
