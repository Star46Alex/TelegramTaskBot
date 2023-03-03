package ru.alexstar.TelegramTaskBot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;
}
