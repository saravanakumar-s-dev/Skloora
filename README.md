# 🚀 Skloora

### A Scalable Developer-Focused Social Networking Platform

Skloora is a Java Spring Boot–based social networking platform designed for developers to connect, share content, communicate, and interact through posts, media, likes, messaging, and notifications.

The project focuses on **clean backend architecture, scalability, security, load balancing, and efficient database communication**.

---

## 📌 What is Skloora?

Skloora is a developer-oriented social platform where users can:

* 👤 Create and manage accounts
* 🔐 Securely authenticate
* 📝 Create and share posts
* 🖼️ Upload and share media
* ❤️ Like posts
* 💬 Communicate with other users
* 🔎 Search for developers
* 📧 Receive email notifications
* 🔑 Manage passwords and account security
* 🌐 Interact with other developers

---

## 🏗️ Architecture

Skloora follows a separated frontend/backend architecture.

```text
                    ┌──────────────────┐
                    │     Frontend     │
                    │   HTML/CSS/JS    │
                    └────────┬─────────┘
                             │
                             ▼
                  ┌─────────────────────┐
                  │   Load Balancer     │
                  │       :9090         │
                  └──────────┬──────────┘
                             │
                ┌────────────┴────────────┐
                ▼                         ▼
       ┌────────────────┐       ┌────────────────┐
       │ Backend        │       │ Backend        │
       │ Instance 1     │       │ Instance 2     │
       │ :8081          │       │ :8082          │
       └───────┬────────┘       └───────┬────────┘
               │                        │
               └───────────┬────────────┘
                           ▼
                  ┌─────────────────┐
                  │   PostgreSQL    │
                  │    Database     │
                  └─────────────────┘
```

The load balancer distributes incoming requests between multiple backend instances, allowing the application to handle increased traffic and providing a foundation for horizontal scaling.

---

## ⚙️ Technology Stack

| Layer             | Technology                       |
| ----------------- | -------------------------------- |
| Backend           | Java                             |
| Framework         | Spring Boot                      |
| Database          | PostgreSQL                       |
| Database Access   | JDBC                             |
| Authentication    | Custom Authentication            |
| Password Security | BCrypt                           |
| Email             | JavaMailSender                   |
| Frontend          | HTML, CSS, JavaScript            |
| Template Engine   | Thymeleaf                        |
| Load Balancing    | Custom Spring Boot Load Balancer |
| Build Tool        | Maven                            |
| Version Control   | Git & GitHub                     |

> **Note:** Skloora currently uses **JDBC for database communication and does not use an ORM such as Hibernate/JPA**.

---

## 🔐 Security

Security is an important part of Skloora.

Current security-related features include:

* 🔒 Password hashing using BCrypt
* 🍪 Cookie-based authentication
* 🔑 Password change functionality
* 📩 OTP-based account operations
* 🛡️ CAPTCHA protection
* 🔐 Secure application configuration
* 🚫 Protected user operations

Sensitive credentials and secrets should be supplied through environment variables rather than committed to the repository.

---

## ⚖️ Load Balancing

Skloora includes a dedicated load-balancing service.

### Current architecture

```text
                 Incoming Request
                        │
                        ▼
              ┌──────────────────┐
              │  Load Balancer   │
              │      :9090       │
              └────────┬─────────┘
                       │
              ┌────────┴────────┐
              ▼                 ▼
         Backend :8081     Backend :8082
```

The load balancer distributes requests between available backend instances.

This provides a foundation for:

* Horizontal scaling
* Multiple backend instances
* Improved availability
* Traffic distribution
* Future server expansion

---

## 📊 Scalability

Skloora is designed with scalability in mind.

Instead of relying on a single backend process, multiple backend instances can run simultaneously behind the load balancer.

```text
              Users
                │
                ▼
         ┌──────────────┐
         │ Load Balancer│
         └───────┬──────┘
                 │
       ┌─────────┼─────────┐
       ▼         ▼         ▼
    Server 1  Server 2  Server 3
```

Additional instances can be introduced as traffic increases.

---

## 🗄️ Database

Skloora uses **PostgreSQL** as its primary database.

Database operations are performed using **JDBC**, providing direct control over SQL queries and database communication.

```text
Spring Boot
     │
     ▼
   JDBC
     │
     ▼
 PostgreSQL
```

There is currently **no JPA/Hibernate ORM layer** in the project.

---

## 📧 Mailing Service

Skloora uses a dedicated mailing service for email-related operations.

The mailing service handles tasks such as:

* Account emails
* OTP emails
* Password-related emails
* Notifications

Keeping mailing operations separate from the main application helps reduce unnecessary coupling between core application logic and email processing.

---

## 🖼️ Media Handling

Skloora supports media uploads associated with user posts.

The architecture is designed with future improvements in mind, such as moving large media files to dedicated object storage and introducing asynchronous processing as the platform grows.

---

## 📁 Repository Structure

Skloora is organized into separate repositories:

```text
Skloora
│
├── Skloora-Frontend
│
└── Skloora-Backend
    │
    ├── Main Application
    ├── Load Balancer
    └── Mailing Service
```

This separation keeps frontend and backend development independent and makes the project easier to maintain.

---

## 📈 Future Improvements

Planned or possible improvements include:

* Redis caching
* Distributed session management
* Object storage for media
* Asynchronous media processing
* Message queues
* Database connection optimization
* Monitoring and metrics
* Centralized logging
* Automated testing
* Containerization
* CI/CD pipelines
* Cloud deployment
* Automatic backend scaling

---

## 🚧 Deployment Status

Skloora is **currently under development and is not deployed as a production application**.

The current development environment runs multiple backend instances locally with a dedicated load balancer.

Future deployment may use Linux-based servers and containerized infrastructure.

---

## 🧪 Current Project Focus

The main focus of the project is building a backend that can evolve from a single-instance application into a scalable multi-instance system.

```text
Single Instance
      │
      ▼
Multiple Instances
      │
      ▼
Load Balancer
      │
      ▼
Scalable Architecture
      │
      ▼
Future Cloud Infrastructure
```

---

## 🎯 Project Goals

Skloora aims to provide:

* Clean architecture
* Secure authentication
* Efficient database access
* Scalable backend infrastructure
* Modular services
* Reliable request handling
* Developer-focused social networking features

---

## 👨‍💻 Author

**Saravanakumar**

Skloora is a personal software project focused on learning and implementing real-world backend engineering, system design, scalability, and distributed application concepts.

---

## ⭐ Project Status

**Status:** 🚧 Active Development

Skloora is continuously evolving with new features, infrastructure improvements, and scalability enhancements.
