/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.diviso.graeshoppe.order.client.store.api;

import com.diviso.graeshoppe.order.client.store.model.Banner;
import com.diviso.graeshoppe.order.client.store.model.BannerDTO;
import java.util.List;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-30T10:34:49.769480+05:30[Asia/Kolkata]")

@Api(value = "BannerResource", description = "the BannerResource API")
public interface BannerResourceApi {

    @ApiOperation(value = "createBanner", nickname = "createBannerUsingPOST", notes = "", response = BannerDTO.class, tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BannerDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/banners",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<BannerDTO> createBannerUsingPOST(@ApiParam(value = "bannerDTO" ,required=true )  @Valid @RequestBody BannerDTO bannerDTO);


    @ApiOperation(value = "deleteBanner", nickname = "deleteBannerUsingDELETE", notes = "", tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/banners/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteBannerUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllBanners", nickname = "getAllBannersUsingGET", notes = "", response = BannerDTO.class, responseContainer = "List", tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BannerDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/banners",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<BannerDTO>> getAllBannersUsingGET(@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getBanner", nickname = "getBannerUsingGET", notes = "", response = BannerDTO.class, tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BannerDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/banners/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<BannerDTO> getBannerUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "listToDto", nickname = "listToDtoUsingPOST", notes = "", response = BannerDTO.class, responseContainer = "List", tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BannerDTO.class, responseContainer = "List"),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/banners/toDto",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<List<BannerDTO>> listToDtoUsingPOST(@ApiParam(value = "banners" ,required=true )  @Valid @RequestBody List<Banner> banner);


    @ApiOperation(value = "searchBanners", nickname = "searchBannersUsingGET", notes = "", response = BannerDTO.class, responseContainer = "List", tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BannerDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/banners",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<BannerDTO>> searchBannersUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "updateBanner", nickname = "updateBannerUsingPUT", notes = "", response = BannerDTO.class, tags={ "banner-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = BannerDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/banners",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<BannerDTO> updateBannerUsingPUT(@ApiParam(value = "bannerDTO" ,required=true )  @Valid @RequestBody BannerDTO bannerDTO);

}
