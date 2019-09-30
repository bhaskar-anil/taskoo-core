package in.taskoo.core.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.request.dto.OfferCreateRequestDto;
import in.taskoo.core.request.dto.OfferUpdateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.service.OfferService;

@RestController
@RequestMapping("/offer")
class OfferController{

	@Resource
	private OfferService offerService;
	
    @PostMapping
    public ResponseEntity<Long> createoffer(@RequestBody @Valid OfferCreateRequestDto offerCreateRequestDto){
    	return ResponseEntity.ok(offerService.create(offerCreateRequestDto).getId());
    }
    @GetMapping
    public Page<OfferResponseDto> search(){
    	//TODO Add request vo and take pagable object from appcontext.
    	return null;
    }
    
    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponseDto> getoffer(@PathVariable("offerId") Long offerId){
    	return ResponseEntity.ok(offerService.get(offerId));
    }
    
    @DeleteMapping("/{offerId}")
    public ResponseEntity<Long> deleteoffer(@PathVariable("offerId") Long offerId){
    	return ResponseEntity.ok(offerService.delete(offerId).getId());
    }
    
    @PutMapping("/{offerId}")
    public ResponseEntity<Long> update(@RequestBody @Valid OfferUpdateRequestDto offerUpdateRequestDto,@PathVariable("offerId") Long offerId){
    	return ResponseEntity.ok(offerService.update(offerUpdateRequestDto,offerId).getId());
    }
    
}