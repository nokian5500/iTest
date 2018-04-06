package org.igov.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.igov.model.ehealth.address.Settlement;
import org.igov.model.ehealth.address.Street;
import org.igov.service.business.address.AddressService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = {"AddresssCommonController"})
@RequestMapping(value = "/common/address")
public class AddresssCommonController {

    private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    @Autowired
    AddressService oAddressService;

    @ApiOperation(value = "Получение списка областей", notes = "##### Пример:\n"
            + "https://delta.test.region.igov.org.ua/wf/service/common/address/getListRegions\n\n")
    @RequestMapping(value = "/getListRegions", method = RequestMethod.GET,
            produces = APPLICATION_JSON_CHARSET_UTF_8)
    public @ResponseBody
    JSONArray getListRegions() {
        return oAddressService.getListRegions();
    }

    @ApiOperation(value = "Получение списка регионов", notes = "##### Пример:\n"
            + "https://delta.test.region.igov.org.ua/wf/service/common/address/getListDistricts\n\n")
    @RequestMapping(value = "/getListDistricts", method = RequestMethod.GET,
            produces = APPLICATION_JSON_CHARSET_UTF_8)
    public @ResponseBody
    List<Settlement> getListDistricts(
            @ApiParam(value = "назва області", required = true) @RequestParam(value = "sRegion", required = true) String sRegion) {
        return oAddressService.getListDistricts(sRegion);
    }
    
    @ApiOperation(value = "Получение списка областей", notes = "##### Пример:\n"
            + "https://delta.test.region.igov.org.ua/wf/service/common/address/getListSettlements\n\n")
    @RequestMapping(value = "/getListSettlements", method = RequestMethod.GET,
            produces = APPLICATION_JSON_CHARSET_UTF_8)
    public @ResponseBody
    List<Settlement> getListSettlements(
            @ApiParam(value = "назва області", required = true) @RequestParam(value = "sRegion", required = true) String sRegion,
            @ApiParam(value = "назва району", required = false) @RequestParam(value = "sDistrict", required = false) String sDistrict,
            @ApiParam(value = "тип населеного пункта", required = true) @RequestParam(value = "sType", required = true) String sType,
            @ApiParam(value = "фільтр по назві населенного пункту", required = false) @RequestParam(value = "sNameFilter", required = false) String sNameFilter) {
        return oAddressService.getListSettlements(sRegion, sDistrict, sType, sNameFilter);
    }
    
    @ApiOperation(value = "Получение списка городов", notes = "##### Пример:\n"
            + "https://delta.test.region.igov.org.ua/wf/service/common/address/getListStreets\n\n")
    @RequestMapping(value = "/getListStreets", method = RequestMethod.GET,
            produces = APPLICATION_JSON_CHARSET_UTF_8)
    public @ResponseBody
    List<Street> getListStreets(
            @ApiParam(value = "ИД населеного пункта", required = true) @RequestParam(value = "sID_Settlement", required = true) String sID_Settlement,
            @ApiParam(value = "тип вулиці", required = false) @RequestParam(value = "sType", required = false) String sType,
            @ApiParam(value = "фільтр по назві вулиці", required = true) @RequestParam(value = "sNameFilter", required = true) String sNameFilter) {
        return oAddressService.getListStreets(sID_Settlement, sType, sNameFilter);
    }
}
