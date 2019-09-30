package in.taskoo.core.service;

import javax.validation.Valid;

import in.taskoo.core.entity.Offer;
import in.taskoo.core.request.dto.OfferCreateRequestDto;
import in.taskoo.core.request.dto.OfferUpdateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;

public interface OfferService {

	Offer create(OfferCreateRequestDto offerCreateRequestDto);

	OfferResponseDto get(Long offerId);

	Offer delete(Long offerId);

	Offer update(@Valid OfferUpdateRequestDto offerUpdateRequestDto, Long offerId);

}
