package com.dalrada.upload.resource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dalrada.upload.intg.exception.SystemException;
import com.dalrada.upload.process.OrderProcess;
import com.dalrada.upload.process.beans.OrderProcessRequest;
import com.dalrada.upload.process.beans.OrderProcessResponse;
import com.dalrada.upload.resource.beans.OrderResourceRequest;
import com.dalrada.upload.resource.beans.UploadResponse;
import com.dalrada.upload.resource.requestBuilder.OrderResourceRequestBuilder;
import com.dalrada.upload.resource.responseBuilder.OrderResourceResponseBuilder;
import com.dalrada.upload.resource.validator.OrderResourceValidator;
import com.dalrada.upload.util.OrderConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

/**
 * @author Amit
 *
 * 06-Jan-2020
 */
@RestController
@Api(value = "Order File Upload Controller")
public class OrderResource{

	private final static Logger logger = LoggerFactory.getLogger(OrderResource.class);
	private final OrderResourceResponseBuilder orderRespBuilder ;
	private final OrderResourceRequestBuilder orderReqBuilder ;
	private final OrderResourceValidator orderValidator ;
	private final OrderProcess orderProcess ;

	@Autowired
	public OrderResource(OrderResourceResponseBuilder orderRespBuilder, OrderResourceRequestBuilder orderReqBuilder,
			OrderResourceValidator orderValidator, OrderProcess orderProcess) {
		super();
		this.orderRespBuilder = orderRespBuilder;
		this.orderReqBuilder = orderReqBuilder;
		this.orderValidator = orderValidator;
		this.orderProcess = orderProcess;
	}

	@PostMapping("orders")
	@ApiOperation(value = "Order Details File Upload Service" , 
	consumes = "application/multipart", httpMethod = "POST" )
	//@PostMapping
	public Mono<UploadResponse> uploadOrder(@RequestBody OrderResourceRequest request ) throws IllegalStateException, IOException, SystemException {
//	public Mono<UploadResponse> uploadOrder() throws SystemException, IllegalStateException, IOException{	

		Resource resource = new FileSystemResource(request.getFile().getAbsolutePath());
		OrderConstants.setResource(resource);
		OrderConstants.setToDate(request.getStartDate());
		OrderConstants.setFromDate(request.getLastDate());
		logger.debug("Entering into the uploadOrder method");
		logger.info("parameters" , request);
		orderValidator.validate(request);


		OrderProcessRequest processRequest = orderReqBuilder.buildRequest(request);
		OrderProcessResponse processResp = orderProcess.uploadOrder(processRequest);
		UploadResponse response = orderRespBuilder.buildResponse(processResp);

		logger.debug("Exiting from the uploadOrder method");
		return Mono.just(response);

	}
	
}
