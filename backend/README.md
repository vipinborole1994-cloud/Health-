# Health Backend

Spring Boot API for the Health website.

## Run

From this directory:

```bash
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.

## Authentication API

Request an OTP:

```http
POST /api/auth/request-otp
Content-Type: application/json

{"mobile":"9876543210"}
```

Verify the development OTP:

```http
POST /api/auth/verify-otp
Content-Type: application/json

{"mobile":"9876543210","otp":"1234"}
```

The OTP and users are currently held in memory for development only. Before production, replace this service with a database, SMS provider, password or OTP expiry, rate limiting, and Spring Security JWT/session authentication.
