# 🔍 Research Assistant – Chrome Extension (Powered by Gemini API)

**Research Assistant** is a Chrome Extension that helps users effortlessly **summarize web content** and **take quick notes** while browsing. Simply select any text on a webpage, and the extension will display a brief, AI-generated summary. Users can also take personal notes on the summarized content for future reference.

---

## 🧠 Features

- ✨ Summarizes selected text using **Google Gemini API**
- 📝 Allows users to take and store quick notes alongside summaries
- ⚡ Fast and responsive user interface built for Chrome
- 🌐 Backend powered by **Spring Boot** and **Java**
- 📩 Uses `WebClient` for non-blocking API requests
- 🔐 API Key integration for secure communication with Gemini

---

## 🛠️ Tech Stack

### 🔧 Backend
- **Java 17+**
- **Spring Boot**
- **Spring WebFlux (`WebClient`)**
- **Lombok**
- **Maven**

### 🌐 Frontend (Chrome Extension)
- **HTML**
- **CSS**
- **JavaScript**

---

## 📦 Build & Run

### 🚀 Backend Setup

1. **Clone the repo**
   ```bash
   git clone https://github.com/your-username/research-assistant.git
   cd research-assistant
   
2. **Set your Gemini API Key**

    In application.properties:

   gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=
   gemini.api.key=YOUR_GEMINI_API_KEY

OR Or set the environment variable:
   export GEMINI_KEY=your_api_key

3. Run the app
    mvn spring-boot:run

4. Test API locally

    Use Postman to send POST requests to:
     http://localhost:8080/api/research/process
   Example JSON request:

   {
   "content": "Spring WebFlux is a reactive framework...",
   "operation": "summarise"
   }

🧩 Chrome Extension
Go to chrome://extensions/

Enable Developer Mode

Click on "Load unpacked"

Select the folder where your extension files (HTML, JS, CSS) are stored

Use the extension on any webpage by selecting text and clicking the extension icon

📚 Chrome Extension Documentation:
Chrome Developers – Extensions Docs
https://developer.chrome.com/docs/extensions/get-started/tutorial/hello-world

🤖 Gemini API
This project integrates Google's Gemini API for natural language understanding

Read more here: Gemini on Google AI
https://ai.google.dev/

🧪 Tools Used
✅ Postman – For testing backend APIs

✅ Google AI Studio – For testing prompts and exploring Gemini’s capabilities

✅ Spring Docs – For backend framework references
Spring Documentation - https://docs.spring.io/spring-framework/reference/

✅ Chrome Developer Documentation – For building the extension
Chrome Extension Docs - https://ai.google.dev/

📁 Folder Structure
research-assistant/
├── backend/
│   ├── src/main/java/com/research/assistant/
│   ├── application.properties
│   └── pom.xml
├── extension/
│   ├── manifest.json
│   ├── popup.html
│   ├── popup.js
│   └── style.css
└── README.md
