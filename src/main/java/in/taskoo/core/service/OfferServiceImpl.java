package in.taskoo.core.service;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.taskoo.core.constant.OfferStatus;
import in.taskoo.core.entity.Offer;
import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.exception.NoDataFoundException;
import in.taskoo.core.repository.OfferRespository;
import in.taskoo.core.request.dto.OfferCreateRequestDto;
import in.taskoo.core.request.dto.OfferUpdateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRespository offerRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Override
	public Offer create(OfferCreateRequestDto offerCreateRequestDto) {
		Offer offer = mapper.map(offerCreateRequestDto, Offer.class);
		offer.setOfferStatus(OfferStatus.INITIATED);
		return offerRepository.save(offer);
	}

	@Override
	public OfferResponseDto get(Long offerId) {
		Offer offer = offerRepository.findByIdAndDeleteFlag(offerId,Boolean.FALSE).orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
		return mapper.map(offer, OfferResponseDto.class);
	}

	@Override
	public Offer delete(Long offerId) {
		Offer offer = offerRepository.findByIdAndDeleteFlag(offerId,Boolean.FALSE).orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
		offer.setDeleteFlag(Boolean.TRUE);
		return offerRepository.save(offer);
	}

	@Override
	public Offer update(@Valid OfferUpdateRequestDto offerUpdateRequestDto, Long offerId) {
		Offer offer = offerRepository.findByIdAndDeleteFlag(offerId,Boolean.FALSE).orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
		mapper.map(offerUpdateRequestDto, offer);
		return offerRepository.save(offer);
	}

}
