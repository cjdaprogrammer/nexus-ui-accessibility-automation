# 🐛 Accessibility (WCAG 2.1 AA) Defect Reports

During automated regression runs using Deque Axe-core integrated with Selenium and Cucumber, the following defects were detected on the live application (`https://nexus-personalos.vercel.app/`).

---

### Defect 1: Icon buttons lack discernible text / accessible name
- **Issue ID**: `BUG-A11Y-01`
- **Severity**: Critical
- **WCAG Standard**: WCAG 2.1 Success Criterion 4.1.2 (Name, Role, Value - Level A)
- **Axe Rule ID**: `button-name`
- **Affected Elements**: 13 `<button>` controls across navigation and action bars
- **Description**: 
  Quick action and navigation buttons render SVG icons without text content or `aria-label` attributes. Screen readers (e.g., NVDA, JAWS) announce them generically as "button", preventing visually impaired users from understanding their function.
- **Steps to Reproduce**:
  1. Navigate to `https://nexus-personalos.vercel.app/`.
  2. Inspect action buttons or run scenario `@accessibility`.
  3. Observe missing accessible name in accessibility tree.
- **Expected Result**: 
  Buttons have an accessible name via visible text or `aria-label` attribute (e.g., `<button aria-label="Add Task">...</button>`).
- **Actual Result**: 
  Elements fail `button-name` rule with critical impact.

---

### Defect 2: Sub-optimal color contrast on dashboard text
- **Issue ID**: `BUG-A11Y-02`
- **Severity**: Serious / High
- **WCAG Standard**: WCAG 2.1 Success Criterion 1.4.3 (Contrast Minimum - Level AA)
- **Axe Rule ID**: `color-contrast`
- **Affected Elements**: 46 text elements
- **Description**: 
  Secondary text and badge labels rendered against dark theme containers fail the minimum contrast threshold of 4.5:1 for standard body text.
- **Expected Result**: 
  Text color achieves at least a 4.5:1 contrast ratio against the background container.
- **Actual Result**: 
  Contrast ratios on affected text nodes measure between 2.1:1 and 3.4:1.