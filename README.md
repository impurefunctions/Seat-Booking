# Bus Seat Booking App

This Android application allows users to book seats on a bus. It features a simple interface for users to log in, select seats, and view their bookings. The app is built using Java and Firebase for the backend.

## Features

- User authentication with employee codes.
- Seat selection from a visual layout.
- Real-time updates on seat availability.
- Saving booking details.

## Setup

To set up the project, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repository/bus-seat-booking.git
2. **Open in Android Studio:**
   - Launch Android Studio.
   - Select "Open an existing Android Studio project."
   - Navigate to the cloned repository and select it.
3. **Firebase Setup:**
   - Go to the Firebase console and create a new project.
   - Follow the instructions to add an Android app to your project.
   - Download the `google-services.json` file and place it in the `app/` directory.
   - Enable the Realtime Database in your Firebase project.
4. **Build the project:**
   - Sync the project with Gradle files.
   - Build and run the app on an emulator or a physical device.

## Usage
1. **Login:**
   - Enter your employee code to log in.
2. **Book a seat:**
   - After logging in, you will see a layout of the bus seats.
   - Select your desired seat.
   - Confirm your booking.
3. **View bookings:**
   - You can view your saved bookings in the app.
