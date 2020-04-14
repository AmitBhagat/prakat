package com.dalrada.user.resource.responseBuilder;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dalrada.user.process.beans.RoleProcessResponse;
import com.dalrada.user.process.beans.UserProcessResponse;
import com.dalrada.user.resource.beans.RoleResourceResponse;
import com.dalrada.user.resource.beans.RoleResourceResponseBody;
import com.dalrada.user.resource.beans.UserResourceResponse;
import com.dalrada.user.resource.beans.UserResourceResponseBody;

@Component
public class ResourceResponseBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ResourceResponseBuilder.class);
	
	public UserResourceResponse buildResponse(UserProcessResponse intgResponse) {
		logger.debug("enter into buildResponse method");
		UserResourceResponse resourceResponse = new UserResourceResponse();
		resourceResponse.setResponseCode(intgResponse.getResponseCode());
		resourceResponse.setResponseMsg(intgResponse.getResponseMsg());
		UserResourceResponseBody responseBody = new UserResourceResponseBody();
		responseBody.setCreatedBy(intgResponse.getRespBody().getCreatedBy());
		responseBody.setCreatedDate(intgResponse.getRespBody().getCreatedDate());
		responseBody.setStatus(intgResponse.getRespBody().getStatus());
		responseBody.setUserEmail(intgResponse.getRespBody().getUserEmail());
		responseBody.setUserId(intgResponse.getRespBody().getUserId());
		responseBody.setUserName(intgResponse.getRespBody().getUserName());
		responseBody.setUserPassword(intgResponse.getRespBody().getUserPassword());	
		resourceResponse.setRespBody(responseBody);
		logger.debug("exit from buildResponse method");
		return resourceResponse;
	}
	
	public List<UserResourceResponse> userBuildResponse(List<UserProcessResponse> intgRespList) {
		logger.debug("enter into buildResponse method");
		List<UserResourceResponse> processRespList = new ArrayList<UserResourceResponse>();
		intgRespList.forEach(intgResponse ->{
			UserResourceResponse resourceResponse = new UserResourceResponse();
			resourceResponse.setResponseCode(intgResponse.getResponseCode());
			resourceResponse.setResponseMsg(intgResponse.getResponseMsg());
			UserResourceResponseBody responseBody = new UserResourceResponseBody();
			responseBody.setCreatedBy(intgResponse.getRespBody().getCreatedBy());
			responseBody.setCreatedDate(intgResponse.getRespBody().getCreatedDate());
			responseBody.setStatus(intgResponse.getRespBody().getStatus());
			responseBody.setUserEmail(intgResponse.getRespBody().getUserEmail());
			responseBody.setUserId(intgResponse.getRespBody().getUserId());
			responseBody.setUserName(intgResponse.getRespBody().getUserName());
			responseBody.setUserPassword(intgResponse.getRespBody().getUserPassword());	
			resourceResponse.setRespBody(responseBody);
			processRespList.add(resourceResponse);
		});
		logger.debug("exit from buildResponse method");
		return processRespList;
	}
	
	public RoleResourceResponse buildResponse(RoleProcessResponse processResponse) {
		logger.debug("enter into buildResponse method");
		RoleResourceResponse roleResourceResponse = new RoleResourceResponse();
		roleResourceResponse.setResponseCode(processResponse.getResponseCode());
		roleResourceResponse.setResponseMsg(processResponse.getResponseMsg());
		RoleResourceResponseBody responseBody = new RoleResourceResponseBody();
		responseBody.setCreatedBy(processResponse.getRespBody().getCreatedBy());
		responseBody.setCreatedDate(processResponse.getRespBody().getCreatedDate());
		responseBody.setStatus(processResponse.getRespBody().getStatus());
		responseBody.setRoleId(processResponse.getRespBody().getRoleId());
		responseBody.setRoleName(processResponse.getRespBody().getRoleName());
		roleResourceResponse.setRespBody(responseBody);
		logger.debug("exit from buildResponse method");
		return roleResourceResponse;
	}
	
	public List<RoleResourceResponse> roleBuildResponse(List<RoleProcessResponse> processResponseListRespList) {
		logger.debug("enter into buildResponse method");
		List<RoleResourceResponse> processRespList = new ArrayList<RoleResourceResponse>();
		processResponseListRespList.forEach(processResponse ->{
			RoleResourceResponse roleResourceResponse = new RoleResourceResponse();
			roleResourceResponse.setResponseCode(processResponse.getResponseCode());
			roleResourceResponse.setResponseMsg(processResponse.getResponseMsg());
			RoleResourceResponseBody responseBody = new RoleResourceResponseBody();
			responseBody.setCreatedBy(processResponse.getRespBody().getCreatedBy());
			responseBody.setCreatedDate(processResponse.getRespBody().getCreatedDate());
			responseBody.setStatus(processResponse.getRespBody().getStatus());
			responseBody.setRoleId(processResponse.getRespBody().getRoleId());
			responseBody.setRoleName(processResponse.getRespBody().getRoleName());
			roleResourceResponse.setRespBody(responseBody);
			processRespList.add(roleResourceResponse);
		});
		logger.debug("exit from buildResponse method");
		return processRespList;
	}
	
}
