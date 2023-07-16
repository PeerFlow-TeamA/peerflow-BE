package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.mapper.RequestUserRecordDTOMapper;
import com.peer.missionpeerflow.dto.request.UserRecordDTO;
import com.peer.missionpeerflow.entity.UserRecord;
import com.peer.missionpeerflow.repository.UserRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRecordService {
    private final UserRecordRepository userRecordRepository;
    private final RequestUserRecordDTOMapper requestUserRecordDTOMapper;

    public UserRecord create(UserRecordDTO userRecordDTO) {
        UserRecord saveEntity = this.requestUserRecordDTOMapper.toEntity(userRecordDTO);
        this.userRecordRepository.save(saveEntity);
        return saveEntity;
    }

    public UserRecord create(String nickname, String password){
        UserRecord userRecord = new UserRecord();
        userRecord.setNickname(nickname);
        userRecord.setPassword(password);
        this.userRecordRepository.save(userRecord);
        return userRecord;
    }
}
