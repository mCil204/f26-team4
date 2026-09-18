# Requirements – Clean Sweep

**Project Name:** Clean Sweep\
**Team:** Ronny Kpa - Provider\
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-09-18

---

## 1. Overview
**Vision.** One or two sentences: who this is for, the core problem, and the outcome.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
- **Customer (e.g., Student/Patient/Pet Owner/etc. )** — 1 line goal statement.
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.
- **SysAdmin (optional)** — 1 line goal statement.

**Scope (this semester).**
- <capability 1>
- <capability 2>
- <capability 3>

**Out of scope (deferred).**
- <deferred 1>
- <deferred 2>

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑1 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑2 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

### 2.2 Provider (Cleaner) Stories
- **US-20 — Create and update cleaner profile**  
  _Story:_ As a cleaner, I want to create and update my profile so that I can attract clients.  
  _Acceptance:_
  ```gherkin
  Scenario: Create and update cleaner profile
    Given I do not have a profile
    When  I provide my details and submit the form
    Then  my profile should be created 
    And   the profile should be visible to customers
  ```

- **US-21 — Define services and pricing**  
  _Story:_ As a cleaner, I want to define my services and pricing so that customers understand my offerings.
  _Acceptance:_
  ```gherkin
  Scenario: Define services and pricing 
    Given I am logged in as a cleaner
    When  I add my services and set pricing 
    Then  the services should be saved, visible and understood to customers
  ```

- **US‑30 — Cancel appointments**  
  _Story:_ As a cleaner, I want to cancel appointments so that I can focus on other appointments  
  _Acceptance:_
  ```gherkin
  Scenario: Cancel appointments
    Given I am logged in as a cleaner
    When  I am offered an appointment that is out of my scope or doesn't seem worth pursuing 
    Then  I should be able to cancel the appointment 
  ```

---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

---

## 4. Assumptions, Constraints, and Policies
- list any rules, policies, assumptions, etc.

---

## 5. Milestones (course‑aligned)
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.