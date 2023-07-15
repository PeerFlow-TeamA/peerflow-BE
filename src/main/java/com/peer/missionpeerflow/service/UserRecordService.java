package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.RequestUserRecordDTOMapper;
import com.peer.missionpeerflow.dto.request.UserRecordDTO;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.repository.UserRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRecordService {
    private final UserRecordRepository userRecordRepository;
    private final RequestUserRecordDTOMapper requestUserRecordDTOMapper;

    @Transactional
    public UserRecord create(UserRecordDTO userRecordDTO) {
        UserRecord saveEntity = this.requestUserRecordDTOMapper.toEntity(userRecordDTO);
        this.userRecordRepository.save(saveEntity);
        return saveEntity;
    }
}
