/**
 * 
 */
package com.dalrada.upload.resource.beans;

/**
 * @author Kavya
 *
 * 06-Jan-2020
 */
public class UploadResponse {

	private String respCode ;
	private String respMsg;
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderIntgResponse [respCode=").append(respCode).append(", respMsg=").append(respMsg).append("]");
		return builder.toString();
	}
}