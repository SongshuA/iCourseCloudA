package util;

import org.json.JSONObject;

public class JSONResponse extends JSONObject {
    boolean result;
    String message;

    public JSONResponse(boolean result){
        super();
        this.put("result", result);
        this.result = result;
    }

    public JSONResponse(boolean result, String message){
        super();
        this.put("result", result);
        this.put("message", message);
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
