package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
}
