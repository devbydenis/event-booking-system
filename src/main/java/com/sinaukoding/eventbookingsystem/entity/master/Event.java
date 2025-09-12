package com.sinaukoding.eventbookingsystem.entity.master;


import com.sinaukoding.eventbookingsystem.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_event", indexes = {
        @Index(name = "idx_event_created_date", columnList = "createdDate"),
        @Index(name = "idx_event_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_event_status", columnList = "status"),
        @Index(name = "idx_event_nama", columnList = "nama"),
        @Index(name = "idx_event_harga", columnList = "harga")
})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(max = 100, message = "Maksimal 100 karakter")
    @Column(nullable = false)
    private String nama;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String deskripsi;

    @Column(nullable = false)
    private LocalDateTime tanggalEvent;

    @Column(nullable = false)
    private String lokasi;

    @Min(value = 0, message = "Kapasitas tidak boleh negatif")
    @Column(nullable = false)
    private Integer kapasitas;

    @Min(value = 0, message = "Harga tidak boleh negatif")
    @Column(nullable = false)
    private Double harga;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<EventImage> listImage = new HashSet<>();
}
