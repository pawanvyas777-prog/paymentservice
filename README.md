Payment Service
📌 Overview

This microservice handles payment confirmation for seat bookings.

It listens to booking messages from RabbitMQ and updates booking status after successful payment.

🏗 Architecture Flow
Seat Booking Service → RabbitMQ → Payment Service → Booking Confirmed

🛠 Tech Stack

Java 17

Spring Boot

RabbitMQ

Docker

⚙ Key Features

RabbitMQ Listener

Asynchronous message processing

Booking confirmation simulation

Clean microservice separation

▶ How To Run

Ensure RabbitMQ is running (via Docker infra)

Start service:

mvn spring-boot:run
