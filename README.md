# Searchify with Firebase Realtime Database

This project integrates Firebase Realtime Database with the Searchify web search application to store search queries and results in real-time.

## Features

- Search functionality with sample results
- Firebase Realtime Database integration for storing search queries
- Recent searches display with clickable history
- Responsive design for all device sizes

## Setup Instructions

### 1. Create a Firebase Project

1. Go to the [Firebase Console](https://console.firebase.google.com/)
2. Click "Add project" and follow the setup wizard
3. Once your project is created, click on "Web" to add a web app to your project
4. Register your app with a nickname (e.g., "Searchify")
5. Copy the Firebase configuration object provided

### 2. Update Firebase Configuration

Open `index.html` and replace the placeholder Firebase configuration with your own:

```javascript
const firebaseConfig = {
    apiKey: "YOUR_API_KEY",
    authDomain: "YOUR_PROJECT_ID.firebaseapp.com",
    databaseURL: "https://YOUR_PROJECT_ID-default-rtdb.firebaseio.com",
    projectId: "YOUR_PROJECT_ID",
    storageBucket: "YOUR_PROJECT_ID.appspot.com",
    messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
    appId: "YOUR_APP_ID"
};
```

### 3. Set Up Realtime Database

1. In the Firebase Console, go to "Realtime Database"
2. Click "Create Database"
3. Start in test mode for development (you can adjust security rules later)

### 4. Database Structure

The application uses the following database structure:

```
/searches - Stores user search queries with timestamps
/results - Stores search results for each query
```

## Running the Application

Simply open the `index.html` file in a web browser. No additional build steps are required.

## How It Works

1. When a user performs a search, the query is saved to Firebase
2. The application checks if results for this query already exist in the database
3. If results exist, they are retrieved; otherwise, sample results are used and stored
4. Recent searches are displayed and can be clicked to repeat the search

## Future Enhancements

- User authentication for personalized search history
- Advanced search analytics
- Custom search result ranking algorithms
- Integration with real search APIs