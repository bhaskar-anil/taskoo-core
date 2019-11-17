package in.taskoo.core.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.request.dto.OfferCommentRequestDto;
import in.taskoo.core.request.dto.OfferCreateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.service.OfferService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/offers")
@Validated
@RequiredArgsConstructor
class OfferController{

	private final OfferService offerService;
	
    @PostMapping
    public ResponseEntity<Boolean> createoffer(@RequestBody @Valid OfferCreateRequestDto offerCreateRequestDto){
    	return ResponseEntity.ok(offerService.create(offerCreateRequestDto));
    }
    
    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponseDto> getoffer(@PathVariable("offerId") Long offerId){
    	return ResponseEntity.ok(offerService.get(offerId));
    }
    
    @PatchMapping("/{offerId}/accept")
    public ResponseEntity<Boolean> accept(@PathVariable("offerId") Long offerId){
    	return ResponseEntity.ok(offerService.accept(offerId));
    }
    
    @PatchMapping("/{offerId}/close")
    public ResponseEntity<Boolean> close(@PathVariable("offerId") Long offerId){
    	return ResponseEntity.ok(offerService.close(offerId));
    }
    
    @PatchMapping("/{offerId}/comment")
    public ResponseEntity<Boolean> comment(@PathVariable("offerId") Long offerId,@RequestBody @Valid OfferCommentRequestDto offerCommentRequestDto ){
    	return ResponseEntity.ok(offerService.comment(offerId,offerCommentRequestDto));
    }
    
}