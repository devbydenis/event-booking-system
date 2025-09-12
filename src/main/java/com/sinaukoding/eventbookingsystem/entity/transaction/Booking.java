package com.sinaukoding.eventbookingsystem.entity.transaction;

import com.sinaukoding.eventbookingsystem.entity.app.BaseEntity;
import com.sinaukoding.eventbookingsystem.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_user", indexes = {
        @Index(name = "idx_booking_created_date", columnList = "createdDate"),
        @Index(name = "idx_booking_modified_date", columnList = "modifiedDate"),
})
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String eventId;

    @Column(nullable = false)
    private LocalDateTime tanggalBooking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    @Min(value = 0, message = "kuantitas tidak boleh negatif")
    private Integer kuantitas;

}
