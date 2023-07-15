package com.peer.missionpeerflow.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_record")
public class UserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(columnDefinition = "varchar(20)")
    private String nickname;

    @NotNull
    @Column(columnDefinition = "varchar(30)")
    private String password;
}
