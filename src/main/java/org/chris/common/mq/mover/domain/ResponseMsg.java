package org.chris.common.mq.mover.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author caizq
 * @date 2018/4/26
 * @since v1.0.0
 */
@Data
public class ResponseMsg<T> implements Serializable {


    /**
     * 响应标识-success
     */
    public static final String RESPONSE_FLAG_SUCCESS = "success";

    /**
     * 响应标识-failure
     */
    public static final String RESPONSE_FLAG_FAILURE = "fail";
    public static final String VALIDA_EMPTY = "VALIDA_ERROR";
    public static final String UNKNOW_ERROR = "UNKNOW_ERROR";
    public static final String SYS_INNER_ERROR = "System inner error";
    private static final long serialVersionUID = -5596930346535993316L;
    /**
     * 请求结果标识
     */
    private String flag;

    /**
     * 请求结果标识
     */
    private String returnCode;

    /**
     * 返回的信息
     */
    private String message;

    /**
     * 返回集合对象信息
     */
    private T obj;

    private ResponseMsg(String flag) {
        this.flag = flag;
    }

    public static ResponseMsg buildSuccessMsg() {
        return new ResponseMsgBuilder(RESPONSE_FLAG_SUCCESS).withMessage(RESPONSE_FLAG_SUCCESS).withReturnCode(RESPONSE_FLAG_SUCCESS).build();
    }
    public static <T>ResponseMsg buildSuccessMsg(T obj) {
        return new ResponseMsgBuilder(RESPONSE_FLAG_SUCCESS).withMessage(RESPONSE_FLAG_SUCCESS).withReturnCode(RESPONSE_FLAG_SUCCESS).withObj(obj).build();
    }


    public static class ResponseMsgBuilder<T> {
        private String flag;
        private String returnCode;
        private String message;
        private T obj;

        private ResponseMsgBuilder(String flag) {
            this.flag = flag;
        }

        public static ResponseMsgBuilder aResponseMsg(String flag) {
            return new ResponseMsgBuilder(flag);
        }

        public ResponseMsgBuilder withReturnCode(String returnCode) {
            this.returnCode = returnCode;
            return this;
        }

        public ResponseMsgBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseMsgBuilder withObj(T obj) {
            this.obj = obj;
            return this;
        }

        public ResponseMsg build() {
            ResponseMsg responseMsg = new ResponseMsg(flag);
            responseMsg.setReturnCode(returnCode);
            responseMsg.setMessage(message);
            responseMsg.setObj(obj);
            return responseMsg;
        }
    }
}
