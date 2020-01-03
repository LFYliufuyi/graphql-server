package net.likeyun.base.enum_base;

public interface BaseEnum<E extends Enum<?>, T> {
    T getValue();
    String getDesc();
}
