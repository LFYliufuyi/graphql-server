package net.likeyun.user.enum_;

import lombok.Setter;
import net.likeyun.base.enum_base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

public enum SexEnum implements BaseEnum<SexEnum, Integer> {
    MAN(1,"男"),
    WOMAN(0,"女"),
    ;

    static Map<Integer, SexEnum> enumMap=new HashMap<>();
    static{
        for(SexEnum e: SexEnum.values()){
            enumMap.put(e.getValue(), e);
        }
    }

    SexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Setter
    private Integer value;
    @Setter
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static SexEnum getEnum(Integer value) {
        return enumMap.get(value);
    }
}
