# Cucumber + Java Selenium Automation Framework

## Overview

This is a **Cucumber + Java Selenium automation framework** designed for robust, maintainable, and scalable web application test automation.

**Technologies Used:**
- Java
- Selenium WebDriver
- Cucumber (BDD)
- Page Object Model (POM)
- XML-based test data

---

## Folder Structure
- src/
- ├─ main/
- │ ├─ java/
- │ │ ├─ Driver/ # WebDriver wrapper, WaitUntil, and helper methods
- │ │ │ ├─ Driver.java
- │ │ │ └─ WaitUntil.java
- │ │ ├─ PageObjects/
- │ │ │ ├─ Locators/ # Element locators
- │ │ │ │ └─ LoginPageLocators.java
- │ │ │ ├─ Models/ # Page models or data models
- │ │ │ │ └─ UserModel.java
- │ │ │ └─ Pages/ # Page Object classes
- │ │ │ ├─ Pages.java
- │ │ │ ├─ LoginPage.java
- │ │ │ └─ DashboardPage.java
- │ │ ├─ TestData/ # EnvironmentConstants.xml for URLs, credentials, configs
- │ │ │ └─ EnvironmentConstants.xml
- │ │ └─ Utils/ # BrowserHelper, InitializeBrowser, XMLFileUtility
- │ │ ├─ BrowserHelper.java
- │ │ ├─ InitializeBrowser.java
- │ │ └─ XMLFileUtility.java
- └─ test/
- ├─ java/
- │ ├─ features/ # Cucumber .feature files (e.g., login.feature)
- │ │ └─ login.feature
- │ ├─ stepDefinition/ # Step definitions for feature files
- │ │ └─ login_stepdef.java
- │ └─ Hooks/ # Hooks for setup/teardown, TestRunner class
- │ ├─ PBHooks.java
- │ └─ TestRunner.java
- └─ resources/ # Optional resources like test data or reports

## Key Components

| Component | Description |
|-----------|-------------|
| **Driver** | Selenium WebDriver wrapper (`Driver.java`) with reusable methods (`FindElement`, `clickElement`, `setText`, `getText`) |
| **WaitUntil** | Explicit wait utility for element visibility, clickability, page load, etc. |
| **PageObjects** | Base Page class (`Pages.java`) and concrete pages (`LoginPage`, `DashboardPage`) implementing POM |
| **Locators & Models** | Centralized element locators and reusable data models |
| **Utils** | `BrowserHelper`, `InitializeBrowser`, `XMLFileUtility` for browser setup and XML-based test data |
| **TestData** | `EnvironmentConstants.xml` containing URLs, credentials, and environment constants |
| **features** | Cucumber `.feature` files defining BDD scenarios |
| **stepDefinition** | Step definition implementations for feature files |
| **Hooks** | `PBHooks.java` for `@Before`/`@After` setup, teardown, and TestRunner class |

---

## Windows Setup Instructions

1. **Clone the repository**

git clone <your-repo-url>
cd <your-project-folder>

2. **Install dependencies**

 - Ensure Java 17+ and Maven are installed
 - mvn clean install

3. **Configure browser drivers** 
 - Place ChromeDriver/GeckoDriver in system PATH or configure in BrowserHelper

4. **Configure test environment**
 - Update EnvironmentConstants.xml with URLs, usernames, passwords, etc.


## Linux Installation
**Steps to deploy automation project on linux machine**:
  **Install Java**:
   - sudo apt update 
   - sudo apt install openjdk-17-jdk -y 
   - java -version

  **Install Maven**:
   - sudo apt install maven -y 
   - mvn -v
 
 **Clone Your Project**
   - git clone https://github.com/navee-hans/PBAutomation.git

  - cd your-repo-name 

 **Install Chrome browser**
  - wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
  - sudo apt install -y ./google-chrome-stable_current_amd64.deb

 **Verify installation**
  - google-chrome --version
 **Allure report setup on linux** 
  - Go to your home or tools directory (optional)
   - cd ~
   - mkdir -p tools && cd tools
   - wget https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.34.1/allure-commandline-2.34.1.zip
   - unzip allure-commandline-2.34.1.zip 
   - mv allure-commandline-2.34.1 allure 
   - Make it globally accessible (optional but recommended)
    - sudo mv allure /opt/allure 
   - Create a symbolic link to run it from anywhere
    - sudo ln -s /opt/allure/bin/allure /usr/bin/allure 
   - Verify installation
    - allure --version

**Run test cases with reports generation**
 - mvn clean test && allure generate allure-results --clean -o target/allure-report
**Run test cases without report**
  - mvn clean test
    
**Features**

 - Page Object Model for maintainable and reusable pages 
 - WaitUntil wrapper for stable waits and page synchronization 
 - Cucumber BDD for readable test scenarios 
 - XML-based test data for configurable environments 
 - Singleton WebDriver to avoid multiple browser instances 
 - Logging & debugging with informative console output

**Best Practices**
 - Always use WaitUntil before interacting with elements 
 - Keep locators centralized in Locators class 
 - Avoid storing WebElement as class variables — fetch dynamically using locators 
 - Use EnvironmentConstants.xml for all URLs and credentials 
 - Capture screenshots on failure (can extend PBHooks)

**Future Enhancements**
 - Integrate Allure or ExtentReports for detailed test reports 
 - Add automatic screenshot capture on test failure 
 - Enable parallel test execution using Cucumber and Maven 
 - Parameterize browser type from Maven profiles or CI/CD pipeline