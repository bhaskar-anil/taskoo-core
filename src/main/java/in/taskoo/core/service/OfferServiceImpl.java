package in.taskoo.core.service;

import static in.taskoo.core.error.message.ApplicationErrorMessages.NO_DATA_FOUND;
import static in.taskoo.core.exception.NoDataFoundException.getException;
import static java.lang.Boolean.FALSE;

import java.time.LocalDateTime;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import in.taskoo.core.constant.OfferStatus;
import in.taskoo.core.entity.Offer;
import in.taskoo.core.entity.OfferComment;
import in.taskoo.core.entity.OfferHistory;
import in.taskoo.core.entity.Task;
import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.exception.NoDataFoundException;
import in.taskoo.core.repository.OfferCommentRespository;
import in.taskoo.core.repository.OfferHistoryRespository;
import in.taskoo.core.repository.OfferRespository;
import in.taskoo.core.repository.TaskRespository;
import in.taskoo.core.request.dto.OfferCommentRequestDto;
import in.taskoo.core.request.dto.OfferCreateRequestDto;
import in.taskoo.core.response.dto.OfferCommentResponseDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.util.DozzerUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

	private final OfferRespository offerRepository;

	private final TaskRespository taskRespository;
	
	private final OfferHistoryRespository offerHistoryRespository;
	
	private final OfferCommentRespository offerCommentRespository;
	
	private final Mapper mapper;
	
	private final DozzerUtil<OfferComment, OfferCommentResponseDto> dozzerUtil;
	
	@Override
	public Boolean create(OfferCreateRequestDto offerCreateRequestDto) {
		Task task = taskRespository.findByIdAndDeleteFlag(offerCreateRequestDto.getTaskId(),FALSE)
				.orElseThrow(()->getException(NO_DATA_FOUND));
		Offer offer = mapper.map(offerCreateRequestDto, Offer.class)
				.setOfferStatus(OfferStatus.CREATED)
				.setTask(task)
				.setOfferDateTime(LocalDateTime.now());
		Offer savedOffer = offerRepository.save(offer);
		this.saveToHistory(savedOffer);
		return Boolean.TRUE;
	}

	@Override
	public OfferResponseDto get(Long offerId) {
		Offer offer = getOffer(offerId);
		List<OfferCommentResponseDto> offerComments = dozzerUtil.mapList(offer.getOfferComments(),OfferCommentResponseDto.class);
		OfferResponseDto offerResponseDto = mapper.map(offer, OfferResponseDto.class);
		offerResponseDto.setComments(offerComments);
		return offerResponseDto;
	}

	@Override
	public Boolean accept(Long offerId) {
		Offer offer = getOffer(offerId);
		offer.setOfferStatus(OfferStatus.ACCEPTED);
		Offer savedOffer = offerRepository.save(offer);
		this.saveToHistory(savedOffer);
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean delete(Long offerId) {
		Offer offer = getOffer(offerId);
		offer.setDeleteFlag(Boolean.TRUE);
		Offer savedOffer = offerRepository.save(offer);
		this.saveToHistory(savedOffer);
		return Boolean.TRUE;
	}

	@Override
	public Boolean close(Long offerId) {
		Offer offer = getOffer(offerId);
		offer.setOfferStatus(OfferStatus.CLOSED);
		Offer savedOffer = offerRepository.save(offer);
		this.saveToHistory(savedOffer);
		return Boolean.TRUE;
	}

	@Override
	public Boolean comment(Long offerId,OfferCommentRequestDto offerCommentRequestDto) {
		Offer offer = getOffer(offerId);
		OfferComment offerComment = mapper.map(offerCommentRequestDto, OfferComment.class)
			.setCommentDateTime(LocalDateTime.now())
			.setOffer(offer);
		offerCommentRespository.save(offerComment);
		return Boolean.TRUE;
	}
	
	private void saveToHistory(Offer savedOffer) {
		OfferHistory history = mapper.map(savedOffer, OfferHistory.class);
		history.setOffer(savedOffer);
		history.setId(null);
		offerHistoryRespository.save(history);
	}
	
	private Offer getOffer(Long offerId) {
		return offerRepository.findByIdAndDeleteFlag(offerId,Boolean.FALSE)
				.orElseThrow(()->NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
	}


}
