# 🏠 Roommate Compatibility & Expense Sharing Platform (Backend)

A production-style **Java Spring Boot backend** for a roommate matching and shared living management platform.

This system helps users:

* find compatible roommates
* create shared groups
* manage expenses
* maintain trust-based profiles
* securely authenticate using JWT-based stateless authentication

---

# 🚀 Tech Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* MySQL
* JWT Authentication

### Architecture

Layered architecture:

```
controller
service
repository
entity
dto
security
config
exception
```

Production-oriented structure followed from day one.

---

# 🔐 Authentication System (Completed)

Implemented a **stateless JWT-based authentication system** with refresh token support.

### Features

✔ User Signup
✔ User Login
✔ Password encryption using BCrypt
✔ Custom UserDetails implementation
✔ Custom UserDetailsService
✔ JWT Access Token generation
✔ Database-backed Refresh Token system
✔ Stateless authentication (no session storage)
✔ Route protection using JWT filter
✔ SecurityFilterChain configuration
✔ Role-based authentication support (USER / ADMIN ready)

---

# 🧠 Authentication Flow

### Signup

```
POST /auth/signup
```

Creates a new user with encrypted password.

---

### Login

```
POST /auth/login
```

Returns:

```
accessToken
refreshToken
```

---

### Access Protected APIs

```
Authorization: Bearer <access_token>
```

JWT filter validates:

* signature
* expiry
* ownership
* role

---

### Refresh Access Token

```
POST /auth/refresh-token
```

Uses stored refresh token to generate new access token.

---

# 🧱 Entities Implemented

### User

Stores authentication details

Fields:

```
id
email
password
role
isActive
isVerified
createdAt
```

---

### RefreshToken

Stored in database for secure token lifecycle management

Fields:

```
id
user
token
expiryDate
```

Supports:

* logout capability
* token revocation
* future multi-device session handling

---

# 🛡 Security Design Decisions

This project uses:

| Feature          | Strategy             |
| ---------------- | -------------------- |
| Access token     | JWT                  |
| Refresh token    | Database stored UUID |
| Sessions         | Disabled             |
| Password storage | BCrypt hashing       |
| Authentication   | CustomUserDetails    |
| Authorization    | Role-based           |

Why DB refresh tokens?

Supports:

* secure logout
* token revocation
* session tracking
* future multi-device login support

Production-grade decision.

---

# 📦 APIs Implemented So Far

### Auth Controller

```
POST /auth/signup
POST /auth/login
POST /auth/refresh-token
```

---

# 🧪 Example Login Response

```
{
  "accessToken": "...",
  "refreshToken": "..."
}
```

---

# 🧭 Upcoming Features (Roadmap)

### Phase 2 — Profile System

User profile management

```
name
age
occupation
city
bio
profile photo
```

---

### Phase 3 — Lifestyle Preferences Engine

Used for compatibility scoring

```
sleep schedule
food habits
cleanliness level
budget range
guest frequency
noise tolerance
work style
```

---

### Phase 4 — Compatibility Matching Engine

Smart roommate suggestion system

```
compatibility score calculation
weighted scoring logic
match lifecycle tracking
match status management
```

---

### Phase 5 — Roommate Groups

Multi-user living groups

```
create group
invite members
join/leave group
group roles
```

---

### Phase 6 — Expense Sharing System

Splitwise-style functionality

```
add expenses
auto settlement calculation
group balance tracking
payment status updates
```

---

### Phase 7 — Notifications System

Event-driven alerts

```
match found
group invite received
expense added
settlement pending
```

---

### Phase 8 — Trust & Safety Layer

Profile credibility system

```
profile completeness score
user reviews
report system
verification badges
```

---

# 🧠 Long-Term Enhancements (Planned)

```
multi-device session support
email verification workflow
image upload support (AWS S3)
Redis caching
rate limiting
admin moderation dashboard
Docker deployment
CI/CD pipeline integration
```

---

# 🎯 Project Goal

This project is designed as a **production-style portfolio backend** demonstrating:

* secure authentication architecture
* scalable entity relationships
* clean layered design
* real-world feature modeling
* REST API best practices

Target role:

```
Java Backend Engineer / Full Stack Developer
```
