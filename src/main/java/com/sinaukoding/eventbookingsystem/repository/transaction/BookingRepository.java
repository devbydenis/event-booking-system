package com.sinaukoding.eventbookingsystem.repository.transaction;

import com.sinaukoding.eventbookingsystem.entity.transaction.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingRepository extends JpaRepository<Booking, String>, JpaSpecificationExecutor<Booking> {
}
