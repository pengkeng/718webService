package servlet.usercenter;

/**
 * 公共响应类
 *
 * @param <T>
 * @author pengqiancheng
 */
public class BaseResponse<T> {

    private int code;

    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 500;

    private String msg;

    private T data;

    public BaseResponse(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(CODE_SUCCESS, "success", null);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<T>(CODE_SUCCESS, message, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(CODE_SUCCESS, "success", data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<T>(CODE_SUCCESS, message, data);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>(CODE_FAIL, "fail", null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<T>(CODE_FAIL, message, null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<T>(CODE_FAIL, "fail", data);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<T>(CODE_FAIL, message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
