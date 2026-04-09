# 🏠 Roommate Compatibility & Shared Living Platform (Backend)

A **production-style Java Spring Boot backend** powering a roommate discovery and shared-living management platform.

The system enables users to:

```
find compatible roommates
send and accept match requests
build roommate connections
create structured lifestyle profiles
receive compatibility-based suggestions
securely authenticate using JWT-based stateless authentication
```

Future versions will support:

```
roommate group formation
shared expense splitting (Splitwise-style)
notifications system
```

This project is designed as a **real-world backend architecture portfolio project**, not a tutorial-level CRUD implementation.

---

# 🚀 Tech Stack

### Backend

```
Java 17
Spring Boot 3
Spring Security
Spring Data JPA (Hibernate)
MySQL
JWT Authentication
```

---

# 🏗 Architecture

Clean layered architecture:

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

Built following **production-oriented service-layer design principles**.

---

# 🔐 Authentication System (Completed)

Implemented a **stateless JWT authentication system with refresh-token lifecycle management**.

### Features

```
✔ User Signup
✔ User Login
✔ BCrypt password encryption
✔ Custom UserDetails implementation
✔ Custom UserDetailsService
✔ JWT Access Token generation
✔ Database-backed Refresh Token system
✔ Refresh token rotation
✔ Stateless authentication (Session disabled)
✔ JWT filter-based request validation
✔ Route protection using SecurityFilterChain
✔ Role-based authorization (USER / ADMIN)
```

---

# 🧠 Authentication Flow

### Signup

```
POST /auth/signup
```

Creates new user with encrypted password.

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

```
token signature
expiry
user identity
authorization role
```

---

### Refresh Access Token

```
POST /auth/refresh-token
```

Uses stored refresh token to generate new access token.

---

# 👤 Profile System (Completed)

Supports structured roommate identity modeling.

### Features

```
✔ Create profile
✔ Update profile (partial update supported)
✔ Get own profile
✔ Admin: view all profiles
✔ Admin: delete profiles
```

Profile fields:

```
name
age
occupation
city
bio
```

Separate from authentication entity (production-grade modeling decision).

---

# 🧬 Lifestyle Preferences Engine (Completed)

Captures compatibility-related behavioral data.

Stored using **enum-based modeling for safe matching logic**.

### Preferences include

```
sleep schedule
food habit
cleanliness level
work mode
smoking preference
drinking preference
guest frequency
budget range
```

Supports:

```
create preferences
update preferences
retrieve preferences
```

---

# 🤝 Compatibility Matching Engine (Completed)

Core feature of the platform.

Generates compatibility scores between users based on lifestyle similarity.

### Matching Logic Includes

```
exact match scoring
flexible matching support (ANY preference)
partial compatibility scoring
budget similarity detection
weighted scoring system (0–100)
```

Endpoint:

```
GET /matches/suggestions
```

Returns ranked roommate recommendations.

---

# 🔗 Match Request System (Completed)

Implements roommate connection workflow similar to social platforms.

### Features

```
✔ Send match request
✔ Accept match request
✔ Reject match request
✔ Prevent duplicate requests
✔ Prevent reverse duplicate requests
✔ Prevent self-requests
✔ Prevent unauthorized status updates
✔ Retrieve incoming requests
✔ Retrieve accepted roommate connections
```

Ensures **bidirectional connection integrity**.

---

# 🧱 Entities Implemented

### User

Authentication entity

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

Secure refresh-token lifecycle tracking

```
id
user
token
expiryDate
```

Supports:

```
token revocation
secure logout architecture
future multi-device session handling
```

---

### Profile

User identity metadata

```
user
name
age
occupation
city
bio
```

---

### LifestylePreferences

Compatibility engine input dataset

```
sleepSchedule
foodHabit
cleanlinessLevel
workMode
smokingPreference
drinkingPreference
guestFrequency
budget
```

---

### MatchRequest

Connection lifecycle tracking

```
sender
receiver
status
createdAt
```

Supports:

```
PENDING
ACCEPTED
REJECTED
```

---

# 🛡 Security Design Decisions

| Feature          | Strategy             |
| ---------------- | -------------------- |
| Access Token     | JWT                  |
| Refresh Token    | Database stored UUID |
| Sessions         | Disabled             |
| Password Storage | BCrypt               |
| Authentication   | CustomUserDetails    |
| Authorization    | Role-based           |
| API Protection   | JWT Filter           |

Database-backed refresh tokens enable:

```
secure logout
token revocation
future session tracking
multi-device support
```

Production-level security design.

---

# 📦 APIs Implemented So Far

### Auth APIs

```
POST /auth/signup
POST /auth/login
POST /auth/refresh-token
```

---

### Profile APIs

```
POST /profile
PUT /profile
GET /profile/me
```

Admin:

```
GET /admin/profile/all
DELETE /admin/profile/{id}
```

---

### Preferences APIs

```
POST /preferences
PUT /preferences
GET /preferences/me
```

---

### Matching APIs

```
GET /matches/suggestions
POST /matches/request/{userId}
PUT /matches/accept/{requestId}
PUT /matches/reject/{requestId}
GET /matches/my-requests
GET /matches/my-connections
```

---

# 🧭 Upcoming Features (Next Phase)

### Roommate Group System (In Progress)

```
create group
invite matched users
accept/reject invitations
view group members
leave group
group lifecycle management
```

Supports transition from:

```
connection → shared living unit
```

---

### Expense Sharing Engine (Planned)

Splitwise-style shared expense tracking

```
add expense
split equally/custom
track balances
settle payments
group ledger tracking
```

Major system capability upgrade.

---

# 🔔 Future Enhancements (Planned)

```
email verification workflow
profile image upload (AWS S3)
Redis caching layer
rate limiting
admin moderation dashboard
Docker deployment
CI/CD pipeline integration
notification system
```

---

# 🎯 Project Goal

This project demonstrates **production-oriented backend engineering skills**:

```
secure authentication architecture
stateless JWT security implementation
enum-driven domain modeling
recommendation engine design
relationship lifecycle workflows
layered service architecture
REST API best practices
```

Target roles:

```
Java Backend Engineer
Spring Boot Developer
Full Stack Developer
Software Development Engineer (SDE)
```

Fixes to be added later:

1. Instead of returning Profile, getAllProfile should return ProfileDTO which should contain UserDTO, to avoid exposing password
2. Create city preference
3. Feature to add profile pic
