# Health Website Ecommerce

This project uses:

- Frontend: HTML, CSS, and JavaScript in the root folder
- Backend: Java Spring Boot in the `backend` folder

## Run the backend

Open a terminal in the project root and run:

```powershell
cd backend
mvn spring-boot:run
```

The Java backend runs at `http://localhost:8080`.

## Run the frontend

Open a second terminal in the project root and run:

```powershell
python -m http.server 5500
```

Open these pages in a browser:

- Main page: `http://localhost:5500/health.html`
- Login: `http://localhost:5500/userLogIn.html`
- Signup: `http://localhost:5500/signup.html`
- Food: `http://localhost:5500/food.html`
- Vitamins: `http://localhost:5500/vitamins.html`

Keep both servers running while testing the website.

## Authentication API

The frontend calls these Spring Boot endpoints:

```text
POST http://localhost:8080/api/auth/request-otp
POST http://localhost:8080/api/auth/verify-otp
```

For development, the OTP is `1234`.

The current OTP storage and authentication token are for development only. Before production, add a database, real SMS provider, OTP expiry, rate limiting, and secure JWT or session authentication.

## Security

Never store GitHub tokens, passwords, API keys, or other secrets in this repository. Any token previously placed in the old README should be revoked immediately.
