package ru.alexstar.TelegramTaskBot.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private  String name;
    @Column(name = "tele_user_id")
    private long teleUserID;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    private List<Task> tasks;
}
