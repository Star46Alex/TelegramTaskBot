package ru.alexstar.TelegramTaskBot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Task extends BaseEntity{

    private  String name;
    private String description;
    @Column(name = "is_done")
    private Boolean isDone;
    @Column(name = "is_taken")
    private Boolean isTaken;
    @Column(name = "time_of_taking", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime timeOfTaking;
    @Column(name = "end_task_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime endTaskTime;

    @ManyToOne ( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
}
