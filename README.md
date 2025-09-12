# Event Booking System

## Overview
aplikasi ini adalah aplikasi yang berguna melakukan booking system untuk event-event yang disediakan oleh Sinau Coding

## Flowchart
```mermaid
flowchart TD
    START[Mulai] --> A[Request oleh user]
    A --> B[GET find-all-event]
    A --> C[GET find-all-event]
    B --> D[User melakukan booking]
    C --> D
    D --> E{User sudah login?}
    
    E -->|NO| F{Sudah punya akun?}
    E -->|YES| G[POST create-booking]
    E -->|YES| H[GET history-booking/:id]
    
    F -->|NO| I[User register akun<br/>POST auth/register]
    F -->|YES| J[User melakukan login<br/>POST auth/login]
    
    H --> L

    I --> J
    J --> E
    
    G --> K[Cetak tiket]
    K --> L[Selesai]
```