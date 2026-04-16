package ru.yandex.practicum.mymarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private Long id;
    private String title;
    private String description;
    private String imgPath;
    private Long price;
    private Long count;
}
