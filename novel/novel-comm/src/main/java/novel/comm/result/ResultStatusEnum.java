package novel.comm.result;

import org.apache.commons.lang3.builder.ToStringBuilder;


public enum ResultStatusEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    WEB_NAME_REPEAT_ERROR(2, "网站名称重复"),
    GROOVY_SCRIPT_ERROR(3, "解析器脚本内容错误"),
    NOT_LEAF_NODE_ERROR(4, "非叶子节点"),
    OBJECT_NOT_EXIST(5, "对象不存在");
    private int code;
    private String msg;

    ResultStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

}
