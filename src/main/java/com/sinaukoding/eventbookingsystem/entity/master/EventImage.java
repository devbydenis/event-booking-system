package com.sinaukoding.eventbookingsystem.entity.master;

import com.sinaukoding.eventbookingsystem.entity.app.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_event_image", indexes = {
        @Index(name = "idx_event_image_created_date", columnList = "createdDate"),
        @Index(name = "idx_event_image_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_event_image_id_event", columnList = "id_event"),
        @Index(name = "idx_event_image_path", columnList = "path")
})
public class EventImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String path;
}
