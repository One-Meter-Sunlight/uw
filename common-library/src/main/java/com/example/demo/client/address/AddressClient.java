package com.example.demo.client.address;

import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.dto.address.AddressDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "address-service", fallbackFactory = AddressClientFallbackFactory.class)
public interface AddressClient {

    @RequestMapping(value = "/addresses/{address_id}", method = RequestMethod.GET)
    CustomResponseBody<AddressDto> findAddressById(@PathVariable("address_id") String _addressId);

    @RequestMapping(value = "/addresses/default", method = RequestMethod.GET)
    CustomResponseBody<AddressDto> findDefaultAddress();

}
