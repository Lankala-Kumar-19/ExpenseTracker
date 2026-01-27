# Expense Tracker - Frontend

This is the **frontend** of the Expense Tracker application, built with **React**. It allows users to **view a landing page**, **login**, and **register**, while interacting with the backend API for expense management. The home page features a **full-screen background image** with **top-right Login/Register buttons** for easy navigation.

---

## Features

- Full-screen responsive landing page with background image  
- Top-right **Login** and **Register** buttons  
- Centered welcome text with short description  
- Responsive layout for mobile and desktop  
- Login and Register forms with state handling and API integration  

---

## Technologies Used

- React (with Hooks)  
- React Router DOM (for navigation)  
- CSS Flexbox and media queries for responsiveness  
- Axios (via `API` service) for HTTP requests  
- ES6+ JavaScript  

---

## Project Structure

The project folder is organized as follows:

- **expense-tracker-frontend/**
  - **public/**
    - **assets/**
      - `home.png` — Landing page background image
  - **src/**
    - **components/**
      - `Home.jsx` — Landing page component
      - `Login.jsx` — Login form component
      - `Register.jsx` — Registration form component
      - `Home.css` — Home page styles
    - **services/**
      - `api.js` — Axios instance for API calls
    - `App.js` — Main app component
  - `package.json`
  - `README.md`

---

## Installation

1. Clone the repository:


2. Navigate to the frontend folder: cd expense-tracker-frontend


3. Install dependencies:

npm install


---

## Running the App

Start the development server:

npm start


The app will run at `http://localhost:5173`. Make sure your backend API is running to enable login and registration functionality.

---

## Usage

- **Home Page:** Displays a full-screen background image with Login/Register buttons at the top-right.  
- **Login Page:** Enter your username and password to log in. Token is stored in `localStorage`.  
- **Register Page:** Create a new account with username, email, and password. Token is stored in `localStorage`.

---

## Notes

- Ensure the backend API endpoints match the paths used in `API.js` (`/login` and `/users/register`).  
- Background images should be placed in `public/assets/` to load properly.  
- The app is **fully responsive**, but you can customize styles in `Home.css` for branding.  

---

## License

This project is **open-source** and free to use under the MIT License.


