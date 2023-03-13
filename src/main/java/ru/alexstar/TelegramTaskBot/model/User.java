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
    @Column(name = "tg_name")
    private  String tgName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String division;
    @Column(name = "tele_user_id")
    private long teleUserID;
    @OneToMany(mappedBy="user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_task",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    private List<Task> tasks;
}
