package au.com.jaycar.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.jaycar.client.dto.AddressInfo;

@RestController
public class ProxyController {

    @Autowired
    private OAuth2RestTemplate restTemplate;
    
    @GetMapping("/proxy/addressInfo")
    public List<AddressInfo> getAddressInfo(){
	List<AddressInfo> infos = restTemplate.getForObject("http://127.0.0.1:7859/resource/api/getAddress/55,gym", List.class);
	return infos;
    }
    
}
