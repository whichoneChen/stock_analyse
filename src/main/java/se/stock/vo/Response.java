package se.stock.vo;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 后端处理结果返回类
 *
 * @author jh
 * @date 2020/07/02
 */

public class Response {

    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 成功：需要的数据
     * 失败：失败原因
     */
    private Object content;
    /**
     * 消息
     */
    private String msg;


    /**
     * 没有返回值的成功信息
     */
    @NotNull
    public static Response buildSuccess() {
        Response r = new Response();
        r.setSuccess(true);
        r.setContent(null);
        r.setMsg("");
        return r;
    }

    /**
     * 返回成功信息
     *
     * @param o 要返回的json数据
     */
    @NotNull
    public static Response buildSuccess(@NotNull Object o) {
        Response r = new Response();
        r.setSuccess(true);
        r.setContent(o);
        r.setMsg("");
        return r;
    }

    /**
     * 返回成功信息
     *
     * @param o 要返回的json数据
     * @param msg 要返回的消息
     */
    @NotNull
    public static Response buildSuccess(@NotNull Object o, @NotNull String msg) {
        Response r = new Response();
        r.setSuccess(true);
        r.setContent(o);
        r.setMsg(msg);
        return r;
    }

    /**
     * 返回失败消息
     *
     * @param s 错误原因/内容
     */
    @NotNull
    public static Response buildFailure(String s) {
        Response r = new Response();
        r.setSuccess(false);
        r.setMsg(s);
        r.setContent(null);
        LOGGER.info("返回错误信息:" + s);
        return r;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

