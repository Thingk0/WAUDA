package com.thingk0.wauda.repository.party;

import com.thingk0.wauda.dto.party.PartyListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomPartyRepository {

    Page<PartyListDto> partyListBySearchCond(String searchCond, Pageable pageable);
}
