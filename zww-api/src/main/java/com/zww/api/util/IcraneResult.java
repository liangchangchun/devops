package com.zww.api.util;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zww.common.Enviroment;

public class IcraneResult implements Serializable{

    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    private Boolean success;
    
    private String statusCode;

    private String message;

    private Object resultData;

    public static IcraneResult build(Boolean success,String statusCode, String message, Object resultData) {
        return new IcraneResult(success,statusCode, message, resultData);
    }

    public static IcraneResult ok(Object resultData) {
        return new IcraneResult(resultData);
    }

    public static IcraneResult ok() {
        return new IcraneResult("");
    }

    public IcraneResult() {

    }

    public static IcraneResult build(Boolean success,String statusCode, String message) {
        return new IcraneResult(success,statusCode, message, "");
    }

    public IcraneResult(Boolean success,String statusCode, String message, Object resultData) {
    	this.success=success;
        this.statusCode = statusCode;
        this.message = message;
        this.resultData = resultData;
    }

    public IcraneResult(Object resultData) {
    	this.success=Enviroment.RETURN_SUCCESS;
        this.statusCode = Enviroment.RETURN_SUCCESS_CODE;
        this.message = Enviroment.RETURN_SUCCESS_MESSAGE;
        this.resultData = resultData;
    }

//    public Boolean isOK() {
//        return this.statusCode == 200;
//    }


    public Boolean getSuccess() {
		return success;
	}
    
    public void setSuccess(Boolean success) {
		this.success = success;
	}
    
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

	public static IcraneResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, IcraneResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("resultData");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("success").asBoolean(),jsonNode.get("statusCode").asText(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static IcraneResult format(String json) {
        try {
            return MAPPER.readValue(json, IcraneResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IcraneResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("resultData");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("success").asBoolean(),jsonNode.get("statusCode").asText(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
