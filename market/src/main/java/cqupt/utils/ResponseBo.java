package cqupt.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 请求返回结果
 * @author xuyi
 *
 */
public class ResponseBo {
	protected Object content;
	protected int ret ;
	protected String msg ;
	private ResponseBo() {
		
	}
	public static ResponseBo build(int ret,String msg,Object content) {
		ResponseBo bo = new ResponseBo();
		bo.setRet(ret);
		bo.setMsg(msg);
		bo.setContent(content);
		return bo ;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toJsonString() {
		JSONObject json = new JSONObject();
		json.put("ret", ret);
		json.put("msg", msg);
		json.put("content", content);
		return json.toJSONString();
	}
}
