
# Weather App

This is a weather app that allows users to search for the weather by city name. The app fetches data from a weather API and displays the current weather conditions.

## Setup Instructions

### 1. Clone the Repository
Clone the repository to your local machine using the following command:

```
git clone https://github.com/bansal70/weather-app.git
```

### 2. Install Dependencies
Make sure you have **Android Studio** installed and then open the project in Android Studio. It will automatically download and install the necessary dependencies.

Alternatively, you can run the following command from the root of the project:

```
./gradlew build
```

### 3. Set the API Key

The app uses a weather API key to fetch weather data. You need to set the API key as an environment variable.

#### **For Local Development**

- **On Windows**:
  1. Open **Command Prompt** or **PowerShell**.
  2. Set the environment variable:
  
  ```bash
  set WEATHER_API_KEY="your_api_key_here"
  ```

  To make this permanent, you can add it to the **Environment Variables** in **Control Panel > System > Advanced System Settings > Environment Variables**.

- **On macOS/Linux**:
  1. Open a terminal.
  2. Add the environment variable to your shell profile file (e.g., `.bashrc`, `.zshrc`):

  ```bash
  export WEATHER_API_KEY="your_api_key_here"
  ```

### 4. Build and Run the App
Once the environment variable is set, build and run the app in Android Studio. The app should now be able to fetch and display weather data.

### 5. Download APK:
You can directly download and test the APK here: https://drive.google.com/file/d/1gIi6wBKHxLBn8kyyrHKRGxZdNyM_0JZ5/view?usp=sharing

## Troubleshooting

- Ensure that the API key is set correctly in your environment variables.
- If the app does not display weather data, verify that the weather API is responding properly by testing the API key in a different environment (e.g., Postman).

