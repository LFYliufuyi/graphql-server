package net.likeyun.base.error_result;

import lombok.Setter;
import net.likeyun.base.enum_base.BaseEnum;

public enum GraphQLErrorResult implements BaseEnum {

    /* graphql错误：10001-19999 */
    GRAPHQL_NULL_ERROR(10001, "不允许为null错误"),
    GRAPHQL_PARSE_ERROR(10002, "序列化Scalar时错误"),
    GRAPHQL_DEFINES_FIELDS_ERROR(10003, "未在Schema中定义的field错误"),
    GRAPHQL_VALIDATION_ERROR(10004, "请求的field错误"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录，请先登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
//    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_HAS_EXISTED(20004, "用户已存在"),
    USER_FORMAT_ERROR(20005, "用户名字格式错误"),

    /* 业务错误：30001-39999 */
//    BUSINESS_GRADE_FORMAT_ERROR(30001, "班级名字格式化出错"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    REDIS_ERROR(40002, "redis出错"),

    /* 数据错误：50001-599999 */
//    RESULT_DATA_NONE(50001, "数据未找到"),
//    DATA_IS_WRONG(50002, "数据有误"),
//    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
//    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
//    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
//    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
//    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
//    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
//    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
//    PERMISSION_UNAUTHENTICATED(70001, "此操作需要登陆系统！"),
    PERMISSION_UNAUTHORISE(70002, "权限不足，无权操作！"),
    PERMISSION_EXPIRE(70003, "登录状态过期！"),
    PERMISSION_TOKEN_EXPIRED(70004, "token已过期"),
//    PERMISSION_LIMIT(70005, "访问次数受限制"),
    PERMISSION_TOKEN_INVALID(70006, "无效token"),
    PERMISSION_SIGNATURE_ERROR(70007, "签名失败"),

    ;



    GraphQLErrorResult(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Setter
    Integer value;
    @Setter
    String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
