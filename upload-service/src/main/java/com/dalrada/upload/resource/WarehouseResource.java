package com.dalrada.upload.resource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dalrada.upload.intg.exception.BusinessException;
import com.dalrada.upload.intg.exception.SystemException;
import com.dalrada.upload.process.OrderProcess;
import com.dalrada.upload.process.WarehouseProcess;
import com.dalrada.upload.process.beans.OrderProcessRequest;
import com.dalrada.upload.process.beans.OrderProcessResponse;
import com.dalrada.upload.process.beans.WarehouseProcessRequest;
import com.dalrada.upload.process.beans.WarehouseProcessResponse;
import com.dalrada.upload.resource.beans.OrderResourceRequest;
import com.dalrada.upload.resource.beans.UploadResponse;
import com.dalrada.upload.resource.beans.WarehouseResourceRequest;
import com.dalrada.upload.resource.exception.InvalidRequestException;
import com.dalrada.upload.resource.requestBuilder.OrderResourceRequestBuilder;
import com.dalrada.upload.resource.requestBuilder.WarehouseResourceRequestBuilder;
import com.dalrada.upload.resource.responseBuilder.OrderResourceResponseBuilder;
import com.dalrada.upload.resource.responseBuilder.WarehouseResourceResponseBuilder;
import com.dalrada.upload.resource.validator.OrderResourceValidator;
import com.dalrada.upload.resource.validator.WarehouseResourceValidator;
import com.dalrada.upload.util.OrderConstants;
import com.dalrada.upload.util.WarehouseConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

/**
 * @author Amit
 *
 * 06-Jan-2020
 */
@RestController
@Api(value = "Warehouse File Upload Controller")
public class WarehouseResource{

	private final static Logger logger = LoggerFactory.getLogger(WarehouseResource.class);
	private final WarehouseResourceResponseBuilder warehouseRespBuilder ;
	private final WarehouseResourceRequestBuilder warehouseReqBuilder ;
	private final WarehouseResourceValidator wareHouseValidator ;
	private final WarehouseProcess warehouseProcess ;

	@Autowired
	public WarehouseResource(WarehouseResourceResponseBuilder warehouseRespBuilder, WarehouseResourceRequestBuilder warehouseReqBuilder,
			WarehouseResourceValidator wareHouseValidator, WarehouseProcess warehouseProcess) {
		super();
		this.warehouseRespBuilder = warehouseRespBuilder;
		this.warehouseReqBuilder = warehouseReqBuilder;
		this.wareHouseValidator = wareHouseValidator;
		this.warehouseProcess = warehouseProcess;
	}

	
	
	@PostMapping( "warehouse")
	@ApiOperation(value = "Warehouse Details File Upload File Service" , 
	consumes = "application/multipart", httpMethod = "POST" )
	public Mono<UploadResponse> uploadWarehouse(
			@RequestBody WarehouseResourceRequest request ) 
			throws IllegalStateException, IOException,SystemException, BusinessException, InvalidRequestException {
		logger.debug("Entering into the resource uploadWarehouse method");
		logger.info("parameters" , request);
		//wareHouseValidator.validate(request);
		Resource resource = new FileSystemResource(request.getFile().getAbsolutePath());
		WarehouseConstants.setConstants(resource , request.getId());
		WarehouseProcessRequest processRequest = warehouseReqBuilder.buildRequest(request);
		
		WarehouseProcessResponse processRespList = warehouseProcess.uploadWarehouse(processRequest);
		
		UploadResponse response = warehouseRespBuilder.buildResponse(processRespList);
		logger.debug("Exiting from the uploadWarehouse method");
		return Mono.just(response);

	}
}
