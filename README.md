# XGoogle Form Test Automation

## 📝 Project Overview

**XGoogle Form Test Automation** is a test automation project built using **Java, Selenium, and TestNG** to automate the submission of a sample Google Form. This project demonstrates dynamic form interaction including filling text inputs, selecting radio buttons, checkboxes, dropdowns, date and time fields, and verifying the form submission success message.

---

## ✅ Automated Test Case

### 🧪 testCase01 – Complete and Submit Google Form

1. Navigate to the Google Form: [https://forms.gle/wjPkzeSEk1CM7KgGA](https://forms.gle/wjPkzeSEk1CM7KgGA)  
2. Fill **"Crio Learner"** in the first text box  
3. Enter the following in the paragraph text area:  
   `"I want to be the best QA Engineer! 1710572021"`  
   > Replace `1710572021` with the **current epoch timestamp** at runtime  
4. Select your **Automation Testing experience** via radio button  
5. Choose the following **checkboxes**: `Java`, `Selenium`, `TestNG`  
6. Choose how you would like to be addressed via **dropdown**  
7. Enter the **current date minus 7 days** in the date field  
8. Enter **07:30** in the time field (can be 24-hour format)  
9. Submit the form  
10. Capture and print the **success message** displayed upon form submission in the console

---

## 🔧 Tech Stack

- **Language:** Java  
- **Automation Framework:** Selenium WebDriver  
- **Testing Framework:** TestNG  
- **Build Tool:** Gradle
- **Browser:** Google Chrome

---

## 🌐 System Requirements

- Java 11 or later
- Google Chrome (latest)
- ChromeDriver (must match your Chrome version)
- Internet access (required to reach the Google Form)

> Ensure `chromedriver` is in your system `PATH`, or configure its location in your Selenium setup.

---

## 📦 Installation

Follow these steps to set up and run the project locally:

### 1. Clone the Repository
```bash
git clone https://github.com/NiviyaJ/Buildout_GoogleForm.git

cd xgoogle-form-test-automation
```

### 2. Make the Gradle Wrapper Executable (Linux/macOS)
```bash
chmod +x gradlew
```
> Windows users can skip this step and use `gradlew.bat` instead.

### 3. Build the Project

Ensure **Java (JDK 11 or higher)** and Gradle are installed, then run:
```bash
gradle clean build
```
This will download all required dependencies and compile the test classes.

---

## 🚀 Running the Tests

To run all tests:

**Linux/macOS:**
```bash
./gradlew test
```

**Windows:**
```bash
gradlew.bat test
```

You can also execute specific test classes via your IDE (e.g., IntelliJ or Eclipse) or configure a `testng.xml` file if needed.

---

## 📬 Author

Maintained by **[Niviya Joy]**  
GitHub: [https://github.com/NiviyaJ]

