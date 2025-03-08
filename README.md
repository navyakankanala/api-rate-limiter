1️⃣ Clone the GitHub repository: git clone https://github.com/your-username/api-rate-limiter.git && cd api-rate-limiter
2️⃣ Verify Gradle & Java installation: Run gradle -v and java -version, install if missing.
3️⃣ Start Redis using Docker: docker run --name redis -d -p 6379:6379 redis
4️⃣ Build and run the application: ./gradlew bootRun (For Windows, use gradlew.bat bootRun).
5️⃣ Test the API: Run curl -H "X-API-KEY: test-user" http://localhost:8080/api/protected-resource and check the response.
6️⃣ Generate the Gradle Build Scan: ./gradlew build --scan (For Windows, use gradlew.bat build --scan).
7️⃣ Copy the Build Scan link from the terminal output and submit it in the job application. 🚀
