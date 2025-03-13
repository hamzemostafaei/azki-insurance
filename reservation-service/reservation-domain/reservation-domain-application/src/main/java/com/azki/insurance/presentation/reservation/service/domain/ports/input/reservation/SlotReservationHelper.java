package com.azki.insurance.presentation.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.presentation.common.app.config.CommonConfigData;
import com.azki.insurance.presentation.common.core.data.ErrorCodeEnum;
import com.azki.insurance.presentation.common.core.data.ErrorDTO;
import com.azki.insurance.presentation.common.utility.SnowFlakeUniqueIDGenerator;
import com.azki.insurance.presentation.domain.api.dto.UserDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.command.ReserveSlotCommand;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.ReservedSlotsDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.search.AvailableSlotsCriteriaDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.search.UserCriteriaDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.exception.ReservationDomainException;
import com.azki.insurance.presentation.reservation.service.domain.ports.output.AvailableSlotsRepository;
import com.azki.insurance.presentation.reservation.service.domain.ports.output.ReservedSlotsRepository;
import com.azki.insurance.presentation.reservation.service.domain.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlotReservationHelper {

    private final CommonConfigData commonConfigData;
    private final UserRepository userRepository;
    private final ReservedSlotsRepository reservedSlotsRepository;
    private final AvailableSlotsRepository availableSlotsRepository;

    public UserDTO validateAndGetUser(String username) {
        UserCriteriaDTO userNameCriteria = new UserCriteriaDTO();
        userNameCriteria.setUsernameEquals(username);

        UserDTO user = userRepository.getSingleResult(userNameCriteria);
        if (user == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    String.format("User with username [%s] not found.", username),
                    "User"
            )));
        }

        return user;
    }

    public AvailableSlotsDTO validateAndGetAvailableSlot(ReserveSlotCommand command) {
        AvailableSlotsDTO availableSlot = availableSlotsRepository.findById(command.getSlotId());
        if (availableSlot == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    String.format("Slot with id [%s] not found.", command.getSlotId()),
                    "Slot"
            )));
        }

        if (availableSlot.getIsReserved()) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.INCONSISTENT_DATA,
                    String.format("Slot with id [%s] already reserved.", command.getSlotId()),
                    "ReservedSlot"
            )));
        }
        return availableSlot;
    }

    public AvailableSlotsDTO validateAndGetReservedSlot(Long slotId) {

        AvailableSlotsCriteriaDTO criteria = new AvailableSlotsCriteriaDTO();
        criteria.setSlotIdEquals(slotId);
        criteria.setIsReserved(true);

        AvailableSlotsDTO reservedSlot = availableSlotsRepository.getSingleResult(criteria);

        if (reservedSlot == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    String.format("Slot with id [%s] not found.", slotId),
                    "ReservedSlot"
            )));
        }

        return reservedSlot;
    }

    public List<AvailableSlotsDTO> validateAndGetAvailableSlots(List<Long> slotIds) {

        List<AvailableSlotsDTO> availableSlots = availableSlotsRepository.findAllById(slotIds);

        if (availableSlots == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    String.format("Slot with ids [%s] not found.", slotIds),
                    "Slot"
            )));
        }

        List<ErrorDTO> errors = new ArrayList<>(availableSlots.size());

        availableSlots.forEach(slot -> {
            if (slot.getIsReserved()) {
                errors.add(new ErrorDTO(
                        ErrorCodeEnum.INCONSISTENT_DATA,
                        String.format("Slot with id [%s] already reserved.", slot.getId()),
                        "ReservedSlot"
                ));
            }
        });

        if (!CollectionUtils.isEmpty(errors)) {
            throw new ReservationDomainException(errors);
        }


        return availableSlots;
    }

    public AvailableSlotsDTO reserveSlot(UserDTO user, AvailableSlotsDTO availableSlot) {
        long id = SnowFlakeUniqueIDGenerator.nextId(commonConfigData.nodeId());

        ReservedSlotsDTO reservedSlot = ReservedSlotsDTO.builder()
                .id(id)
                .userId(user.getUserId())
                .availableSlotId(availableSlot.getId())
                .build();

        reservedSlotsRepository.save(reservedSlot);

        availableSlot.setIsReserved(true);
        return availableSlotsRepository.save(availableSlot);
    }

    public void reserveSlots(UserDTO user, List<AvailableSlotsDTO> availableSlots) {

        List<ReservedSlotsDTO> reservedSlots =
                availableSlots.stream()
                        .peek(availableSlot -> availableSlot.setIsReserved(true))
                        .map(availableSlot -> {
                            ReservedSlotsDTO reservedSlot = new ReservedSlotsDTO();
                            reservedSlot.setId(SnowFlakeUniqueIDGenerator.nextId(commonConfigData.nodeId()));
                            reservedSlot.setUserId(user.getUserId());
                            reservedSlot.setAvailableSlotId(availableSlot.getId());
                            return reservedSlot;
                        })
                        .toList();

        reservedSlotsRepository.saveAll(reservedSlots);
        availableSlotsRepository.saveAll(availableSlots);
    }

    public AvailableSlotsDTO getNearestAvailableSlot(Date startTime) {

        AvailableSlotsDTO nearestAvailableSlot = availableSlotsRepository.findNearestAvailableSlot(startTime);

        if (nearestAvailableSlot == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    "No available slots exist at this time.",
                    "NearestAvailableSlot")
            ));
        }

        return nearestAvailableSlot;

    }
}
