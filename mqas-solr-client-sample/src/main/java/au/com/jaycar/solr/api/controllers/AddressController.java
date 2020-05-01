package au.com.jaycar.solr.api.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import au.com.jaycar.solr.domain.AddressInfo;
import au.com.jaycar.solr.repos.AddressInfoRepo;

@RestController
public class AddressController {

    private AddressInfoRepo addressInfoRepo;
    
    private Pageable pageable;
    
    @GetMapping("/api/getAddress/{terms}")
    public List<AddressInfo> findAddress(@PathVariable(name = "terms")  String... terms) {
	
	Stream<String> queryStream = Stream.of(terms);
	
	String queryText = queryStream.map(s -> s.concat("*")).collect(Collectors.joining(" "));
	
	Page<AddressInfo> page = addressInfoRepo.getAddressInfo(queryText, getPageable());
	
	return page.toList();
	
    }
    
    @GetMapping("/api/getAddress1/{query}")
    public List<AddressInfo> findAddress1(@PathVariable(name = "query")  String queryText) {
	
	Page<AddressInfo> page = addressInfoRepo.getAddressInfo1(queryText, getPageable());
	
	return page.toList();
	
    }

    public AddressInfoRepo getAddressInfoRepo() {
	return addressInfoRepo;
    }

    @Autowired
    public void setAddressInfoRepo(AddressInfoRepo addressInfoRepo) {
	this.addressInfoRepo = addressInfoRepo;
    }

    public Pageable getPageable() {
	return pageable;
    }

    @Autowired
    public void setPageable(Pageable pageable) {
	this.pageable = pageable;
    }
    
}
