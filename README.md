# 🧪 NEXUS Automated UI & Accessibility Test Framework

[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen)]()
[![Java](https://img.shields.io/badge/Java-21-orange)]()
[![Selenium](https://img.shields.io/badge/Selenium-4.26-blue)]()
[![Cucumber](https://img.shields.io/badge/BDD-Cucumber-green)]()
[![Axe-core](https://img.shields.io/badge/Accessibility-WCAG_2.1_AA-purple)]()
[![Jenkins](https://img.shields.io/badge/CI%2FCD-Jenkins_Pipeline-red)]()

An end-to-end BDD automation framework combining functional UI testing and automated WCAG 2.1 Level AA accessibility auditing for the [Nexus Personal OS](https://nexus-personalos.vercel.app/) application.

---

## 🛠️ Architecture & Tech Stack

| Technology | Purpose |
|---|---|
| **Java 21** | Core programming language |
| **Selenium WebDriver 4** | Cross-browser web automation |
| **Cucumber BDD** | Gherkin-based behavioral specifications |
| **TestNG** | Test execution engine & runner assertions |
| **Deque Axe-core** | Automated WCAG 2.1 A & AA accessibility scans |
| **Jenkins** | Continuous integration pipeline via declarative `Jenkinsfile` |
| **Maven** | Dependency management and build orchestration |

---

## 📂 Framework Structure

```text
src/
├── test/
│   ├── java/
│   │   ├── pages/            # Page Object Model (POM) representations
│   │   ├── runners/          # TestNG Cucumber Runner
│   │   ├── stepdefinitions/  # Cucumber step definitions & Axe-core audit hook
│   │   └── support/          # WebDriver factory & browser lifecycle
│   └── resources/
│       └── features/         # Feature files (@smoke, @dashboard, @accessibility)
BUG_REPORTS.md                # Documented defects found by automation
Jenkinsfile                   # CI/CD pipeline definition
pom.xml                       # Maven build configuration