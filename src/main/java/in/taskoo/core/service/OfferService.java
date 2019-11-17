package in.taskoo.core.service;

import in.taskoo.core.request.dto.OfferCommentRequestDto;
import in.taskoo.core.request.dto.OfferCreateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;

public interface OfferService {
	Boolean create(OfferCreateRequestDto offerCreateRequestDto);
	OfferResponseDto get(Long offerId);
	Boolean delete(Long offerId);
	Boolean close(Long offerId);
	Boolean comment(Long offerId,OfferCommentRequestDto offerCommentRequestDto);
	Boolean accept(Long offerId);
}
